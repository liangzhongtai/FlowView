package com.juzi.flowview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.juzi.flowview.global.OnFlowClickListener;
import com.juzi.flowview.view.FlowView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button    mSearchBtn;
    private Button    mFilterBtn;
    private Button    mMultiLevelBtn;
    private Button    mCellBtn;
    private Button    mAnimationBtn;

    private RadioGroup mStyle0RG;
    private RadioGroup mStyle1RG;
    private RadioGroup mStyle11RG;
    private RadioGroup mStyle14RG;
    private RadioGroup mStyle2RG;
    private RadioGroup mStyle3RG;
    private RadioGroup mStyle4RG;
    private RadioGroup mStyle5RG;
    private RadioGroup mStyle6RG;
    private RadioGroup mStyle7RG;
    private RadioGroup mStyle8RG;
    private RadioGroup mStyle9RG;
    private RadioGroup mStyle10RG;
    private RadioGroup mStyle12RG;

    private FlowView mStyle0FV;
    private FlowView mStyle1FV;
    private FlowView mStyle11FV;
    private FlowView mStyle14FV;
    private FlowView mStyle2FV;
    private FlowView mStyle3FV;
    private FlowView mStyle4FV;
    private FlowView mStyle5FV;
    private FlowView mStyle6FV;
    private FlowView mStyle7FV;
    private FlowView mStyle8FV;
    private FlowView mStyle9FV;
    private FlowView mStyle10FV;
    private FlowView mStyle12FV;
    private static String[] citys = new String[]{"北京", "呼和浩特", "张家口", "南京", "乌鲁木齐", "丹东", "张家界"
            , "九寨沟", "梅州", "拉萨", "西安", "洛阳", "桂林", "成都","洛阳","杭州","西宁","兰州","苏州","张掖","开封"};
    private static String[] fruits = new String[]{"苹果", "巨峰葡萄", "沙田柚", "新疆哈密瓜", "猕猴桃", "橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","陕西蟠桃"};
    private static String[] fruitsPreAll = new String[]{"全部", "巨峰葡萄", "沙田柚", "新疆哈密瓜", "陕西猕猴桃", "江西橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","陕西蟠桃"};
    private static String[] fruitsLastAll = new String[]{"巨峰葡萄", "沙田柚", "新疆哈密瓜", "陕西猕猴桃", "江西橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","全部"};
    private static String[] fruitsClose = new String[]{"巨峰葡萄", "沙田柚", "新疆哈密瓜", "陕西猕猴桃", "江西橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","关闭"};
    private static String[] fruitsTwo = new String[]{"巨峰葡萄", "沙田柚", "新疆哈密瓜", "陕西猕猴桃", "江西橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","全选","反选"};
    private static String[] fruitsThree = new String[]{"巨峰葡萄", "沙田柚", "新疆哈密瓜", "陕西猕猴桃", "江西橘子"
            ,  "番荔枝", "广西火龙果", "八月炸", "万寿果", "海南椰子", "甘肃西瓜", "高州荔枝"
            ,"陕西水蜜桃","云南菠萝","云南红毛丹","广西芒果","海南香蕉","台湾山竹","广州佛手果"
            ,"广西菠萝","新疆香梨","山西鸭梨","陕西樱桃","甘肃李子","陕西黄杏","山东大枣"
            ,"黑龙江羊奶子","广东蛇皮果","河南黄桃","河南油桃","全选","反选","确定"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();

    }

    private void initView() {
        mSearchBtn      = (Button) findViewById(R.id.btn_search);
        mFilterBtn      = (Button) findViewById(R.id.btn_filter);
        mMultiLevelBtn  = (Button) findViewById(R.id.btn_multelevel);
        mCellBtn        = (Button) findViewById(R.id.btn_cell);
        mAnimationBtn   = (Button) findViewById(R.id.btn_animation);

        mStyle0RG   = (RadioGroup) findViewById(R.id.rg_style0);
        mStyle1RG   = (RadioGroup) findViewById(R.id.rg_style1);
        mStyle11RG  = (RadioGroup) findViewById(R.id.rg_style11);
        mStyle14RG  = (RadioGroup) findViewById(R.id.rg_style14);
        mStyle2RG   = (RadioGroup) findViewById(R.id.rg_style2);
        mStyle3RG   = (RadioGroup) findViewById(R.id.rg_style3);
        mStyle4RG   = (RadioGroup) findViewById(R.id.rg_style4);
        mStyle5RG   = (RadioGroup) findViewById(R.id.rg_style5);
        mStyle6RG   = (RadioGroup) findViewById(R.id.rg_style6);
        mStyle7RG   = (RadioGroup) findViewById(R.id.rg_style7);
        mStyle8RG   = (RadioGroup) findViewById(R.id.rg_style8);
        mStyle9RG   = (RadioGroup) findViewById(R.id.rg_style9);
        mStyle10RG  = (RadioGroup) findViewById(R.id.rg_style10);
        mStyle12RG  = (RadioGroup) findViewById(R.id.rg_style12);

        mStyle0FV   = (FlowView) findViewById(R.id.fv_style0);
        mStyle1FV   = (FlowView) findViewById(R.id.fv_style1);
        mStyle11FV  = (FlowView) findViewById(R.id.fv_style11);
        mStyle14FV  = (FlowView) findViewById(R.id.fv_style14);
        mStyle2FV   = (FlowView) findViewById(R.id.fv_style2);
        mStyle3FV   = (FlowView) findViewById(R.id.fv_style3);
        mStyle4FV   = (FlowView) findViewById(R.id.fv_style4);
        mStyle5FV   = (FlowView) findViewById(R.id.fv_style5);
        mStyle6FV   = (FlowView) findViewById(R.id.fv_style6);
        mStyle7FV   = (FlowView) findViewById(R.id.fv_style7);
        mStyle8FV   = (FlowView) findViewById(R.id.fv_style8);
        mStyle9FV   = (FlowView) findViewById(R.id.fv_style9);
        mStyle10FV  = (FlowView) findViewById(R.id.fv_style10);
        mStyle12FV  = (FlowView) findViewById(R.id.fv_style12);
    }

    private void initListener() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
        mFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FilterActivity.class));
            }
        });
        mMultiLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MulteLevelActivity.class));
            }
        });
        mCellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CellActivity.class));
            }
        });
        mAnimationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
            }
        });

        mStyle0RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common){
                    mStyle0FV.setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                            .addViewCommon(citys, R.layout.textview_flow, 1, false)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single){
                    mStyle0FV.setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingle(citys, R.layout.textview_flow, 0, 1, false)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle0FV.setAttr(R.color.color4dBlack, R.drawable.shape_rectangle_corner4_gray_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutile(citys, R.layout.textview_flow, list, 1, false)
                            .setUseSelected(true);
                }
            }
        });
        mStyle1RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_grid){
                    mStyle1FV.addViewCommon(citys, R.layout.textview_flow, 1, false)
                            .setUseGrid(true)
                            .setOneLineCount(3)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_grid){
                    mStyle1FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingle(citys, R.layout.textview_flow, 0, 1, false)
                            .setUseGrid(true)
                            .setOneLineCount(3)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_grid){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle1FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutile(citys, R.layout.textview_flow, list, 1, false)
                            .setUseGrid(true)
                            .setOneLineCount(3)
                            .setUseSelected(true);
                }
            }
        });
        mStyle11RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_autogrid){
                    mStyle11FV.addViewCommon(citys, R.layout.textview_flow, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_autogrid){
                    mStyle11FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingle(citys, R.layout.textview_flow, 0, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_autogrid){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle11FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutile(citys, R.layout.textview_flow, list, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(true);
                }
            }
        });
        mStyle14RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_autogrid_useHLine){
                    mStyle14FV.addViewCommon(citys, R.layout.textview_flow, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_autogrid_useHLine){
                    mStyle14FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingle(citys, R.layout.textview_flow, 0, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_autogrid_useHLine){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle14FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutile(citys, R.layout.textview_flow, list, 1, false)
                            .setAutoUseGrid(true)
                            .setUseSelected(true);
                }
            }
        });
        mStyle2RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_lines){
                    mStyle2FV.addViewCommon(fruits,R.layout.textview_flow, 2, 2)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_lines){
                    mStyle2FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingle(fruits, R.layout.textview_flow, 0, 2, 2)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_lines){
                    List list = new ArrayList();
                    list.add(2);

                    list.add(5);
                    list.add(7);
                    mStyle2FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutile(fruits, R.layout.textview_flow, list, 2, 2)
                            .setUseSelected(true);
                }
            }
        });
        mStyle3RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_title){
                    mStyle3FV.addViewCommonTitle(fruits, "水果", R.layout.textview_flow_title, R.layout
                            .textview_flow_news, 3)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_title){
                    mStyle3FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingleTitle(fruits, "水果", R.layout.textview_flow_title, R.layout.textview_flow_news, 0, 3)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_title){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle3FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileWithTitle(fruits, "水果", R.layout.textview_flow_title, R.layout.textview_flow_news, list, 3)
                            .setUseSelected(true);
                }
            }
        });
        mStyle4RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_close){
                    mStyle4FV
                            .setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                            .setButtonAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_red_solid)
                            .addViewCommonClose(fruitsClose, R.layout.textview_flow, 4, false)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_close){
                    mStyle4FV
                            .setButtonAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_red_solid)
                            .setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewSingleClose(fruitsClose, R.layout.textview_flow, 0, 4, false)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_close){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle4FV
                            .setButtonAttr(R.color.colorWhite,R.drawable.shape_rectangle_corner4_red_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileClose(fruitsClose, R.layout.textview_flow, list, 4, false)
                            .setUseSelected(true);
                }
            }
        });
        mStyle5RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_all_pre){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle5FV.setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setButtonAttr(R.color.colorWhite,R.drawable.shape_rectangle_corner4_blue_solid)
                            .addViewMutileAll(fruitsPreAll, R.layout.textview_flow, list, 5, true);
                }else if(checkedId==R.id.rb_all_last){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle5FV.setAttr(R.color.color4dBlack,R.drawable.shape_rectangle_corner4_gray_solid)
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setButtonAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_blue_solid)
                            .addViewMutileAll(fruitsLastAll, R.layout.textview_flow, list, 5, false);
                }
            }
        });
        mStyle6RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_all_two){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle6FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileTwoButton(fruitsTwo
                                    , R.layout.textview_flow_three_button, R.layout.textview_flow_three_button
                                    , R.layout.textview_flow, list, 6);
                }else if(checkedId==R.id.rb_all_two_with_title){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle6FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileTwoButtonWithTitle(fruitsTwo, "水果"
                                    , R.layout.textview_flow_title, R.layout.textview_flow_three_button
                                    , R.layout.textview_flow_three_button, R.layout.textview_flow, list, 6);
                }
            }
        });
        mStyle7RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_mutile_three){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle7FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileThreeButton(fruitsThree
                                    , R.layout.textview_flow_three_button, R.layout.textview_flow_three_button
                                    , R.layout.textview_flow_three_button
                                    , R.layout.textview_flow, list, 7);
                }else if(checkedId==R.id.rb_mutile_three_with_title){
                    List list = new ArrayList();
                    list.add(2);
                    list.add(5);
                    list.add(7);
                    mStyle7FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .addViewMutileThreeButtonWithTitle(fruitsThree, "水果"
                                    , R.layout.textview_flow_title, R.layout.textview_flow_three_button
                                    , R.layout.textview_flow_three_button
                                    , R.layout.textview_flow_three_button, R.layout.textview_flow, list, 7);
                }
            }
        });
        mStyle8RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_single_title_common){
                    mStyle8FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setOneLineTitle(false).addViewSingleTitle(fruits, "水果"
                            , R.layout.textview_flow_title, R.layout.textview_flow, 0, 3);
                }else if(checkedId==R.id.rb_single_title_oneline){
                    mStyle8FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setOneLineTitle(true).addViewSingleTitle(fruits, "水果"
                            , R.layout.textview_flow_title, R.layout.textview_flow, 0, 3);
                }
            }
        });
        mStyle9RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_horizon){
                    mStyle9FV.addViewCommon(fruits, R.layout.textview_flow, 9, true)
                            .setHorizonDivisor(12)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_horizon){
                    mStyle9FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setUseSelected(true).addViewSingle(fruits, R.layout.textview_flow, 0, 9, true)
                            .setHorizonDivisor(12)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_horizon){
                    List<Integer> list = new ArrayList<>();
                    list.add(3);
                    list.add(4);
                    mStyle9FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setUseSelected(true).addViewMutile(fruits, R.layout.textview_flow, list, 9, true)
                            .setHorizonDivisor(12)
                            .setUseSelected(true);
                }
            }
        });
        mStyle10RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_horizon_grid){
                    mStyle10FV
                            .setOneLineCount(15)
                            .addViewCommon(fruits, R.layout.textview_flow, 9, true)
                            .setUseGrid(true)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_horizon_grid){
                    mStyle10FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setOneLineCount(15)
                            .addViewSingle(fruits, R.layout.textview_flow, 0, 9, true)
                            .setUseGrid(true)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_horizon_grid){
                    List<Integer> list = new ArrayList<>();
                    list.add(3);
                    list.add(4);
                    mStyle10FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setOneLineCount(15)
                            .addViewMutile(fruits, R.layout.textview_flow, list, 9, true)
                            .setUseGrid(true)
                            .setUseSelected(true);
                }
            }
        });
        mStyle12RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_common_horizon_autogrid){
                    mStyle12FV.addViewCommon(fruits,R.layout.textview_flow, 9, true)
                            .setLineCount(4)
                            .setUseSelected(false);
                }else if(checkedId==R.id.rb_single_horizon_autogrid){
                    mStyle12FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setUseSelected(true).addViewSingle(fruits, R.layout.textview_flow, 0, 9, true)
                            .setLineCount(4)
                            .setUseSelected(true);
                }else if(checkedId==R.id.rb_mutile_horizon_autogrid){
                    List<Integer> list = new ArrayList<>();
                    list.add(3);
                    list.add(4);
                    mStyle12FV
                            .setSelectedAttr(R.color.colorWhite, R.drawable.shape_rectangle_corner4_green_solid)
                            .setUseSelected(true).addViewMutile(fruits, R.layout.textview_flow, list, 9, true)
                            .setLineCount(4)
                            .setUseSelected(true);
                }
            }
        });
    }

    private void initData() {
    }



}
