package com.example.pm2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log
import com.example.pm2.DataList

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DBConstan.DATABASE_NAME, null, DBConstan.DATABASE_VERSION) {
    companion object {
        private lateinit var INSTANCE: DatabaseHandler
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false

        fun closeDatabase() {
            if (database.isOpen && databaseOpen) {
                database.close()
                databaseOpen = false

                Log.i("Database" , "Database close")
            }
        }

        fun initDatabaseInstance(context: Context): DatabaseHandler {
            INSTANCE = DatabaseHandler(context)
            return INSTANCE
        }

        fun addItem(items: DataList): Long {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database" , "Database Open")
            }
            val contentValues = ContentValues()

            contentValues.put(DBConstan.NAME, items.Name) // EmpModelClass Name
            contentValues.put(DBConstan.STOCK, items.Stock ) // EmpModelClass Phone
            contentValues.put(DBConstan.PRICE, items.Price)
            // Inserting Row

            return database.insert(DBConstan.TABLE_NAME, null, contentValues)

        }

        fun updateItems(items: DataList): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database" , "Database Open")
            }
            val contentValues = ContentValues()
            contentValues.put(DBConstan.ID, items.Id)
            contentValues.put(DBConstan.NAME, items.Name) // EmpModelClass Name
            contentValues.put(DBConstan.STOCK, items.Stock ) // EmpModelClass Phone
            contentValues.put(DBConstan.PRICE, items.Price)

            return database.update(DBConstan.TABLE_NAME, contentValues, "${DBConstan.ID} = ${items.Id}", null)


        }

        //Getting all user list
        fun getAllitemData(): MutableList<DataList> {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database" , "Database Open")
            }
            val itList: MutableList<DataList> = ArrayList()
            val cursor = database.rawQuery("SELECT  * FROM ${DBConstan.TABLE_NAME}", null)

            cursor.use { cur ->
                if(cursor.moveToFirst()) {
                    do {

                        val itemInfo = DataList()
                        itemInfo.Id = cur.getInt(cur.getColumnIndex(DBConstan.ID))
                        itemInfo.Name = cur.getString(cur.getColumnIndex(DBConstan.NAME))
                        itemInfo.Stock = cur.getInt(cur.getColumnIndex(DBConstan.STOCK))
                        itemInfo.Price = cur.getInt(cur.getColumnIndex(DBConstan.PRICE))

                        itList.add(itemInfo)
                    } while ((cursor.moveToNext()))
                }
            }
            return itList
        }

        fun deleteItems(id: Int):Int{
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database" , "Database Open")
            }
            return database.delete(DBConstan.TABLE_NAME, "${DBConstan.ID} = $id", null)
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DBConstan.QUERY_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DBConstan.QUERY_UPGRADE)
        onCreate(db)
    }
}