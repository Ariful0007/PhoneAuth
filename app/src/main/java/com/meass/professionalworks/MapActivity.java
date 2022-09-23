package com.meass.professionalworks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.common.collect.Maps;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.android.SphericalUtil;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener{

GoogleMap gMap;
GoogleApiClient googleApiClient;
LocationRequest locationRequest;
Location myLocation;
Marker myLocationMarker;
EditText search,search_2;
Button seerach_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        search=findViewById(R.id.search);
        seerach_to=findViewById(R.id.seerach_to);
        search_2=findViewById(R.id.search_2);
        SupportMapFragment supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
        seerach_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(search.getText().toString())||TextUtils.isEmpty(search_2.getText().toString())) {
                }
                else {
                    supportMapFragment.getMapAsync(MapActivity.this);
                }
             /*
                String location=search.getText().toString();
                String location_second=search_2.getText().toString();
                List<Address>locationAdress=null;
                List<Address>addresseslocation=null;
                if ((location!=null || !location.equals(""))
                && (location_second!=null || !location_second.equals(""))) {
                    Geocoder geocoder=new Geocoder(MapActivity.this);
                    try {
                        locationAdress=geocoder.getFromLocationName(location,1);
                        addresseslocation=geocoder.getFromLocationName(location_second,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //0==1
                    Address address=locationAdress.get(0);
                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                    gMap.addMarker(new MarkerOptions().title(location).position(latLng));
                    gMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    gMap.moveCamera(CameraUpdateFactory.zoomTo(11));
                    //second
                    Address address1=addresseslocation.get(0);
                    LatLng loating2=new LatLng(address1.getLatitude(),address1.getLongitude());
                    gMap.addMarker(new MarkerOptions().title(location_second).position(loating2));
                    gMap.animateCamera(CameraUpdateFactory.newLatLng(loating2));
                    gMap.moveCamera(CameraUpdateFactory.zoomTo(11));
                    double distance= SphericalUtil.computeDistanceBetween(latLng,loating2);
                    //10000122122452 M
                    double totaldistance=distance/1000;
                    double bywalking=(totaldistance*10)/60;
                    Toast.makeText(MapActivity.this, ""+totaldistance, Toast.LENGTH_SHORT).show();



                }
              */

            }
        });

    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    LocationManager locationManager;
    String c_latitude, c_longitude;
    //
    private static final int REQUEST_LOCATION = 1;
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MapActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                c_latitude = String.valueOf(lat);
                c_longitude = String.valueOf(longi);
                // showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //buildGoogleApiClient();
            gMap.setMyLocationEnabled(true);
            gMap.setTrafficEnabled(true);



        }
        gMap.getUiSettings().setZoomControlsEnabled(true);
       LatLng madrid =new LatLng(Double.parseDouble(c_latitude), Double.parseDouble(c_longitude));

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 10));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(madrid, 12.0f));
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(Double.parseDouble(c_latitude), Double.parseDouble(c_longitude)))
                .title("Current Location");
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(marker);
        /*
        LatLng latLng=new LatLng(-35,151);
        gMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
         */
       /*
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
                    PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();

                gMap.setMyLocationEnabled(true);
            }
            else {
                buildGoogleApiClient();
                gMap.setMyLocationEnabled(true);
            }
        }
        */
        if (TextUtils.isEmpty(search.getText().toString())|| TextUtils.isEmpty(search_2.getText().toString())) {
        }
        else {
            String location=search.getText().toString();
            String location_second=search_2.getText().toString();
            List<Address>locationAdress=null;
            List<Address>addresseslocation=null;
            if ((location!=null || !location.equals(""))
                    && (location_second!=null || !location_second.equals(""))) {
                Geocoder geocoder=new Geocoder(MapActivity.this);
                try {
                    locationAdress=geocoder.getFromLocationName(location,1);
                    addresseslocation=geocoder.getFromLocationName(location_second,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //0==1
                Address address=locationAdress.get(0);
                LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());

                Marker melbourne = gMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Start Point"));
                melbourne.showInfoWindow();
                //second
                Address address1=addresseslocation.get(0);
                LatLng loating2=new LatLng(address1.getLatitude(),address1.getLongitude());
                Marker sydne = gMap.addMarker(new MarkerOptions()
                        .position(loating2)
                        .title("End Point"));
                melbourne.showInfoWindow();
                LatLng zaragoza = new LatLng(41.648823,-0.889085);

                //Define list to get all latlng for the route
                List<LatLng> path = new ArrayList();
                gMap.addPolyline(new PolylineOptions().add(latLng,loating2)
                        .width(5)
                        .color(Color.RED)
                        .geodesic(true));
                gMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                gMap.moveCamera(CameraUpdateFactory.zoomTo(12));

//
                //Execute Directions API request
                GeoApiContext context = new GeoApiContext.Builder()
                        .apiKey("AIzaSyBrPt88vvoPDDn_imh-RzCXl5Ha2F2LYig")
                        .build();
                DirectionsApiRequest req = DirectionsApi.getDirections(context, String.valueOf(latLng), String.valueOf(loating2));
                try {
                    DirectionsResult res = req.await();

                    //Loop through legs and steps to get encoded polylines of each step
                    if (res.routes != null && res.routes.length > 0) {
                        DirectionsRoute route = res.routes[0];

                        if (route.legs !=null) {
                            for(int i=0; i<route.legs.length; i++) {
                                DirectionsLeg leg = route.legs[i];
                                if (leg.steps != null) {
                                    for (int j=0; j<leg.steps.length;j++){
                                        DirectionsStep step = leg.steps[j];
                                        if (step.steps != null && step.steps.length >0) {
                                            for (int k=0; k<step.steps.length;k++){
                                                DirectionsStep step1 = step.steps[k];
                                                EncodedPolyline points1 = step1.polyline;
                                                if (points1 != null) {
                                                    //Decode polyline and add points to list of route coordinates
                                                    List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                    for (com.google.maps.model.LatLng coord1 : coords1) {
                                                        path.add(new LatLng(coord1.lat, coord1.lng));
                                                    }
                                                }
                                            }
                                        } else {
                                            EncodedPolyline points = step.polyline;
                                            if (points != null) {
                                                //Decode polyline and add points to list of route coordinates
                                                List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                for (com.google.maps.model.LatLng coord : coords) {
                                                    path.add(new LatLng(coord.lat, coord.lng));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch(Exception ex) {
                    //  Log.e(TAG, ex.getLocalizedMessage());
                }


                if (path.size() > 0) {
                    PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.RED).width(5);
                    gMap.addPolyline(opts);
                }

                gMap.getUiSettings().setZoomControlsEnabled(true);

                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));


            }
        }



    }
protected synchronized void buildGoogleApiClient(){
    /*
        googleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    googleApiClient.connect();
     */


}
    @Override
    public void onConnected(@Nullable Bundle bundle) {
       /*
        locationRequest=new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED) {
          LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }
        */
                //0 1 -1


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
  /*
        myLocation=location;
        if (myLocationMarker!=null) {
            myLocationMarker.remove();
        }
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        myLocationMarker=gMap.addMarker(markerOptions);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        if (googleApiClient!=null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }

   */

    }
}