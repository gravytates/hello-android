package com.gradyshelton.foodnsuch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FoodActivity : AppCompatActivity() {
    private var mFoodTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        mFoodTextView = findViewById(R.id.foodTextView)
        val intent = intent
        val food = intent.getStringExtra("food")
        mFoodTextView!!.text = "Here is food that matches: $food"
    }
}
