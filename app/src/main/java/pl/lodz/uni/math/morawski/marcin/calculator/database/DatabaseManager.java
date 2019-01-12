package pl.lodz.uni.math.morawski.marcin.calculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseManager {

    private DatabaseManager() {
    }

    public static void clearHistory(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        db.delete(DatabaseContract.DatabaseHistoryEntry.TABLE_NAME, null, null);

        databaseHelper.close();
    }

    public static void insertEquation(Context context, String equation) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DatabaseHistoryEntry.COLUMN_NAME_EXPRESSION, equation);
        db.insert(DatabaseContract.DatabaseHistoryEntry.TABLE_NAME, null, values);

        databaseHelper.close();
    }

    public static List<String> loadHistory(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.DatabaseHistoryEntry.COLUMN_NAME_EXPRESSION
        };

        Cursor cursor = db.query(
                DatabaseContract.DatabaseHistoryEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List<String> equationList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String equation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DatabaseHistoryEntry.COLUMN_NAME_EXPRESSION));
            equationList.add(equation);
        }

        cursor.close();
        databaseHelper.close();

        return equationList;

    }
}
