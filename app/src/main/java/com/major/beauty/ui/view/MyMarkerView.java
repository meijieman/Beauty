package com.major.beauty.ui.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.major.beauty.R;

/**
 * Desc: 自定义覆盖物
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui.view
 * ProjectName: Beauty
 * Date: 2019/6/6 18:26
 */
public class MyMarkerView extends MarkerView {

    private TextView tvContent;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            tvContent.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else if (e instanceof BarEntry) {
            BarEntry be = (BarEntry) e;
            tvContent.setText(Utils.formatNumber(be.getY(), 0, true));
        } else {

            tvContent.setText(Utils.formatNumber(e.getX(), 0, true));
        }
    }

    @Override
    public MPPointF getOffset() {
        MPPointF mpPointF = new MPPointF();
        mpPointF.x = -getWidth() / 2f;
        mpPointF.y = -getHeight();

        return mpPointF;
    }
}
