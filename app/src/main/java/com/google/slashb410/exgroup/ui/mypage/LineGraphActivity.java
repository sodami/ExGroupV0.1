package com.google.slashb410.exgroup.ui.mypage;//package com.google.slashb410.exgroup.ui.mypage;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.ViewGroup;
//
//import com.google.slashb410.exgroup.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Tacademy on 2017-02-03.
// */
//
//public class LineGraphActivity extends Activity {
//
//    private ViewGroup layoutGraphView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_home);
//
//        layoutGraphView = (ViewGroup) findViewById(R.id.tab1);
//        setLineGraph();
//    }
//
//    public void setLineGraph() {
//        //all setting
//        LineGraphVO vo = makeLineGraphAllSetting();
//
//        //default setting
////		LineGraphVO vo = makeLineGraphDefaultSetting();
//
//        layoutGraphView.addView(new LineGraphView(this, vo));
//    }
//
//
////    /**
////     * make simple line graph
////     * @return
////     */
////    public LineGraphVO makeLineGraphDefaultSetting() {
////
////        String[] legendArr 	= {"1","2","3","4","5"};
////        float[] graph1 		= {500,100,300,200,100};
////        float[] graph2 		= {000,100,200,100,200};
////        float[] graph3 		= {200,500,300,400,000};
////
////        List<LineGraph> arrGraph 		= new ArrayList<LineGraph>();
////        arrGraph.add(new LineGraph("android", 0xaa66ff33, graph1));
////        arrGraph.add(new LineGraph("ios", 0xaa00ffff, graph2));
////        arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));
////
////        LineGraphVO vo = new LineGraphVO(legendArr, arrGraph);
////        return vo;
////    }
//
//    /**
//     * make line graph using options
//     * @return
//     */
//    public LineGraphVO makeLineGraphAllSetting() {
//        //BASIC LAYOUT SETTING
//        //padding
//        int paddingBottom 	= LineGraphVO.DEFAULT_PADDING;
//        int paddingTop 		= LineGraphVO.DEFAULT_PADDING;
//        int paddingLeft 	= LineGraphVO.DEFAULT_PADDING;
//        int paddingRight 	= LineGraphVO.DEFAULT_PADDING;
//
//        //graph margin
//        int marginTop 		= LineGraphVO.DEFAULT_MARGIN_TOP;
//        int marginRight 	= LineGraphVO.DEFAULT_MARGIN_RIGHT;
//
//        //max value
//        int maxValue 		= LineGraphVO.DEFAULT_MAX_VALUE;
//
//        //increment
//        int increment 		= LineGraphVO.DEFAULT_INCREMENT;
//
//        //GRAPH SETTING
//        String[] legendArr 	= {"MON","TUE","WED","THU","FRI","SAT","SUN"};
//        float[] graph1 		= {55,57,48,53,50,57,64};
////        float[] graph2 		= {000,100,200,100,200};
////        float[] graph3 		= {200,500,300,400,000};
//
//        List<LineGraph> arrGraph 		= new ArrayList<LineGraph>();
//
//        arrGraph.add(new LineGraph("android", 0xaa66ff33, graph1, R.mipmap.ic_launcher));
//        // 친구랑 경쟁 몸무게
////        arrGraph.add(new LineGraph("ios", 0xaa00ffff, graph2));
////        arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));
//
//        LineGraphVO vo = new LineGraphVO(
//                paddingBottom, paddingTop, paddingLeft, paddingRight,
//                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);
//
//        //set animation
//        vo.setAnimation(new GraphAnimation(GraphAnimation.DEFAULT_DURATION, GraphAnimation.DEFAULT_DURATION));
//        //set graph name box
//        vo.setGraphNameBox(new GraphNameBox());
//        //set draw graph region
////		vo.setDrawRegion(true);
//
//        //use icon
////		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
////		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
////		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));
//
////		LineGraphVO vo = new LineGraphVO(
////				paddingBottom, paddingTop, paddingLeft, paddingRight,
////				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
//        return vo;
//    }
//
//}