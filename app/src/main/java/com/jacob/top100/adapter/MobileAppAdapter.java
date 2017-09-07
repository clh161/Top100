package com.jacob.top100.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacob.top100.R;
import com.jacob.top100.model.MobileApp;
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
        holder.getName().setText(mobileApp.getName());
    }

    @Override
    public int getItemCount() {
        return mMobileApps.size();
    }

    public void setMobileApps(List<MobileApp> mobileApps) {
        mMobileApps = mobileApps;
    }
}
