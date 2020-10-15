package com.example.pm2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHolder (itemview: View?) : RecyclerView.ViewHolder(itemview!!) {
        private val itemName = itemview?.findViewById(R.id.item_name) as TextView
        private val itemStock = itemview?.findViewById(R.id.item_stock) as TextView
        private val itemPrice = itemview?.findViewById(R.id.item_price) as TextView
        fun bindContent(menuItem: DataList, listener: OnItemClickListener, position: Int) {
            itemStock.text = menuItem.Stock.toString()
            itemName.text = menuItem.Name
            itemPrice.text = "Rp. ${menuItem.Price}"
        }


}