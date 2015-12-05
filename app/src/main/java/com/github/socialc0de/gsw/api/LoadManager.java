package com.github.socialc0de.gsw.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.socialc0de.gsw.activities.MainActivity;
import com.github.socialc0de.gsw.api.interfaces.FaqCategoryRestClient;
import com.github.socialc0de.gsw.api.interfaces.RestArrayRequestCallBack;
import com.github.socialc0de.gsw.api.interfaces.PhraseCategoryRestClient;
import com.github.socialc0de.gsw.customClasses.api.FaqCategory;
import com.github.socialc0de.gsw.customClasses.api.PhraseCategory;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.springframework.core.NestedRuntimeException;

import java.util.ArrayList;

/**
 * Created by roman on 05.12.2015.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LoadManager implements org.androidannotations.api.rest.RestErrorHandler{
    public static final  int NETWORK_NOT_AVAILABLE = 0;
    public static final  int NETWORK_AVAILABLE = 1;
    public static final  int NETWORK_MISC = 2;
    public static final  int NETWORK_SERVICE_NOT_AVAILABLE = 3;

    private Context mCurrentActivity = null;
    private RestArrayRequestCallBack mCallback = null;

    private int mNetworkstate = NETWORK_AVAILABLE;

    @AfterInject
    protected void afterInject() {

    }

    /**
     * Loads infos and warnings results
     *
     * @param callback - callback is called after response with result and networt state (NETWORK_NOT_AVAILABLE or NETWORK_AVAILABLE)
     */

    @Background
    public void loadFaqCategoryResults(final RestArrayRequestCallBack callback) {
        FaqCategoryRestClient mRestClient;
        mNetworkstate = NETWORK_AVAILABLE;


        if(callback == null){
            return;
        }
        mCallback = callback; // store callback class for error

        mRestClient = RestClientHelper.createFaqCategoryRestClientWithTimeout(getCurrentActivity());//new CurrencyRestClient_(getCurrentActivity());
        mRestClient.setRestErrorHandler(this);

        Boolean networkAvailable = checkNetworkState();
        if(networkAvailable == null){
            return;
        }

        if(!networkAvailable){
            mNetworkstate = NETWORK_NOT_AVAILABLE;
            callback.onRestResults(mNetworkstate, null);
        }else {
            ArrayList<FaqCategory> results = mRestClient.loadFaqCategoryFromRest();

            if(mNetworkstate == NETWORK_AVAILABLE) {
                if (callback != null && !callback.isDestroyed()) {
                    callback.onRestResults(mNetworkstate, results);
                }
            }
        }
    }

    @Background
    public void loadPhraseCategoryResults(final RestArrayRequestCallBack callback) {
        PhraseCategoryRestClient mRestClient;
        mNetworkstate = NETWORK_AVAILABLE;


        if(callback == null){
            return;
        }
        mCallback = callback; // store callback class for error

        mRestClient = RestClientHelper.createPhraseCategoryRestClientWithTimeout(getCurrentActivity());//new CurrencyRestClient_(getCurrentActivity());
        mRestClient.setRestErrorHandler(this);

        Boolean networkAvailable = checkNetworkState();
        if(networkAvailable == null){
            return;
        }

        if(!networkAvailable){
            mNetworkstate = NETWORK_NOT_AVAILABLE;
            callback.onRestResults(mNetworkstate, null);
        }else {
            ArrayList<PhraseCategory> results =  mRestClient.loadPhraseCategoryFromRest();

            if(mNetworkstate == NETWORK_AVAILABLE) {
                if (callback != null && !callback.isDestroyed()) {
                    callback.onRestResults(mNetworkstate, results);
                }
            }
        }
    }

    public void setCurrentActivity(Context currentActivity){
        this.mCurrentActivity = currentActivity;
    }

    private Context getCurrentActivity(){
        return this.mCurrentActivity;
    }

    /**
     * Check network state. Returns true, if network connected
     * @return true, if connected
     */
    private Boolean checkNetworkState(){

        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.getMainActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        //BackgroundExecutor.cancelAll("cancellable_task", true);
        mNetworkstate = NETWORK_MISC;
        if(mCallback!=null && !mCallback.isDestroyed()){
            mCallback.onRestResults(mNetworkstate, null);
        }
    }


}

