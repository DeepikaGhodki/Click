package com.example.deepika.click;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;


public class Main extends ActionBarActivity {

    int count=0;
    public static final String MAIN= "MAIN";
    Button start;
    DisplayMetrics display = new DisplayMetrics();
    long t1=0, t2=0, time= t2-t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button)findViewById(R.id.start);

        final TextView number= (TextView)findViewById(R.id.noOfClicks); //made final to be used inside inner class
        final Context context= getApplicationContext(); //final for inner class

       // SQLiteDatabase db= openOrCreateDatabase("ClickDB",MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS ScoreBoard (Name VARCHAR,Time INTEGER");
        //db.execSQL();
        getWindowManager().getDefaultDisplay().getMetrics(display); //what is this???
        //has to be inside onCreate

        Toast.makeText(context, "Click the button 20 times as fast as you can", Toast.LENGTH_SHORT).show();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no= String.valueOf(count);
                Log.d(MAIN,"On Click");
                if(count==0){
                    start.setText("Click Me");
                    t1=System.currentTimeMillis();
                }
                else {
                    number.setText(no);
                    if(count==20){
                        start.setEnabled(false);
                        t2=System.currentTimeMillis();
                        Toast.makeText(context,"Time taken is "+ String.valueOf((t2-t1))+ " milliseconds",Toast.LENGTH_SHORT).show();
                        Log.d(MAIN, String.valueOf(t2-t1));
                        Intent intent= new Intent(Main.this, result.class);
                        intent.putExtra("time", String.valueOf(t2-t1));
                        Log.d(MAIN,"Start Activity");
                        startActivity(intent);

                    }
                }
                count++;
                Log.i(MAIN, String.valueOf(t1/1000));
                Log.i(MAIN, String.valueOf(t2/1000));
                Log.i(MAIN, String.valueOf(t2-t1));

                setPos(start);
            }
        });

    }

    public void setPos(Button b){

        float x,y;
        RelativeLayout layout= (RelativeLayout)findViewById(R.id.layout);
        x= (float) ((float)Math.random()*(layout.getWidth()-b.getWidth()) );
        y= (float) ((float)Math.random()*(layout.getHeight()-b.getHeight()));

        //with getsize()
        /* x = (float) Math.random() * (p.x);
        y=(float)Math.random()* (p.y);
        Log.i("X:", String.valueOf(p.x));
        Log.i("Y:", String.valueOf(p.y));*/
        // x=Math.abs(x);
        //y=Math.abs(y);
        x=Math.abs(x);
        y=Math.abs(y);
        Log.i(MAIN, String.valueOf(x));
        Log.i(MAIN, String.valueOf(y));
        // Log.i("button w:", String.valueOf(b.getWidth())); //200px
        //Log.i("button h:", String.valueOf(b.getHeight()));//100px
        // Log.i("screen w:", String.valueOf(display.widthPixels)); 720pixels
        // Log.i("screen h:", String.valueOf(display.heightPixels)); //1184pixels
        //Log.i("layout height",String.valueOf(layout.getHeight())); 1022px

        start.setX(x);
        start.setY(y);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.scoreboard) {
            Intent intent= new Intent(Main.this,Score.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
