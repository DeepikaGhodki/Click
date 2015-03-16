package com.example.deepika.click;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.OnClickListener;


public class result extends ActionBarActivity {

    String playerName;
    EditText name;
    TextView res,enter,congrats;
    long time;
    Button newGame,add;
    ClickDatabaseHandler Cdb;
    Entry entry[]= new Entry[3];
    public static final String RESULT= "RESULT";


            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        enter= (TextView)findViewById(R.id.textView);
        res= (TextView)findViewById(R.id.result);
        congrats= (TextView)findViewById(R.id.congo);
        name= (EditText)findViewById(R.id.playerName); //this will be blank initially

        add= (Button)findViewById(R.id.add);


        Bundle data= getIntent().getExtras();
        time= Integer.parseInt(String.valueOf(data.get("time")));
       // Log.d(RESULT, String.valueOf(time));

        res.setText("You finished in "+data.get("time")+" milliseconds");
        Cdb = new ClickDatabaseHandler(getApplicationContext());

        final SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        final  SharedPreferences.Editor editor= pref.edit();

                /*if(pref.getString("key","").equals("")){
                    playerName= name.getText().toString();
                    Log.d(RESULT, "not set key value "+ pref.getString("key","def"));
                    editor.putString("key",name.getText().toString());
                    Log.d(RESULT, "set key value "+ pref.getString("key","def"));
                    editor.commit();
                }
                else{
                    name.setText(pref.getString("key",playerName));
                    Log.d(RESULT, "Not the first time "+pref.getString("key",playerName));
                    playerName= name.getText().toString();
                    Log.d(RESULT,"From editText "+playerName);
                }*/
                    Log.d(RESULT,"reached tilll here.");
                Log.d(RESULT,pref.getString("key","Null"));
                       if(pref.getString("key","").equals("")){
                            playerName= name.getText().toString();
                            //if(playerName.equals(""))playerName= name.getText().toString();
                            Log.d(RESULT, "not set key value "+ pref.getString("key",playerName));
                            editor.putString("key",playerName);
                            Log.d(RESULT, "set key value "+ pref.getString("key",playerName));
                            editor.commit();
                        }
                        else{
                            name.setText(pref.getString("key",playerName));
                            Log.d(RESULT, "Not the first time "+pref.getString("key",playerName));
                            playerName= name.getText().toString();
                            Log.d(RESULT,"From editText "+playerName);
                        }



       // Log.d(RESULT, String.valueOf(time));

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(pref.getString("key","").equals("")){
                    playerName= name.getText().toString();
                    Log.d(RESULT, "not set key value "+ pref.getString("key",""));
                    if(playerName.equals(""))playerName= name.getText().toString();
                    editor.putString("key",playerName);
                    Log.d(RESULT, "set key value "+ pref.getString("key",""));
                    editor.commit();
                }
                else{
                    name.setText(pref.getString("key",playerName));
                    Log.d(RESULT, "Not the first time "+pref.getString("key",playerName));
                    playerName= name.getText().toString();
                    Log.d(RESULT,"From editText "+playerName);

                }*/
                //playerName= name.getText().toString();
                playerName= name.getText().toString();
                editor.putString("key",playerName);
                editor.commit();
                Log.d(RESULT, "name "+ name.getText().toString()+ " name");
                if(playerName.equals(""))add.setEnabled(false);
                Cdb.addScore(new Entry(playerName,time));
                add.setEnabled(false);
            }
        });

       //Entry tableEntry;
       //tableEntry=Cdb.getData(1);
       //Log.d(RESULT, "name " + tableEntry.get_name()+ " " + tableEntry.get_id());

     /*entry= Cdb.topThree();
                Log.d(RESULT, "1."+entry[0].printEntry());
                Log.d(RESULT, "2."+entry[1].printEntry());
                Log.d(RESULT, "3."+entry[2].printEntry());
//

            String res[];
                res= Cdb.printThree();
                Log.d(RESULT, "1."+res[0]);
                Log.d(RESULT, "2."+res[1]);
                Log.d(RESULT, "3."+res[2]);
*/
        //starting a new game
        newGame= (Button)findViewById(R.id.newGame);

        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start= new Intent(result.this, Main.class);
                startActivity(start);
            }
        });


    }

//displaying name from database

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_result, menu);
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
            Intent intent= new Intent(result.this,Score.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
