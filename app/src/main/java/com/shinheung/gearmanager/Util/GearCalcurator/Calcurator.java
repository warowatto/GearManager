package com.shinheung.gearmanager.Util.GearCalcurator;

import com.shinheung.gearmanager.Data.Gear;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class Calcurator implements GearCalcurator {

    Gear gear;

    public Calcurator(Gear gear) {
        this.gear = gear;
    }

    @Override
    public double getZm() {
        double solve = (gear.getPressAngle() * gear.getTeeth()) / 180d + 0.5d;
        return Math.ceil(solve);
    }

    @Override
    public double getOD() {
        return 0;
    }

    @Override
    public double getSm(double zm) {
        double m = gear.getModule();
        double a0 = Math.toRadians(gear.getPressAngle());
        double t = gear.getTeeth();
        double b0 = Math.toRadians(gear.getTorisionAngle());
        double an = gear.getAddTeeth();

        double frontPressAngle = Math.toDegrees(Math.atan(Math.tan(a0) / Math.cos(b0)));

        return m * Math.cos(a0) * (
                (Math.PI * (zm - 0.5)) + (t * inv(frontPressAngle))
        ) + 2d * m * an * Math.sin(a0);
    }

    @Override
    public double inv(double degree) {
        return Math.tan(Math.toRadians(degree)) - Math.toRadians(degree);
    }
}
