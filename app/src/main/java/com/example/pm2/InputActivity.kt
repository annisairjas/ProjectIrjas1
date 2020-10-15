package com.example.pm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    private var helper: DatabaseHandler? = null
    private var isAdd: Boolean = false
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)



        addbutton.setOnClickListener {
            val nama = addName.text.toString()
            val harga = addPrice.text.toString()
            val stok = addStock.text.toString()

            if (nama.isEmpty()) {
                return@setOnClickListener
            }

            if (harga.isEmpty()) {
                return@setOnClickListener
            }

            if (stok.isEmpty()) {
                return@setOnClickListener
            }


            val data = DataList()
            data.Name = nama
            data.Price = harga.toInt()
            data.Stock = stok.toInt()

            val stat = DatabaseHandler.addItem(data)

            if (stat > 0) {

                addName.text.clear()
                addPrice.text.clear()
                addStock.text.clear()

                addPrice.clearFocus()
                addName.clearFocus()
                addStock.clearFocus()

                Toast.makeText(this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show()
            }

        }



        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Input Data Barang"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseHandler.closeDatabase()
    }
}