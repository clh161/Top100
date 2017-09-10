package com.jacob.top100.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
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
    private final int mItemLayout;
    private boolean isNaughtyLayout = true;

    public MobileAppAdapter(@LayoutRes int itemLayout) {
        mItemLayout = itemLayout;
    }

    @Override
    public MobileAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return new MobileAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MobileAppViewHolder holder, int position) {
        MobileApp mobileApp = mMobileApps.get(position);
        if (holder.getRank() != null)
            holder.getRank().setText(String.valueOf(position + 1));
        holder.getName().setText(mobileApp.getName());
        holder.getCategory().setText(mobileApp.getCategory());
        if (holder.getRatingBar() != null)
            if (mobileApp.getRating() != null) {
                holder.getRatingBar().setVisibility(View.VISIBLE);
                holder.getRatingBar().setRating(mobileApp.getRating());
            } else
                holder.getRatingBar().setVisibility(View.INVISIBLE);
        if (holder.getRatingCount() != null)
            if (mobileApp.getRatingCount() != null) {
                holder.getRatingCount().setVisibility(View.VISIBLE);
                holder.getRatingCount().setText(String.format("(%d)", mobileApp.getRatingCount()));
            } else
                holder.getRatingCount().setVisibility(View.INVISIBLE);
        if (isNaughtyLayout) {
            float iconRoundedRadius = holder.getIcon().getResources().getDimension(R.dimen.app_radius);
            float iconWidth = holder.getIcon().getResources().getDimension(R.dimen.app_height);
            holder.getIcon().setCornerRadius(position % 2 == 0 ? iconRoundedRadius : iconWidth / 2);
        }
        if (mobileApp.getIcon() != null)
            Glide.with(holder.getIcon().getContext()).load(mobileApp.getIcon()).into(holder.getIcon());
        else
            holder.getIcon().setImageResource(0);
    }

    @Override
    public int getItemCount() {
        return mMobileApps.size();
    }

    public void setNaughtyLayout(boolean naughtyLayout) {
        isNaughtyLayout = naughtyLayout;
    }

    public void setMobileApps(List<MobileApp> mobileApps) {
        mMobileApps = mobileApps;
    }
}
