package wvu.fashionstore;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UserInfo extends Activity {
	private String username;
	private String gender;
	private double height;
	private String topsize;
	private int waistsize;
	private String favoriteColor;
	private int price;
	DBAdapter db = new DBAdapter(this);
	private static final String TAG = "MyActivity";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.userinfo);		
			
		Spinner topsizeView = (Spinner) findViewById(R.id.topsize);
		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
				R.array.topsizes, android.R.layout.simple_spinner_item);
		adapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		topsizeView.setAdapter(adapter1);

		Spinner waist_size = (Spinner) findViewById(R.id.waistsize);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.waistsizes, android.R.layout.simple_spinner_item);
		adapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		waist_size.setAdapter(adapter2);

		Button submitView = (Button) findViewById(R.id.submit);
		
		submitView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final EditText nameView = (EditText) findViewById(R.id.name);
				username = nameView.getText().toString();
				Log.v(TAG, "username =" + username);
				
				RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
				switch(radioGroup.getCheckedRadioButtonId()){
				case R.id.female:
					gender = "female";
					break;
				case R.id.male:
					gender = "male";
					break;
				}
				Log.v(TAG, "gender =" + gender);

				Spinner topsizeView = (Spinner) findViewById(R.id.topsize);

				topsize = topsizeView.getItemAtPosition(
						(int) topsizeView.getSelectedItemId()).toString();
				Log.v(TAG, "topsize =" + topsize);

				Spinner waist_size = (Spinner) findViewById(R.id.waistsize);

				String stringWaistsize = waist_size.getItemAtPosition(
						(int) waist_size.getSelectedItemId()).toString();
				waistsize = Integer.valueOf(stringWaistsize);
				Log.v(TAG, "waistsize =" + waistsize);

				EditText heightView = (EditText) findViewById(R.id.height);
				String stringHeight = heightView.getText().toString();
				height = Double.valueOf(stringHeight);
				Log.v(TAG, "height =" + height);

				EditText favoriteColorView = (EditText) findViewById(R.id.favouritecolor);
				favoriteColor = favoriteColorView.getText().toString();
				Log.v(TAG, "favoriteColor =" + favoriteColor);
				
				EditText priceRange = (EditText) findViewById(R.id.price);
				String stringPrice = priceRange.getText().toString();
				price = Integer.valueOf(stringPrice);
				Log.v(TAG, "price =" + price);
				
				db.open();
				long id = -1;				
				id = db.insertUserInfo(username, gender, height, topsize, waistsize, favoriteColor, price);
				if(id == -1){
					Log.v(TAG, "Insert Unsuccessful");
				}else{
					Log.v(TAG, "Insert Successful");
				}
				
				Cursor c = db.getAllUserInfo();
				if (c.moveToFirst()) {
					do {
						if(c.moveToLast()){
							DisplayTitle(c);
						}
					} while (c.moveToNext());
				}
				db.close();
				processSubmit();
			}
		});

		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				processSubmit();
			}
		});
	}

	public void DisplayTitle(Cursor c) {
		Toast.makeText(
				this,
				"UserId: " + c.getString(0) + "\n" + "Username: "
						+ c.getString(1) + "\n" + "Gender: " + c.getString(2)
						+ "\n" + "Height:  " + c.getString(3) + "\n"
						+ "TopSize:  " + c.getString(4) + "\n"
						+ "WaistSize:  " + c.getString(5)+ "\n"
						+ "FavoriteColor:  " + c.getString(6)+ "\n"
						+ "Price:  " + c.getString(7),
				Toast.LENGTH_LONG).show();
	}
	
	public void processSubmit() {
		Intent weather = new Intent(this, Weather.class);
		startActivity(weather);
	}

}