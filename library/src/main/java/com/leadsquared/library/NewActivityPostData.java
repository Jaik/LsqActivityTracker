package com.leadsquared.library;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jainendra Kumar on 4/5/2016.
 */
class NewActivityPostData {
    private String EmailAddress;
    private int ActivityEvent;
    private String ActivityNote;
    private String ActivityDateTime;


    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public int getActivityEvent() {
        return ActivityEvent;
    }

    public void setActivityEvent(int activityEvent) {
        ActivityEvent = activityEvent;
    }

    public String getActivityNote() {
        return ActivityNote;
    }

    public void setActivityNote(String activityNote) {
        ActivityNote = activityNote;
    }

    public String getActivityDateTime() {
        return ActivityDateTime;
    }

    public void setActivityDateTime(String activityDateTime) {
        ActivityDateTime = activityDateTime;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EmailAddress", EmailAddress);
            jsonObject.put("ActivityEvent", ActivityEvent);
            jsonObject.put("ActivityNote", ActivityNote);
            jsonObject.put("ActivityDateTime", ActivityDateTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
