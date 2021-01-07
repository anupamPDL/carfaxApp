package com.anupam.carfaxapp.ui

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.anupam.carfaxapp.R
import com.anupam.carfaxapp.data.entity.Listings
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

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
    ● Button to call the dealer


 */

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeToolBar()
        getMyIntents()
    }

    private fun initializeToolBar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getMyIntents(){
        val receivedObj = intent.extras?.get("car_details") as Listings
        Log.d("Received: ", "$receivedObj")
        initializeDataToView(receivedObj)

    }

    private fun initializeDataToView(receivedObj: Listings?){

        val carDetails = receivedObj?.year.toString() + " " + receivedObj?.make + " "+ receivedObj?.model + " "+ receivedObj?.trim
        textView_carName.text = carDetails

        //: - Price and Mileage--------------------------------
        val mileage = receivedObj?.mileage
        val refinedMile: String
        val priceAndMileage: String

        if (mileage != null) {

            if(mileage / 1000 > 1) {
                refinedMile = (mileage / 1000).toString()
                priceAndMileage = "$ "+ receivedObj.listPrice + " | "+refinedMile + " k mi"
            }
            else if(mileage / 1000000 > 1) {
                refinedMile = (mileage / 1000000).toString()
                priceAndMileage = "$ "+ receivedObj.listPrice + " | "+refinedMile + " m mi"
            }else {
                priceAndMileage = "$ "+ receivedObj.listPrice + " | "+ receivedObj.mileage + " mi"
            }
            textView_price_and_miles.text = priceAndMileage
        }
        //:-----------------------------------------------------

        val combinedAddress = receivedObj?.dealer?.address +", "+ receivedObj?.dealer?.state
        textView_location.text = combinedAddress

        textView_exteriorColor.text = receivedObj?.exteriorColor
        textView_interiorColor.text = receivedObj?.interiorColor
        textView_driveType.text = receivedObj?.drivetype
        textView_transmissionType.text = receivedObj?.transmission
        textView_bodyStyle_type.text = receivedObj?.bodytype
        textView_engineType.text = receivedObj?.engine
        textView_fuelType.text = receivedObj?.fuel

        val phone = receivedObj?.dealer?.phone
        btn_call_dealer.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phone")
            startActivity(dialIntent)
        }

        val photoUrl = receivedObj?.images?.firstPhoto?.large
        val url = if (photoUrl != null) "$photoUrl?w=360" else null //1

        loadImageIntoView(url)
    }

    private fun loadImageIntoView(imageUrl: String?) {
        Glide.with(imageView_car)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.no_imge_placeholder)
                .fallback(R.drawable.placeholder_no_image)
                .into(imageView_car) //8
    }
}