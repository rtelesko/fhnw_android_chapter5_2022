package com.example.lotteryfivetips;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Constants
    static final int MAX_TIPS = 5;
    static final int LOTTO_NUMBERS = 6;
    // GUI controls
    private TextView tvResult;
    private TextView tvValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get reference to the TextView which shows the result
        tvResult = findViewById(R.id.tvResult);
        // Get references to the TextViews which show the numbers and the validation result
        tvValidation = findViewById(R.id.tvValidation);
    }

    // Event handler method
    public void generateRandomNumbers(View view) {

        // Initialize TextView for numbers
        tvResult.setText("");

        // Initialize TextView for validation
        tvValidation.setText("");

        int numbers[][] = new int[MAX_TIPS][LOTTO_NUMBERS];
        Random random = new Random();

        // Generate random tips
        for (int tip = 0; tip < MAX_TIPS; tip++) {
            tvResult.append("Tip " + (tip + 1) + ":    ");
            for (int number = 0; number < LOTTO_NUMBERS; number++) {
                numbers[tip][number] = random.nextInt(42) + 1;
            }
            // Sort for later viewing duplicates more easily
            Arrays.sort(numbers[tip]);
            for (int item : numbers[tip]) {
                tvResult.append(item + "  ");
            }
            tvResult.append("\n" + "\n");
        }

        // Call validation
        if (validationCheck(numbers)) {
            tvValidation.setTextColor(new Color().parseColor("#008000"));
            tvValidation.setText("Validation for duplicates: Tips OK - Good Luck!");
        } else {
            tvValidation.setTextColor(new Color().parseColor("#FF0000"));
            tvValidation.setText("Validation for duplicates: Tips NOT valid - Make a new try!");
        }

    }

    // Clear content
    public void clearRandomNumbers(View view) {
        tvResult.setText("");
        tvValidation.setText("");
    }

    // Check for duplicates (within one tip and for whole tips)
    private boolean validationCheck(int[][] numbers) {
        // Check for duplicate numbers within a tip
        for (int tip = 0; tip < MAX_TIPS; tip++) {
            for (int i = 0; i < LOTTO_NUMBERS; i++) {
                for (int j = i + 1; j < LOTTO_NUMBERS; j++) {
                    if (numbers[tip][i] == numbers[tip][j]) {
                        return false;  // Got a duplicate element within a tip
                    }
                }
            }
        }
        // Check for duplicate tips
        for (int tip = 0; tip < MAX_TIPS; tip++) {
            for (int i = tip + 1; i < MAX_TIPS; i++) {
                if (Arrays.equals(numbers[tip], numbers[i])) {
                    return false;  // Got two duplicate tips
                }
            }
        }
        return true;
    }
}
