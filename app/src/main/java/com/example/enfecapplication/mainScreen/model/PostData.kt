package com.example.enfecapplication.mainScreen.model
import com.google.gson.annotations.SerializedName

data class PostData(@SerializedName("userId"   ) var userId   : Int?    = 0,
                    @SerializedName("Id"   ) var Id   : Int?    = 0,
                    @SerializedName("title"   ) var title   : String?    = "",
                    @SerializedName("body"   ) var body   : String?    = "",
                    @SerializedName("geo"   ) var geo   : Geo?    = Geo(),
                    var show   : Int?    = 0,
                    var uID   : Int?    = 0,
                    @SerializedName("company"   ) var company   : Company?    = Company()
    )


