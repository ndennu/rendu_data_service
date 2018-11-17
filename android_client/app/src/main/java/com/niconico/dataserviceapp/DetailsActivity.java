package com.niconico.dataserviceapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.niconico.dataserviceapp.Adapter.StoreAvailabilityAdapter;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.StoreAvailability;
import com.niconico.dataserviceapp.WebServices.IAPIListener;
import com.niconico.dataserviceapp.WebServices.SearchStoreParams;
import com.niconico.dataserviceapp.WebServices.StoreProvider;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radiusSpinner)
    MaterialSpinner radiusSpinner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MapFragment mapFragment;
    Map map;

    private int selectedRadius = 1;
    private long itemId;

    private List<StoreAvailability> storeList;
    private StoreAvailabilityAdapter storeAvailabilityAdapter;

    private static final int LOCATION_PERMISSION_REQUEST = 1736;

    private PositioningManager posManager;
    private boolean paused = false;

    private boolean canLoadStores = true;

    public static String ITEM_ID = "ITEM_ID";
    public static String ITEM_NAME = "ITEM_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.sort_menu);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        Intent intent = getIntent();
        itemId = intent.getLongExtra(ITEM_ID, -1);

        toolbar.setTitle(intent.getStringExtra(ITEM_NAME));

        setupLocation();
        initUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        paused = false;
        if (posManager != null) {
            posManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
        }
    }

    @Override
    public void onPause() {
        if (posManager != null) {
            posManager.stop();
        }
        paused = true;
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (posManager != null) {
            // Cleanup
            posManager.removeListener(positionListener);
        }
        map = null;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sorted_item:
                PopupMenu popup = new PopupMenu(DetailsActivity.this, toolbar);
                popup.setGravity(Gravity.END);
                popup.getMenuInflater().inflate(R.menu.sort_items_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().compareTo(getResources().getString(R.string.a_z)) == 0) {
                            sortListByName();
                        } else if (item.getTitle().toString().compareTo(getResources().getString(R.string.z_a)) == 0) {
                            sortListByNameInvert();
                        } else if (item.getTitle().toString().compareTo(getResources().getString(R.string.distance)) == 0) {
                            sortListByDistance();
                        } else if (item.getTitle().toString().compareTo(getResources().getString(R.string.nb_personnes)) == 0) {
                            sortListByNbPersonnes();
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //getCurrentLocation();
                }
            }
        }
    }


    // Define positioning listener
    private PositioningManager.OnPositionChangedListener positionListener = new PositioningManager.OnPositionChangedListener() {

        public void onPositionUpdated(PositioningManager.LocationMethod method, GeoPosition position, boolean isMapMatched) {
            if (!paused) {
                map.setCenter(position.getCoordinate(), Map.Animation.NONE);
                if (canLoadStores) {
                    canLoadStores = false;
                    getNearestStores(position.getCoordinate().getLatitude(), position.getCoordinate().getLongitude());
                }
            }
        }

        public void onPositionFixChanged(PositioningManager.LocationMethod method, PositioningManager.LocationStatus status) {
        }
    };


    /**
     * Initialize UI
     */
    private void initUI() {
        radiusSpinner.setItems(1, 5, 10, 15, 20, 25, 50, 100);
        radiusSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<Integer>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Integer item) {
                selectedRadius = item;
                canLoadStores = true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        storeList = new ArrayList<>();
        storeAvailabilityAdapter = new StoreAvailabilityAdapter(storeList);
        storeAvailabilityAdapter.setListener(new StoreAvailabilityAdapter.Listener() {
            @Override
            public void onClick(StoreAvailability store) {
                //Toast.makeText(DetailsActivity.this, store.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(storeAvailabilityAdapter);

        // initialize the Map Fragment and retrieve the map that is associated to the fragment
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    initializeMap();

                } else {
                    Toast.makeText(
                            DetailsActivity.this,
                            "ERROR: Cannot initialize MapFragment",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * Ask for location permission
     */
    private void setupLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_PERMISSION_REQUEST);
            } else {
                // Request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_PERMISSION_REQUEST);
            }
        } else {
            // Permission has already been granted
        }
    }

    private void initializeMap() {
        map = mapFragment.getMap();

        map.setCenter(new GeoCoordinate(48.8468616, 2.2924879), Map.Animation.NONE);
        map.setZoomLevel(14);

        mapFragment.getMapGesture().addOnGestureListener(new MapGesture.OnGestureListener.OnGestureListenerAdapter() {
            @Override
            public boolean onMapObjectsSelected(List<ViewObject> list) {
                for (ViewObject viewObj : list) {
                    if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                        if (((MapObject) viewObj).getType() == MapObject.Type.MARKER) {
                            // On marker click event
                            ((MapMarker) viewObj).showInfoBubble();
                        }
                    }
                }
                return false;
            }
        });

        PositioningManager.getInstance().start(PositioningManager.LocationMethod.GPS_NETWORK);
        PositioningManager.getInstance().addListener(new WeakReference<>(positionListener));
        map.getPositionIndicator().setVisible(true);
    }

    private void getNearestStores(Double lat, Double lng) {
        StoreProvider.getInstance().searchStores(
                new SearchStoreParams(Double.toString(lat), Double.toString(lng), this.selectedRadius * 1000, this.itemId),
                new IAPIListener<APIResult<ArrayList<StoreAvailability>>>() {
                    @Override
                    public void onSuccess(APIResult<ArrayList<StoreAvailability>> response) {
                        Toast.makeText(
                                DetailsActivity.this,
                                Integer.toString(response.data.size()) + " magasins trouvés",
                                Toast.LENGTH_LONG).show();

                        Image myImage = new Image();
                        try {
                            myImage.setImageResource(R.drawable.pin_map);
                        } catch (IOException e) {
                            finish();
                        }

                        for (int i = 0; i < response.data.size(); i++) {
                            StoreAvailability storeAvailability = response.data.get(i);

                            // Create the MapMarker
                            MapMarker myMapMarker = new MapMarker(
                                    new GeoCoordinate(
                                            Double.parseDouble(storeAvailability.getLatitude()),
                                            Double.parseDouble(storeAvailability.getLongitude())),
                                    myImage);
                            myMapMarker.setTitle(storeAvailability.getName());
                            myMapMarker.setDescription(storeAvailability.getAddress());
                            map.addMapObject(myMapMarker);
                        }

                        storeList.clear();
                        storeList.addAll(response.data);
                        storeAvailabilityAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String s) {
                        Log.e("API_FAIL", s);
                    }
                });
    }

    private void sortListByName() {
        for (int j = 0; j < storeList.size(); j++) {
            for (int i = j + 1; i < storeList.size(); i++) {
                if (storeList.get(i).getName().compareTo(storeList.get(j).getName()) < 0) {
                    StoreAvailability temp = storeList.get(j);
                    storeList.set(j, storeList.get(i));
                    storeList.set(i, temp);
                }
            }
        }
        storeAvailabilityAdapter.notifyDataSetChanged();
    }

    private void sortListByNameInvert() {
        sortListByName();
        Collections.reverse(storeList);
    }

    private void sortListByDistance() {
        int n = storeList.size();
        StoreAvailability temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (storeList.get(j - 1).getDistance() > storeList.get(j).getDistance()) {
                    temp = storeList.get(j - 1);
                    storeList.set(j - 1, storeList.get(j));
                    storeList.set(j, temp);
                }
            }
        }
        storeAvailabilityAdapter.notifyDataSetChanged();
    }

    private void sortListByNbPersonnes() {
        int n = storeList.size();
        StoreAvailability temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (storeList.get(j - 1).getVisitor() > storeList.get(j).getVisitor()) {
                    temp = storeList.get(j - 1);
                    storeList.set(j - 1, storeList.get(j));
                    storeList.set(j, temp);
                }
            }
        }
        storeAvailabilityAdapter.notifyDataSetChanged();
    }
}
