package com.jacob.top100.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jacob.top100.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Jacob Ho
 */
public class MobileAppViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rank)
    TextView mRank;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.icon)
    RoundedImageView mIcon;

    public MobileAppViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getName() {
        return mName;
    }

    public RoundedImageView getIcon() {
        return mIcon;
    }

    public TextView getRank() {
        return mRank;
    }
}

