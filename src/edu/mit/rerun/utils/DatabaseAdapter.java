package edu.mit.rerun.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A wrapper for sqlitedatabase.
 * Used to keep track of user's filters
 */
public class DatabaseAdapter {

    public static final String KEY_PARSEID = "parseId";
    public static final String KEY_ROWID = "_id";
    
//    private DbHelper mDbHelper;
//    private SQLiteDatabase mDb;
//    private final Context mContext;
    
//    private class DbHelper extends SQLiteOpenHelper {

//        public DbHelper(Context context) {
//            super(context, DATABASE_NAME, null, 1);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(DATABASE_CREATE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            Log.w("PrintersDbAdapter", "Upgrading database from version " + oldVersion + " to "
//                    + newVersion + ", which will destroy all old data");
//            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
//            onCreate(db);
//
//        }

//    }
}
