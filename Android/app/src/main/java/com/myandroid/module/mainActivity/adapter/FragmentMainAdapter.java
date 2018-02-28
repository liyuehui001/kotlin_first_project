package com.myandroid.module.mainActivity.adapter;

import android.content.Context;
import android.lyh.com.android.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myandroid.base.AndroidRecyclerViewHolder;
import com.myandroid.module.mainActivity.bean.MainItem;
import com.myandroid.widget.NineGridLayout;
import com.staticVar.IUrl;
import com.staticVar.RequestStr;

import java.util.ArrayList;

/**
 * Created by 小黑 on 2017/11/28.
 */

public class FragmentMainAdapter extends RecyclerView.Adapter<AndroidRecyclerViewHolder>{

    private Context mContext;
    private ArrayList<MainItem> lists = new ArrayList<>();

    public FragmentMainAdapter(Context ctx,ArrayList<MainItem> listsItem){
        this.mContext = ctx;
        this.lists = listsItem;
    }


    @Override
    public AndroidRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RequestStr.mainViewType.TYPE_NORMAL){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_normal,parent,false);
        }else if (viewType == RequestStr.mainViewType.TYPE_GUANGGAO){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_guanggao,parent,false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_normal,parent,false);
        }
        AndroidRecyclerViewHolder arviewHolder = new AndroidRecyclerViewHolder(view);
        arviewHolder.setViewType(viewType);

        return arviewHolder;
    }

    @Override
    public void onBindViewHolder(AndroidRecyclerViewHolder holder, int position) {
        View view = holder.getView();
        NineGridLayout nineGridLayout = (NineGridLayout)view.findViewById(R.id.layout_nine_grid);
        nineGridLayout.setIsShowAll(false);

        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < lists.get(position).getImage_urls().size(); i++) {
            urls.add(IUrl.BASE_URL+lists.get(position).getImage_urls().get(i));
        }
        
        nineGridLayout.setUrlList(urls);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type_return=0;
        int viewtype = Integer.parseInt(lists.get(position).getViewtype());
        switch (viewtype){
            case 0:
                type_return = RequestStr.mainViewType.TYPE_NORMAL;
                break;
            case 1:
                type_return = RequestStr.mainViewType.TYPE_GUANGGAO;
                break;
        }
        return type_return;
    }
}
