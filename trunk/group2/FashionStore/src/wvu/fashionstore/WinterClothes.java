package wvu.fashionstore;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WinterClothes extends Activity {
	private int userId;
	private boolean female, male;
	private boolean height4, height5, height6; 
	private boolean smallSize, extraSmallSize, mediumSize, largeSize, extraLargeSize;
	private boolean size26, size28, size30, size32, size34;
	private boolean red, pink, black, white, gray, yellow;
	private boolean price10, price20, price30, price40, price50;
	DBAdapter db = new DBAdapter(this);
	private static final String TAG = "MyActivity";
	ArrayList<WinterClothes> winterclothes = new ArrayList<WinterClothes>();	 
	

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.checkuser);	
		final WinterClothes userinfo = new WinterClothes();
		Button submitView = (Button) findViewById(R.id.submit);
		
		submitView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText userName = (EditText) findViewById(R.id.userID);
				String userID = userName.getText().toString();
				userinfo.userId = Integer.valueOf(userID);
				Log.v(TAG, "userId =" + userinfo.userId);
				
				db.open();
				Cursor mCursor = db.getSingleUserInfo(userinfo.userId);
				DisplayTitle(mCursor);
				String columnValue = mCursor.getString(0);
				Log.v(TAG, "userIDValue =" + columnValue);
				if(columnValue.equalsIgnoreCase(userID)){
					Log.v(TAG, "User Found");
					columnValue = "";
					columnValue = mCursor.getString(2);
					if(columnValue.equalsIgnoreCase("female")){
						userinfo.female = true;
					}else{
						userinfo.male = true;
					}
					columnValue = "";
					columnValue = mCursor.getString(3);
					if(columnValue.equalsIgnoreCase("4")){
						userinfo.height4 = true;
					}else if(columnValue.equalsIgnoreCase("5")){
						userinfo.height5 = true;
					}else if(columnValue.equalsIgnoreCase("6")){
						userinfo.height6 = true;
					}					
					columnValue = "";
					columnValue = mCursor.getString(4);
					if(columnValue.equalsIgnoreCase("Small")){
						userinfo.smallSize = true;
					}else if(columnValue.equalsIgnoreCase("Extra Small")){
						userinfo.extraSmallSize = true;
					}else if(columnValue.equalsIgnoreCase("Medium")){
						userinfo.mediumSize = true;
					}else if(columnValue.equalsIgnoreCase("Large")){
						userinfo.largeSize = true;
					}else if(columnValue.equalsIgnoreCase("Extra Large")){
						userinfo.extraLargeSize = true;
					}
					columnValue = "";
					columnValue = mCursor.getString(5);
					if(columnValue.equalsIgnoreCase("26")){
						userinfo.size26 = true;
					}else if(columnValue.equalsIgnoreCase("28")){
						userinfo.size28 = true;
					}else if(columnValue.equalsIgnoreCase("30")){
						userinfo.size30 = true;
					}else if(columnValue.equalsIgnoreCase("32")){
						userinfo.size32 = true;
					}else if(columnValue.equalsIgnoreCase("34")){
						userinfo.size34 = true;
					}
					columnValue = "";
					columnValue = mCursor.getString(6);
					if(columnValue.equalsIgnoreCase("red")){
						userinfo.red = true;
					}else if(columnValue.equalsIgnoreCase("pink")){
						userinfo.pink = true;
					}else if(columnValue.equalsIgnoreCase("black")){
						userinfo.black = true;
					}else if(columnValue.equalsIgnoreCase("white")){
						userinfo.white = true;
					}else if(columnValue.equalsIgnoreCase("gray")){
						userinfo.gray = true;
					}else if(columnValue.equalsIgnoreCase("yellow")){
						userinfo.yellow = true;
					}
					columnValue = "";
					columnValue = mCursor.getString(7);
					if(columnValue.equalsIgnoreCase("10")){
						userinfo.price10 = true;
					}else if(columnValue.equalsIgnoreCase("20")){
						userinfo.price20 = true;
					}else if(columnValue.equalsIgnoreCase("30")){
						userinfo.price30 = true;
					}else if(columnValue.equalsIgnoreCase("40")){
						userinfo.price40 = true;
					}else if(columnValue.equalsIgnoreCase("50")){
						userinfo.price50 = true;
					}
					getWinterclothes().add(userinfo);					
					processSubmit();					
					
				}else {
					Log.v(TAG, "User Not Found");
				}
				db.close();
			}
		});
		
	}
	
	public void DisplayTitle(Cursor c) {
		Toast.makeText(
				this,
				"UserId: " + c.getString(0) + "\n" + "Username: "
						+ c.getString(1) + "\n" + "Gender: " + c.getString(2)
						+ "\n" + "Height:  " + c.getString(3) + "\n"
						+ "\n" + "TopSize:  " + c.getString(4) + "\n"
						+ "WaistSize:  " + c.getString(5) + "\n"
						+ "FavoriteColor:  " + c.getString(6)+ "\n"
						+ "Price:  " + c.getString(7),
				Toast.LENGTH_LONG).show();
	}
	public void processSubmit() {
		for (WinterClothes sObj : winterclothes) {
			Log.v(TAG, "sObj.isFemale() " + sObj.isFemale());
			Log.v(TAG, "sObj.isRed() " + sObj.isRed());
			Log.v(TAG, "sObj.isSize26() " + sObj.isSize26());
			Log.v(TAG, "sObj.isHeight4() " + sObj.isHeight4());
			Log.v(TAG, "sObj.isSmallSize() " + sObj.isSmallSize());
			Log.v(TAG, "sObj.isPrice10() " + sObj.isPrice10());
			if (sObj.isFemale() && sObj.isRed() && sObj.isSize26()
					&& sObj.isHeight4() && sObj.isSmallSize()
					&& sObj.isPrice10()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.femaleredclothes);
				setContentView(imageView);
			} else if (sObj.isFemale() && sObj.isPink() && sObj.isSize28()
					&& sObj.isHeight5() && sObj.isMediumSize()
					&& sObj.isPrice20()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.femalepink);
				setContentView(imageView);
			}  else if (sObj.isFemale() && sObj.isBlack() && sObj.isSize30()
					&& sObj.isHeight6() && sObj.isLargeSize()
					&& sObj.isPrice30()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.femaleblack);
				setContentView(imageView);
			}  else if (sObj.isFemale() && sObj.isYellow() && sObj.isSize30()
					&& sObj.isHeight5() && sObj.isMediumSize()
					&& sObj.isPrice20()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.femaleyellow);
				setContentView(imageView);
			} else if (sObj.isMale() && sObj.isBlack() && sObj.isSize28()
					&& sObj.isHeight4() && sObj.isSmallSize()
					&& sObj.isPrice10()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.mensclothes);
				setContentView(imageView);
			} else if (sObj.isMale() && sObj.isGray() && sObj.isSize30()
					&& sObj.isHeight5() && sObj.isMediumSize()
					&& sObj.isPrice20()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.mensclothes);
				setContentView(imageView);
			} else if (sObj.isMale() && sObj.isRed() && sObj.isSize30()
					&& sObj.isHeight6() && sObj.isMediumSize()
					&& sObj.isPrice20()) {
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.mensred);
				setContentView(imageView);
			} else if(sObj.isFemale()){
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.clothes);
				setContentView(imageView);
			} else if(sObj.isMale()){
				ImageView imageView = new ImageView(this);
				imageView.setImageResource(R.drawable.mensclothes);
				setContentView(imageView);
			}
		}
	}	
	
	public int getUserId() {
		return userId;
	}

	public boolean isFemale() {
		return female;
	}

	public boolean isMale() {
		return male;
	}

	public boolean isHeight4() {
		return height4;
	}

	public boolean isHeight5() {
		return height5;
	}

	public boolean isHeight6() {
		return height6;
	}

	public boolean isSmallSize() {
		return smallSize;
	}

	public boolean isExtraSmallSize() {
		return extraSmallSize;
	}

	public boolean isMediumSize() {
		return mediumSize;
	}

	public boolean isLargeSize() {
		return largeSize;
	}

	public boolean isExtraLargeSize() {
		return extraLargeSize;
	}

	public boolean isSize26() {
		return size26;
	}

	public boolean isSize28() {
		return size28;
	}

	public boolean isSize30() {
		return size30;
	}

	public boolean isSize32() {
		return size32;
	}

	public boolean isSize34() {
		return size34;
	}

	public boolean isRed() {
		return red;
	}

	public boolean isPink() {
		return pink;
	}

	public boolean isBlack() {
		return black;
	}

	public boolean isWhite() {
		return white;
	}
	
	public boolean isYellow() {
		return yellow;
	}

	public boolean isGray() {
		return gray;
	}

	public void setGray(boolean gray) {
		this.gray = gray;
	}

	public boolean isPrice10() {
		return price10;
	}

	public boolean isPrice20() {
		return price20;
	}

	public boolean isPrice30() {
		return price30;
	}

	public boolean isPrice40() {
		return price40;
	}

	public boolean isPrice50() {
		return price50;
	}
	
	public ArrayList<WinterClothes> getWinterclothes() {
		return winterclothes;
	}

}