package com.shinheung.gearmanager.MVP;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public interface StockAddContract {

    public interface View {

        void showDatePickerDialog();

        void errorBuildObject(Exception e);
    }

    public interface Presenter {
        void selectDate();

        void create();

    }
}
