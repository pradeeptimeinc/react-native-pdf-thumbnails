package com.rctpdfthumbnails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.rctpdfthumbnails.R;

import java.util.ArrayList;

/**
 * Created by sharmapra on 5/10/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<String> arrayList ;
    private  ArrayList<Bitmap> bitmapArrayList=new ArrayList<>();
    private Context context;
    private MyRecycler mMyRecycler;
    public MyRecyclerAdapter(Context context, MyRecycler myRecycler) {
//        this.arrayList = new ArrayList<>(10);
//        for (int i=0;i<20;i++){
//            arrayList.add("index"+i);
//        }
        this.context = context;
        mMyRecycler = myRecycler;
    }

    public void setDataSet(ArrayList<Bitmap> bitmapArrayList){
        this.bitmapArrayList = bitmapArrayList;
//        this.reactContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(parent.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        layoutParams.setMargins(20,20,20,20);
        layout.setLayoutParams(layoutParams);
        layout.setBackgroundColor(Color.CYAN);




        ImageView imageView = new ImageView(parent.getContext());
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        imageView.setTag("ITEM_IMAGE");

        layout.addView(imageView);

        MyViewHolder viewHolder =  new MyViewHolder(layout);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.mTextView.setText(arrayList.get(position));
//        holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        holder.mImageView.setImageBitmap(bitmapArrayList.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Clicked",Toast.LENGTH_SHORT).show();
                mMyRecycler.onReceiveNativeEvent(holder.getAdapterPosition());

            }
        });
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
//        return arrayList.size();
        return bitmapArrayList.size();
    }
}
