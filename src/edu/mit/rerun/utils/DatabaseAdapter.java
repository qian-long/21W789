package edu.mit.rerun.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.mit.rerun.model.Filter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A wrapper for sqlitedatabase. Used to keep track of user's filters
 */
public class DatabaseAdapter {
    public static final String TAG = "DatabaseAdapter";
    public static final String KEY_FILTERNAME = "filterName";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_USED = "used";
    public static final String KEY_FILTER_KEYWORDS = "keywords";

    private DbHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mContext;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "filters";
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + DATABASE_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "filterName TEXT NOT NULL, " + "keywords TEXT," + " used INTEGER);";

    private class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }

    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx
     *            the Context within which to work
     */
    public DatabaseAdapter(Context ctx) {
        this.mContext = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException
     *             if the database could be neither opened or created
     */
    public DatabaseAdapter open() throws SQLException {
        mDbHelper = new DbHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    /**
     * @return List of all filters
     */
    public List<Filter> getAllFilters() {
        List<Filter> filters = new ArrayList<Filter>();
        Cursor cursor = mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID,
                KEY_FILTERNAME, KEY_FILTER_KEYWORDS }, null, null, null, null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(1);
            String[] words = cursor.getString(2)
                    .split(Filter.KEYWORD_DELIMITER);
            Set<String> set = new HashSet<String>(Arrays.asList(words));
            filters.add(new Filter(name,(KEY_USED == "1"),  set));
            cursor.moveToNext();
        }
        cursor.close();
        return filters;
    }

    /**
     * @return List of used filters
     */
    public List<Filter> getUsedFilters() {
        List<Filter> filters = new ArrayList<Filter>();
        Cursor cursor = mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID,
                KEY_FILTERNAME, KEY_FILTER_KEYWORDS, KEY_USED }, "KEY_USED "
                + "equals 1", null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(1);
            String[] words = cursor.getString(2)
                    .split(Filter.KEYWORD_DELIMITER);
            Set<String> set = new HashSet<String>(Arrays.asList(words));
            filters.add(new Filter(name, (KEY_USED == "1"), set));
            cursor.moveToNext();
        }
        cursor.close();
        return filters;
    }
    
    /**
     * Create a new row with Filter If row is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param parseId
     *          parseId of the printer
     * @return rowId or -1 if failed
     */
    public long addFilter(Filter filter) {
        Log.i(TAG, "adding filter");
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FILTERNAME, filter.getFiltername());
        initialValues.put(KEY_USED, filter.getUsedStatus());
        initialValues.put(KEY_FILTER_KEYWORDS, filter.getKeyWordsString());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * 
     * @param name
     * @return True if deleted, False otherwise
     */
    public boolean removeFilter(String name) {
        return mDb.delete(DATABASE_TABLE, KEY_FILTERNAME + "=\"" + name + "\"",
                null) > 0;
    }
    
    public void close() {
        mDbHelper.close();
    }
}
