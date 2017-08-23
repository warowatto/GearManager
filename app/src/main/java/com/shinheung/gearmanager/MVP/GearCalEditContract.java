package com.shinheung.gearmanager.MVP;

import com.shinheung.gearmanager.Data.Gear;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public interface GearCalEditContract {

    public interface View {
        void gearGeneratorOK(Gear gear);

        void gearGeneratorFail(Exception e);
    }

    public interface Presenter {
        void gearGanerator();
    }
}
