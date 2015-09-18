package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.util.CoordinateUtil;

public class MainTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

	public static void main(String args[]) throws Exception{


		//Casa - -30.016521, -51.169557
		String gpsCoordinateHome = "-30.016521, -51.169557";
		String gpsCoordinate="-30.0192702,-51.1815179";

		double latitude = CoordinateUtil.toLatitude(gpsCoordinate);
		System.out.println("LAT :"+latitude);
		double longitude = CoordinateUtil.toLongitude(gpsCoordinate);
		System.out.println("LNG :"+longitude);

		//String addressByGpsCoordinates = CoordinateUtil.getAddressByGpsCoordinates(String.valueOf(latitude), String.valueOf(longitude));
		//System.out.println("Cade?"+addressByGpsCoordinates);

		//CityFinder cityFinder=new CityFinder();
		//City city = cityFinder.findByLatLong(latitude, longitude);
		//System.out.println("City "+city);

		double distFrom = CoordinateUtil.getDistance(gpsCoordinateHome, gpsCoordinate);
		LOGGER.info("Dist found :"+distFrom);
	}
}
