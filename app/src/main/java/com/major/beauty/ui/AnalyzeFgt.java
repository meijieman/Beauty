package com.major.beauty.ui;

import android.graphics.Color;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.major.beauty.R;
import com.major.beauty.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.vm
 * ProjectName: Beauty
 * Date: 2019/6/3 12:42
 */
public class AnalyzeFgt extends BaseFragment {

    @BindView(R.id.bc_analyze)
    BarChart mBarChart;

    @Override
    protected int getRootView() {
        return R.layout.fgt_analyze;
    }

    @Override
    protected void init() {

        initBarChart();
        List<Float> dateValueList = getDatas();
        showBarChart(dateValueList, "净资产收益率（%）", getResources().getColor(R.color.colorAccent));

    }

    private List<Float> getDatas() {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextFloat() * 100f);
        }
        return list;
    }

    public void showBarChart(List<Float> dateValueList, String name, int color) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, dateValueList.get(i), "xa");
            entries.add(barEntry);
        }
        // 每一个 BarDataSet 代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, name);
        initBarDataSet(barDataSet, color);

        BarData data = new BarData(barDataSet);
//        data.setValueFormatter(new CustomerPercentFormatter(data));
        mBarChart.setData(data);
    }

    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        // 是否绘制数据值
        barDataSet.setDrawValues(true);
//        barDataSet.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return value + "单位";
//            }
//        });
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(11f);
        barDataSet.setValueTextSize(7f);
        barDataSet.setValueTextColor(color);

    }

    /**
     * 初始化BarChart图表
     */
    private void initBarChart() {
        // 右下角描述内容
        Description desc = new Description();
        desc.setText("2019-06");
        mBarChart.setDescription(desc);
//        mBarChart.setNoDataText("暂无数据");
//        mBarChart.setDoubleTapToZoomEnabled(false);
//        mBarChart.setDrawBorders(false);

        mBarChart.setScaleEnabled(false);
//        mBarChart.setTouchEnabled(false);

        mBarChart.setDrawBorders(false);
        mBarChart.setDrawBarShadow(true);
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        mBarChart.setDrawGridBackground(false);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        //显示边框
        mBarChart.setDrawBorders(false);
        //设置动画效果
        mBarChart.animateY(1000, Easing.Linear);
        mBarChart.animateX(1000, Easing.Linear);

        mBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), e.getX() + "  " + e.getData(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //X轴
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);

//        xAxis.setAxisMinimum(0f);
        // 粒度
        xAxis.setGranularityEnabled(false);
        xAxis.setGranularity(1f);

        //自定义 MarkerView
//        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
//        mBarChart.setMarker(mv);

        //左侧Y轴
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        // 限制线
        leftAxis.setDrawLimitLinesBehindData(false);
        LimitLine limitLine = new LimitLine(62f, "合格线");
        limitLine.setLineColor(getResources().getColor(R.color.colorPrimary));
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);//文字颜色、大小
        limitLine.setTextSize(8f);
        limitLine.setLineWidth(1f);
        limitLine.enableDashedLine(5f, 3f, 0);  //设置虚线
        leftAxis.addLimitLine(limitLine);
        // 保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);

        //右侧Y轴
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);
        rightAxis.setEnabled(false); // 不显示右侧 y 轴

        //图例
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
}
