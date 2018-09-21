package com.gradyshelton.foodnsuch

import android.app.Application
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gradyshelton.foodnsuch.model.realm.Ingredient

import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where
//import sun.misc.MessageUtils.where


const val EXTRA_MESSAGE = "com.gradyshelton.foodnsuch.MESSAGE"

class MainActivity : AppCompatActivity() {
    var realm : Realm? = null
    var results: RealmResults<Ingredient>? = null
    private var mFindFoodButton: Button? = null
    private var mFoodEditText: EditText? = null
    private var mPriceEditText: EditText? = null

    private var starterIngredients: MutableList<String> = mutableListOf("Avocado", "Garlic", "Lime", "Salt", "Pepper", "Onion", "Chicken", "Beef", "Cheese", "Red Wine", "Basil", "Whiskey", "Potato", "Tomato", "Lettuce")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .name("foodnsuch.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(realmConfig)
        val realm: Realm? = Realm.getDefaultInstance()

        for (i in starterIngredients) {
            var id: Int? = realm?.where(Ingredient::class.java)?.max("id")?.toInt()
            if (id != null) {
                id++
            } else {
                id = 1
            }
            realm?.executeTransaction(){
                val ingredient: Ingredient = realm.createObject(Ingredient::class.java, id)
                ingredient.name = i
                ingredient.price = 1.5
            }
        }


        setContentView(R.layout.activity_main)
        val fontPath = "fonts/Roboto-ThinItalic.ttf"
        val mAppNameTextView = findViewById<View>(R.id.appNameTextView) as TextView
        val robotoFont = Typeface.createFromAsset(assets, fontPath)
        mAppNameTextView.typeface = robotoFont

        mFindFoodButton = findViewById<View>(R.id.findFoodButton) as Button
        mFoodEditText = findViewById<View>(R.id.foodEditText) as EditText
        mPriceEditText = findViewById<View>(R.id.priceEditText) as EditText
        mFindFoodButton!!.setOnClickListener {
            val inputFood = mFoodEditText!!.text.toString()
            val inputPrice: Double? = (mPriceEditText!!.text.toString()).toDoubleOrNull()

            if (inputFood != "") {
                //set price
                var price = if (inputPrice != null) { inputPrice } else { 1.5 }
                //hacky id incrementing
                val id: Int? = realm?.where(Ingredient::class.java)?.max("id")?.toInt()
                var idA = 0
                if (id != null) { idA = id }
                idA++
                Log.d(TAG, "id: $id")

                realm?.executeTransaction(){
                    val ingredient: Ingredient = realm.createObject(Ingredient::class.java, idA)
                    ingredient.name = inputFood
                    ingredient.price = price
                }
            }

            val intent = Intent(this@MainActivity, FoodActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, inputFood)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        results?.removeAllChangeListeners()
        results = null
        realm?.close()
        realm = null
    }

    companion object {
        val TAG = MainActivity::class.java.getSimpleName()
    }

}

