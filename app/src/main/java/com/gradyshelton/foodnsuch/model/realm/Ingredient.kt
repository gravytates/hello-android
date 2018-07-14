package com.gradyshelton.foodnsuch.model.realm

//import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Ingredient : RealmObject {
    @PrimaryKey
    @Required
    var id: String = ""
    var name: String = ""
    var price: Double = 0.0
    constructor()
}

//open class Ingredient(@SerializedName(value = "name") var name: String,
//                      @SerializedName(value = "price") var price: Double) : RealmObject() {
//    constructor() : this("", 0.0)
//}