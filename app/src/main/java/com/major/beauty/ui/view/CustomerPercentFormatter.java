package com.major.beauty.ui.view;

import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui.view
 * ProjectName: Beauty
 * Date: 2019/6/25 9:18
 */
public class CustomerPercentFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public CustomerPercentFormatter(ChartData data) {
        mFormat = new DecimalFormat("###,###,##0.0");
        List dataSets = data.getDataSets();
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + " %";
    }
}