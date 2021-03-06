package com.anupam.carfaxapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.anupam.carfaxapp.R
import com.anupam.carfaxapp.adapter.MyCustomRecyclerAdapter
import com.anupam.carfaxapp.data.db.DatabaseService
import com.anupam.carfaxapp.data.entity.Listings
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlin.collections.ArrayList

/*
    Note: This is a simple CarFax App that shows list of cars and its details.
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
    private var carListArray = ArrayList<Listings>()
    private lateinit var database: DatabaseService
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = DatabaseService(this)

        initializeToolBar()
        initializeSwipeRefresh()
        switchBetweenInternetAndCache()

        // database.deleteAllData()
    }

    private fun initializeToolBar() {
        toolbar.title = "CARFAX"
        setSupportActionBar(toolbar)
    }

    private fun initializeSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            switchBetweenInternetAndCache()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun switchBetweenInternetAndCache() {
        carListArray.clear()

        if(isNetworkAvailable(this)){
            Toast.makeText(this, "Loading from server.", Toast.LENGTH_LONG).show()
            initializeRetrofitAndLiveData()

        }else{
            Toast.makeText(this, "No network. Loading from db.", Toast.LENGTH_LONG).show()
            carListArray = database.getAllCars() as ArrayList<Listings>

            initializeRecyclerView()
            progress_circular.visibility = View.GONE
        }
    }

    private fun initializeRetrofitAndLiveData() {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.cars.observe(this, Observer {

          carResponse -> val cars = carResponse.carList

            for(car in cars) {
                carListArray.add(car)
            }

            initializeRecyclerView()
            saveDataToCache(cars)
            progress_circular.visibility = View.GONE
        })
    }

    private fun initializeRecyclerView() {

        linearLayoutManager = LinearLayoutManager(this)
        car_list_recycler_view.layoutManager = linearLayoutManager
        myCustomRecyclerAdapter = MyCustomRecyclerAdapter(carListArray, this)
        car_list_recycler_view.adapter = myCustomRecyclerAdapter
        myCustomRecyclerAdapter.notifyDataSetChanged()

    }

    private fun saveDataToCache(car: List<Listings>) {
        database.insertAll(car)
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}