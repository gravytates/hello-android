package com.gradyshelton.foodnsuch

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mFindFoodButton: Button? = null
    private var mFoodEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fontPath = "fonts/Roboto-ThinItalic.ttf"
        val mAppNameTextView = findViewById<View>(R.id.appNameTextView) as TextView
        val robotoFont = Typeface.createFromAsset(assets, fontPath)
        mAppNameTextView.typeface = robotoFont

        mFindFoodButton = findViewById<View>(R.id.findFoodButton) as Button
        mFoodEditText = findViewById<View>(R.id.foodEditText) as EditText
        mFindFoodButton!!.setOnClickListener {
            val food = mFoodEditText!!.text.toString()
            Log.d(TAG, food)
            val intent = Intent(this@MainActivity, FoodActivity::class.java)
            intent.putExtra("food", food)
            startActivity(intent)
        }
    }

    companion object {
        val TAG = MainActivity::class.java!!.getSimpleName()
    }
}


