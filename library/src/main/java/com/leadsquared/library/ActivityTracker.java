package com.leadsquared.library;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jainendra Kumar on 4/5/2016.
 */
public class ActivityTracker {
    static ActivityTracker activityTracker;
    static String mAccessKey;
    static String mSecretKey;
    static Context mContext;
    String mEmail;
    String createActivityUrl = "https://api.leadsquared.com/v2/ProspectActivity.svc/Create?accessKey=%s&secretKey=%s";

    public static ActivityTracker newInstance(Context context, String accessKey, String secretKey) {
        if (activityTracker != null) {
            activityTracker = new ActivityTracker();
        }
        mAccessKey = accessKey;
        mSecretKey = secretKey;
        mContext = context;
        return activityTracker;
    }

    public void identify(String emailAddress) {
        mEmail = emailAddress;
        new Thread() {
            @Override
            public void run() {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(mContext, "com.leadsquared.activitytracker");
                sharedPreferenceManager.storeStringPreference("accessKey", mAccessKey);
                sharedPreferenceManager.storeStringPreference("secretKey", mSecretKey);
                sharedPreferenceManager.storeStringPreference("email", mEmail);
            }
        }.start();
    }

    private ActivityTracker() {
    }

    public void trackEvent(final String emailAddress, final int notableActivityEvent, final String activityNote) {
        new Thread() {
            @Override
            public void run() {
                if (mAccessKey == null) {
                    reinitializeTokens();
                }
                NewActivityPostData newActivityPostData = new NewActivityPostData();
                newActivityPostData.setEmailAddress(emailAddress);
                newActivityPostData.setActivityEvent(notableActivityEvent);
                newActivityPostData.setActivityNote(activityNote);
                newActivityPostData.setActivityDateTime(stringFromDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

                String response = HttpCall.post(String.format(createActivityUrl, mAccessKey, mSecretKey), newActivityPostData.toJson());
                Log.d("LeadSquared.API", response);
            }
        }.start();
    }


    public static String stringFromDate(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    private void reinitializeTokens() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(mContext, "com.leadsquared.activitytracker");
        mAccessKey = sharedPreferenceManager.getStringPreference("accessKey");
        mSecretKey = sharedPreferenceManager.getStringPreference("secretKey");
        mEmail = sharedPreferenceManager.getStringPreference("email");
    }
}
