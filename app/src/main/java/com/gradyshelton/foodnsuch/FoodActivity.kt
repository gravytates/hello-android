package com.gradyshelton.foodnsuch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.AdapterView
import com.gradyshelton.foodnsuch.model.realm.Ingredient
import io.realm.Realm
import io.realm.kotlin.where

class FoodActivity : AppCompatActivity() {
    private var mFoodTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val realm = Realm.getDefaultInstance()
        val mIngredientListView = findViewById<ListView>(R.id.ingredientListView)
        val addedIngredient: String = intent.getStringExtra(EXTRA_MESSAGE)
        Toast.makeText( this@FoodActivity, "$addedIngredient added", Toast.LENGTH_LONG).show()

        val alphabetizedRealmIngredients = realm.where<Ingredient>().sort("name").findAllAsync()
        val ingredientArray: MutableList<String> = mutableListOf()

        for (ingredient in alphabetizedRealmIngredients) {
            val name = ingredient.name.toString()
            val price = ingredient.price.toString()
            ingredientArray.add("$name: $$price")
        }
        Log.d(TAG, "ingredientList: $alphabetizedRealmIngredients")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientArray)
        mIngredientListView.setAdapter(adapter)

        mIngredientListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val ingredient = (view as TextView).text.toString()
                Toast.makeText(this@FoodActivity, ingredient, Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        val TAG = MainActivity::class.java.getSimpleName()
    }
}
