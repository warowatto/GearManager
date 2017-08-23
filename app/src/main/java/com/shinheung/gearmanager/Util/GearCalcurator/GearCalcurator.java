package com.shinheung.gearmanager.Util.GearCalcurator;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public interface GearCalcurator {

    double getZm();

    double getOD();

    double getSm(double zm);

    double inv(double degree);
}
