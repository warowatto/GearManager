package com.shinheung.gearmanager.MVP;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public interface StockListContract {

    public enum ORDER {
        DATE_ASC, DATE_DESC,
    }

    public interface View {
        void dataLoading(String message);

        void loadingError(String message);
    }

    public interface Presenter {
        void loadStockList();

        void changeSort(ORDER sort);

        void addStock();
    }
}
