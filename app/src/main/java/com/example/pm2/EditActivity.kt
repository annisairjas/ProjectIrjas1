package com.example.pm2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    var dataBarang = DataList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        bindView()

        editbutton.setOnClickListener {

            val nama = editname.text.toString()
            val harga = editprice.text.toString()
            val stok = editstock.text.toString()

            if (nama.isEmpty()) {

                return@setOnClickListener
            }

            if (harga.isEmpty()) {

                return@setOnClickListener
            }

            if (stok.isEmpty()) {

                return@setOnClickListener
            }


            dataBarang.Name = nama
            dataBarang.Price = harga.toInt()
            dataBarang.Stock = stok.toInt()

            val stat = DatabaseHandler.updateItems(dataBarang)

            if (stat > 0) {
                val bind = Bundle()
                bind.putParcelable("DATA", dataBarang)

                val intent = Intent()
                intent.putExtras(bind)

                setResult(Activity.RESULT_OK, intent)

                Toast.makeText(this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show()
            }
        }

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Edit Barang"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


    }

    private fun bindView() {
        val bind = intent.extras
        dataBarang = bind!!.getParcelable("DATA")!!

        editname.setText(dataBarang.Name)
        editprice.setText(dataBarang.Price.toString())
        editstock.setText(dataBarang.Stock.toString())


    }
    override fun onDestroy() {
        super.onDestroy()

        DatabaseHandler.closeDatabase()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}