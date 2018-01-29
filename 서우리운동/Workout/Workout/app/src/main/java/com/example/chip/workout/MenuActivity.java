package com.example.chip.workout;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.chip.workout.R.drawable.marker;

public class MenuActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener
{
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions myLocationMarker;
    MarkerOptions park1;        //  와우근린공원1
    MarkerOptions park2;        //  와우어린이공원
    MarkerOptions park3;        //  와우근린공원2
    int pid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("MainActivity","GoogleMap 객체가 준비됨.");
                map=googleMap;
                LatLng address = new LatLng(37.566535,126.9779691);//초기 위도 경도
                CameraPosition cp = new CameraPosition.Builder().target((address )).zoom((float) 10.75).build();//초기 줌
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

//이하 공원들 마커

                Intent passedIntent = getIntent();
                int[] mypid= passedIntent.getIntArrayExtra("pid");


                for(int i=0; i<mypid.length;i++) {

                    pid=mypid[i];

                    if (i==0 && pid == 1) {
                        if (park1 == null) {
                            park1 = new MarkerOptions();
                            park1.position(new LatLng(37.552122, 126.930235));
                            park1.title("와우근린공원1\n");
                            park1.snippet("자세히보기");
                            park1.icon(BitmapDescriptorFactory.fromResource(marker));
                            map.addMarker(park1);

                        }
                        else {
                            park1.position(new LatLng(37.552122, 126.930235));
                        }


                    }
                    else if (i==1 && pid == 1) {
                        if (park2 == null) {
                            park2 = new MarkerOptions();
                            park2.position(new LatLng(37.548797, 126.925406));
                            park2.title("와우어린이공원\n");
                            park2.snippet("자세히보기");
                            park2.icon(BitmapDescriptorFactory.fromResource(marker));
                            map.addMarker(park2);
                        } else {
                            park2.position(new LatLng(37.548797, 126.925406));
                        }
                    }
                    else if (i==2 && pid == 1) {
                        if (park3 == null) {
                            park3 = new MarkerOptions();
                            park3.position(new LatLng(37.549888, 126.927293));
                            park3.title("와우근린공원2\n");
                            park3.snippet("자세히보기");
                            park3.icon(BitmapDescriptorFactory.fromResource(marker));
                            map.addMarker(park3);
                        } else {
                            park3.position(new LatLng(37.549888, 126.927293));
                        }
                    }
                }

                //이상 공원들 마커

                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        //  Toast.makeText(this, marker.getTitle(),                   //테스트용 토스트
                        //                   Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);   // 공원정보 엑티비티로
                        intent.putExtra("gid", marker.getTitle());      //공원의 이름을 받아서 DetailActivity로 전달
                        startActivityForResult(intent, 102);
                    }
                });

            }

        });


        MapsInitializer.initialize(this);

        requestMyLocation();
 //     map.setOnInfoWindowClickListener(this);
/*
        Button button3=(Button) findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMyLocation();
            }
        });

*/

        Button button2=(Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }

    public void requestMyLocation(){
        long minTime=10000;
        float minDistance=0;


        LocationManager manager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        showCurrentLocation(location);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                }
        );
    }

    public void showCurrentLocation(Location location){
        LatLng curPoint = new LatLng(location.getLatitude(),location.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,13));
        showMyLocationMarker(location);
    }



    private void processIntent(Intent intent, int pid){




    }

    @Override
    protected void onPause() {
        super.onPause();

        if(map!=null){
            map.setMyLocationEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(map!=null){
            map.setMyLocationEnabled(true);
        }
    }




    public void showMyLocationMarker(Location location){

        if(myLocationMarker == null){
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(),location.getLongitude()));
            myLocationMarker.title("내위치\n");
            myLocationMarker.snippet("GPS");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(marker));

            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        }
        else{
            myLocationMarker.position(new LatLng(location.getLatitude(),location.getLongitude()));
        }

        //Toast.makeText(this, this.getTitle(),Toast.LENGTH_SHORT).show();
    //    map.setOnInfoWindowClickListener(this);//마커 클릭 리스너

    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        //  Toast.makeText(this, marker.getTitle(),                   //테스트용 토스트
        //                   Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);   // 공원정보 엑티비티로

        intent.putExtra("gid", marker.getTitle());      //공원의 이름을 받아서 DetailActivity로 전달
        startActivityForResult(intent, 102);
    }



    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


