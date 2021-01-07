package com.anupam.carfaxapp.ui

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.anupam.carfaxapp.R
import com.anupam.carfaxapp.adapter.MyCustomRecyclerAdapter
import com.anupam.carfaxapp.data.entity.CarList
import com.anupam.carfaxapp.data.entity.Listings
import com.anupam.carfaxapp.retrofit.GetCarService
import com.anupam.carfaxapp.retrofit.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

/*
    Note: This is a CarFax App developed for Android Technical Assignment.
    Developed by: Anupam Poudel
    Dated: 2021-01-05

    Summary:
    CarFax app will connect to used car listings
    backend in order to retrieve and display car listings data.

    Basic Function:
    1. Main Screen

    CarFax App will display a list of vehicles with the following information:
    ● Vehicle photo
    ● Year, make, model, trim
    ● Price
    ● Mileage
    ● Location
    ● Button to call dealer

    Tapping on the card will take the user to details screen.
    Likewise, tapping “Call dealer button” will initiate a phone call
    towards the dealer.

    2. Details Screen
    The details screen of selected vehicle will display:
    ● Vehicle photo
    ● Year, make, model, trim
    ● Price
    ● Mileage
    ● Call dealer button
    ● Vehicle info
    ● Location
    ● Interior color
    ● Exterior color
    ● Drive type
    ● Transmission
    ● Engine
    ● Body style

    Main Activity will load the app, pull json data from the API and display list of cars.

 */

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var myCustomRecyclerAdapter: MyCustomRecyclerAdapter
    private val carListArray = ArrayList<Listings>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeToolBar()
        initializeRetrofit()

    }

    private fun initializeToolBar() {
        toolbar.title = "CARFAX"
        setSupportActionBar(toolbar)
    }

    private fun initializeRetrofit() {

        val service = RetrofitClientInstance.carList?.create(GetCarService::class.java)
        val call = service?.getAllCarList()

        call?.enqueue(object : Callback<CarList> {

            override fun onFailure(call: Call<CarList>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong. Server says: "+t.localizedMessage, Toast.LENGTH_LONG).show()
                progress_circular.visibility = View.GONE
            }

            override fun onResponse(call: Call<CarList>, response: Response<CarList>) {

                val body = response.body()
                val cars = body?.carList

                for(car in cars!!) {
                    carListArray.add(car)
                }

                if(response.isSuccessful){
                    initializeRecyclerView()
                    progress_circular.visibility = View.GONE

                }else{
                    Toast.makeText(applicationContext, "Something went wrong. Server says: "+response.message(), Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun initializeRecyclerView() {

        linearLayoutManager = LinearLayoutManager(this)
        car_list_recycler_view.layoutManager = linearLayoutManager
        myCustomRecyclerAdapter = MyCustomRecyclerAdapter(carListArray, this)
        car_list_recycler_view.adapter = myCustomRecyclerAdapter
        myCustomRecyclerAdapter.notifyDataSetChanged()

    }
}