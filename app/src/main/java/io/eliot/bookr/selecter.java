package io.eliot.bookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Date;

import io.eliot.bookr.printer;

public class selecter extends AppCompatActivity {
   //TODO setup parse DB to hold id's and busy times
    void alertDisplayer(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(selecter.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
    //ID for getting times
    EditText id;
    //ID for setting up times
    EditText id2;
    //Button for getting times
    Button getTimes;
    //Button for setting up
    Button setID;
    ArrayList timings;
    public void finder(int early, int late) {
        int counter = early;
        while(counter >  late){
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selector);
        id = findViewById(R.id.id);
        id2 = findViewById(R.id.editText4);
        setID = findViewById(R.id.generateID);
        getTimes = findViewById(R.id.selectorGo);
        //SET ID AND GENERATE TIMES HERE
        setID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }
                ).start();
            }
        });
        //RETREAVE TIMES HERE
        getTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ParseObject idObject = new ParseObject(id.getText().toString());
                        if(idObject.isDataAvailable()){
                            String name = idObject.getString("Name");
                            int early = idObject.getInt("early"+ );
                            String breaks = idObject.getString("breaks");
                            int finish = idObject.getInt("finish");
                            Boolean today = idObject.getBoolean("today");
                            if(today){
                                Intent intent = new Intent(selecter.this, Timings.class);
                                intent.putExtra("start", Integer.toString(early));
                                intent.putExtra("end", Integer.toString(finish));
                                //intent.putExtra("today",);
                                startActivity(intent);
                            }else{
                                alertDisplayer(name+" is not working today","Do you want to look at tomorrow?");
                            }
                            idObject.getBoolean("7");
                        }else{
                            alertDisplayer("ERROR","Entered ID doesn't exist. Please check for spelling mistakes.");
                        }
                    }
                });
            }
        });
    }


}
