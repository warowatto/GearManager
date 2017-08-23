package com.shinheung.gearmanager.MVP;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public interface GearCalDetailContract {

    public interface View {

    }

    public interface Presenter {
        String getGearName();

        String getSM(int zm, int format);

        String getZM();

        void changeZm(boolean sign);
    }
}
