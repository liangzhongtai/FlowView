package com.juzi.flowview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.juzi.flowview.global.OnFlowClickListener;
import com.juzi.flowview.view.FlowView;

/**
 * Created by liangzhongtai on 2017/9/18.
 */
public class FilterActivity extends AppCompatActivity{
    private Button mShowBtn;
    private Button mUseGridBtn;
    private FlowView mPlaceFV;
    private FlowView mPriceFV;
    private FlowView mWeightFV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mShowBtn    = (Button) findViewById(R.id.btn_show);
        mUseGridBtn = (Button) findViewById(R.id.btn_grid);
        mPlaceFV    = (FlowView) findViewById(R.id.fv_place);
        mPriceFV    = (FlowView) findViewById(R.id.fv_price);
        mWeightFV   = (FlowView) findViewById(R.id.fv_weight);
    }

    private void initListener() {
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceFV.setOneLineTitle(true)
                        .setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutileWithTitle(new String[]{"山东", "广西", "海南", "四川", "云南", "贵州", "新疆", "辽宁"},
                                "产地:", R.layout.textview_flow_title, R.layout.textview_flow, null, 1);
                mPriceFV.setOneLineTitle(true)
                        .setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutileWithTitle(new String[]{"1~15", "15~40", "40~70", "70~100", "100~200", "200以上"},
                                "价格/元:", R.layout.textview_flow_title, R.layout.textview_flow, null, 2);
                mWeightFV.setOneLineTitle(true)
                        .setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                        .addViewMutileWithTitle(new String[]{"100以内", "100~200", "200~350", "350~500",
                                "500~1000", "1000以上"}, "单重/g:", R.layout.textview_flow_title
                                , R.layout.textview_flow, null, 3);
            }
        });

        mUseGridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceFV.setAutoUseGrid(mPlaceFV.useAutoGrid=!mPlaceFV.useAutoGrid);
                mPriceFV.setAutoUseGrid(mPriceFV.useAutoGrid = !mPriceFV.useAutoGrid);
                mWeightFV.setAutoUseGrid(mWeightFV.useAutoGrid = !mWeightFV.useAutoGrid);

                mPlaceFV.requestLayout();
                mPriceFV.requestLayout();
                mWeightFV.requestLayout();
            }
        });


    }

    private void initData() {

    }
}
