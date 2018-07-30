package com.gradyshelton.foodnsuch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class FoodActivity : AppCompatActivity() {
    private var mFoodTextView: TextView? = null
    private var ingredients = arrayOf<String>("Avocado", "Garlic", "Lime", "Salt", "Pepper", "Onion", "Chicken", "Beef", "Cheese", "Red Wine", "basil", "whiskey", "potato", "tomato", "lettuce")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val mIngredientListView = findViewById<ListView>(R.id.ingredientListView)
        val mInfoTextView = findViewById<TextView>(R.id.infoTextView)
        val mUserInput: String = intent.getStringExtra(EXTRA_MESSAGE)
        val addedIngredients = ingredients.plus(mUserInput)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, addedIngredients)
        mIngredientListView.setAdapter(adapter)

    }
}
