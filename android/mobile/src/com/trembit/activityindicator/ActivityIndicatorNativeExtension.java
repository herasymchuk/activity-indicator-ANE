package com.trembit.activityindicator;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class ActivityIndicatorNativeExtension implements FREExtension {
    public static ActivityIndicatorNativeExtensionContext context;

    public FREContext createContext(String extId) {
        if(context == null) {
            context = new ActivityIndicatorNativeExtensionContext();
        }
        return context;
    }

    @Override
    public void dispose() {
    }

    public void initialize() {
    }
}
