package com.shinheung.gearmanager.Util;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class AndroidDataBindingUtils {

    @BindingAdapter({"bind:printDate"})
    public static void printDate(TextView textView, @NonNull Date date) {
        String formatDate = new SimpleDateFormat("yyyy.MM.dd").format(date);
        textView.setText(formatDate);
    }
}
