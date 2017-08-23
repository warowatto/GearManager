package com.shinheung.gearmanager.Adapter.ViewHolder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class DefaultBindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    T binding;

    public DefaultBindingViewHolder(T itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }

    public T getBinding() {
        return binding;
    }
}
