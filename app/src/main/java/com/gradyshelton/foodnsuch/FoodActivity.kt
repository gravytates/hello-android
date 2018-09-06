package com.gradyshelton.foodnsuch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.AdapterView

class FoodActivity : AppCompatActivity() {
    private var mFoodTextView: TextView? = null
    private var ingredients: MutableList<String> = mutableListOf("Avocado", "Garlic", "Lime", "Salt", "Pepper", "Onion", "Chicken", "Beef", "Cheese", "Red Wine", "Basil", "Whiskey", "Potato", "Tomato", "Lettuce")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val mIngredientListView = findViewById<ListView>(R.id.ingredientListView)
        val mInfoTextView = findViewById<TextView>(R.id.infoTextView)
        val mUserInput: String = intent.getStringExtra(EXTRA_MESSAGE)
        val addedIngredients = ingredients

        if (mUserInput != "") {
            addedIngredients.add(mUserInput)
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, addedIngredients)
        mIngredientListView.setAdapter(adapter)


        mIngredientListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val ingredient = (view as TextView).text.toString()
                Toast.makeText(this@FoodActivity, ingredient, Toast.LENGTH_LONG).show()
            }
        })
    }
}
