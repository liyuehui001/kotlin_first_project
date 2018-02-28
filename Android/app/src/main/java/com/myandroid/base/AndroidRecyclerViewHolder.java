package com.myandroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class AndroidRecyclerViewHolder extends RecyclerView.ViewHolder  {

    private int viewType;
    private View view;

    public AndroidRecyclerViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public View getView(){
        return this.view;
    }

}
