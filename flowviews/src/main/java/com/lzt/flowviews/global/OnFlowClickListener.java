package com.lzt.flowviews.global;


import android.view.View;

/**
 * Created by Liangzhongtai on 2016/6/12.
 */
public abstract class OnFlowClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {}
    public void onClickedView(View view,Object value,int position,int type){}
    public void startAnimation(View child, int index) {}
}
