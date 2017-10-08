package com.juzi.flowview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.lzt.flowviews.global.OnFlowClickListener;
import com.lzt.flowviews.view.FlowView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liangzhongtai on 2017/9/18.
 */
public class SearchActivity extends AppCompatActivity{
    private EditText    mET;
    private ImageButton mIB;
    private FlowView    mFV;
    private List<String> historys;
    private int lastSelected = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mET = (EditText)    findViewById(R.id.et);
        mIB = (ImageButton) findViewById(R.id.ib);
        mFV = (FlowView)    findViewById(R.id.fv);
    }

    private void initListener() {
        mIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(historys.contains(mET.getText().toString()))return;
                historys.add(mET.getText().toString());
                mFV.setOneLineTitle(true).addViewSingleTitle(historys.toArray(new String[]{}), "历史记录:"
                        , R.layout.textview_flow_title, R.layout.textview_flow, lastSelected, 3)
                    .setUseSelectedAttr(false);
            }
        });

        mFV.setOnFlowClickListener(new OnFlowClickListener() {
            @Override
            public void onClickedView(View view, Object value, int position, int type) {
                lastSelected = position;
                mET.setText((historys.toArray(new String[]{})[position]));
            }
        });
    }


    private void initData() {
        historys = new ArrayList<>();
        mFV.setOneLineTitle(true).addViewSingleTitle(historys.toArray(new String[]{}), "历史记录:"
                , R.layout.textview_flow_title, R.layout.textview_flow, lastSelected, 3)
            .setUseSelectedAttr(false);
    }
}
