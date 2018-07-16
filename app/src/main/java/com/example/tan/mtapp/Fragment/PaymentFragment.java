package com.example.tan.mtapp.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.HistoryCallbackListener;
import com.example.tan.mtapp.Adapter.ConfirmAdapter;
import com.example.tan.mtapp.Adapter.CustomDialog_detail;
import com.example.tan.mtapp.Model.HistoryModel;
import com.example.tan.mtapp.R;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements LocationListener{

    public static final int REQUEST_LOCATION_PERMISSIONS_CODE = 0;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 0;//1000 * 60 * 1; // 1 minute

    private LocationManager locationManager;
    public Location location;
    public Location myLocation  = new Location("My Location");
    public Location locationA = new Location("Location");
    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    boolean locationServiceAvailable;
    double lat1 = 13.766877;
    double lng1 = 100.560723;
    RecyclerView mRecyclerView;
    ConfirmAdapter adapter;
    View view;
    String TAG = "PaymentFragment";
    ConnectionManager connect = new ConnectionManager();
    HistoryCallbackListener historyCallbackListener = new HistoryCallbackListener() {

        @Override
        public void onResponse(List<HistoryModel> historyModel, Retrofit retrofit) {
            StaticClass.HISTORY_MODEL = historyModel;
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: history" + t);
        }

        @Override
        public void onBodyError(ResponseBody responseBody) {
            Log.d(TAG, "onBodyError: history" + responseBody);
        }

        @Override
        public void onBodyErrorIsNull() {
            Log.d(TAG, "onBodyErrorIsNull: history");
        }
    };

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connect.getHistory(historyCallbackListener, StaticClass.USER_MODEL.getProfile().getId_member());
                pullToRefresh.setRefreshing(false);
            }
        });
        locationA.setLatitude(lat1);
        locationA.setLongitude(lng1);
        setPayment();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestLocationPermission();

    }

    public void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSIONS_CODE);
        } else {
            initLocationService();
        }
    }

    private void initLocationService() {

        if ( Build.VERSION.SDK_INT >= 23 &&
                ActivityCompat.checkSelfPermission( getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }

        try   {
            this.locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            // Get GPS and network status
            this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled)    {
                // cannot get location
                this.locationServiceAvailable = false;
            }

            this.locationServiceAvailable = true;

            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null)   {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    updateLatestLocation();
                }
            }

            if (isGPSEnabled)  {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                if (locationManager != null)  {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    updateLatestLocation();
                }
            }
        } catch (Exception ex)  {
            Log.e(TAG, ex.getMessage());

        }
    }

    private void updateLatestLocation() {
        if (location != null) {
            Log.d(TAG, "updateLatestLocation: " + location.getLatitude() + "," + location.getLongitude());
            Log.d(TAG, "updateLatestLocationA: " + locationA.getLatitude() + "," + locationA.getLongitude());
            myLocation.setLatitude(location.getLatitude());
            myLocation.setLongitude(location.getLongitude());
            Log.d(TAG, "updateLatestLocationDistance: " + locationA.distanceTo(myLocation));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateLatestLocation();
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

    public void setPayment() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.reHis);
        adapter = new ConfirmAdapter(getContext(), StaticClass.HISTORY_MODEL, new ConfirmAdapter.OnItemClickListener() {
            @Override
            public void moreClick(HistoryModel.DetailBean activityAdapters, int position) {
//                Log.d(TAG, "moreClick: " + location.getLatitude() + "," + location.getLongitude());
                if (locationA.distanceTo(myLocation) > 100) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Out of Location");
                    builder.show();
                } else {
                    CustomDialog_detail cdd = new CustomDialog_detail(getActivity(), activityAdapters);
                    cdd.show();
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanGroupIndex(int adapterPosition, int spanCount) {
                return super.getSpanGroupIndex(adapterPosition, spanCount);
            }

            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
    }
}
