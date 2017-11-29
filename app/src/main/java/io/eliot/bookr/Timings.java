package io.eliot.bookr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.nio.Buffer;
import java.util.ArrayList;

public class Timings extends AppCompatActivity {
    Bundle bundle = getIntent().getExtras();
    boolean dayNorm = bundle.getBoolean("dayNorm");
    String breaks = bundle.getString("breaks");
    String name = bundle.getString("name");
    int end = bundle.getInt("end");
    int start = bundle.getInt("start")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timings);
    }
    ArrayList timings;
    public void finder(int earlyH, int lateH,int earlyM, int lateM) {
        int counterH = earlyH;
        int counterM = earlyM;
        while(counterH <=  lateH&&counterM<=lateM){
            timings.add(Integer.toString(counterH)+"."+Integer.toString(counterM));
        }

    }

}
