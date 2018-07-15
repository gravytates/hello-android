package com.gradyshelton.foodnsuch.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Ingredient : RealmObject {
    @PrimaryKey
    @Required
    var id: String = ""
    @Required
    var name: String = ""
    var price: Double = 0.0
    constructor()
}
