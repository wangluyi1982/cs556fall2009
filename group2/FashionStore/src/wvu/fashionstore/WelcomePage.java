package wvu.fashionstore;

import wvu.fashionstore.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomePage extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Summer Clothes");
		menu.add(0, 1, 1, "Winter Clothes");
		return true;
	}

	/**
	 * Override onOptionsItemSelected to execute code for each menu item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/**
		 * Select statement to handle calls to specific menu items
		 */
		switch (item.getItemId()) {
		case 0:
			processSummerClothes();
			return true;
		case 1:
			return true;
		}
		return true;
	}

	public void processSummerClothes() {
		Intent autocomplete = new Intent(this, SummerClothes.class);
		startActivity(autocomplete);
	}

}