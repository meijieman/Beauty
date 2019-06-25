package com.major.beauty.ui;

import android.graphics.Color;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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
    @BindView(R.id.pc_analyze)
    PieChart mPieChart;
    @BindView(R.id.lc_analyze)
    LineChart mLineChart;
    @BindView(R.id.rc_analyze)
    RadarChart mRadarChart;

    @Override
    protected int getRootView() {
        return R.layout.fgt_analyze;
    }

    @Override
    protected void init() {

        initBarChart();
        List<Float> dateValueList = getDatas();
        showBarChart(dateValueList, "净资产收益率（%）", getResources().getColor(R.color.colorAccent));

        initPieChart();

        initLineChart();

        initRadarChart();
    }

    // 折线图
    private void initLineChart() {
        mLineChart.setScaleEnabled(false);
        //设置样式
        YAxis rightAxis = mLineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);

        YAxis leftAxis = mLineChart.getAxisLeft();
        //设置图表左边的y轴禁用
//        leftAxis.setEnabled(false);
        //设置x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大后x轴标签重绘

        //透明化图例
        Legend legend = mLineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);

        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);

        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            entries.add(new Entry(i, new Random().nextInt(300)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
        dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
        dataSet.setLineWidth(1f);//线条宽度
        //3.chart设置数据
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);

    }

    // 雷达图
    private void initRadarChart() {
        XAxis xAxis = mRadarChart.getXAxis();
        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setAxisMinimum(0);
        List<RadarEntry> radarEntries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            radarEntries.add(new RadarEntry((i+1)*10));
        }
        RadarDataSet dataSet = new RadarDataSet(radarEntries,"");
        dataSet.setColor(Color.RED);
        RadarData radarData = new RadarData(dataSet);
        mRadarChart.animateXY(1000,1000);
        mRadarChart.setData(radarData);



    }


    // 饼图
    private void initPieChart() {
        Description description = new Description();
        description.setText("");
        mPieChart.setDescription(description);
        // 实心饼图
//        mPieChart.setHoleRadius(0f);
//        mPieChart.setTransparentCircleRadius(0f);

        List<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(30f, "耗材a"));
        data.add(new PieEntry(60f, "耗材b"));
        data.add(new PieEntry(10f, "其他"));

        PieDataSet dataSet = new PieDataSet(data, "");
        dataSet.setDrawValues(true);
        dataSet.setValueFormatter(new PercentFormatter());

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.colorAccent));
        colors.add(getResources().getColor(R.color.colorPrimary));
        colors.add(getResources().getColor(R.color.colorPrimaryDark));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);

        mPieChart.setData(pieData);


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

        mBarChart.setDrawBorders(true);
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
