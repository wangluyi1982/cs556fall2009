//
// GoogleQuery - Java class to query Google via their AJAX API.
// John Richards <richardsjohnt@gmail.com>
//
// Requires JSON library: http://www.json.org/java/
//

package app.clickaccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.nio.charset.Charset;

import java.util.Map;
import java.util.HashMap;

//Get JSON library from http://www.json.org/java/
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class GoogleQuery {
	// This should be set to your web site.
	private static final String HTTP_REFERER = "http://www.wvu.edu";
	
	// This must be a multiple of 8 and can be no larger than 64.
	private static final int DEFAULT_MAX_NUMBER_OF_RESULTS = 24;
	
	// Various data related to the search URL.
	private static final String BASE_GOOGLE_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&rsz=large";
	private static final String PAGE_NUM_PREFIX = "&start=";
	private static final String QUERY_PREFIX = "&q=";
	
	// This method returns a map of Google results.
	// maxNumResults MUST be a multiple of 8... and can be no larger than 64.
	// The URL is the key, the page title is the value.
	public static Map<String, String> makeQuery(String query, int maxNumResults) {
		// Make sure the maxNumResults is a multiple of 8 and no more than 64.
		if (maxNumResults % 8 != 0 || maxNumResults < 8 || maxNumResults > 64) {
			maxNumResults = DEFAULT_MAX_NUMBER_OF_RESULTS;
		}
		
		// Convert query.
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			// This should never happen.
			if (Charset.isSupported("UTF-8")) {
				throw new RuntimeException("Cannot encode query to UTF-8.");
			} else {
				throw new RuntimeException("UTF-8 encoding is not available.");
			}
		}

		// Search Google... looping through each page of results.
		Map<String, String> finalResults = new HashMap<String, String>(maxNumResults);
		int i = 0;
		while (i < maxNumResults && finalResults != null) {
			// Open Connection to Google.
			URLConnection connection = GoogleQuery.openConnectionToGoogle(query, i);
			if (connection == null) {
				finalResults = null;
			} else {
				// Google requires the HTTP referrer to be set.
				connection.addRequestProperty("Referer", HTTP_REFERER);
				
				// Get results.
				JSONArray encodedResults = GoogleQuery.getGoogleSearchResults(connection);
				
				if (encodedResults == null) {
					finalResults = null;
				} else {
					getURLsAndTitles(encodedResults, finalResults);
				}
			}
			
			// 8 results per page.
			i = i + 8;
		}
		
		return finalResults;
	}
	
	private static URLConnection openConnectionToGoogle(String query, int page_num) {
		// Build search URL.
		URL url;
		try {
			url = new URL(BASE_GOOGLE_URL + PAGE_NUM_PREFIX + page_num + QUERY_PREFIX + query);
		} catch (MalformedURLException ex) {
			// This should never occur.
			throw new RuntimeException("Cannot create Google Search AJAX API URL.");
		}
		
		URLConnection openConnection;
		try {
			openConnection = url.openConnection();
		} catch (IOException ex) {
			openConnection = null;
		}
		
		return openConnection;
	}
	
	private static JSONArray getGoogleSearchResults(URLConnection connection) {
		// Read response from the connection.
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException ex) {
			builder = null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				builder = null;
			}
		}
		
		// Retrieve the JSON encoded results from the response.
		JSONArray encodedResultsArray = null;
		try {
			if (builder != null) {
				JSONObject json = new JSONObject(builder.toString());
				encodedResultsArray = json.getJSONObject("responseData").getJSONArray("results");
			}
		} catch (JSONException ex) {
			// Nothing you can really do if there is a problem with JSON.
			encodedResultsArray = null;
		}
		
		return encodedResultsArray;
	}
	
	private static void getURLsAndTitles(JSONArray encodedResults, Map<String, String> urlsAndTitles) {
		try {
			// Loop through encoded results to pull title & URL.
			for (int i = 0; i < encodedResults.length(); i++) {
				JSONObject encodedResult = encodedResults.getJSONObject(i);
				urlsAndTitles.put(encodedResult.getString("url"), encodedResult.getString("titleNoFormatting"));
			}
		} catch (JSONException ex) {
			// JSON exception... nothing you can really do.
			urlsAndTitles = null;
		}
	}
}
