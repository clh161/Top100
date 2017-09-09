package com.jacob.top100.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jacob.top100.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * @author Jacob Ho
 */
public class MobileAppViewHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.rank)
    TextView mRank;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.category)
    TextView mCategory;
    @BindView(R.id.icon)
    RoundedImageView mIcon;
    @Nullable
    @BindView(R.id.rating_bar)
    MaterialRatingBar mRatingBar;
    @Nullable
    @BindView(R.id.rating_count)
    TextView mRatingCount;

    public MobileAppViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getName() {
        return mName;
    }

    public TextView getCategory() {
        return mCategory;
    }

    public RoundedImageView getIcon() {
        return mIcon;
    }

    public TextView getRank() {
        return mRank;
    }

    public MaterialRatingBar getRatingBar() {
        return mRatingBar;
    }

    @Nullable
    public TextView getRatingCount() {
        return mRatingCount;
    }
}

