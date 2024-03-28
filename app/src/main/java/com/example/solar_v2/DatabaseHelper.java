package com.example.solar_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SolarSystem.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlanetaContract.PlanetaEntry.TABLE_NAME + " (" +
                    PlanetaContract.PlanetaEntry._ID + " INTEGER PRIMARY KEY," +
                    PlanetaContract.PlanetaEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    PlanetaContract.PlanetaEntry.COLUMN_NAME_TAMANO + " INTEGER," +
                    PlanetaContract.PlanetaEntry.COLUMN_NAME_COLOR + " TEXT," +
                    PlanetaContract.PlanetaEntry.COLUMN_NAME_ESTADO + " INTEGER," +
                    PlanetaContract.PlanetaEntry.COLUMN_NAME_IMAGEN + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nothing here
    }
}
