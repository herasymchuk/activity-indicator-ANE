package com.trembit.activityindicator;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ANEUtils {

    public static void addViewToRoot(Activity act, View v) {
        ViewGroup root = (FrameLayout) act.findViewById(android.R.id.content);
        root = (ViewGroup) root.getChildAt(0);
        ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        root.addView(v, params);
    }

    public static void removeView(Activity act, View v) {
        ViewGroup root = (ViewGroup) act.findViewById(android.R.id.content);
        root = (ViewGroup) root.getChildAt(0);
        root.removeView(v);
    }

}
