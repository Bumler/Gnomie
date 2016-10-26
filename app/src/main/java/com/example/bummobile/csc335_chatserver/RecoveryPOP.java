package com.example.bummobile.csc335_chatserver;

import android.app.Activity;

/**
 * Created by Bum Mobile on 10/25/2016.
 */

public class RecoveryPOP extends Activity {
    private Button complete;
    private EditText feet;
    private int numFeet = 0;
    private String stf;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_pop_altitude);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //this sets the size of the screen
        int width = (int) (dm.widthPixels * .40);
        int height = (int) (dm.heightPixels * .15);

        getWindow().setLayout(width, height);

        feet= (EditText)findViewById(R.id.altitude);

        setFeet();
        back();
    }
    private void setFeet(){
        stf = "bob";
        stf = feet.getText().toString();
        if(feet.getText().toString().length()!= 0)
            numFeet = Integer.parseInt(feet.getText().toString());
    }

    private void back(){
        complete = (Button)findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feet.getText().toString().length()!= 0)
                {numFeet = Integer.parseInt(feet.getText().toString());}
                if (numFeet != 0 && numFeet > 0) {
                    Log.d("Title", "inside");
                    Intent i = new Intent(pop_altitude_Activity.this, altitude_launch_Activity.class);
                    i.putExtra("feet", numFeet);
                    i.putExtra("custom",true);
                    startActivity(i);
                    pop_altitude_Activity.this.overridePendingTransition(0, 0);
                }
            }
        });
    }
}
