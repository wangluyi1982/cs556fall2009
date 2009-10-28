package app.clickaccess;
import java.util.Iterator;
import java.util.Map;

public class GoogleQueryExample {
	public static void exampleQuery(String usersQuery) {
		Map<String, String> results = GoogleQuery.query(usersQuery, 24);
		
		int size = results.size();
		
		System.out.println("There were " + size + " results.");

		Iterator<Map.Entry<String, String>> keysAndValues = results.entrySet().iterator();
		for (int i = 0; i < size; i++) {
			Map.Entry<String, String> e = (Map.Entry<String, String>)keysAndValues.next();
			// Remember... the key is the URL and the value is the title.
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}
}