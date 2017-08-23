package com.shinheung.gearmanager.Util.String;

import com.google.common.base.Strings;

/**
 * Created by yongheekim on 2017. 8. 18..
 */

public class Converter {

    public static String degreeToTime(double val) {
        // 각도를 도분초로
        double degree = Math.floor(val);
        double min = Math.floor((val - degree) * 60d);
        double sec = Math.floor((val - degree - (min / 60d)) * 3600d);

        return String.format("%.0f˚%.0f'%.0f\"", degree, min, sec);
    }

    public static double timeToDegree(String val) {
        // 도분초를 각도로
        String[] slice = val.split("[.˚\'\"]");

        double degree = 0d;
        double rate = 1d;
        for (String value : slice) {
            boolean hasText = Strings.isNullOrEmpty(value);
            if (hasText) break;

            degree += Double.valueOf(value) / rate;
            rate *= 60;
        }

        return degree;
    }
}