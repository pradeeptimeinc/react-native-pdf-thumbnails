package com.rctpdfthumbnails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by sharmapra on 5/10/18.
 */

public class MyRecycler extends LinearLayout {
    public MyRecycler(Context context) {
        super(context);
    }
    public void onReceiveNativeEvent(int index) {
        Log.e("ONREC", "JHBJJB");
        WritableMap event = Arguments.createMap();
        event.putString("message", "MyMessage");
        event.putInt("index", index+1);
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "topChange",
                event);
    }
}
