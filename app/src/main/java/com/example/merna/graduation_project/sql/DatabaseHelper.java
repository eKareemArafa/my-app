
package com.example.merna.graduation_project.sql;

/**
 * Created by merna on 5/21/2017.
 */

import android.app.VoiceInteractor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.name;
import static com.example.merna.graduation_project.R.drawable.user;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "PPT App.db";


    // Mobile owner table name
    private static final String TABLE_USER = "Mobile_Owner";

    // Mobile owner Table Columns names
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USER_GMAIL = "gmail";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_PhoneNumber = "phonenumber";
    private static final String COLUMN_USER_National_ID = "national_ID";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_Stego_image="Stego_image";
    //friend list Table Name
    private static final String TABLE_FRIEND="friend_list";
//friend list Table colums
    private static final String COLUMN_FRIEND_NAME="NAME";
    private static final String COLUM_FRIEND_PHONENOM="PHONE";


    // create  Mobile owner table sql query


    // drop table sql query

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_USER_GMAIL + " TEXT," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_PhoneNumber + " TEXT," +
               // COLUMN_USER_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_USER_National_ID + " TEXT PRIMARY KEY , " +
                COLUMN_USER_PASSWORD + " TEXT, " +
                COLUMN_USER_Stego_image + " TEXT " +")";

        String CREATE_FRIEND_TABLE =" CREATE TABLE "+ TABLE_FRIEND+"( "+
                COLUM_FRIEND_PHONENOM +" TEXT , "+
                COLUMN_FRIEND_NAME+" TEXT , "+
                " PRIMARY KEY( " + COLUM_FRIEND_PHONENOM+ " , " + COLUMN_FRIEND_NAME+ " ) );";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FRIEND_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    //User user =new User();
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_GMAIL, user.getGmail());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PhoneNumber, user.getPhoneNumber());
        values.put(COLUMN_USER_National_ID, user.getId());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        Log.d("query","Done");
        getinfo(user);
        db.close();
    }


    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateGmail(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_GMAIL, user.getGmail());
       // values.put(COLUMN_USER_NAME, user.getName());
       // values.put(COLUMN_USER_PhoneNumber, user.getPhoneNumber());
       // values.put(COLUMN_USER_National_ID, user.getId());
       // values.put(COLUMN_USER_PASSWORD, user.getPassword());


        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_National_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void updatePass(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_USER_GMAIL, user.getGmail());
        // values.put(COLUMN_USER_NAME, user.getName());
        // values.put(COLUMN_USER_PhoneNumber, user.getPhoneNumber());
        // values.put(COLUMN_USER_National_ID, user.getId());
         values.put(COLUMN_USER_PASSWORD, user.getPassword());


        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_National_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     */
    public void getinfo(User user){
//        User user =new User();
        SQLiteDatabase dbh=this.getReadableDatabase();
        Cursor cursor=dbh.rawQuery("SELECT * FROM "+TABLE_USER,null);
        Log.d("record nomber","is"+cursor.getCount());
        if (cursor!=null)
            cursor.moveToNext();
        user.setId(cursor.getString(cursor.getColumnIndex(COLUMN_USER_National_ID)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
        user.setGmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GMAIL)));
        user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
        user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PhoneNumber)));

        String name=user.getName();
        String id=user.getId();
        String phone=user.getPhoneNumber();
        String gmail=user.getGmail();
        String pass=user.getPassword();
        Log.d("Name",name);
        Log.d("Phone",phone);
        Log.d("ID",id);
        Log.d("Password",pass);
        Log.d("Gmail",gmail);
        dbh.close();
    }


    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_National_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public User get_user (String National){
        String query ="SELECT * "+
                " FROM "+TABLE_USER +
                " WHERE " + COLUMN_USER_National_ID + " = "+ National ;

        User use = new User();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);

        if (cursor != null)
            cursor.moveToNext();

        use.setId(cursor.getString(cursor.getColumnIndex(COLUMN_USER_National_ID)));
        use.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
        use.setGmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GMAIL)));
        use.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
        db.close();
        return use;
    }

   public String  getID(){
       User user = new User();
       SQLiteDatabase db =this.getReadableDatabase();
       String query=" SELECT " + COLUMN_USER_National_ID +
               " FROM " + TABLE_USER ;
       Cursor cursor=db.rawQuery(query,null);
       if (cursor!=null)
           cursor.moveToNext();
       user.setId(cursor.getString(cursor.getColumnIndex(COLUMN_USER_National_ID)));
       String id=user.getId();
       return id ;
   }
    public String getGmail(){
        User user =new User();
        SQLiteDatabase db=this.getReadableDatabase();
        String query=" SELECT " + COLUMN_USER_GMAIL +
                " FROM " + TABLE_USER ;
        Cursor cutsor =db.rawQuery(query,null);
        if (cutsor!=null)
            cutsor.moveToNext();
        user.setGmail(cutsor.getString(cutsor.getColumnIndex(COLUMN_USER_GMAIL)));
        String mail=user.getGmail();
        return mail;
    }
public  String getPass(){
    User user =new User();
    SQLiteDatabase db=this.getReadableDatabase();
    String query=" SELECT " + COLUMN_USER_PASSWORD +
            " FROM " + TABLE_USER ;
    Cursor cursor =db.rawQuery(query,null);
    if (cursor!=null)
        cursor.moveToNext();
    user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
    String pass=user.getPassword();
    return pass;

}

}
