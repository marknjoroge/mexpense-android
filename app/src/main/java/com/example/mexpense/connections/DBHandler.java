package com.example.mexpense.connections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mexpense.models.Expenses;
import com.example.mexpense.models.TripDetails;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MEXPENSEDB";

    private static final String TRIP_ENTRIES_TABLE = "TripEntries";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESTINATION = "destination";
    private static final String TRIP_DATE = "date";
    private static final String REQUIRES_ASSESSMENT = "requiresAssessment";
    private static final String DESCRIPTION = "description";
    private static final String TYPE_OF_TRIP = "typeOfTrip";
    private static final String WAS_TRIP_FUN = "wasTripFun";

    private static final String EXPENSE_TABLE = "Expenses";
    private static final String TRIP_ID = "tripId";
    private static final String TYPES_OF_EXPENSES = "typesOfExpenses";
    private static final String AMOUNT = "amount";
    private static final String TIME = "time";
    private static final String EXPENSE_COMMENTS = "comments";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TRIP_ENTRIES_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + DESTINATION + " TEXT,"
                + TRIP_DATE + " TEXT,"
                + REQUIRES_ASSESSMENT + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + TYPE_OF_TRIP + " TEXT,"
                + WAS_TRIP_FUN + " TEXT"
                + ")";

        String CREATE_EXPENSES_TABLE = "CREATE TABLE " + EXPENSE_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TRIP_ID + " INTEGER,"
                + TYPES_OF_EXPENSES + " TEXT,"
                + AMOUNT + " TEXT,"
                + TIME + " TEXT,"
                + EXPENSE_COMMENTS + " TEXT"
                + ")";

        db.execSQL(CREATE_TRIPS_TABLE);
        db.execSQL(CREATE_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_ENTRIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);

        onCreate(db);
    }


    /**
     *
     * TRIP ENTRY SETUP
     *
     *
     * */

    public void insertTripEntry(String name, String destination, String date,
                         String requiresRiskAssessment, String description,
                         String typeOfTrip, String wasTripFun) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(NAME, name);
        cValues.put(DESTINATION, destination);
        cValues.put(TRIP_DATE, date);
        cValues.put(REQUIRES_ASSESSMENT, requiresRiskAssessment);
        cValues.put(DESCRIPTION, description);
        cValues.put(TYPE_OF_TRIP, typeOfTrip);
        cValues.put(WAS_TRIP_FUN, wasTripFun);

        long newRowId = db.insert(TRIP_ENTRIES_TABLE, null, cValues);
        db.close();
    }

    public ArrayList<TripDetails> getAllTrips() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TripDetails> tripDetailsArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TRIP_ENTRIES_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            TripDetails tripDetails = new TripDetails(
                    cursor.getInt(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getString(7));

            tripDetailsArrayList.add(tripDetails);
        }
        cursor.close();
        return tripDetailsArrayList;
    }

    public TripDetails getSingleTrip(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TripDetails tripDetails = new TripDetails();

        Cursor cursor = db.query(TRIP_ENTRIES_TABLE, new String[]{ID,
                        NAME, DESTINATION, TRIP_DATE, REQUIRES_ASSESSMENT,
                        DESCRIPTION, TYPE_OF_TRIP, WAS_TRIP_FUN},
                ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            tripDetails = new TripDetails(
                    cursor.getInt(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getString(7));
        }
        assert cursor != null;
        cursor.close();
        return tripDetails;
    }

    public ArrayList<TripDetails> searchDetails(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TripDetails> tripDetailsArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TRIP_ENTRIES_TABLE
                + " WHERE " + NAME + " LIKE '" + name + "%'";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            TripDetails tripDetails = new TripDetails(
                    cursor.getInt(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getString(7));

            tripDetailsArrayList.add(tripDetails);
        }
        cursor.close();
        return tripDetailsArrayList;
    }

    public void deleteTripDetails(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TRIP_ENTRIES_TABLE, ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    /**
     *
     * EXPENSES SETUP
     *
     * */

    public void insertExpense(String type, int tripID, String amount, String time,
                         String comments) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(TRIP_ID, tripID);
        cValues.put(TYPES_OF_EXPENSES, type);
        cValues.put(AMOUNT, amount);
        cValues.put(TIME, time);
        cValues.put(EXPENSE_COMMENTS, comments);

        long newRowId = db.insert(EXPENSE_TABLE, null, cValues);
        db.close();
    }

    public ArrayList<Expenses> getAllExpenses(int tripId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Expenses> expensesArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + EXPENSE_TABLE + " WHERE tripId = " + tripId;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Expenses expenses = new Expenses(
                    cursor.getInt(0)
                    , cursor.getInt(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5));

            expensesArrayList.add(expenses);
        }
        cursor.close();
        return expensesArrayList;
    }

    public void deleteExpense(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EXPENSE_TABLE, ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

}
