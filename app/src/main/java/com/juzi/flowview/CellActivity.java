package com.juzi.flowview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.juzi.flowview.view.FlowView;

/**
 * Created by liangzhongtai on 2017/9/19.
 */
public class CellActivity extends AppCompatActivity{
    private RadioGroup mRG;
    private CheckBox mFirstHCB;
    private CheckBox mLastHCB;
    private CheckBox mFirstVCB;
    private CheckBox mLastVCB;
    private CheckBox mCoverHCB;
    private CheckBox mCoverVCB;
    private CheckBox mCoverTitleCB;

    private FlowView mFV;
    private FlowView mTitleFV;

    private static String[] fruits = new String[]{"苹果", "巨峰葡萄", "沙田柚", "新疆哈密瓜", "猕猴桃", "橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"/*
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","陕西蟠桃"*/};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mRG         = (RadioGroup) findViewById(R.id.rg_lineType);
        mFirstHCB   = (CheckBox) findViewById(R.id.cb_firstHLine);
        mLastHCB    = (CheckBox) findViewById(R.id.cb_lastHLine);
        mFirstVCB   = (CheckBox) findViewById(R.id.cb_firstVLine);
        mLastVCB    = (CheckBox) findViewById(R.id.cb_lastVLine);
        mCoverHCB   = (CheckBox) findViewById(R.id.cb_coverHLine);
        mCoverVCB   = (CheckBox) findViewById(R.id.cb_coverVLine);
        mCoverTitleCB = (CheckBox) findViewById(R.id.cb_coverTitleLine);

        mFV        = (FlowView) findViewById(R.id.fv_hv);
        mTitleFV   = (FlowView) findViewById(R.id.fv_hv_title);
    }


    private void initListener() {
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_solid){
                    mFV .setLineType(FlowView.LTYPE_SOLID)
                        .setAttr(R.color.color4dBlack, android.R.color.transparent)
                        .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                        .setAttrPadding(7, 0, 7, 0)
                        .setAttrSpace(0, 0)
                        .setUseSelected(true)
                        .addViewSingle(fruits, R.layout.textview_flow_cell, 0, 1, false);
                    mTitleFV.setLineType(FlowView.LTYPE_SOLID)
                            .setAttr(R.color.color4dBlack,android.R.color.transparent)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                            .setAttrPadding(7, 0, 7, 0)
                            .setAttrSpace(0, 0)
                            .setOneLineTitle(true).addViewSingleTitle(fruits, "水果:"
                            , R.layout.textview_flow_title, R.layout.textview_flow_cell, 0, 3);
                }else if (checkedId == R.id.rb_dash){
                    mFV .setLineType(FlowView.LTYPE_DASH)
                            .setAttr(R.color.color4dBlack,android.R.color.transparent)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                            .setAttrPadding(7, 0, 7, 0)
                            .setAttrSpace(0, 0)
                            .setUseSelected(true)
                            .addViewSingle(fruits, R.layout.textview_flow_cell, 0, 1, false);

                    mTitleFV.setLineType(FlowView.LTYPE_DASH)
                            .setAttr(R.color.color4dBlack, android.R.color.transparent)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_green_solid)
                            .setAttrPadding(7, 0, 7, 0)
                            .setAttrSpace(0, 0)
                            .setOneLineTitle(true).addViewSingleTitle(fruits, "水果:"
                            , R.layout.textview_flow_title, R.layout.textview_flow_cell, 0, 3)
                            .setUseSelected(true);
                }
            }
        });
        mFirstHCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.firstHLine = isChecked;
                mFV.requestLayout();
                mTitleFV.firstHLine = isChecked;
                mTitleFV.requestLayout();
            }
        });

        mLastHCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.lastHLine = isChecked;
                mFV.requestLayout();
                mTitleFV.lastHLine = isChecked;
                mTitleFV.requestLayout();
            }
        });

        mFirstVCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.firstVLine = isChecked;
                mFV.requestLayout();
                mTitleFV.firstVLine = isChecked;
                mTitleFV.requestLayout();
            }
        });

        mLastVCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.lastVLine = isChecked;
                mFV.requestLayout();
                mTitleFV.lastVLine = isChecked;
                mTitleFV.requestLayout();
            }
        });

        mCoverHCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.hLineAllCover = isChecked;
                mFV.requestLayout();
                mTitleFV.hLineAllCover = isChecked;
                mTitleFV.requestLayout();
            }
        });
        mCoverVCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.vLineAllCover = isChecked;
                mFV.requestLayout();
                mTitleFV.vLineAllCover = isChecked;
                mTitleFV.requestLayout();
            }
        });

        mCoverTitleCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFV.titleLineAllCover = isChecked;
                mFV.requestLayout();
                mTitleFV.titleLineAllCover = isChecked;
                mTitleFV.requestLayout();
            }
        });
    }

    private void initData() {

    }
}
