package com.example.emanuelepaciolla.rubrica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emanuele Paciolla on 11/03/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String KEY_ID = "id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";
    public static final String KEY_TELEFONO = "telefono";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_INDIRIZZO = "indirizzo";

    private static final String DATABASE_NAME = "Rubrica";

    private static final String TABLE_NOTES = "contacts";

    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String creation = "CREATE TABLE " + TABLE_NOTES + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NOME + " TEXT, "
                + KEY_COGNOME + " TEXT, " + KEY_TELEFONO + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_INDIRIZZO + " TEXT " + ")";
        System.out.println(creation);
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public long addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, contact.getName());
        values.put(KEY_COGNOME, contact.getSurname());
        values.put(KEY_TELEFONO, contact.getCellphone());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_INDIRIZZO, contact.getAddress());

        long ritorno = db.insert(TABLE_NOTES, null, values);
        db.close();
        return ritorno;
    }
    public void deleteContact(Contact contact){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NOTES, KEY_ID + "= ?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }
    public List<Contact> getAllContact(){
        ArrayList<Contact> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact note = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                // Adding note to list
                contactList.add(note);

            } while (cursor.moveToNext());
        }

        // return notes list
        return contactList;
    }
    public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, contact.getName());
        values.put(KEY_COGNOME, contact.getSurname());
        values.put(KEY_TELEFONO, contact.getCellphone());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_INDIRIZZO, contact.getAddress());

        db.update(TABLE_NOTES, values, KEY_ID + " = ? ", new String[]{String.valueOf(contact.getID())});
        db.close();
    }
}
