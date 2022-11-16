package com.example.weatherappavito.presenation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.weatherappavito.R
import com.example.weatherappavito.databinding.FragmentSearchCityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.NonDisposableHandle.parent


class SearchCityFragment : Fragment() {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding
        get() = _binding!!

    //location

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        {
//
//
//            private LocationManager mLocationManager;
//            private LocationListener mLocationListener;
//            private Location mLocation;
//            private TextView mLatitudeTextView, mLongitudeTextView;
//
//            private static final long MINIMUM_DISTANCE_FOR_UPDATES = 10; // в метрах
//            private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // в мс
//
//
//            public void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//
//                setContentView(R.layout.activity_main);
//
//                mLatitudeTextView = (TextView) findViewById (R.id.textViewLatitude);
//                mLongitudeTextView = (TextView) findViewById (R.id.textViewLongitude);
//
//                mLocationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
//
//                Criteria criteria = new Criteria();
//                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//                criteria.setPowerRequirement(Criteria.POWER_LOW);
//                criteria.setAltitudeRequired(false);
//                criteria.setBearingRequired(false);
//                criteria.setCostAllowed(true);
//                String provider = mLocationManager . getBestProvider (criteria, true);
//
//                mLocation = mLocationManager.getLastKnownLocation(provider);
//                mLocationListener = new MyLocationListener ();
//
//                showCurrentLocation(mLocation);
//
//                // Регистрируемся для обновлений
//                mLocationManager.requestLocationUpdates(
//                    provider,
//                    MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_FOR_UPDATES,
//                    mLocationListener
//                );
//            }
//
//            @Override
//            public void onPause() {
//                super.onPause();
//                mLocationManager.removeUpdates(mLocationListener);
//            }
//
//            public void onClick(View v) {
//                showCurrentLocation(mLocation);
//            }
//
//            protected void showCurrentLocation(Location location) {
//                if (location == null) {
//                    mLatitudeTextView.setText("Не работает");
//                    mLongitudeTextView.setText("Не работает");
//                }
//                if (location != null) {
//                    mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
//                    mLongitudeTextView.setText(String.valueOf(location.getLongitude()));
//                }
//
//            }
//
//
//

    }



    companion object{

    }
}