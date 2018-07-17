package com.gradyshelton.foodnsuch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import com.gradyshelton.foodnsuch.model.realm.Ingredient

import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter


class IngredientAdapter internal constructor(data: OrderedRealmCollection<Ingredient>) : RealmBaseAdapter<Ingredient>(data), ListAdapter {

    private val IngredientListActivity IngredientListActivity::class.java.simpleName

    private class ViewHolder {
        internal var ingredientName: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ingredient_list_row, parent, false)
            viewHolder = ViewHolder()
            viewHolder.ingredientName = convertView!!.findViewById(R.id.ingredient_item_name) as TextView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        if (adapterData != null) {
            val ingredient = adapterData!![position]
            viewHolder.ingredientName.setText(ingredient.name)
        }

        return convertView
    }
}