
package com.trembit.activityindicator;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class ANEUtils {
    /**
     * Position and resize view in parent view.
     *
     * @param v    The view which should be positioned
     * @param rect
     */
    public static void positionAndResizeView(View v, Rect rect) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(v.getLayoutParams().width, v.getLayoutParams().height);
        params.setMargins(rect.left, rect.top, 0, 0);
        v.setLayoutParams(params);
    }

    /**
     * Add a view to the display list on top of all views in an activity.
     *
     * @param act  Activity which should be use to create a view
     * @param v    the view which will be added
     * @param rect size and position of the added view
     * @return relative layout which contains the passed view.
     */
    public static View addView(Activity act, View v, Rect rect) {
        RelativeLayout rl = new RelativeLayout(act);
        ViewGroup root = (ViewGroup) act.findViewById(android.R.id.content);
        root = (ViewGroup) root.getChildAt(0);
        //TODO: try to add MapLayout directly to root
        root.addView(rl, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rect.width(), rect.height());
        params.setMargins(rect.left, rect.top, 0, 0);
        rl.addView(v, params);

        return rl;
    }

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

//    public static RelativeLayout addMap(Activity act, View v, Rect rect) {
//        MapFragment mMapFragment = MapFragment.newInstance();
//        FragmentTransaction fragmentTransaction =
//                act.getFragmentManager().beginTransaction();
//        fragmentTransaction.add(android.R.id.content, mMapFragment);
//        fragmentTransaction.commit();
//
//
//        RelativeLayout rl = new RelativeLayout(act);
//
//        ViewGroup root = (ViewGroup)act.findViewById(android.R.id.content);
//        root = (ViewGroup)root.getChildAt(0);
//
//        root.addView(rl, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rect.width(), rect.height());
//        params.setMargins(rect.left, rect.top, 0, 0);
//        rl.addView(v, params);
//
//        return rl;
//    }

}
