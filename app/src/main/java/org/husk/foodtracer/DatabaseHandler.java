package org.husk.foodtracer;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    // Method to add new course to our sqlite database
    public void addNewFoodItem(String foodName, String date, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(nameCol, foodName);
        values.put(dateCol, date);
        values.put(quantityCol, quantity);

        db.insert(tableName, null, values);

        db.close();
    }

}
