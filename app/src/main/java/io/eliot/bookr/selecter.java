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

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    int earlyH;
    int finishH;
    int earlyM;
    int finishM;
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
                            String date = DateFormat.getDateInstance().format(new Date());
                            //GETTING FROM DB INFO
                            String name = idObject.getString("Name");
                            String daysWorking = idObject.getString("daysWorking");
                            String breaks = idObject.getString("breaks"+date);
                            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
                            if(daysWorking.contains(simpleDateformat.format("dd"))){

                            }
                            Boolean dayNorm = idObject.getBoolean("dayNorm");
                            if(dayNorm){
                                    int earlyH = idObject.getInt("earlyH");
                                    int finishH = idObject.getInt("finishH");
                                    int earlyM = idObject.getInt("earlyM");
                                    int finishM = idObject.getInt("finishM"); }
                            else{
                                int earlyH = idObject.getInt("earlyH"+date);
                                int finishH = idObject.getInt("finishH"+date);
                                int earlyM = idObject.getInt("earlyM"+date);
                                int finishM = idObject.getInt("finishM"+date);
                            }


                            if(today){
                                //SENDING TO NEW ACTIVITY TO SELECT TIMES
                                Intent intent = new Intent(selecter.this, Timings.class);
                                intent.putExtra("startH", Integer.toString(earlyH));
                                intent.putExtra("endH", Integer.toString(finishH));
                                intent.putExtra("startM", Integer.toString(earlyM));
                                intent.putExtra("endM", Integer.toString(finishM));
                                intent.putExtra("name", name);
                                intent.putExtra("breaks",breaks);
                                intent.putExtra("dayNorm",dayNorm);
                                intent.putExtra("workingDays",daysWorking);
                                startActivity(intent);
                            }else{
                                alertDisplayer(name+" is not working today","We will show you the next available day instead");

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
