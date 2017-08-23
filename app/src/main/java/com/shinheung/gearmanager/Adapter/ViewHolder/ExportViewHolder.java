package com.shinheung.gearmanager.Adapter.ViewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.shinheung.gearmanager.Activities.StockDetailActivity;
import com.shinheung.gearmanager.Adapter.ExportListAdapter;
import com.shinheung.gearmanager.Data.Export;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ListItemExportBinding;

import io.realm.Realm;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class ExportViewHolder extends DefaultBindingViewHolder<ListItemExportBinding>
        implements ExportListAdapter.Handler {

    public ExportViewHolder(ListItemExportBinding itemView) {
        super(itemView);
    }

    @Override
    public void wrapDetailPage(int stockId) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, StockDetailActivity.class);
        intent.putExtra("id", stockId);

        context.startActivity(intent);
    }

    @Override
    public void deleteExport(ExportListAdapter adapter, Export export) {
        Context context = itemView.getContext();

        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_title_delete))
                .setMessage(context.getString(R.string.dialog_message_delete, "출고"))
                .setPositiveButton(context.getString(R.string.dialog_button_delete), (dialog, position) ->
                        removeData(adapter, export)
                )
                .setNegativeButton(context.getString(R.string.dialog_button_cancel), null)
                .show();
    }

    private void removeData(ExportListAdapter adapter, Export export) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            export.deleteFromRealm();
            adapter.refresh();
        });
    }
}
