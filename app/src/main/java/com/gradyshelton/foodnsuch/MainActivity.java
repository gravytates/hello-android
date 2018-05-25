package com.gradyshelton.foodnsuch;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindFoodButton;
    private EditText mFoodEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fontPath = "fonts/Roboto-ThinItalic.ttf";
        TextView mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), fontPath);
        mAppNameTextView.setTypeface(robotoFont);

        mFindFoodButton = (Button) findViewById(R.id.findFoodButton);
        mFoodEditText = (EditText) findViewById(R.id.foodEditText);
        mFindFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = mFoodEditText.getText().toString();
                Log.d(TAG, food);
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            }
        });
    }
}


