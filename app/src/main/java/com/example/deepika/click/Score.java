package com.example.deepika.click;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Score extends ActionBarActivity {

    ClickDatabaseHandler Cdb;
    public static final String SCORE= "SCORE";
    Entry entry[]= new Entry[3];
    TextView name, time, p1,p2,p3,t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Cdb = new ClickDatabaseHandler(getApplicationContext());
        entry= Cdb.topThree();
        Log.d(SCORE, "1."+entry[0].printEntry());
        Log.d(SCORE, "2."+entry[1].printEntry());
        Log.d(SCORE, "3."+entry[2].printEntry());

        name= (TextView)findViewById(R.id.playerName);
        time= (TextView)findViewById(R.id.time);
        p1=(TextView)findViewById(R.id.p1);
        t1=(TextView)findViewById(R.id.t1);
        p2=(TextView)findViewById(R.id.p2);
        t2=(TextView)findViewById(R.id.t2);
        p3=(TextView)findViewById(R.id.p3);
        t3=(TextView)findViewById(R.id.t3);

        p1.setText(entry[0].get_name());
        t1.setText(entry[0].get_time()+"");
        p2.setText(entry[1].get_name());
        t2.setText(entry[1].get_time()+"");
        p3.setText(entry[2].get_name());
        t3.setText(entry[2].get_time()+"");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
