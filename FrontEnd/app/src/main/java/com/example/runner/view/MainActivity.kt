package com.example.runner.view


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute

import com.example.runner.*
import com.example.runner.R
import com.example.runner.api.ApiInterface
import com.example.runner.databinding.ActivityMainBinding

import com.mapbox.api.directions.v5.models.DirectionsRoute

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.mapbox.maps.MapView


class MainActivity() : AppCompatActivity(), OnMapReadyCallback, PermissionsListener,
    MapboxMap.OnMapClickListener {


    private val REQUEST_CODE_AUTOCOMPLETE = 7171
    private var permissionsManager: PermissionsManager? = null
    private var locationComponent: LocationComponent? = null
    private var currentRoute: DirectionsRoute? = null
    private var navigationMapRoute: NavigationMapRoute? = null
    private val TAG = "DirectionsActivity"
    private val geoJsonSourceLayerId = "GeoJsonSourceLayerId"
    private val symbolIconId = "SymbolIconId"
    private lateinit var binding: ActivityMainBinding
    private var mapboxMap: MapboxMap? = null
    var mapView: MapView? = null
    private var retrofit: Retrofit? = null
    private var retrofitInterface: ApiInterface? = null
    private val BASE_URL = "http://10.0.2.2:3000"


    // private val TAG = MainActivity::class.java.simpleName
    //private val REQUEST_LOCATION_PERMISSION = 1
    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))

        setContentView(R.layout.activity_main)
        setContentView(R.layout.fragment_map)
        mapView = findViewById(R.id.mapView)


        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)



        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit!!.create(ApiInterface::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> replaceFragment(HomeFragment())
                R.id.person -> replaceFragment(ProfileFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
                R.id.Search -> replaceFragment(SearchFragment())

                // R.id.fab -> replaceFragment(SettingsFragment())

                else -> {


                }

            }

            true

        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->


            //Snackbar.make(view, "Here's a maps", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()


            replaceFragment(mapFragment())


        }


    }


    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()


    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }


    override fun onStop() {
        super.onStop()
        mapView?.onStop()

    }


    override fun onDestroy() {
        super.onDestroy()
        mapView?.onLowMemory()

    }


    override fun onMapReady(mapboxMap: MapboxMap) {

        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->


        }


    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {

        TODO("Not yet implemented")
    }

    override fun onPermissionResult(granted: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onMapClick(point: LatLng): Boolean {
        TODO("Not yet implemented")
    }


}








