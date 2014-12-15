package com.trembit.activityindicator.functions;

import android.util.Log;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.adobe.fre.*;
import com.trembit.activityindicator.ActivityIndicatorNativeExtensionContext;

import java.util.Arrays;

public class showActivityIndicator implements FREFunction {

    private static final Integer[] AVAILABLE_STYLES = {813, 907, 908, 909, 910};

    @Override
    public FREObject call(FREContext context, FREObject[] freObjects) {
        ActivityIndicatorNativeExtensionContext cnt = (ActivityIndicatorNativeExtensionContext) context;
        int xPos = -1;
        int yPos = -1;
        int style = -1;
        int color = -1;
        try {
            xPos = freObjects[0].getAsInt();
            yPos = freObjects[1].getAsInt();
            style = freObjects[2].getAsInt();
            color = freObjects[3].getAsInt();

        } catch (FRETypeMismatchException e) {
            e.printStackTrace();
        } catch (FREInvalidObjectException e) {
            e.printStackTrace();
        } catch (FREWrongThreadException e) {
            e.printStackTrace();
        }

        //Log.d(ActivityIndicatorNativeExtensionContext.ANE_NAME, "showActivityIndicator c: " + color);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(xPos, yPos, 0, 0);
        if (!Arrays.asList(AVAILABLE_STYLES).contains(style)) {
            style = android.R.attr.progressBarStyle;
        }
        int uid = cnt.addActivityIndicator(params, style, color);
        FREObject returnValue = null;
        try {
            returnValue = FREObject.newObject(uid);
        } catch (FREWrongThreadException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    private class SyncOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            // Synchronize code here

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {

        }


    }
}


