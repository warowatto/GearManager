package com.shinheung.gearmanager.Data;

import java.io.Serializable;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class Gear implements Serializable {

    double toolSize = 0d;

    boolean isDP = false;

    double teeth = 0d;

    double pressAngle = 20.0;

    double torisionAngle = 0d;

    double addTeeth = 0d;

    boolean isLowTeeth = false;

    public Gear(double toolSize, boolean isDP, double teeth) {
        setToolSize(toolSize, isDP);
        this.teeth = teeth;
    }

    public Gear(double toolSize, boolean isDP, double teeth, double pressAngle) {
        this(toolSize, isDP, teeth);
        this.pressAngle = pressAngle;
    }

    public Gear(double toolSize, boolean isDP, double teeth, double pressAngle, double torisionAngle) {
        this(toolSize, isDP, teeth);
        this.pressAngle = pressAngle;
        this.torisionAngle = torisionAngle;
    }

    public Gear(double toolSize, boolean isDP, double teeth, double pressAngle, double torisionAngle, double addTeeth) {
        this(toolSize, isDP, teeth);
        this.pressAngle = pressAngle;
        this.torisionAngle = torisionAngle;
        this.addTeeth = addTeeth;
    }

    public double getModule() {
        return toolSize;
    }

    public double getToolSize() {
        return isDP ? 25.4 / toolSize : toolSize;
    }

    public void setToolSize(double toolSize, boolean isDP) {
        this.isDP = isDP;
        this.toolSize = isDP ? 25.4 / toolSize : toolSize;
    }

    public boolean isLowTeeth() {
        return isLowTeeth;
    }

    public void setLowTeeth(boolean lowTeeth) {
        isLowTeeth = lowTeeth;
    }

    public boolean isDP() {
        return isDP;
    }

    public double getTeeth() {
        return teeth;
    }

    public void setTeeth(double teeth) {
        this.teeth = teeth;
    }

    public double getPressAngle() {
        return pressAngle;
    }

    public void setPressAngle(double pressAngle) {
        this.pressAngle = pressAngle;
    }

    public double getTorisionAngle() {
        return torisionAngle;
    }

    public void setTorisionAngle(double torisionAngle) {
        this.torisionAngle = torisionAngle;
    }

    public double getAddTeeth() {
        return addTeeth;
    }

    public void setAddTeeth(double addTeeth) {
        this.addTeeth = addTeeth;
    }
}

