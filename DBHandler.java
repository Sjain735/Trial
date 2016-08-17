package com.example.user1.proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pawan on 14-Aug-16.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "DATABASE";
    private static final String TABLE_NOTES = "NOTES";

    private static final String KEY_NAME = "Name";
    private static final String KEY_TEXT = "Text";
    private static final String KEY_TIME = "Time";
    private static final String KEY_DATE = "Date";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_NAME + " TEXT,"
                + KEY_TEXT + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT PRIMARY KEY " + ")";

        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void new_note(String Name, String Text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String date = "04/12/1996";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date date_use = null;
        try {
            date_use = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("Catch of date", String.valueOf(date_use));
        }

        values.put(KEY_NAME, Name);
        values.put(KEY_TEXT, Text);
        values.put(KEY_TIME, System.currentTimeMillis());
        values.put(KEY_DATE, String.valueOf(date_use));
        Log.d("CREATE_DATE", String.valueOf(date_use));
        Log.d("CREATE_NOTE", KEY_TEXT);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public void delete_note(String Date, String Time) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_NOTES + " WHERE " + KEY_DATE + " = "
                + Date + " AND " + KEY_TIME + " = " + Time;
        Cursor csr = db.rawQuery(query, null);
        csr.close();
        db.close();
    }

    public String[][] get_note() {
        SQLiteDatabase db = this.getReadableDatabase();

        String data[][];
        String query = "SELECT * FROM " + TABLE_NOTES;
        Cursor csr = db.rawQuery(query, null);
        csr.moveToFirst();
        int count = csr.getCount();
        data = new String[4][count];
        int i = 0;
        while (count > 0) {
            data[0][i] = csr.getString(0);
            data[1][i] = csr.getString(1);
            data[2][i] = csr.getString(2);
            data[3][i] = csr.getString(3);
            i++;
            csr.moveToNext();
            count--;
        }
        csr.close();
        db.close();
        return data;
    }
}
