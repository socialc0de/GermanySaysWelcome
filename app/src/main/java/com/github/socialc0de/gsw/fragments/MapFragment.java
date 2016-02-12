package com.github.socialc0de.gsw.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.socialc0de.gsw.R;
import com.github.socialc0de.gsw.async.MyAsyncTask;
import com.github.socialc0de.gsw.async.TaskResult;
import com.github.socialc0de.gsw.async.interfaces.AsyncCallBack;
import com.github.socialc0de.gsw.async.interfaces.CustomAsyncTask;
import com.github.socialc0de.gsw.customClasses.MapItem;
import com.melnykov.fab.FloatingActionButton;

import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

/**
 * Created by patricebecker on 20/11/15.
 */

public class MapFragment extends Fragment implements View.OnClickListener {
    private MapView mMapView;
    private MapController mMapController;
    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private GeoPoint startPoint;
    private FloatingActionButton floatingActionButton;
    private String[] filterArray;
    CustomAsyncTask filterTask = new CustomAsyncTask() {

        @Override
        public TaskResult run(TaskResult taskResult) {
            Integer[] which = (Integer[]) taskResult.getData();

            Log.d("AsyncTask called", "");
            ArrayList<ArrayList<MapItem>> retrievedData = new ArrayList<ArrayList<MapItem>>();
            ArrayList<MapItem> authorities = new ArrayList<MapItem>();
            ArrayList<MapItem> wifihotspots = new ArrayList<MapItem>();
            ArrayList<MapItem> hospitals = new ArrayList<MapItem>();
            ArrayList<MapItem> helpcenters = new ArrayList<MapItem>();

            for (int i = 0; i < which.length; i++) {
                String currentValue = filterArray[i];
                Log.d("currentValue = ", currentValue);
                switch (i) {
                    case 0:
                        authorities.add(new MapItem(50.102130, 8.637670, "Ausländeramt Frankfurt", "Detail Description", R.drawable.ic_info_outline_white));
                        retrievedData.add(authorities);
                        break;
                    case 1:
                        wifihotspots.add(new MapItem(50.102150, 8.637310, "Freifunk Frankfurt", "Detail Description", R.drawable.ic_network_wifi_white));
                        retrievedData.add(wifihotspots);
                        break;
                    case 2:
                        hospitals.add(new MapItem(50.102200, 8.636540, "Uniklinik Frankfurt", "Detail Description", R.drawable.ic_local_hospital_white_24dp));
                        retrievedData.add(hospitals);
                        break;
                    case 3:
                        helpcenters.add(new MapItem(50.102310, 8.635620, "Refugee Center Frankfurt", "Detail Description", R.drawable.ic_help_white_24dp));
                        retrievedData.add(helpcenters);
                        break;
                    default:
                        break;
                }
            }
            taskResult.setData(retrievedData);
            taskResult.setError(false);
            return taskResult;
        }
    };
    private ArrayList<ArrayList<MapItem>> retrievedData;
    private String TAG = "MapFragmentTag: ";
    AsyncCallBack callBack = new AsyncCallBack() {
        @Override
        public void done(TaskResult data) {
            retrievedData = (ArrayList<ArrayList<MapItem>>) data.getData();

            RadiusMarkerClusterer poiMarkers = new RadiusMarkerClusterer(getActivity());
            Drawable clusterIconD = getResources().getDrawable(R.drawable.clustericon);
            Bitmap clusterIcon = ((BitmapDrawable) clusterIconD).getBitmap();
            poiMarkers.setIcon(clusterIcon);

            Drawable authority = getResources().getDrawable(R.drawable.ic_local_hospital_white_24dp);
            Drawable hospital = getResources().getDrawable(R.drawable.ic_local_hospital_white_24dp);
            Drawable wifihotpots = getResources().getDrawable(R.drawable.ic_local_hospital_white_24dp);
            Drawable helpcenters = getResources().getDrawable(R.drawable.ic_local_hospital_white_24dp);

            //hospital.setTint(getResources().getColor(R.color.accentColor));

            ArrayList<Drawable> iconList = new ArrayList<Drawable>();
            iconList.add(hospital);
            iconList.add(authority);
            iconList.add(wifihotpots);
            iconList.add(helpcenters);

            for (int i = 0; i < retrievedData.size(); i++) {
                for (int t = 0; t < retrievedData.get(i).size(); t++) {
                    MapItem mapItem = retrievedData.get(i).get(t);
                    GeoPoint mapPoint = new GeoPoint(mapItem.getLatitude(), mapItem.getLongitude());
                    Log.d(TAG, "i: " + i + " t: " + t + " retrievedData: " + mapItem.toString());
                    Marker mapMarker = new Marker(mMapView);
                    mapMarker.setPosition(mapPoint);
                    mapMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    mapMarker.setTitle(mapItem.getTitle());
                    mapMarker.setSubDescription(mapItem.getDescription());

                    mapMarker.setIcon(iconList.get(i));
                    poiMarkers.add(mapMarker);
                }
            }
            Log.d(TAG, " retrievedData loaded");
            mMapView.getOverlays().add(poiMarkers);
            mMapView.invalidate();
        }

        @Override
        public void error(TaskResult data) {

        }
    };

    public MapFragment() {
        // Required empty public constructor
    }

    /*
    AsyncCallBack callBack = new AsyncCallBack() {
        @Override
        public void done(TaskResult data) {

        }

        @Override
        public void error(TaskResult data) {

        }
    };

    CustomAsyncTask filterTask = new CustomAsyncTask() {

        @Override
        public TaskResult run(TaskResult taskResult) {

            done oder error im CallBack --> taskResult.setError(false);
            daten setzen --> taskResult.setData(retrievedData);
            return taskResult;
        }

    };
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);


        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.filterButton);
        floatingActionButton.setOnClickListener(this);

        filterArray = getResources().getStringArray(R.array.filterChooser);

        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setMultiTouchControls(true);
        mMapView.setBuiltInZoomControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(13);


        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Log.d(""+location.getLongitude(),""+location.getLatitude());
        if (location != null) startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

        mScaleBarOverlay = new ScaleBarOverlay(getContext());
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2, 10);
        mMapView.getOverlays().add(this.mScaleBarOverlay);

        mMapController.setCenter(startPoint);

        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        startMarker.setTitle("Start point");
        mMapView.getOverlays().add(startMarker);
        mMapView.invalidate();

        /*
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        for (double i = 0.01; i<0.2; i = i+0.01){
            GeoPoint endPoint = new GeoPoint(48.13+i, -1.63-i);
            Log.d("GeoPoint: ","Lat: "+endPoint.getLatitude()+"Long: "+endPoint.getLongitude());
            waypoints.add(endPoint);
        }


        mMapView.invalidate();

        */

        return view;
    }

    @Override
    public void onClick(View v) {
        new MaterialDialog.Builder(getContext())
                .title(R.string.filter)
                .items(R.array.filterChooser)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        for (int i = 0; i < which.length; i++)
                            Log.d("Selected items", " : " + which[i]);

                        MyAsyncTask asyncTask = new MyAsyncTask(filterTask, which, callBack);
                        asyncTask.execute();

                        return true;
                    }
                })
                .positiveText(R.string.done)
                .show();
    }
}
