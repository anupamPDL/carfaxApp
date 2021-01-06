package com.anupam.carfaxapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anupam.carfaxapp.R

/*
    Note: This is a CarFax App developed for Android Technical Assignment.
    Developed by: Anupam Poudel
    Dated: 2021-01-05

    Details Activity will display the following:
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

 */

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}