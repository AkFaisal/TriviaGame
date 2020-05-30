package com.example.TriviaGame.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userdata.db";
    public static final String TABLE_NAME = "userdetails";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";
    public static final String PHONE_NUMBER = "phonenumber";
    public static final String PASSWORD = "password";
    public static final String SCORE = "score";
    public static final int VERSION_NUMBER = 21;
    private Context context;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, " + NAME + " VARCHAR(100)," + GENDER + " TEXT(6) NOT NULL," + EMAIL + " TEXT(100) NOT NULL, "
                                                + BIRTHDAY + " TEXT(8)  NOT NULL, " + PHONE_NUMBER + " TEXT(100) NOT NULL, " + PASSWORD + " TEXT(100) NOT NULL , " + SCORE + " TEXT  )" ;

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super( context, DATABASE_NAME, null, VERSION_NUMBER );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try {

            db.execSQL( CREATE_TABLE );


        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try {
            db.execSQL( DROP_TABLE );
            onCreate( db );

        } catch (Exception e) {

        }

    }

    public long insertData(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( NAME, userDetails.getName() );
        contentValues.put( GENDER, userDetails.getGender() );
        contentValues.put( EMAIL, userDetails.getEmail() );
        contentValues.put( BIRTHDAY, userDetails.getBirthday() );
        contentValues.put( PHONE_NUMBER, userDetails.getPhone() );
        contentValues.put( PASSWORD, userDetails.getPassword() );
        long rowId = db.insert( TABLE_NAME, null, contentValues );
        return rowId;
    }
    public UserDetails findUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + "='" + email+ "' AND " + PASSWORD + "=" + password;
        Cursor cursor = db.rawQuery(query, null );
        if (cursor.getCount() == 0) {
            Toast.makeText( context, "No data is found", Toast.LENGTH_SHORT ).show();
            return  null;
        }

        cursor.moveToFirst();
        UserDetails userDetails = new UserDetails(
                cursor.getLong(cursor.getColumnIndex(ID)),
                cursor.getString( cursor.getColumnIndex( NAME ) ),
                cursor.getString( cursor.getColumnIndex( GENDER ) ),
                cursor.getString( cursor.getColumnIndex( EMAIL ) ),
                cursor.getString( cursor.getColumnIndex( BIRTHDAY ) ),
                cursor.getString( cursor.getColumnIndex( PHONE_NUMBER ) ),
                cursor.getString( cursor.getColumnIndex( PASSWORD ) ),
                cursor.getString( cursor.getColumnIndex( SCORE ) )
        );

        cursor.close();
        db.close();

        return userDetails;
    }

 public long update(UserDetails userDetails ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

     contentValues.put( SCORE, userDetails.getScore());

     long rowId =db.update( TABLE_NAME,  contentValues ,"id=?",new String[]{String.valueOf(userDetails.getId()  )});


     return rowId;
 }

}
