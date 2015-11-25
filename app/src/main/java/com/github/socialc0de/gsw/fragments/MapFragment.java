package com.github.socialc0de.gsw.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.socialc0de.gsw.R;
import com.melnykov.fab.FloatingActionButton;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/**
 * Created by patricebecker on 20/11/15.
 */

public class MapFragment extends Fragment implements View.OnClickListener{
    private MapView mMapView;
    private MapController mMapController;
    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private GeoPoint startPoint;
    private FloatingActionButton floatingActionButton;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.filterButton);
        floatingActionButton.setOnClickListener(this);

        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setMultiTouchControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(13);
        startPoint = new GeoPoint(48.13, -1.63);
        mMapController.setCenter(startPoint);

        mScaleBarOverlay = new ScaleBarOverlay(getContext());
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2, 10);
        mMapView.getOverlays().add(this.mScaleBarOverlay);



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

        RadiusMarkerClusterer poiMarkers = new RadiusMarkerClusterer(getActivity());
        Drawable clusterIconD = getResources().getDrawable(R.drawable.fr);
        Bitmap clusterIcon = ((BitmapDrawable)clusterIconD).getBitmap();
        poiMarkers.setIcon(clusterIcon);

        Drawable poiIcon = getResources().getDrawable(R.drawable.de);
        for (int i = 0; i < waypoints.size(); i++){
            Marker poiMarker = new Marker(mMapView);
            poiMarker.setTitle("SAMPLE MARKER");
            poiMarker.setSnippet("SAMPLE MARKER DESCRIPTION");
            poiMarker.setPosition(waypoints.get(i));
            poiMarker.setIcon(poiIcon);
            poiMarkers.add(poiMarker);
        }
        mMapView.getOverlays().add(poiMarkers);
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
                        /**
                         * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected check box to actually be selected.
                         * See the limited multi choice dialog example in the sample project for details.
                         **/
                        return true;
                    }
                })
                .positiveText(R.string.done)
                .show();
    }
}
