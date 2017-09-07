package com.jacob.top100.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jacob.top100.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Jacob Ho
 */
public class MobileAppViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    TextView mName;

    public MobileAppViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getName() {
        return mName;
    }
}

