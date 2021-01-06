package com.anupam.carfaxapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anupam.carfaxapp.R

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
    ● Call dealer button

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

    Main Activity will load the app and display list of cars.

 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}