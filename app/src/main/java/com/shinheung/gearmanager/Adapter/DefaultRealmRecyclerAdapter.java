package com.shinheung.gearmanager.Adapter;

import android.support.v7.widget.RecyclerView;

import com.shinheung.gearmanager.Adapter.ViewHolder.DefaultBindingViewHolder;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public abstract class DefaultRealmRecyclerAdapter<E extends RealmObject, T extends DefaultBindingViewHolder> extends RecyclerView.Adapter<T> {

    RealmResults<E> results;

    public DefaultRealmRecyclerAdapter() {

    }

    public DefaultRealmRecyclerAdapter(RealmResults<E> results) {
        this.results = results;
    }

    public void setQuery(RealmResults<E> query) {
        results = query;
        notifyDataSetChanged();
    }

    public void setSort(String filed, Sort sort) {
        results = results.sort(filed, sort);
        notifyDataSetChanged();
    }

    public E getItem(int position) {
        return results.get(position);
    }

    public void refresh() {
        if (results != null) {
            results = results.where().findAll();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }
}
