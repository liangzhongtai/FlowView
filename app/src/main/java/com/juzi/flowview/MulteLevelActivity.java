package com.juzi.flowview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lzt.flowviews.global.OnFlowClickListener;
import com.lzt.flowviews.view.FlowView;

/**
 * Created by liangzhongtai on 2017/9/18.
 */
public class MulteLevelActivity extends AppCompatActivity{
    private Button mShowBtn;
    private FlowView mProviceFV;
    private FlowView mCityFV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilevel);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mShowBtn    = (Button) findViewById(R.id.btn_show);
        mProviceFV  = (FlowView) findViewById(R.id.fv_province);
        mCityFV     = (FlowView) findViewById(R.id.fv_city);
    }

    private void initListener() {
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProviceFV
                        .setAttr(R.color.color8dBlack,R.color.colorb3Gray)
                        .setSelectedAttr(R.color.color4dBlack, R.color.colora3Gray)
                        .setAttrPadding(0,0,0,0)
                        .setAttrSpace(0,0)
                        .addViewSingle(new String[]{"广西", "广东", "河南", "安徽"}, R.layout.textview_flow_province, 0, 1, false)
                        .setUseGrid(true)
                        .setOneLineCount(1)
                        .setUseSelected(true);
                mCityFV.setAttr(R.color.color4dBlack,R.color.colorb3Gray)
                        .setSelectedAttr(R.color.colorWhite, R.color.color32Green)
                        .setAttrPadding(14,0,14,0)
                        .addViewSingle(new String[]{"南宁", "柳州", "桂林", "玉林", "百色", "贵港", "防城港", "北海", "梧州","钦州"}, R.layout
                                .textview_flow_city, 0, 1, false)
                        .setUseGrid(true)
                        .setOneLineCount(3)
                        .setUseSelected(true);
            }
        });
        mProviceFV.setOnFlowClickListener(new OnFlowClickListener() {
            @Override
            public void onClickedView(View view, Object value, int position, int type) {
                String[] citys = null;
                if (position == 0) {
                    citys = new String[]{"南宁", "柳州", "桂林", "玉林", "百色", "贵港", "防城港", "北海", "梧州", "钦州"};
                } else if (position == 1) {
                    citys = new String[]{"广州", "深圳", "珠海", "佛山", "东莞", "河源", "惠州", "梅州", "潮汕", "汕头", "湛江"};
                } else if (position == 2) {
                    citys = new String[]{"郑州", "洛阳", "开封", "安阳", "漯河", "驻马店", "平阳", "新郑", "三门峡"};
                } else if (position == 3) {
                    citys = new String[]{"合肥", "徽州", "安庆"};
                }
                mCityFV.addViewSingle(citys, R.layout.textview_flow_city, 0, 1,
                        false)
                        .setUseGrid(true)
                        .setOneLineCount(3)
                        .setUseSelected(true);
            }
        });
        mCityFV.setOnFlowClickListener(new OnFlowClickListener() {
            @Override
            public void onClickedView(View view, Object value, int position, int type) {

            }
        });
    }

    private void initData() {

    }
}
