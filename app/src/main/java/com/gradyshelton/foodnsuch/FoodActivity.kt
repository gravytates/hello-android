package com.gradyshelton.foodnsuch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    private var id = 1
    private var starterIngredients: MutableList<String> = mutableListOf("Avocado", "Garlic", "Lime", "Salt", "Pepper", "Onion", "Chicken", "Beef", "Cheese", "Red Wine", "Basil", "Whiskey", "Potato", "Tomato", "Lettuce")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        val realm = Realm.getDefaultInstance()
        val mIngredientListView = findViewById<ListView>(R.id.ingredientListView)
        val addedIngredient: String = intent.getStringExtra(EXTRA_MESSAGE)

        for (i in starterIngredients) {
            realm.executeTransaction(){
                val ingredient: Ingredient = realm.createObject(Ingredient::class.java, id.toString())
                ingredient.name = i
                ingredient.price = 1.5
            }
            id++
        }
        if (addedIngredient != "") {
            realm.executeTransaction(){
                val ingredient: Ingredient = realm.createObject(Ingredient::class.java, id.toString())
                ingredient.name = addedIngredient
                ingredient.price = 1.5
            }
            id++
        }

        val realmIngredients = realm.where<Ingredient>().findAllAsync()

        var ingredientNames: MutableList<String>? = null
        for (i in realmIngredients) {
            ingredientNames?.add(i.name.toString())
        }
        if (ingredientNames != null) {
            val nonNullIngredientNames = ingredientNames
            val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, nonNullIngredientNames)
            mIngredientListView.setAdapter(adapter)
        }


        mIngredientListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val ingredient = (view as TextView).text.toString()
                Toast.makeText(this@FoodActivity, ingredient, Toast.LENGTH_LONG).show()
            }
        })
    }
}
