package com.example.lotterycosts;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Total costs
    double totalCosts;
    // GUI controls
    private Spinner spTips;
    private RadioGroup rgLottery;
    private RadioButton rbLottery;
    private TextView tvCosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get references to the Spinner, RadioGroup, RadioButtons and the result TextView
        spTips = findViewById(R.id.spTips);
        rgLottery = findViewById(R.id.rgLottery);
        tvCosts = findViewById(R.id.tvCosts);
    }

    public void calculateCosts(View view) {

        // Get selected radio button from radioGroup
        int selectedType = rgLottery.getCheckedRadioButtonId();

        // Find the radiobutton by returned id
        rbLottery = findViewById(selectedType);

        // Get the selected spinner item and convert it to int for calculation
        int numberTips = Integer.parseInt(spTips.getSelectedItem().toString());

        // Calculation of costs
        if (rbLottery.getText().equals("Swiss Lottery"))
            totalCosts = 2.5 * numberTips;
        else totalCosts = 3.5 * numberTips;

        // Display result
        tvCosts.setText("Total Costs: " + totalCosts + " CHF");

    }
}

