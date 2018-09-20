package com.gradyshelton.foodnsuch.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Ingredient : RealmObject {
    @PrimaryKey
    @Required
    var id: Int? = null
    @Required
    var name: String? = null
    var price: Double? = null
    constructor()
}
