package com.trembit.activityindicator.functions;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.adobe.fre.*;
import com.trembit.activityindicator.ANEUtils;
import com.trembit.activityindicator.ActivityIndicatorNativeExtensionContext;

public class removeActivityIndicator implements FREFunction {
    @Override
    public FREObject call(FREContext context, FREObject[] freObjects) {
        ActivityIndicatorNativeExtensionContext cnt = (ActivityIndicatorNativeExtensionContext)context;
        int index = -1;
        try {
            index = freObjects[0].getAsInt();

        } catch (FRETypeMismatchException e) {
            e.printStackTrace();
        } catch (FREInvalidObjectException e) {
            e.printStackTrace();
        } catch (FREWrongThreadException e) {
            e.printStackTrace();
        }
        if(index > -1) {
            RelativeLayout layout = cnt.getActivityIndicatorLayout();
            if (layout != null) {
                cnt.removeActivityIndicator(index);
            }
        }
        return null;
    }
}


