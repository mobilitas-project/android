package com.mobilitas.android

import android.content.Context
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.maps.model.BitmapDescriptor

object MapUtils {

    fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    fun distanceBetween(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val locationA = Location("Point A")
        locationA.latitude = lat1
        locationA.longitude = lng1

        val locationB = Location("Point B")
        locationB.latitude = lat1
        locationB.longitude = lng2

        val dist = (locationA.distanceTo(locationB) / 1000).toDouble()
        Log.i("Distance of", dist.toString())
        return dist
    }
}