package com.example.sprint.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.sprint.Entidades.Producto;

public class DBLocal extends SQLiteOpenHelper {
    private  SQLiteDatabase sqLiteDatabase;

    public DBLocal (Context context) {
        super(context, "G101.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTS(" +
                "id TEXT PRIMARY KEY," +
                "name TEXT," +
                "description TEXT," +
                "prece TEXT, "+
                "foto TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PRODCTS");
    }

    public void insertData(Producto producto) {
        String sql = "INSERT INTO PRODUCTS VALUES (?,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, producto.getId());
        statement.bindString(2, producto.getName());
        statement.bindString(3, producto.getDescription());
        statement.bindString(4, String.valueOf(producto.getPrice()));
        statement.bindString(5, producto.getImage());

        statement.executeInsert();
    }

    public Cursor getData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTS", null);
        return cursor;
    }

}
