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
//UGLY UGLY UGLY
class FoodActivity : AppCompatActivity() {
    private var mFoodTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val realm = Realm.getDefaultInstance()
        val mIngredientListView = findViewById<ListView>(R.id.ingredientListView)
        val addedIngredient: String = intent.getStringExtra(EXTRA_MESSAGE)
        Toast.makeText( this@FoodActivity, "$addedIngredient added", Toast.LENGTH_LONG).show()

        val realmIngredients = realm.where<Ingredient>().findAllAsync()
        val ingredientNames: MutableList<String> = mutableListOf()

        for (ingredient in realmIngredients) {
            ingredientNames.add(ingredient.name.toString())
        }
        Log.d(TAG, "ingredientList: $ingredientNames")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredientNames)
        mIngredientListView.setAdapter(adapter)

        mIngredientListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val ingredient = (view as TextView).text.toString()
                Toast.makeText(this@FoodActivity, ingredient, Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        val TAG = MainActivity::class.java!!.getSimpleName()
    }
}
