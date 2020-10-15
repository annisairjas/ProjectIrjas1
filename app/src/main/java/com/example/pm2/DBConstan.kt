package com.example.pm2

class DBConstan {
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "StoreDatabase.db"
        val TABLE_NAME = "store_table"
        val ID = "id"
        val NAME = "name"
        val PRICE = "price"
        val STOCK = "stock"

        val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT , $PRICE INTEGER , $STOCK INTEGER)"
        val QUERY_UPGRADE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}