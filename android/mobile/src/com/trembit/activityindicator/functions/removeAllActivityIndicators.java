package com.trembit.activityindicator.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.trembit.activityindicator.ActivityIndicatorNativeExtensionContext;

public class removeAllActivityIndicators implements FREFunction {
    @Override
    public FREObject call(FREContext context, FREObject[] freObjects) {
        ActivityIndicatorNativeExtensionContext cnt = (ActivityIndicatorNativeExtensionContext) context;
        cnt.removeAllActivityIndicators();
        return null;
    }
}


