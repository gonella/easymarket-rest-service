package com.easymarket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class CoordinateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoordinateUtil.class);

	private static final String COMMA = ",";

	public static LatLng toLatLng(String gpsCoordinate) throws Exception {

		double latitude = toLatitude(gpsCoordinate);
		double longiture = toLongitude(gpsCoordinate);

		LatLng location = new LatLng(latitude, longiture);

		return location;
	}

	public static double toLatitude(String gpsCoordinate) throws Exception {
		String[] result = validateGpsCoordinateFormat(gpsCoordinate);

		double lat = Double.parseDouble(result[0]);

		return lat;
	}

	public static double toLongitude(String gpsCoordinate) throws Exception {

		String[] result = validateGpsCoordinateFormat(gpsCoordinate);

		double lng = Double.parseDouble(result[1]);

		return lng;
	}

	private static String[] validateGpsCoordinateFormat(String gpsCoordinate) throws Exception {
		Validator.throwExcetionValueNotEspecifiedIfMessageIsEmpty("Gps Coordinate", gpsCoordinate);
		String[] result = gpsCoordinate.split(COMMA);
		return result;
	}

	public static double distFrom(String gpsCoordinate1, String gpsCoordinate2) throws Exception {

		double lat1 = toLatitude(gpsCoordinate1);
		double lng1 = toLongitude(gpsCoordinate1);
		double lat2 = toLatitude(gpsCoordinate2);
		double lng2 = toLongitude(gpsCoordinate2);

		double result = distFrom(lat1, lng1, lat2, lng2);
		return result;
	}

	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double a = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return dist;
	}

	/**
	 * It will retrieve address associated to GPS
	 *
	 * @param lng
	 * @param lat
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException
	 */
	public static String getAddressByGpsCoordinates(String lng, String lat)
			throws MalformedURLException, IOException, org.json.simple.parser.ParseException {

		String link = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng;
		URL url = new URL(link);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		String formattedAddress = "";
		LOGGER.info("Address from :" + link);
		try {
			InputStream in = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String result, line = reader.readLine();
			result = line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}

			JSONParser parser = new JSONParser();
			JSONObject rsp = (JSONObject) parser.parse(result);

			if (rsp.containsKey("results")) {
				JSONArray matches = (JSONArray) rsp.get("results");
				JSONObject data = (JSONObject) matches.get(0); // TODO: check if
				// idx=0 exists
				formattedAddress = (String) data.get("formatted_address");
			}

			return "";
		} finally {
			urlConnection.disconnect();
			return formattedAddress;
		}
	}

	// ** The Earth's radius, in kilometers. */
	private static final double EARTH_RADIUS_M = 6371.0 * 1000.0;

	// /** The Earth's radius, in miles. */
	// private static final double EARTH_RADIUS_M = 3961.0;

	public static float getDistance(String gpsCoordinate1,String gpsCoordinate2) throws Exception {
		double lat1 = toLatitude(gpsCoordinate1);
		double lng1 = toLongitude(gpsCoordinate1);
		double lat2 = toLatitude(gpsCoordinate2);
		double lng2 = toLongitude(gpsCoordinate2);

		float  result = getDistance(lat1, lng1, lat2, lng2);

		return result;
	}
	/**
	 * Gets the great circle distance in meters between two geographical points,
	 * using the
	 * <a href="http://en.wikipedia.org/wiki/Haversine_formula">haversine
	 * formula</a>.
	 *
	 * @param latitude1
	 *            the latitude of the first point
	 * @param longitude1
	 *            the longitude of the first point
	 * @param latitude2
	 *            the latitude of the second point
	 * @param longitude2
	 *            the longitude of the second point
	 * @return the distance, in meters, between the two points
	 */
	public static float getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double dLat = Math.toRadians(latitude2 - latitude1);
		double dLon = Math.toRadians(longitude2 - longitude1);
		double lat1 = Math.toRadians(latitude1);
		double lat2 = Math.toRadians(latitude2);
		double sqrtHaversineLat = Math.sin(dLat / 2);
		double sqrtHaversineLon = Math.sin(dLon / 2);
		double a = sqrtHaversineLat * sqrtHaversineLat
				+ sqrtHaversineLon * sqrtHaversineLon * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (float) (EARTH_RADIUS_M * c);
	}

	/**
	 * This method returns an compound index for given location.
	 *
	 * @param latitude
	 * @param longitude
	 * @return String Index
	 */
	public static String LOCINDEX(double latitude, double longitude) {
		String pattern = "000";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);

		String latStr = decimalFormat.format((int) (latitude));
		String lonStr = decimalFormat.format((int) (longitude));
		return latStr + ":" + lonStr;
	}

	/**
	 * This method returns an compound index for given location and 8 other
	 * squares around it.
	 *
	 * @param latitude
	 * @param longitude
	 * @return String[] indexes
	 */

	public static String[] INDEXESAROUND(double latitude, double longitude) {
		String[] result = new String[9];

		for (int latInd = -1; latInd <= 1; latInd++)
			for (int lonInd = -1; lonInd <= 1; lonInd++) {

				result[(latInd + 1) * 3 + (lonInd + 1)] = LOCINDEX(latitude + (latInd), longitude + (lonInd));

			}

		return result;
	}

	public static AddressComponent getAddressBasedOnGpsCoordinatesFromGoogleMapApi(String gpsCoordinate)
			throws Exception {
		LatLng location = toLatLng(gpsCoordinate);

		AddressComponent result = null;

		// FIXME - Gonella - API KEY - Temporary
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDP-_bvm8_8kbdx4TCdlwFnBGBniNgWByk");
		GeocodingResult[] reverseGeocode = GeocodingApi.reverseGeocode(context, location).await();

		GeocodingResult geocodingResult = null;
		if (reverseGeocode != null && reverseGeocode.length > 0) {
			geocodingResult = reverseGeocode[0];

			if (geocodingResult != null && geocodingResult.addressComponents != null) {
				AddressComponent addressComponent = geocodingResult.addressComponents[0];
				result = addressComponent;
			}
		}
		return result;

	}
}
