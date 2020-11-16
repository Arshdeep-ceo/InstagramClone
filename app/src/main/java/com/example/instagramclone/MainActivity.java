package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave,btnGetAllData;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetData;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(MainActivity.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);

        txtGetData  = findViewById(R.id.txtGetData);

        btnGetAllData = findViewById(R.id.btnGetAllData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxers = "";
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("Dqz3Wio3kx", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){

                            txtGetData.setText(object.get("name") + "Punch Power: " + object.get("punch_power") );
                        }
                    }
                });
            }
        });
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                       if (e== null){
                           if (objects.size() > 0){

                               for (ParseObject kickBoxer : objects){
                                   allKickBoxers = allKickBoxers + kickBoxer.get("name")  + "\n";
                               }
                               FancyToast.makeText(MainActivity.this,  allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                           }

                       }
                    }
                });
            }
        });

    }


    @Override
    public void onClick(View v) {
        try {
            ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punch_power", edtPunchPower.getText().toString());
            kickBoxer.put("kick_speed", edtKickSpeed.getText().toString());
            kickBoxer.put("kick_power", edtKickPower.getText().toString());

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("name").toString() + " is saved ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }
}