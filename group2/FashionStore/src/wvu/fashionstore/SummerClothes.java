package wvu.fashionstore;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SummerClothes extends Activity {
	private String username;
	private String gender;
	private String height;
	private String topsize;
	private String waistsize;
	private String favoriteColor;
	DBAdapter db = new DBAdapter(this);
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.userinfo);		

		final EditText nameView = (EditText) findViewById(R.id.name);
		username = nameView.getText().toString();
		// final TextView name = (TextView) findViewById(R.id.Name);
		OnClickListener radio_listener = new OnClickListener() {
			public void onClick(View v) {
				// Perform action on clicks
				RadioButton rb = (RadioButton) v;
				Toast.makeText(SummerClothes.this, rb.getText(),
						Toast.LENGTH_SHORT).show();
				RadioButton female = (RadioButton) findViewById(R.id.female);

				RadioButton male = (RadioButton) findViewById(R.id.male);
				if (female.isChecked()) {
					gender = "female";
				}
				if (male.isChecked()) {
					gender = "male";
				}
			}

		};

		Spinner topsizeView = (Spinner) findViewById(R.id.topsize);

		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
				R.array.topsizes, android.R.layout.simple_spinner_item);
		adapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		topsizeView.setAdapter(adapter1);
		topsize = topsizeView.getItemAtPosition(
				(int) topsizeView.getSelectedItemId()).toString();

		Spinner waist_size = (Spinner) findViewById(R.id.waistsize);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.waistsizes, android.R.layout.simple_spinner_item);
		adapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		waist_size.setAdapter(adapter2);
		waistsize = waist_size.getItemAtPosition(
				(int) waist_size.getSelectedItemId()).toString();

		final TextView heightView = (TextView) findViewById(R.id.Height);
		height = heightView.getText().toString();
		final TextView favoriteColorView = (TextView) findViewById(R.id.FavouriteColor);
		favoriteColor = favoriteColorView.getText().toString();
     
		Button submitView = (Button) findViewById(R.id.submit);
		submitView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	db.open();
        		long id;
        		//id = db.insertUserInfo("Sneha", "female", "5.7", "medium", "30", "red");
        		db.deleteUserInfo(2);
        		Cursor c = db.getAllUserInfo();
        		if (c.moveToFirst()) {
        			do {
        				DisplayTitle(c);
        			} while (c.moveToNext());
        		}

        		db.close();
            }
        });

		Button cancel = (Button) findViewById(R.id.cancel);
		
		
	}

	public void DisplayTitle(Cursor c) {
		Toast.makeText(
				this,
				"UserId: " + c.getString(0) + 
				        "\n" + "Username: " + c.getString(1) + 
				        "\n" + "Gender: " + c.getString(2) + 
						"\n" + "TopSize:  " + c.getString(3) + 
						"\n" + "WaistSize:  " + c.getString(4) + 
						"\n" + "FavoriteColor:  " + c.getString(5),Toast.LENGTH_LONG)
				.show();
	}

}