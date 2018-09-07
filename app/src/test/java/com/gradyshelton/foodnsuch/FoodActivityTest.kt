package com.gradyshelton.foodnsuch

import android.os.Build
import android.widget.ListView
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config


@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP))
@RunWith(RobolectricGradleTestRunner::class)
class FoodActivityTest {
    private var activity: FoodActivity? = null
    private var mIngredientListView: ListView? = null

    @Before
    fun setup() {
        val activity = Robolectric.setupActivity(FoodActivity::class.java)
        mIngredientListView = activity.findViewById(R.id.ingredientListView)
    }
    
    @Test
    fun ingredientListViewPopulates() {
        assertNotNull(mIngredientListView?.getAdapter())
        assertEquals(mIngredientListView?.getAdapter()?.getCount(), 15)
    }
}
