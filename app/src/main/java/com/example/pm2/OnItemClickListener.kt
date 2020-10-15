package com.example.pm2

import com.example.pm2.DataList

interface OnItemClickListener {
    fun onClick(data : DataList , position : Int)
}