package com.page.page;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public static final String KEY_CLASS_SUBJECT = "class_name";
    public static final String KEY_CLASS_NUMBER = "class_number";
    public static final String KEY_PRICE = "price";

    private static final String DATABASE_NAME = "BookList";
    private static final String DATABASE_TABLE = "bookTable";
    private static final int DATABASE_VERSION = 6;

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
                            KEY_PHONE_NUMBER + " TEXT NOT NULL, " +
                            KEY_PRICE + " TEXT NOT NULL" + " )" /*, " +
                            KEY_CLASS_SUBJECT + " TEXT NOT NULL, " +
                            KEY_CLASS_NUMBER + " TEXT NOT NULL" + */
                    //Don't forget to make "TEXT NOT NULL, " if it isn't the last one
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

    //Instead of Open, use Write
    public LiteSequel write() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String description, String phoneNumber, String price) {//, String subject, String number
        ContentValues cv = new ContentValues();
        //Put spinner stuff here.
        cv.put(KEY_DESCRIPTION, description);
        cv.put(KEY_PHONE_NUMBER, phoneNumber);
        cv.put(KEY_PRICE, price);

//      cv.put(KEY_CLASS_SUBJECT, subject);
//      cv.put(KEY_CLASS_NUMBER, number);

        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[] {KEY_ROWID, KEY_DESCRIPTION, KEY_PHONE_NUMBER, KEY_PRICE/*, KEY_CLASS_SUBJECT, KEY_CLASS_NUMBER*/};
        //cursor lets you read from your db
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = " ";
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iDescription = c.getColumnIndex(KEY_DESCRIPTION);
        int iPhone = c.getColumnIndex(KEY_PHONE_NUMBER);
        int iPrice = c.getColumnIndex(KEY_PRICE);

        //int iSubject = c.getColumnIndex(KEY_CLASS_SUBJECT);
        //int iNumber = c.getColumnIndex(KEY_CLASS_NUMBER);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                result = result + c.getString(iRow) + " " + c.getString(iDescription) + " " + c.getString(iPhone) + " " + c.getString(iPrice) +  "\n";
        // + c.getString(iSubject) + " " + c.getString(iNumber)
        }

        return result;
    }






    /* Inner class that defines the table contents
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
    }*/

}
