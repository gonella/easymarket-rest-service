package com.easymarket.gps;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.FileSystemResource;

import com.easymarket.util.CoordinateUtil;

/**
 * http://download.geonames.org/export/dump/
 *
 *  Offline solution (On going)
 *
 * @author Adriano
 *
 */
public class CityFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityFinder.class);

    Map<String, List<City>> cities = new HashMap<String, List<City>>();

    public CityFinder() throws UnexpectedInputException, ParseException, Exception {

        String fileName = "gpsdata/brgps.txt";
        //String fileName = "gpsdata/allCountries.txt";
        //String fileName = "gpsdata/cities1000.txt";

        FlatFileItemReader<City> itemReader = reader(fileName, columnsReader);

        List<String> listBR = new ArrayList<String>();

        LOGGER.info("Starting to read the CSV file for cities.");

        City readCity;
        boolean isException;

        do {

            readCity = null;
            isException = false;

            try {
                readCity = itemReader.read();

                if (readCity != null) {

                    //if ("BR".equals(readCity.getCountrycode()))
                    {
                        addtoMap(readCity);
                        LOGGER.debug("City read from the file " + readCity.toString());
                        listBR.add(cityToLine(readCity));
                    }
                }

            } catch (FlatFileParseException e) {
                isException = true;
            }

        } while ((readCity != null) || (isException));

        LOGGER.info("Finished reading the cities CSV file.");

        //LOGGER.info("Writing...");
        //String fileNameWrite = "gpsdata/brgps.txt";
        //FileUtils.writeLines(new File(fileNameWrite), listBR);


        //Spring BATCH
        //FlatFileItemWriter<City> writer = writer(fileNameWrite, columnsReader);
        //writer.write(listBR);
        //writer.close();


        LOGGER.info("Finished");
    }

    private FlatFileItemReader<City> reader(String fileName, String[] columns) {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        FlatFileItemReader<City> itemReader = new FlatFileItemReader<City>();
        itemReader.setResource(new FileSystemResource(file));
        itemReader.setLinesToSkip(1);

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);

        lineTokenizer.setNames(columns);

        DefaultLineMapper<City> lineMapper = new DefaultLineMapper<City>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new CityFieldSetMapper());

        itemReader.setLineMapper(lineMapper);
        itemReader.open(new ExecutionContext());
        return itemReader;
    }

    public FlatFileItemWriter<City> writer(String fileName, String[] columns) {
        FlatFileItemWriter<City> writer = new FlatFileItemWriter<City>();
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());
        writer.setResource(new FileSystemResource(file));

        DelimitedLineAggregator<City> delLineAgg = new DelimitedLineAggregator<City>();
        delLineAgg.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);

        BeanWrapperFieldExtractor<City> fieldExtractor = new BeanWrapperFieldExtractor<City>();
        fieldExtractor.setNames(columns);

        delLineAgg.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(delLineAgg);
        writer.setEncoding("ISO-8859-1");
        writer.setAppendAllowed(true);
        writer.setShouldDeleteIfExists(true);

        ExecutionContext executionContext = new ExecutionContext();

        writer.open(executionContext);

        return writer;
    }

    public City findByLatLong(Double latitude, Double longitude) {
        return getFromMap(latitude, longitude);
    }

    /* Adds the given city to the hashmap with location based index */
    private void addtoMap(City city) {

        String index = CoordinateUtil.LOCINDEX(city.getLatitude(), city.getLongitude());
        // LOGGER.debug("Index for (" + city.getLatitude() + ", " +
        // city.getLongitude() + ") is " + index);

        List<City> sameIndexCities = cities.get(index);

        if (sameIndexCities == null)
            sameIndexCities = new ArrayList<City>();

        // Add the new city into the list of cities sharing the same index.
        sameIndexCities.add(city);
        // Add the list into the hashmap
        cities.put(index, sameIndexCities);
    }

    /* Gets a city from the hashmap for given latitude and longitude */
    private City getFromMap(Double latitude, Double longitude) {

        City result = null;

        String[] indexes = CoordinateUtil.INDEXESAROUND(latitude, longitude);

        for (String index : indexes) {

            List<City> citiesForIndex = cities.get(index);

            if (citiesForIndex != null) {
                for (City city : citiesForIndex) {

                    if (result == null)
                        result = city;
                    else {
                        float resultDistance = CoordinateUtil.getDistance(latitude, longitude, result.getLatitude(),
                                result.getLongitude());
                        LOGGER.debug("Distance for " + result.getCity() + " is " + resultDistance + "m");
                        float cityDistance = CoordinateUtil.getDistance(latitude, longitude, city.getLatitude(),
                                city.getLongitude());
                        LOGGER.debug("Distance for " + city.getCity() + " is " + cityDistance + "m");

                        if (cityDistance < resultDistance) // if this city is
                            // closer than
                            // earlier result
                            result = city;
                    }
                }
            }
        }

        return result;
    }

    String[] columnsReader = new String[] { "geonameid", "city", "asciiname", "alternatenames", "latitude", "longitude",
            "featureclass", "featurecode", "countrycode", "cc2", "admin1code", "admin2code", "admin3code", "admin4code",
            "population", "elevation", "dem", "timezone", "modified" };

    public class CityFieldSetMapper implements FieldSetMapper<City> {

        @Override
        public City mapFieldSet(FieldSet fieldSet) {

            if (fieldSet == null)
                return null;

            City city = new City();
            city.setGeonameid(fieldSet.readString("geonameid"));
            city.setCity(fieldSet.readString("city"));
            city.setAsciiname(fieldSet.readString("asciiname"));
            city.setAlternatenames(fieldSet.readString("alternatenames"));
            city.setLatitude(fieldSet.readDouble("latitude"));
            city.setLongitude(fieldSet.readDouble("longitude"));
            city.setFeatureclass(fieldSet.readString("featureclass"));
            city.setFeaturecode(fieldSet.readString("featurecode"));
            city.setCountrycode(fieldSet.readString("countrycode"));
            city.setCc2(fieldSet.readString("cc2"));
            city.setAdmin1code(fieldSet.readString("admin1code"));
            city.setAdmin2code(fieldSet.readString("admin2code"));
            city.setAdmin3code(fieldSet.readString("admin3code"));
            city.setAdmin4code(fieldSet.readString("admin4code"));
            city.setPopulation(fieldSet.readLong("population"));
            city.setElevation(fieldSet.readInt("elevation"));
            city.setDem(fieldSet.readString("dem"));
            city.setTimezone(fieldSet.readString("timezone"));
            city.setModified(fieldSet.readString("modified"));
            return city;
        }

    }
    public String cityToLine(City city){

        String array[]={city.getGeonameid(),city.getCity(),city.getAsciiname(),city.getAlternatenames(),city.getLatitude().toString(),
                city.getLongitude().toString(),city.getFeatureclass(),city.getFeaturecode(),city.getCountrycode(),city.getCc2(),
                city.getAdmin1code(),city.getAdmin2code(),city.getAdmin3code(),city.getAdmin4code(),city.getPopulation().toString(),city.getElevation().toString(),
                city.getDem(),city.getTimezone(),city.getModified()};

        String join = StringUtils.join(array, DelimitedLineTokenizer.DELIMITER_TAB);

        return join;
    }

    public class City implements Serializable {

        private static final long serialVersionUID = -6593819646281952836L;

        private String admin1code;
        private String admin2code;
        private String admin3code;
        private String admin4code;
        private String dem;

        private String cc2;
        private String featurecode;
        private String featureclass;
        private String alternatenames;
        private String geonameid;
        private String countrycode;
        private String city;
        private String asciiname;
        private Double latitude;
        private Double longitude;
        private Long population;
        private Integer elevation;
        private String timezone;
        private String modified;

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAsciiname() {
            return asciiname;
        }

        public void setAsciiname(String asciiname) {
            this.asciiname = asciiname;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Long getPopulation() {
            return population;
        }

        public void setPopulation(Long population) {
            this.population = population;
        }

        public Integer getElevation() {
            return elevation;
        }

        public void setElevation(Integer elevation) {
            this.elevation = elevation;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        @Override
        public String toString() {
            return "City [countrycode=" + countrycode + ", city=" + city + ", asciiname=" + asciiname + ", latitude="
                    + latitude + ", longitude=" + longitude + ", population=" + population + ", elevation=" + elevation
                    + ", timezone=" + timezone + ", modified=" + modified + "]";
        }

        public String getGeonameid() {
            return geonameid;
        }

        public void setGeonameid(String geonameid) {
            this.geonameid = geonameid;
        }

        public String getAlternatenames() {
            return alternatenames;
        }

        public void setAlternatenames(String alternatenames) {
            this.alternatenames = alternatenames;
        }

        public String getFeatureclass() {
            return featureclass;
        }

        public void setFeatureclass(String featureclass) {
            this.featureclass = featureclass;
        }

        public String getFeaturecode() {
            return featurecode;
        }

        public void setFeaturecode(String featurecode) {
            this.featurecode = featurecode;
        }

        public String getCc2() {
            return cc2;
        }

        public void setCc2(String cc2) {
            this.cc2 = cc2;
        }

        public String getAdmin1code() {
            return admin1code;
        }

        public void setAdmin1code(String admin1code) {
            this.admin1code = admin1code;
        }

        public String getAdmin2code() {
            return admin2code;
        }

        public void setAdmin2code(String admin2code) {
            this.admin2code = admin2code;
        }

        public String getAdmin3code() {
            return admin3code;
        }

        public void setAdmin3code(String admin3code) {
            this.admin3code = admin3code;
        }

        public String getAdmin4code() {
            return admin4code;
        }

        public void setAdmin4code(String admin4code) {
            this.admin4code = admin4code;
        }

        public String getDem() {
            return dem;
        }

        public void setDem(String dem) {
            this.dem = dem;
        }

    }

}