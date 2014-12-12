package com.trembit.activityindicator;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

/**
 * Created by Maksym on 25.01.14.
 */
public class ActivityIndicatorNativeExtension implements FREExtension {
    public static ActivityIndicatorNativeExtensionContext context;

    /*
     * Creates a new instance of GMap2NativeExtensionContext when the context is created
	 * from the actionscript code.
	 */
    public FREContext createContext(String extId) {
        if(context == null) {
            context = new ActivityIndicatorNativeExtensionContext();
        }
        return context;
    }

    /*
     * Called if the extension is unloaded from the process. Extensions
     * are not guaranteed to be unloaded; the runtime process may exit without
     * doing so.
     */
    @Override
    public void dispose() {
    }

    /*
      * Extension initialization.
      */
    public void initialize() {
    }
}
