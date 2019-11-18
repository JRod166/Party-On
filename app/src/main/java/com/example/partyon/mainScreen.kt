package com.example.partyon

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class mainScreen : AppCompatActivity() {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fast-sands-11156.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var locationUp :Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()

        val code = findViewById(R.id.partyCode) as EditText
        val name = findViewById(R.id.partyName) as EditText
        val join = findViewById(R.id.joinParty) as Button
        val create = findViewById(R.id.createParty) as Button
        val location = findViewById(R.id.gpsParty) as Button

        val intent = intent
        val id = intent.getStringExtra("id")

        join.setOnClickListener{
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.join(code.text.toString()).execute()
            val body=response.body()
            if(body!!.getStatus() == "200") {
                toast(body.getName().toString())
                val intentParty = Intent(this,party::class.java)
                intentParty.putExtra("id",body.getId())
                intentParty.putExtra("name",body.getName())
                startActivity(intentParty)
            }
        }
        create.setOnClickListener{
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response=mAPIService.create(name.text.toString(),id.toString(),locationUp.latitude.toString(),locationUp.longitude.toString()).execute()
            val body=response.body()
            if(body!!.getStatus() == "200") {
                toast(body.getId().toString())
                val intentParty = Intent(this,party::class.java)
                intentParty.putExtra("id",body.getId())
                intentParty.putExtra("name",body.getName())
                intentParty.putExtra("latitude",body.getLatitude())
                intentParty.putExtra("longitude",body.getLongitude())
                startActivity(intentParty)
            }
        }

        location.setOnClickListener{
            var mAPIService = getRetrofit().create(APIService::class.java)
            val response = mAPIService.closeParties(locationUp.latitude.toString(),locationUp.longitude.toString()).execute()
            val body = response.body()
            if(body!!.getStatus() == "200")
            {
                val intentParties = Intent(this,parties::class.java)
                intentParties.putExtra("latitude",locationUp.latitude.toString())
                intentParties.putExtra("longitude",locationUp.longitude.toString())
                startActivity(intentParties)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        locationUp = location
                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }
}
