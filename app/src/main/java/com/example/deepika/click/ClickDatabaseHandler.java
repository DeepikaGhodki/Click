package com.example.deepika.click;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

/**
 * Created by Deepika on 03-03-2015.
 */
public class ClickDatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION= 2; //first time on upgrading, update version
    private static final String DATABASE_NAME= "click.db";
    private static final String TABLE_NAME= "score";

    private  static final String ClickDatabaseHandler= "ClickDatabaseHandler";
    //column names
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_TIME="time";

    public ClickDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //to be done on creating the table for the first time
        String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "("+ COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                     COLUMN_NAME + " TEXT DEFAULT 'player', " +
                     COLUMN_TIME + " INTEGER DEFAULT '0'" +" );";
        db.execSQL(CREATE_TABLE);
        Log.d(ClickDatabaseHandler, "Database created");
    }

    //add a new row

    public void addScore(Entry entry){
        SQLiteDatabase db = this.getWritableDatabase(); //db is the key to the database i.e. needed to access database
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entry.get_name()); //  Name
        values.put(COLUMN_TIME, entry.get_time()); // Time

        // Inserting Row
        Log.d(ClickDatabaseHandler, db.isOpen()+" "+ entry.get_id()+" "+ entry.get_name());
        try{
            db.insert(TABLE_NAME, null, values);
            Log.d(ClickDatabaseHandler, "Inserted " );

        }catch (Exception e){
            Log.e(ClickDatabaseHandler, e.getMessage());
        }

        db.close(); // Closing database connection
    }

    public String[] printThree() {
        SQLiteDatabase db = getWritableDatabase();
        String res[] = new String[3];
        res[0]=null;
        res[1]=null;
        res[2]=null;
        int i = 0;
        String query = "SELECT " + COLUMN_ID+ " , "+ COLUMN_NAME + ", " + COLUMN_TIME + " FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
         do{

                res[i] = c.getString(0) + " : " + c.getString(1)+" : "+c.getString(2);

            i++;

        }while (c.moveToNext() && (i < 3));
        return res;
    }
    public Entry[] topThree(){

        SQLiteDatabase db= getWritableDatabase();
        String query= "SELECT "+ COLUMN_ID+" , "+ COLUMN_NAME+ " , "+ COLUMN_TIME+ " FROM "+ TABLE_NAME+ " ORDER BY "+COLUMN_TIME +"  ";
        //String query = "SELECT " + COLUMN_ID+ " , "+ COLUMN_NAME + ", " + COLUMN_TIME + " FROM " + TABLE_NAME +" ";
        Entry entry[]= new Entry[3];
        entry[0]=new Entry();
        entry[1]=new Entry();
        entry[2]=new Entry();
        Cursor c= db.rawQuery(query,null);
        int i=0;
        /*c.moveToFirst();
        do{ //changed

                //Log.d(ClickDatabaseHandler,"in topThree "+ c.moveToFirst());
                //Log.d(ClickDatabaseHandler,"in topThree " + c.getString(0));

            Log.d(ClickDatabaseHandler,"in topThree " + entry[i].get_id());
            entry[i].set_name(c.getString(1));
                    Log.d(ClickDatabaseHandler, "name " + i + " " + c.getString(1));
                    entry[i].set_time(Integer.parseInt(c.getString(2)));
                    Log.d(ClickDatabaseHandler, "name " + i + " " + (c.getString(2)));
            c.moveToNext();
            i++;

        }while(c.moveToNext() && (i < 3));*/

        while(c.moveToNext() && i<3){

            Log.d(ClickDatabaseHandler,"in topThree " + entry[i].get_id());
            entry[i].set_name(c.getString(1));
            Log.d(ClickDatabaseHandler, "name " + i + " " + c.getString(1));
            entry[i].set_time(Integer.parseInt(c.getString(2)));
            Log.d(ClickDatabaseHandler, "name " + i + " " + (c.getString(2)));
            i++;

        }
        c.close();
        db.close();
        return entry;

    }



    public Entry getData(int n){
        SQLiteDatabase db= this.getWritableDatabase();
        String query= "SELECT * FROM "+ TABLE_NAME+ " WHERE "+COLUMN_ID+ " = "+ n + " ";
        Entry e = new Entry();
        Cursor c= db.rawQuery(query,null);
        Log.d(ClickDatabaseHandler," "+ db.isOpen());
        c.moveToFirst();
        if (c.moveToFirst()){
            e.set_id(Integer.parseInt(c.getString(0)));
            e.set_name(c.getString(1));
            e.set_time(Integer.parseInt(c.getString(2)));
            Log.d(ClickDatabaseHandler,"getData "+c.getString(1));
        }
        else e= null;
        return e;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME +";");
        onCreate(db);
    }
}
