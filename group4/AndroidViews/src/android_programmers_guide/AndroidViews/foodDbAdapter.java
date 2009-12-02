package android_programmers_guide.AndroidViews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class foodDbAdapter {

    public static final String KEY_CALORIES = "calories";
    public static final String KEY_ROWID = "id";
    public static final String KEY_FOOD = "food";
    public static final String KEY_FIT = "fitness";

    private static final String TAG = "foodDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "healthfitness";
    private static final String DATABASE_TABLE = "health";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
        "create table health (id integer primary key autoincrement, "
                + "calories double , "
                + "fitness double , "
                + "food double);";
    
    private final Context mCtx;
    
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
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

   
       public foodDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public foodDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    public long createNote(double calories,double food,double fitness) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CALORIES, calories);
        initialValues.put(KEY_FIT, fitness );
        initialValues.put(KEY_FOOD, food );
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                        KEY_CALORIES,
                        KEY_FIT,
                        KEY_FOOD}, 
                        KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllNotes() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, 
        		KEY_CALORIES,
        		KEY_FIT,
        		KEY_FOOD}, 
        		null, null, null, null, null);
    }

    public boolean updateFood(long rowId, double food) {
        ContentValues args = new ContentValues();
       
        args.put(KEY_FOOD, food);
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateCal(long rowId, double cal) {
        ContentValues args = new ContentValues();
       
        args.put(KEY_CALORIES, cal);
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateFit(long rowId, double fit) {
        ContentValues args = new ContentValues();
       
        args.put(KEY_FIT, fit);
        
        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
