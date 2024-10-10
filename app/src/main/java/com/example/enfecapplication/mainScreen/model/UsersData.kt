package com.example.enfecapplication.mainScreen.model
import com.google.gson.annotations.SerializedName


data class UsersData(@SerializedName("id"   ) var id   : Int?    = 0,
                     @SerializedName("address"   ) var address   : Address?    = Address(),
                     @SerializedName("company"   ) var company   : Company?    = Company()
    )
data class Address (@SerializedName("geo"   ) var geo   : Geo?    = Geo())

data class Geo(@SerializedName("lat"   ) var lat   : String?    = "",
               @SerializedName("lng"   ) var lng   : String?    = "")

data class Company(@SerializedName("name"   ) var name   : String?    = "",
                   @SerializedName("catchPhrase"   ) var catchPhrase   : String?    = "",
                   @SerializedName("bs"   ) var bs   : String?    = "")
