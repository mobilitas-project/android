package com.mobilitas.android

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import android.support.v7.widget.Toolbar
import android.view.View
import com.mobilitas.android.data.RetrofitInitializer
import com.mobilitas.android.house.House
import com.mobilitas.android.job.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    enum class PinType {
        WORK, HOME
    }

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationclient: FusedLocationProviderClient
    private lateinit var mLastLocation: Location
    private lateinit var mLocationCallback: LocationCallback
    private lateinit var mLocationRequest: LocationRequest
    private var mLocationUpdateState = false
    private var mMarkers = HashMap<String, String>()
    private var mMarkersForHouse = HashMap<String, String>()
    private var clickedOnce = false
    private var workButtonClicked = false
    private var homeButtonClicked = false
    private lateinit var jobs: List<Job>

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setBackgroundColor(Color.TRANSPARENT)
        setSupportActionBar(toolbar)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mFusedLocationclient = LocationServices.getFusedLocationProviderClient(this)

        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                mLastLocation = p0!!.lastLocation
            }
        }
        createLocationRequest()
        requestJob()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                mLocationUpdateState = true
                startLocationUpdates()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mFusedLocationclient.removeLocationUpdates(mLocationCallback)
    }

    override fun onResume() {
        super.onResume()
        if (!mLocationUpdateState) {
            startLocationUpdates()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        if (!clickedOnce) {
            val jobId = mMarkers.get(marker?.id)
            if (jobId != null) {
                Log.i("Job ID", jobId)
                for (job in jobs) {
                    if (job.id.equals(jobId)) {
                        clickedOnce = true
                        mMap.clear()
                        placeMarkerOnMap(job, PinType.WORK)
                        for (house in mockHouse()) {
                            if (MapUtils.distanceBetween(job.lat, job.lng, house.lat, house.lng) <= 1) {
                                placeMarkerOnMap(house, PinType.HOME)
                            }
                        }
                    }
                }
            } else {
                val houseId = mMarkersForHouse.get(marker?.id)
                Log.i("House ID", houseId)
            }
        } else {
            for (job in jobs) {
                placeMarkerOnMap(job, PinType.WORK)
            }
            for (house in mockHouse()) {
                placeMarkerOnMap(house, PinType.HOME)
            }
            clickedOnce = false
        }
        return false
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap.isMyLocationEnabled = true
        mFusedLocationclient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                mLastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17.0f))
            }
        }
    }

    private fun placeMarkerOnMap(location: Any, pinType: PinType) {
        when (pinType) {
            PinType.WORK -> {
                val job = location as Job
                val latLng = LatLng(job.lat, job.lng)
                val markerOptions = MarkerOptions()
                        .position(latLng)
                        .title(job.title)
                        .snippet(job.activities)
                markerOptions.icon(MapUtils.bitmapDescriptorFromVector(this, R.drawable.ic_pin_blue_work))
                val marker = mMap.addMarker(markerOptions)
                mMarkers.put(marker.id, job.id)
            }
            PinType.HOME -> {
                val house = location as House
                val latLng = LatLng(house.lat, house.lng)
                val markerOptions = MarkerOptions()
                        .position(latLng)
                        .title(house.name)
                        .snippet(house.address)
                markerOptions.icon(MapUtils.bitmapDescriptorFromVector(this, R.drawable.ic_pin_pink_home))
                val marker = mMap.addMarker(markerOptions)
                mMarkersForHouse.put(marker.id, house.id)
            }
            else -> {
                return
            }
        }
    }

    private fun getAddress(latlng: LatLng): String {
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(latlng.latitude, latlng.longitude, 1)
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mFusedLocationclient.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 10000
        mLocationRequest.fastestInterval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            mLocationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(this@MapsActivity, REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error
                }
            }
        }
    }

    fun workButtonClicked(view: View) {
        val floatingActionButton = view as FloatingActionButton
        if (!workButtonClicked) {
            floatingActionButton.setBackgroundColor(resources.getColor(android.R.color.white))
            floatingActionButton.setImageDrawable(resources.getDrawable(R.drawable.ic_close))
            workButtonClicked = true
            mMap.clear()
            for (job in jobs) {
                placeMarkerOnMap(job, PinType.WORK)
            }
        } else {
            floatingActionButton.setBackgroundColor(resources.getColor(R.color.blue))
            floatingActionButton.setImageDrawable(resources.getDrawable(R.drawable.ic_filter_list))
            workButtonClicked = false
            for (house in mockHouse()) {
                placeMarkerOnMap(house, PinType.HOME)
            }
        }
    }

    fun homeButtonClicked(view: View) {
        val floatingActionButton = view as FloatingActionButton
        if (!homeButtonClicked) {
            floatingActionButton.setBackgroundColor(resources.getColor(android.R.color.white))
            floatingActionButton.setImageDrawable(resources.getDrawable(R.drawable.ic_close))
            homeButtonClicked = true
            mMap.clear()
            for (house in mockHouse()) {
                placeMarkerOnMap(house, PinType.HOME)
            }
        } else {
            floatingActionButton.setBackgroundColor(resources.getColor(R.color.pink))
            floatingActionButton.setImageDrawable(resources.getDrawable(R.drawable.ic_filter_list))
            homeButtonClicked = false
            for (job in jobs) {
                placeMarkerOnMap(job, PinType.WORK)
            }
        }
    }

    private fun requestJob() {
        val call = RetrofitInitializer().jobService().fetchJobs()
        call.enqueue(object : Callback<List<Job>> {
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                response.body().let {
                    if (it != null) {
                        jobs = it
                        for (job in jobs) {
                            placeMarkerOnMap(job, PinType.WORK)
                        }
                        for (house in mockHouse()) {
                            placeMarkerOnMap(house, PinType.HOME)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Job>>, t: Throwable?) {
                Log.e("OnFailure error", t?.message)
            }
        })
    }

    fun mockHouse(): List<House> {
        val list = mutableListOf<House>()
        list.add(House("0", -23.6048819, -46.6957957, "Apartamento com 1 dorm, 55m²", 4724.00, "Rua Sansão Alves dos Santos, Brooklin, São Paulo"))
        list.add(House("1", -23.6080094, -46.6938952, "Apartamento com 3 dorms, 136m²", 7517.00, "Rua Arandu, Brooklin, São Paulo"))
        list.add(House("2", -23.6129957, -46.6938146, "Apartamento com 1 dorm, 45m²", 3980.00, "Rua Castilho, Brooklin, São Paulo"))
        return list.toList()
    }
}