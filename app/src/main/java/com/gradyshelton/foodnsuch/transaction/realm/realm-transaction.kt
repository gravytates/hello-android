package com.gradyshelton.foodnsuch.transaction.realm

import io.realm.Realm
import io.realm.Realm.Transaction
import com.gradyshelton.foodnsuch.model.realm.Ingredient

open class IngredientTransaction : Transaction {
//store a datum
    override fun execute(realm: Realm?) {
        Realm.getDefaultInstance().use { r ->
            r.executeTransaction { realm ->
                realm.copyToRealmOrUpdate(Ingredient().apply {
                    id = 1
                    name = "salt"
                    price = 1.5
                })
            }
        }
    }
}
