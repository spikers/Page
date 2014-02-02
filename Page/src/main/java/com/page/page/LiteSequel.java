package com.page.page;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLException;

/**
 * Created by timfe_000 on 1/31/14.
 */
public class LiteSequel {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_DESCRIPTION = "book_description";
    public static final String KEY_PHONE_NUMBER = "phone_number";

    private static final String DATABASE_NAME = "BookList";
    private static final String DATABASE_TABLE = "bookTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {
        private DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_DESCRIPTION + " TEXT NOT NULL, " +
                            KEY_PHONE_NUMBER + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public LiteSequel(Context c) {
        ourContext = c;
    }

    public LiteSequel write() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String description, String phoneNumber) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_DESCRIPTION, description);
        cv.put(KEY_PHONE_NUMBER, phoneNumber);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }







    /* Inner class that defines the table contents
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
    }*/

}
