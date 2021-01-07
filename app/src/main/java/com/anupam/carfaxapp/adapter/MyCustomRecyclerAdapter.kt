package com.anupam.carfaxapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anupam.carfaxapp.R
import com.anupam.carfaxapp.data.entity.Listings
import com.anupam.carfaxapp.ui.DetailsActivity
import kotlinx.android.synthetic.main.custom_car_list_view.view.*

/*
    By: Anupam Poudel 2020
    This class is an adapter for recycler view in main activity
 */

class MyCustomRecyclerAdapter(private val cars: ArrayList<Listings>, private val context: Context):
    RecyclerView.Adapter<MyCustomRecyclerAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_car_list_view, parent, false)

        return ItemHolder(itemView)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val car = cars[position]
        val refinedMile: String
        val priceAndMileage: String

        val carDesc = car.year.toString() + " " + car.make + " "+ car.model + " "+ car.trim
        val address = car.dealer.address + ", "+car.dealer.state

        if(car.mileage /1000 > 1) {
            refinedMile = (car.mileage / 1000).toString()
            priceAndMileage = "$ "+car.listPrice + " | "+refinedMile + "k mi"

        }else if(car.mileage / 1000000 > 1){
            refinedMile = (car.mileage / 1000000).toString()
            priceAndMileage = "$ "+car.listPrice + " | "+refinedMile + "m mi"
        }
        else{
            priceAndMileage = "$ "+car.listPrice + " | "+car.mileage + " mi"
        }

        holder.itemView.textView_price_and_miles_main.text = priceAndMileage
        holder.itemView.textView_carName_main.text = carDesc
        holder.itemView.textView_address_main.text = address

        val phone = car.dealer.phone

        holder.itemView.btn_dial_main.setOnClickListener {
            initiateCallToDealer(phone)
        }

        holder.itemView.cardView_card.setOnClickListener {
            initiateDetailsActivity(car)
        }
    }

    class ItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        override fun onClick(v: View) {
            Log.d("RecyclerView", "testing!")

        }
    }


    private fun initiateCallToDealer(phone: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phone")
        context.startActivity(dialIntent)
    }

    private fun initiateDetailsActivity(car: Listings) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("car_details", car)
       context.startActivity(intent)
    }
}