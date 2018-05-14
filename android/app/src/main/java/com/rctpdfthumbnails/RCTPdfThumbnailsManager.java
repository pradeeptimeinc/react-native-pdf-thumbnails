//  Created by react-native-create-bridge

package com.rctpdfthumbnails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.barteksc.pdfviewer.util.FileUtils;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RCTPdfThumbnailsManager extends ViewGroupManager<MyRecycler> {
    public static final String REACT_CLASS = "RCTPdfThumbnails";
    MyRecyclerAdapter  adapter;
    String filePath;
    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-components-android.html#1-create-the-viewmanager-subclass
        return REACT_CLASS;
    }

    @Override
    public MyRecycler createViewInstance(ThemedReactContext context){
        Log.e("brfherbjfb","sejrhfebjjfhr");
        // Create a view here
        // https://facebook.github.io/react-native/docs/native-components-android.html#2-implement-method-createviewinstance
        MyRecycler layout = new MyRecycler(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        TextView titleView = new TextView(context);
//        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        titleView.setLayoutParams(lparams);
////        titleView.setTextAppearance(context);
//        titleView.setText("Hallo World!");
//        titleView.setTextSize(44);
//        layout.addView(titleView);
        RecyclerView myRecyclerView  = new RecyclerView(context);
        myRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        myRecyclerView.setBackgroundColor(Color.YELLOW);
        adapter  = new MyRecyclerAdapter(context, layout);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        layout.addView(myRecyclerView);
        try {
            extractImages(context,"sample1.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return layout;
    }

    @ReactProp(name = "exampleProp")
    public void setExampleProp(View view, String prop) {
        Log.e("bfrejfbjer",prop);
        // Set properties from React onto your native component via a setter method
        // https://facebook.github.io/react-native/docs/native-components-android.html#3-expose-view-property-setters-using-reactprop-or-reactpropgroup-annotation
    }
    @ReactProp(name = "path")
    public  void setPathProp(View view, String prop){
        Log.e("somehsberjber", prop);
        this.filePath = prop;
        try {
            extractImages(view.getContext(),prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void extractImages(Context context, String pdfFileName) throws IOException {
        //File f = FileUtils.fileFromAsset(context, pdfFileName);
        //Uri uri = Uri.fromFile(f);
        Uri uri = Uri.parse(this.filePath);
        int pageNumber = 0;
        PdfiumCore pdfiumCore = new PdfiumCore(context);
        try {
            ParcelFileDescriptor fd = context.getContentResolver().openFileDescriptor(uri, "r");
            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
            int totalPages = pdfiumCore.getPageCount(pdfDocument);
            ArrayList<Bitmap> bitmapArrayList  = new ArrayList<>(totalPages);
            for(int i=0;i<totalPages;i++){
                pdfiumCore.openPage(pdfDocument, i);
                int width = pdfiumCore.getPageWidthPoint(pdfDocument, i);
                int height = pdfiumCore.getPageHeightPoint(pdfDocument, i);
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                pdfiumCore.renderPageBitmap(pdfDocument, bmp, i, 0, 0, width, height);
                bitmapArrayList.add(bmp);
            }
            pdfiumCore.closeDocument(pdfDocument);
            for (Bitmap bmp: bitmapArrayList){
                System.out.println(bmp.getByteCount());
            }
            adapter.setDataSet(bitmapArrayList);
            // important!
        } catch(Exception e) {
            //todo with exception
        }

    }

}
