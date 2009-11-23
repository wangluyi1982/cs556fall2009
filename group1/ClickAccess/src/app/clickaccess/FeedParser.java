package app.clickaccess;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * A class to parse a variety of feeds (RSS, ATOM, etc.).
 * @author John Richards
 * @version 0.1
 *
 */
public class FeedParser {
	private Set<URL> feeds;
	
	/**
	 * Class constructor.
	 */
	public FeedParser() {
		feeds = new HashSet<URL>();
	}
	
	/**
	 * Adds the specified feed URL to the list of feeds to watch.
	 * 
	 * @param feedURL The URL of the feed to watch
	 * 
	 * @throws RuntimeException
	 */
	public void addNewFeed(String feedURL) {
		URL feed = null;

		try {
			feed = new URL(feedURL);
		}
		catch (MalformedURLException ex) {
			throw new RuntimeException("Invalid URL.");
		}

		feeds.add(feed);
	}
	
	/**
	 * Removes the specified feed URL from the list of feeds to watch.
	 * 
	 * @param feedURL The URL of the feed to remove
	 * 
	 * @throws RuntimeException
	 */
	public void removeFeed(String feedURL) {
		URL feed = null;

		try {
			feed = new URL(feedURL);
		}
		catch (MalformedURLException ex) {
			throw new RuntimeException("Invalid URL.");
		}

		feeds.remove(feed);
	}
	
	/**
	 * Parses all of the feeds in the list of feeds to watch, then returns
	 * a Map of URLs and titles based on the feed entries.
	 * <p>
	 * The URL of the entry is the key and the title of the entry is the value.
	 * <p>
	 * It's important to note that the user must take care of history. This
	 * method returns what ever is in the feed.
	 * 
	 * @throws IOException 
	 * @throws FeedException 
	 * 
	 * @return A <code>Map</code> of URLs and titles based on the feed entries
	 */
	public Map<String,String> checkFeeds() throws FeedException, IOException {
		Map<String,String> results = new HashMap<String,String>();
		
		for (URL feed : feeds) {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed f = input.build(new XmlReader(feed));
			
			for (Object o : f.getEntries()) {
				SyndEntry entry = (SyndEntry)(o);
				
				results.put(entry.getLink(), entry.getTitle());
			}
		}
		
		return results;
	}
}
