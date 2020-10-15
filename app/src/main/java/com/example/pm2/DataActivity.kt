package com.example.pm2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity(), OnItemClickListener {

    private var positionStats = 0
    private var dataDaftarBarang: MutableList<DataList> = ArrayList()
    lateinit private var adapterDaftarBarang: DataAdapter
    var recyclerView: RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)


        dataDaftarBarang = DatabaseHandler.getAllitemData()

        adapterDaftarBarang = DataAdapter(dataDaftarBarang, this, applicationContext)

        recyclerView = findViewById(R.id.cardlist)

        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView)?.layoutManager = linearLayoutManager

        menuItem.setHasFixedSize(true)
        menuItem.layoutManager = LinearLayoutManager(this)
        menuItem.adapter = adapterDaftarBarang

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Data Barang"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        button.setOnClickListener {
            val intent = Intent(this@DataActivity, InputActivity::class.java)
            intent.putExtra("ADD", false)

            startActivity(intent)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                val dataBarang: DataList? = data.extras?.getParcelable("DATA")
                if (dataBarang != null) {
                    dataDaftarBarang[positionStats] = dataBarang
                }
                adapterDaftarBarang.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseHandler.closeDatabase()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun dataedit (view: View?) {
        val intent2 = Intent(this@DataActivity, EditActivity::class.java)
        startActivity(intent2)
    }

    override fun onClick(data: DataList, position: Int) {
        TODO("Not yet implemented")
    }

}

