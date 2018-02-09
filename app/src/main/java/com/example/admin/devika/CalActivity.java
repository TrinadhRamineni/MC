package com.example.admin.devika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class CalActivity extends AppCompatActivity {
    private EditText weight,feet,inches,years;
    private TextView tvMen,tvWomen;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        weight = (EditText) findViewById(R.id.etWeight);
        feet = (EditText) findViewById(R.id.etFeet);
        inches = (EditText) findViewById(R.id.etInches);
        years = (EditText) findViewById(R.id.etAge);
        calculate = (Button) findViewById(R.id.btnCalcutate);
        tvMen = (TextView) findViewById(R.id.tvMen);
        tvWomen = (TextView) findViewById(R.id.tvWomen);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float w = Float.valueOf(weight.getText().toString());
                float f = Float.valueOf(feet.getText().toString());
                float i = Float.valueOf(inches.getText().toString());
                float y = Float.valueOf(years.getText().toString());
                DecimalFormat df = new DecimalFormat("#.###");

                double mv = (66.4730 + (13.7516 * w * 2.2046226218) + (5.0033 * ((f * 30.48) + (i * 3.048))) - (6.7550 * y));
                double wv = (655.0955 + (9.5634 * w * 2.2046226218) + (1.8496 * ((f * 30.48) + (i * 3.048))) - (4.6756 * y));
              //  mv = Double.parseDouble(df.format(mv));
              //  wv = Double.parseDouble(df.format(wv));
                String men = Double.toString(mv);
                String women = Double.toString(wv);
                tvMen.setText("For Men " + men + " Calories");
                tvWomen.setText("For Women " + women + " Calories");
            }
        });
    }
}
