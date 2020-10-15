package com.example.pm2

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize

data class DataList (var Id: Int, var Name: String, var Price: Int, var Stock: Int) : Parcelable{
    constructor() : this(0, "", 0, 0)
}