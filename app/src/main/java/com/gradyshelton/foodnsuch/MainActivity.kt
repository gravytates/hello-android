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

const val EXTRA_MESSAGE = "com.gradyshelton.foodnsuch.MESSAGE"

class MainActivity : AppCompatActivity() {
    var realm : Realm? = null
    var results: RealmResults<Ingredient>? = null
    private var mFindFoodButton: Button? = null
    private var mFoodEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .name("foodnsuch.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(realmConfig)

        val realm = Realm.getDefaultInstance()
        var results = realm.where<Ingredient>().findAllAsync()
        results.addChangeListener(RealmChangeListener { element ->

            })

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
            intent.putExtra(EXTRA_MESSAGE, food)
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
        val TAG = MainActivity::class.java!!.getSimpleName()
    }

}



