package app.clickaccess;

import java.io.IOException;
import java.util.Map;
import com.sun.syndication.io.FeedException;

// Shows how to use the FeedParser class.
public class FeedParserExample {
	void runExample() throws FeedException, IOException {
		// Create a new parser.
		FeedParser fp = new FeedParser();
		
		// Add a couple blog feeds.
		fp.addNewFeed("http://blogs.msdn.com/oldnewthing/rss.xml");
		fp.addNewFeed("http://blogs.msdn.com/larryosterman/rss.xml");
		
		// Show the results.
		for (Map.Entry<String,String> post : fp.checkFeeds().entrySet()) {
			// Remember... the key is the URL and the value is the title.
			System.out.println("Post");
			System.out.println("Title: " + post.getValue());
			System.out.println("URL: " + post.getKey());
			System.out.println();
		}
	}
}
