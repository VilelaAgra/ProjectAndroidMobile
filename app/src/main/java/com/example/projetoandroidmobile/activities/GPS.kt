package com.example.projetoandroidmobile.activities

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.projetoandroidmobile.R
import com.example.projetoandroidmobile.databinding.GpsComponentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class GPSComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback {

    private val DEFAULT_ZOOM: Float = 15f

    private var mLatitude: Double = -9.653444
    private var mLongitude: Double = -35.700978
    private lateinit var mGoogleMap: GoogleMap

    private val mGpsComponentBinding: GpsComponentBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.gps_component,
        this,
        true
    )

    init {
        initMap()
    }

    private fun setupLatitude(latitude: Double) {
        mLatitude = latitude
    }

    private fun setupLongitude(longitude: Double){
        mLongitude = longitude
    }

    fun initMap() {
        mGpsComponentBinding.gpsMapView.onCreate(null)
        mGpsComponentBinding.gpsMapView.onResume()
        mGpsComponentBinding.gpsMapView.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onMapReady(googleMap: GoogleMap?) {
        MapsInitializer.initialize(Objects.requireNonNull(context))
        if (googleMap != null) {
            mGoogleMap = googleMap
        }
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.addMarker(MarkerOptions().position(LatLng(mLatitude, mLongitude)))
        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    mLatitude,
                    mLongitude
                ), DEFAULT_ZOOM
            )
        )

    }


}