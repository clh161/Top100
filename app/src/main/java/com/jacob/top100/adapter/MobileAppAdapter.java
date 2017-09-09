package com.jacob.top100.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jacob.top100.R;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.model.MobileAppImage;
import com.jacob.top100.viewholder.MobileAppViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Ho
 */
public class MobileAppAdapter extends RecyclerView.Adapter<MobileAppViewHolder> {

    private List<MobileApp> mMobileApps = new ArrayList<>();

    @Override
    public MobileAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobile_app, parent, false);
        return new MobileAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MobileAppViewHolder holder, int position) {
        MobileApp mobileApp = mMobileApps.get(position);
        holder.getRank().setText(String.valueOf(position + 1));
        holder.getName().setText(mobileApp.getName());
        List<MobileAppImage> images = mobileApp.getImages();
        float iconRoundedRadius = holder.getIcon().getResources().getDimension(R.dimen.app_radius);
        float iconWidth = holder.getIcon().getResources().getDimension(R.dimen.app_height);
        holder.getIcon().setCornerRadius(position % 2 == 0 ? iconRoundedRadius : iconWidth / 2);
        if (!images.isEmpty())
            Glide.with(holder.getIcon().getContext()).load(images.get(0).getUrl()).into(holder.getIcon());
        else
            holder.getIcon().setImageResource(0);
    }

    @Override
    public int getItemCount() {
        return mMobileApps.size();
    }

    public void setMobileApps(List<MobileApp> mobileApps) {
        mMobileApps = mobileApps;
    }
}
