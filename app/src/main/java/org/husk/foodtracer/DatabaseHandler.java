package org.husk.foodtracer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Declaring names for database and table, also version
    private static final String databaseName = "food_inventory_db";
    private static final int databaseVersion = 1;
    private static  final String tableName = "food_inventory";
    private static  final String idCol = "id";
    private static final String nameCol = "food_name";
    private static final String dateCol = "date";
    private static final String quantityCol = "quantity";

    // Constructor
    public DatabaseHandler(@NonNull Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version,
                           @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, int version,
                           @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + "("
                + idCol + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + nameCol + " TEXT,"
                + dateCol + " TEXT,"
                + quantityCol + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    // Method to add new food item to our sqlite database
    public void addNewFoodItem(String foodName, String date, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(nameCol, foodName);
        values.put(dateCol, date);
        values.put(quantityCol, quantity);

        db.insert(tableName, null, values);

        db.close();
    }

    // Method to reading the food items from database
    public ArrayList<FoodItem> readFoodItems() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorFoodItems = db.rawQuery("SELECT * FROM " + tableName, null);

        ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();

        if (cursorFoodItems.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursorFoodItems.getInt(
                        cursorFoodItems.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursorFoodItems.getString(
                        cursorFoodItems.getColumnIndex("food_name"));
                @SuppressLint("Range") String date = cursorFoodItems.getString(
                        cursorFoodItems.getColumnIndex("date"));
                @SuppressLint("Range") int quantity = cursorFoodItems.getInt(
                        cursorFoodItems.getColumnIndex("quantity"));

                foodItemArrayList.add(new FoodItem(id, name, date, quantity));

            } while (cursorFoodItems.moveToNext());
        }
        cursorFoodItems.close();

        return foodItemArrayList;
    }

    // Method to update food item in the databse
    public void updateCourse(String foodName, String date, int quantity) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(nameCol, foodName);
        values.put(dateCol, date);
        values.put(quantityCol, quantity);

        // TODO update values in database

    }

    // Method to delete food item in the database
    public void deleteCourse(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(tableName, "id=?", new String[]{String.valueOf((id))});
        db.close();
    }

}
