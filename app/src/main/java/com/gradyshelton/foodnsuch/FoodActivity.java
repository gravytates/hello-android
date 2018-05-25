package com.gradyshelton.foodnsuch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {
    private TextView mFoodTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        mFoodTextView = (TextView) findViewById(R.id.foodTextView);
        Intent intent = getIntent();
        String food = intent.getStringExtra("food");
        mFoodTextView.setText("Here is food that matches: " + food);
    }
}
