package wvu.fashionstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_USERID = "userid";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_TOPSIZE = "topsize";
	public static final String KEY_WAISTSIZE = "waistsize";
	public static final String KEY_FAVORITE_COLOR = "favorite_color";
	public static final String KEY_PRICE = "price";
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "fashionstore";
	private static final String DATABASE_TABLE = "userinfo";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table userinfo (userid integer primary key autoincrement, "
			+ "username text not null, gender text not null, "
			+ "height double not null, topsize text not null, " 
			+ "waistsize integer not null, favorite_color text not null, price integer not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS userinfo");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a userInfo into the database---
	public long insertUserInfo(String username, String gender, double height, String topsize, int waistsize, String favorite_color, int price) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USERNAME, username);
		initialValues.put(KEY_GENDER, gender);
		initialValues.put(KEY_HEIGHT, height);
		initialValues.put(KEY_TOPSIZE, topsize);		
		initialValues.put(KEY_WAISTSIZE, waistsize);
		initialValues.put(KEY_FAVORITE_COLOR, favorite_color);
		initialValues.put(KEY_PRICE, price);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular userInfo---
	public boolean deleteUserInfo(long userId) {
		return db.delete(DATABASE_TABLE, KEY_USERID + "=" + userId, null) > 0;
	}

	// ---retrieves all the userInfo---
	public Cursor getAllUserInfo() {
		return db.query(DATABASE_TABLE, new String[] { KEY_USERID, KEY_USERNAME,
				KEY_GENDER, KEY_HEIGHT, KEY_TOPSIZE, KEY_WAISTSIZE, KEY_FAVORITE_COLOR, KEY_PRICE}, null, null, null, null, null);
	}

	// ---retrieves a particular userInfo---
	public Cursor getSingleUserInfo(long userId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_USERID, KEY_USERNAME, KEY_GENDER, KEY_HEIGHT, KEY_TOPSIZE, KEY_WAISTSIZE, KEY_FAVORITE_COLOR, KEY_PRICE}, KEY_USERID
				+ "=" + userId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	// ---updates a userInfo---
	public boolean updateUserInfo(long userId, String username, String gender,
			double height, String topsize, int waistsize, String favorite_color, int price) {
		ContentValues args = new ContentValues();
		args.put(KEY_USERNAME, username);
		args.put(KEY_GENDER, gender);
		args.put(KEY_HEIGHT, height);
		args.put(KEY_TOPSIZE, topsize);
		args.put(KEY_WAISTSIZE, waistsize);
		args.put(KEY_FAVORITE_COLOR, favorite_color);
		args.put(KEY_PRICE, price);
		return db.update(DATABASE_TABLE, args, KEY_USERID + "=" + userId, null) > 0;
	}
}
