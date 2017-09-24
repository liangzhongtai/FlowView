package com.juzi.flowview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.juzi.flowview.global.FlowAnimateUtils;
import com.juzi.flowview.global.OnFlowClickListener;
import com.juzi.flowview.view.FlowView;

/**
 * Created by liangzhongtai on 2017/9/20.
 */
public class AnimationActivity extends AppCompatActivity{
    private Button mRandomBtn;
    private Button mDelayBtn;
    private Button mScaleBtn;
    private FlowView mAnimationFV;
    private static String[] fruits = new String[]{"苹果", "巨峰葡萄", "沙田柚", "新疆哈密瓜", "猕猴桃", "橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","陕西蟠桃"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mRandomBtn   = (Button) findViewById(R.id.btn_animation_random);
        mDelayBtn    = (Button) findViewById(R.id.btn_animation_delay);
        mScaleBtn    = (Button) findViewById(R.id.btn_animation_scale);
        mAnimationFV = (FlowView) findViewById(R.id.fv_animation);
    }

    private void initListener() {
        mRandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationFV
                        .setAnimationType(FlowView.ANIMATION_ALPAH_RANDOM)
                        .setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutile(fruits, R.layout.textview_flow, null, 1, false)
                        .setUseSelected(true);
            }
        });
        mDelayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationFV
                        .setAnimationType(FlowView.ANIMATION_ALPHA_DELAY)
                        .setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutile(fruits, R.layout.textview_flow, null, 1, false)
                        .setUseSelected(true);
            }
        });
        mScaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimationFV
                        .setAnimationType(FlowView.ANIMATION_SCALE_RANDOM)
                        .setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutile(fruits, R.layout.textview_flow, null, 1, false)
                        .setUseSelected(true);
            }
        });
        mAnimationFV.setOnFlowClickListener(new OnFlowClickListener() {
            @Override
            public void startAnimation(View child, int index) {
                FlowAnimateUtils.startNopeAndTada(AnimationActivity.this,child);
            }
        });
    }

    private void initData() {

    }
}
