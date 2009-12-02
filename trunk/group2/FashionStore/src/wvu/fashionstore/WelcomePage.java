package wvu.fashionstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.net.Uri; 

public class WelcomePage extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.main);		    	    
		Button yes = (Button) findViewById(R.id.yes);		
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				processUserInfo();
			}
		});

		Button no = (Button) findViewById(R.id.no);

		no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				processDisplay();
			}
		});		
	}

	public void processUserInfo() {
		Intent userInfo = new Intent(this, UserInfo.class);
		startActivity(userInfo);
	}

	public void processDisplay() {
		Intent weather = new Intent(this, Weather.class);
		startActivity(weather);
	}
	
	public void displayPicture(){
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.welcome);
		setContentView(imageView);
	}
}