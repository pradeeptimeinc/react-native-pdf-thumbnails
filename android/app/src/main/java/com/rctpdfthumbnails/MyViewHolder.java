package com.rctpdfthumbnails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sharmapra on 5/10/18.
 */

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    ImageView mImageView;
    public MyViewHolder(View itemView) {
        super(itemView);
//        mTextView = (TextView) itemView.findViewWithTag("MyABC");

        mImageView = (ImageView) itemView.findViewWithTag("ITEM_IMAGE");
    }
}
