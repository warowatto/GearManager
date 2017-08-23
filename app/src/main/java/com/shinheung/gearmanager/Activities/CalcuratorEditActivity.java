package com.shinheung.gearmanager.Activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.common.base.Strings;
import com.google.common.primitives.Chars;
import com.shinheung.gearmanager.Data.Gear;
import com.shinheung.gearmanager.MVP.GearCalEditContract;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.Util.String.Converter;
import com.shinheung.gearmanager.databinding.ActivityCalEditBinding;

import io.reactivex.Observable;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class CalcuratorEditActivity extends AppCompatActivity
        implements GearCalEditContract.View, GearCalEditContract.Presenter, TextWatcher {

    ActivityCalEditBinding binding;
    ObservableField<String> objDegree;
    ObservableBoolean objIsDegree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cal_edit);

        init();
    }

    private void init() {
        objDegree = new ObservableField<>("0");
        objIsDegree = new ObservableBoolean(true);

        binding.editTorisionAngle.addTextChangedListener(this);

        binding.setHandler(this);
        binding.setIsDegree(objIsDegree);
        binding.setPrintDegree(objDegree);
    }

    @Override
    public void gearGeneratorOK(Gear gear) {
        Intent intent = new Intent(this, CalcuratorDetailActivity.class);
        intent.putExtra("gear", gear);
        startActivity(intent);
    }

    @Override
    public void gearGeneratorFail(Exception e) {
        Toast.makeText(this, "모듈과 잇수는 필수 입력사항입니다", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gearGanerator() {
        try {
            double module = Double.valueOf(binding.editModule.getText().toString());
            boolean isDp = binding.checkDp.isChecked();
            boolean isLowTeeth = binding.checkLowTeeth.isChecked();
            double teeth = Double.valueOf(binding.editTeeth.getText().toString());
            double pressAngle = Double.valueOf(getResources().getStringArray(R.array.pressAngle)[binding.spinnerPressAngle.getSelectedItemPosition()]);

            String torisionAngleString = binding.editTorisionAngle.getText().toString();
            double torisionAngle = Strings.isNullOrEmpty(torisionAngleString) ? 0d : Double.valueOf(torisionAngleString);

            String addTeethString = binding.editAddTeeth.getText().toString();
            double addTeeth = Strings.isNullOrEmpty(addTeethString) ? 0d : Double.valueOf(addTeethString);

            Gear gear = new Gear(module, isDp, teeth, pressAngle, torisionAngle, addTeeth);
            gear.setLowTeeth(isLowTeeth);
            gearGeneratorOK(gear);
        } catch(Exception e) {
            gearGeneratorFail(e);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (Strings.isNullOrEmpty(charSequence.toString())) return;
        String text = charSequence.toString();
        boolean isDegree = Observable.fromIterable(Chars.asList(text.toCharArray())).filter(character -> character == '.').count().blockingGet() < 2;
        String printText = isDegree ? Converter.degreeToTime(Double.valueOf(text)) : String.valueOf(Converter.timeToDegree(text));

        objIsDegree.set(isDegree);
        objDegree.set(printText);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
