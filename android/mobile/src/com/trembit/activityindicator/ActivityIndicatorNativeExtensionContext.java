package com.trembit.activityindicator;

import android.util.Log;
import android.util.SparseArray;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.trembit.activityindicator.functions.removeActivityIndicator;
import com.trembit.activityindicator.functions.removeAllActivityIndicators;
import com.trembit.activityindicator.functions.showActivityIndicator;
import java.util.HashMap;
import java.util.Map;

public class ActivityIndicatorNativeExtensionContext extends FREContext {

    public static final String ANE_NAME = "ActivityIndicator";

    @Override
    public Map<String, FREFunction> getFunctions() {

        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
        functionMap.put("show", new showActivityIndicator());
        functionMap.put("hide", new removeActivityIndicator());
        functionMap.put("hideAll", new removeAllActivityIndicators());
        return functionMap;
    }

    @Override
    public void dispose() {

    }

    private RelativeLayout activityIndicatorLayout;

    public RelativeLayout getActivityIndicatorLayout() {
        return activityIndicatorLayout;
    }

    public void setActivityIndicatorLayout(RelativeLayout activityIndicatorLayout) {
        this.activityIndicatorLayout = activityIndicatorLayout;
    }

    private static final SparseArray<ProgressBar> activityIndicatorsMap = new SparseArray<ProgressBar>();
    private static int indicatorUID = -1;

    public int addActivityIndicator(RelativeLayout.LayoutParams params, int style, int color) {
        final ProgressBar progressBar = new ProgressBar(getActivity(), null, style);
        //Log.d(ANE_NAME, "addActivityIndicator style: " + style);
        if (color > -1) {
            //Log.d(ANE_NAME, "addActivityIndicator color: " + color);
            progressBar.getIndeterminateDrawable().setColorFilter(color | 0xFF000000, android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        RelativeLayout layout = getActivityIndicatorLayout();
        if (layout == null) {
            layout = new RelativeLayout(getActivity());
            ANEUtils.addViewToRoot(getActivity(), layout);
            setActivityIndicatorLayout(layout);
        }
        layout.addView(progressBar, params);
        progressBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                progressBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                progressBar.setX(progressBar.getX() - progressBar.getWidth() / 2);
                progressBar.setY(progressBar.getY() - progressBar.getHeight() / 2);
            }
        });
        indicatorUID++;
        activityIndicatorsMap.put(indicatorUID, progressBar);
        return indicatorUID;
    }

    public void removeActivityIndicator(int index) {
        if (activityIndicatorsMap.get(index) != null) {
            RelativeLayout layout = getActivityIndicatorLayout();
            if (layout != null) {
                layout.removeView(activityIndicatorsMap.get(index));
                activityIndicatorsMap.remove(index);
            }
        }
    }

    public void removeAllActivityIndicators() {
        activityIndicatorsMap.clear();
        if (getActivityIndicatorLayout() != null) {
            getActivityIndicatorLayout().removeAllViews();
        }
    }
}
