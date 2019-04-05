package sossi.techlanta.techlantademo;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import sossi.techlanta.techlantademo.model.Event;
import sossi.techlanta.techlantademo.model.EventsManager;

public class WelcomeScreenActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
// Or use LocationManager.GPS_PROVIDER

        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            double lat = lastKnownLocation.getLatitude();
            double longitude = lastKnownLocation.getLongitude();
//            LatLng here = new LatLng(lat, longitude);
            LatLng here = new LatLng(33.7764, -84.3884);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 17));
            List<Event> allEvents = EventsManager.getInstance().events;
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    MarkerData markerData =  (MarkerData) marker.getTag();
                    Event event = markerData.e;
                    if (!markerData.clickedOnce) {
                        markerData.clickedOnce = true;
                    } else {
                        Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                        i.putExtra("event", event);
                        startActivity(i);
                    }
                }
            });
            for (int i = 0; i < allEvents.size(); i++) {
                Event e = allEvents.get(i);
                LatLng ePosition = new LatLng(e.latitude, e.longitude);
                Marker marker = mMap.addMarker(new MarkerOptions().position(ePosition).title(e.name).snippet(e.description));
                MarkerData markerData = new MarkerData(e);
                marker.setTag(markerData);
            }
//            mMap.moveCamera(CameraUpdateFactory.zoomBy(17));

        } catch (SecurityException se) {
            Log.e("SecLocationManager", se.toString());

            Toast.makeText(this, "We need permission to show your current location", Toast.LENGTH_SHORT).show();
        }
    }
}
