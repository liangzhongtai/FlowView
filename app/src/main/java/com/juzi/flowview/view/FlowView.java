package com.juzi.flowview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juzi.flowview.R;
import com.juzi.flowview.global.FlowAnimateUtils;
import com.juzi.flowview.global.Global;
import com.juzi.flowview.global.OnFlowClickListener;

import java.util.ArrayList;
import java.util.List;

public class FlowView extends ViewGroup {
	public final static int LTYPE_SOLID = 0;
	public final static int LTYPE_DASH  = 1;
	public final static int LTYPE_CIRCLE= 2;
	public Paint paint;

	public List<List<View>> allChildViewLines = new ArrayList<>();
	public List<Integer> oddSpaceAllLines = new ArrayList<>();
	public List<Float> hLineYs;
	public List<Float> vLineXs;
	public boolean useAutoGrid;
	public boolean useGrid;
	public boolean useHLine;
	public boolean useVLine;
	public int padding_leftLine;
	public int padding_rightLine;
	public int padding_topLine;
	public int padding_bottomLine;
	public int hLineColor;
	public int vLineColor;
	public float hLineWidth;
	public float vLineWidth;
	public float oneLineHeight;
	public float oneLineWidth;
	public boolean firstHLine;
	public boolean lastHLine;
	public boolean firstVLine;
	public boolean lastVLine;
	public boolean hLineAllCover;
	public boolean vLineAllCover;
	public boolean titleLineAllCover;

	public int lineType;
	public String dash;
	public float[] dashedArray;
	public float circleRadius;
	public float circleInterval;

	public int oneLineMaxWidth;
	public int maxTextLine = 1;
	public int oneLineCount;
	public int lineCount;
	public float horizionWidth;
	public int padding_left;
	public int padding_top;
	public int padding_right;
	public int padding_bottom;
	public int h_Space;
	public int v_Space;
	public int selectedType;
	public boolean useSelected = true;
	public boolean allAtStart;
	public boolean closeAtStart;
	public boolean confimrAtStart;
	public boolean useSelectedAttr = true;
	public boolean oneLineTitle;
	public final static int COMMON_WITH_CLOSE = -2;
	public final static int SINGLE = 0;
	public final static int SINGLE_WITH_CLOSE = 1;
	public final static int SINGLE_WITH_CONFIRM = 2;

	public final static int SINGLE_WITH_TITLE = 3;
	public final static int SINGLE_WITH_TITLE_CLOSE = 4;
	public final static int SINGLE_WITH_TITLE_CONFIRM = 5;

	public final static int MULTIPLE = 6;
	public final static int MULTIPLE_WITH_CLOSE = 7;
	public final static int MULTIPLE_WITH_ALL = 8;

	public final static int MULTIPLE_WITH_TITLE = 9;
	public final static int MULTIPLE_WITH_TITLE_CLOSE = 10;
	public final static int MULTIPLE_WITH_TITLE_ALL = 11;

	public final static int MULTIPLE_WITH_TWO_BUTTON = 12;
	public final static int MULTIPLE_WITH_THREE_BUTTON = 13;
	public final static int MULTIPLE_WITH_TITLE_TWO_BUTTON = 14;
	public final static int MULTIPLE_WITH_TITLE_THREE_BUTTON = 15;

	public final static int SINGLE_GROUP = 16;
	public final static int SINGLE_GROUP_WITH_TITLE = 17;
	public final static int MULTIPLE_GROUP = 18;
	public final static int MULTIPLE_GROUP_WITH_TITLE = 19;
	public final static int MULTIPLE_GROUP_WITH_TITLE_ALL = 20;
	public final static int MULTIPLE_GROUP_WITH_TITLE_TWO_BUTTON = 21;
	public final static int ONE = 18;
	private View lastView;
	private int selectedAll = -1;
	private int selectedClose = -1;
	private int selectedInvert = -1;
	private int selectedConfirm = -1;
	private int titleIndex = -1;
	public int lastSelected = -1;
	public int limitLines = -1;
	public boolean horizon;
	public int horizonDivisor = -1;
	public int magnification = -1;
	private List<Integer> lastSelecteds;
	public List<Integer> groupPositions;
	public int[] hotSelecteds;
	public List<List<Integer>> groupLastSelecteds;
	private int color;
	private int bgRes;
	private int bgColor;
	private int selectedColor;
	private int selectedBgRes;
	private int selectedBgColor;
	private int hotColor;
	private int hotBgRes;
	private int hotBgColor;
	private int buttonColor;
	private int buttonBgRes;
	private int buttonBgColor;
	private int animation_type;
	private OnFlowClickListener itemListener;
	public final static int ANIMATION_ALPHA_DELAY   = 1;
	public final static int ANIMATION_ALPAH_RANDOM  = 2;
	public final static int ANIMATION_SCALE_RANDOM  = 3;
	public final static int ANIMATION_FREE 			= 4;
	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			int selected = (int) v.getTag(R.id.fl_position);
			if(useSelected) {

				if(itemListener!=null)
				itemListener.startAnimation(v,selected);

				boolean singleType = selectedType==COMMON_WITH_CLOSE||selectedType == SINGLE||
						selectedType == SINGLE_WITH_TITLE ||selectedType==SINGLE_WITH_CLOSE||
						selectedType==SINGLE_WITH_CONFIRM ||selectedType==SINGLE_WITH_TITLE_CLOSE||
						selectedType==SINGLE_WITH_TITLE_CONFIRM;
				boolean closeAndConfirm = selected==selectedClose||selected==selectedConfirm;
				if(closeAndConfirm)setVisibility(View.GONE);
				if (lastView != null) {
					if (singleType&& lastSelected != selected&&!closeAndConfirm&&useSelectedAttr) {
						((TextView) lastView).setTextColor(color);
						if(bgColor>0)lastView.setBackgroundColor(bgColor);
						if(bgRes>0) lastView.setBackgroundResource(bgRes);
					}
				}
				lastView = v;
				if(!closeAndConfirm)lastSelected = selected;
				if (singleType&&!closeAndConfirm&&useSelectedAttr) {
					((TextView) v).setTextColor(selectedColor);
					if(selectedBgColor>0)v.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0) v.setBackgroundResource(selectedBgRes);
				} else if(!singleType){
					boolean hasButtons = (selectedType==MULTIPLE_WITH_THREE_BUTTON
							||selectedType==MULTIPLE_WITH_TWO_BUTTON
							||selectedType==MULTIPLE_WITH_TITLE_TWO_BUTTON
							||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON)?true:false;
					boolean hasTitle = selectedType==MULTIPLE_WITH_TITLE
							||selectedType==MULTIPLE_WITH_TITLE_ALL
							||selectedType==MULTIPLE_WITH_TITLE_CLOSE
							||selectedType==MULTIPLE_WITH_TITLE_TWO_BUTTON
							||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON;
					boolean threeButton = selectedType==MULTIPLE_WITH_THREE_BUTTON
							||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON;

					if(hasButtons&&selected==selectedAll){
						int count = getChildCount();
						int start = hasTitle?1:0;
						int end = threeButton?(count - 3):(count-2);

						for (int i = start; i < end; i++) {
							if(useSelectedAttr) {
								((TextView) getChildAt(i)).setTextColor(selectedColor);
								if(selectedBgColor>0)getChildAt(i).setBackgroundColor(selectedBgColor);
								if(selectedBgRes>0) getChildAt(i).setBackgroundResource(selectedBgRes);
							}
							if(!lastSelecteds.contains(i)) {
								lastSelecteds.add(i);
							}
						}
					}else if(hasButtons&&selected==selectedInvert){
						int count = getChildCount();
						int start = hasTitle?1:0;
						int end = threeButton?(count - 3):(count-2);

						for (int i = start; i < end; i++) {
							if (lastSelecteds.contains(i)){
								((TextView) getChildAt(i)).setTextColor(color);
								if(bgColor>0)getChildAt(i).setBackgroundColor(bgColor);
								if(bgRes>0) getChildAt(i).setBackgroundResource(bgRes);
								int length = lastSelecteds.size();
								for(int j=0;j<length;j++){
									if(lastSelecteds.get(j)==i){
										lastSelecteds.remove(j);
										break;
									}
								}
							}else{
								if(useSelectedAttr) {
									((TextView) getChildAt(i)).setTextColor(selectedColor);
									if(selectedBgColor>0)getChildAt(i).setBackgroundColor(selectedBgColor);
									if(selectedBgRes>0)getChildAt(i).setBackgroundResource(selectedBgRes);
								}
								lastSelecteds.add(i);
							}
						}
					}else if(selected==selectedConfirm){
						setVisibility(View.GONE);
					}else if(selected==selectedClose){
						setVisibility(View.GONE);
					}else if(lastSelecteds.contains(selected)) {
						if (selected == selectedAll) {
							int count = getChildCount();
							int start = hasTitle?1:0;

							for (int i = start; i < count; i++) {
								((TextView) getChildAt(i)).setTextColor(color);
								if(bgColor>0)getChildAt(i).setBackgroundColor(bgColor);
								if(bgRes>0)getChildAt(i).setBackgroundResource(bgRes);
							}
							lastSelecteds.clear();
						}else{
							((TextView) v).setTextColor(color);
							if(bgColor>0)v.setBackgroundColor(bgColor);
							if(bgRes>0) v.setBackgroundResource(bgRes);
							int length = lastSelecteds.size();
							for(int i=length-1;i>=0;i--) {
								if(lastSelecteds.get(i)==selected) {
									lastSelecteds.remove(i);
									break;
								}
							}
						}
					} else {
						if (selected == selectedAll) {
							int count = allAtStart?getChildCount():getChildCount()-1;
							int start = hasTitle?1:0;

							((TextView) getChildAt(selectedAll)).setTextColor(buttonColor);
							if(buttonBgColor>0)getChildAt(selectedAll).setBackgroundColor(buttonBgColor);
							if(buttonBgRes>0) getChildAt(selectedAll).setBackgroundResource(buttonBgRes);
							lastSelecteds.add(selectedAll);
							for (int i = start+selectedAll==0?1:0; i < count; i++) {
								if(useSelectedAttr) {
									((TextView) getChildAt(i)).setTextColor(selectedColor);
									if(selectedBgColor>0)getChildAt(i).setBackgroundColor(selectedBgColor);
									if(selectedBgRes>0) getChildAt(i).setBackgroundResource(selectedBgRes);
								}
								if (!lastSelecteds.contains(i)) lastSelecteds.add(i);
							}
						} else {
							if(useSelectedAttr) {
								((TextView) v).setTextColor(selectedColor);
								if(selectedBgColor>0)v.setBackgroundColor(selectedBgColor);
								if(selectedBgRes>0) v.setBackgroundResource(selectedBgRes);
							}
							lastSelecteds.add(selected);
						}
					}
				}
			}
			if(itemListener!=null)itemListener
					.onClickedView(v, v.getTag(R.id.fl_value),(int) v.getTag(R.id.fl_position), (Integer) v.getTag(R.id.fl_type));
		}
	};
	public FlowView(Context context) {
		this(context, null);
	}

	public FlowView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}



	@SuppressWarnings("ResourceType")
	public void init(AttributeSet attrs){
		if(attrs != null) {
			TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.FlowView);
			float density	= getResources().getDisplayMetrics().density;
			useAutoGrid     = array.getBoolean(R.styleable.FlowView_fv_useAutoGrid, false);
			useGrid			= array.getBoolean(R.styleable.FlowView_fv_useGrid, false);
			if(useGrid)useAutoGrid = false;
			oneLineCount	= array.getInt(R.styleable.FlowView_fv_oneLineCount, 4);
			lineCount		= array.getInt(R.styleable.FlowView_fv_lineCount, 0);
			if(lineCount>0){
				useGrid = false;
				useAutoGrid = true;
				horizon = true;
			}
			horizionWidth   = getResources().getDisplayMetrics().density*array.getFloat(R.styleable
					.FlowView_fv_horizionWidth,getResources().getDisplayMetrics().widthPixels);
			maxTextLine		= array.getInt(R.styleable.FlowView_fv_maxTextLine, 1);

			useHLine 		= array.getBoolean(R.styleable.FlowView_fv_useHLine, false);
			useVLine		= array.getBoolean(R.styleable.FlowView_fv_useVLine, false);
			padding_leftLine  = (int) array.getDimension(R.styleable.FlowView_fv_padding_leftLine,0);
			padding_rightLine = (int) array.getDimension(R.styleable.FlowView_fv_padding_rightLine,0);
			padding_topLine   = (int) array.getDimension(R.styleable.FlowView_fv_padding_topLine,0);
			padding_bottomLine= (int) array.getDimension(R.styleable.FlowView_fv_padding_topLine,0);
			hLineColor	    = array.getColor(R.styleable.FlowView_fv_hLineColor, getResources().getColor(android.R.color.darker_gray));
			vLineColor		= array.getColor(R.styleable.FlowView_fv_vLineColor, getResources().getColor(android.R
					.color.darker_gray));
			hLineWidth		= array.getDimension(R.styleable.FlowView_fv_hLineWidth, 1);
			vLineWidth		= array.getDimension(R.styleable.FlowView_fv_vLineWidth, 1);
			firstHLine		= array.getBoolean(R.styleable.FlowView_fv_firstHLine, true);
			lastHLine		= array.getBoolean(R.styleable.FlowView_fv_lastHLine, true);
			firstVLine		= array.getBoolean(R.styleable.FlowView_fv_firstVLine, true);
			lastVLine		= array.getBoolean(R.styleable.FlowView_fv_lastVLine,true);
			hLineAllCover	= array.getBoolean(R.styleable.FlowView_fv_hLineAllCover,true);
			vLineAllCover	= array.getBoolean(R.styleable.FlowView_fv_vLineAllCover,true);
			titleLineAllCover = array.getBoolean(R.styleable.FlowView_fv_titleLineAllCover,true);
			lineType		= array.getInt(R.styleable.FlowView_fv_lineType, LTYPE_SOLID);
			dash			= array.getString(R.styleable.FlowView_fv_dash);
			if(TextUtils.isEmpty(dash))dash = "4,4,4,4";
			String[] strs = dash.split(",");
			dashedArray = new float[strs.length];
			for (int i=0;i<strs.length;i++){
				dashedArray[i] = density*Float.valueOf(strs[i]);
			}

			circleRadius	= array.getDimension(R.styleable.FlowView_fv_circleRadius, density * 1);
			circleInterval  = array.getDimension(R.styleable.FlowView_fv_circleInterval,density * 4);

			selectedType 	= array.getInt(R.styleable.FlowView_fv_selected_type, SINGLE);
			useSelected 	= array.getBoolean(R.styleable.FlowView_fv_use_selected, true);
			allAtStart 		= array.getBoolean(R.styleable.FlowView_fv_all_at_start, false);
			closeAtStart 	= array.getBoolean(R.styleable.FlowView_fv_colse_at_start, false);
			confimrAtStart  = array.getBoolean(R.styleable.FlowView_fv_confirm_at_start, false);

			color 			= array.getColor(R.styleable.FlowView_fv_color, getResources().getColor(android.R.color.black));
			bgRes 			= array.getResourceId(R.styleable.FlowView_fv_bg,0);
			bgColor         = array.getColor(R.styleable.FlowView_fv_bgColor, getResources().getColor(android.R.color.white));

			selectedColor 	= array.getColor(R.styleable.FlowView_fv_selected_color, getResources().getColor(android.R
					.color.white));
			selectedBgRes 	= array.getResourceId(R.styleable.FlowView_fv_selected_bg, 0);
			selectedBgColor = array.getColor(R.styleable.FlowView_fv_selected_bgColor, getResources().getColor(android.R.color.white));

			buttonColor 	=  array.getColor(R.styleable.FlowView_fv_button_color, getResources().getColor(android.R.color.holo_blue_dark));
			buttonBgRes 	=  array.getColor(R.styleable.FlowView_fv_button_bg, 0);
			buttonBgColor   = array.getColor(R.styleable.FlowView_fv_button_bgColor,getResources().getColor(android.R.color.white));

			hotColor 		= array.getColor(R.styleable.FlowView_fv_hot_color, getResources().getColor(android.R.color.holo_red_dark));
			hotBgRes 		= array.getColor(R.styleable.FlowView_fv_hot_bg, 0);
			hotBgColor      = array.getColor(R.styleable.FlowView_fv_hot_bgColor,getResources().getColor(android.R.color.white));

			animation_type  = array.getInt(R.styleable.FlowView_fv_animation_type, 0);
			horizonDivisor  = array.getInteger(R.styleable.FlowView_fv_horizonDivisor, 12);
			oneLineTitle 	= array.getBoolean(R.styleable.FlowView_fv_oneLineTitle, false);
			int PADDING 	= (int) (array.getDimension(R.styleable.FlowView_fv_padding,density*7));
			padding_left 	= (int) array.getDimension(R.styleable.FlowView_fv_h_space,PADDING*2);
			padding_top 	= (int) array.getDimension(R.styleable.FlowView_fv_h_space,PADDING*2);
			padding_right 	= (int) array.getDimension(R.styleable.FlowView_fv_h_space,PADDING*2);
			padding_bottom 	= (int) array.getDimension(R.styleable.FlowView_fv_h_space,PADDING*2);
			h_Space 		= (int) array.getDimension(R.styleable.FlowView_fv_h_space,PADDING);
			v_Space 		= (int) array.getDimension(R.styleable.FlowView_fv_v_space,PADDING);
			array.recycle();
		}
	}


	public void setOnFlowClickListener(OnFlowClickListener itemListener) {
		this.itemListener = itemListener;
	}

	public FlowView setAttrPadding(float padding_left,float padding_top,float padding_right,float padding_bottom){
		this.padding_left = (int) (getResources().getDisplayMetrics().density*padding_left);
		this.padding_top = (int) (getResources().getDisplayMetrics().density*padding_top);
		this.padding_right = (int) (getResources().getDisplayMetrics().density*padding_right);
		this.padding_bottom = (int) (getResources().getDisplayMetrics().density*padding_bottom);
		return this;
	}

	public FlowView setAttrSpace(float h_space,float v_space){
		this.h_Space = (int) (getResources().getDisplayMetrics().density*h_space);
		this.v_Space = (int) (getResources().getDisplayMetrics().density*v_space);
		return this;
	}

	public FlowView setAttr(int color,int bgRes){
		this.color = getResources().getColor(color);
		this.bgRes = bgRes;
		this.bgColor = 0;
		return this;
	}

	public FlowView setAttrColor(int color,int bgColor){
		this.color = getResources().getColor(color);
		this.bgColor = getResources().getColor(bgColor);
		this.bgRes = 0;
		return this;
	}

	public FlowView setSelectedAttr(int selectedColor,int selectedBgRes){
		this.selectedColor = getResources().getColor(selectedColor);
		this.selectedBgRes = selectedBgRes;
		this.selectedBgColor = 0;
		return this;
	}

	public FlowView setSelectedAttrColor(int selectedColor,int selectedBgColor){
		this.selectedColor = getResources().getColor(selectedColor);
		this.selectedBgColor = getResources().getColor(selectedBgColor);
		this.selectedBgRes = 0;
		return this;
	}

	public FlowView setButtonAttr(int buttonColor,int buttonBgRes){
		this.buttonColor = getResources().getColor(buttonColor);
		this.buttonBgRes = buttonBgRes;
		this.buttonBgColor = 0;
		return this;
	}

	public FlowView setButtonAttrColor(int buttonColor,int buttonBgColor){
		this.buttonColor = getResources().getColor(buttonColor);
		this.buttonBgColor = getResources().getColor(buttonBgColor);
		this.buttonBgRes = 0;
		return this;
	}

	public FlowView setHotAttr(int hotColor,int hotBgRes){
		this.hotColor = getResources().getColor(hotColor);
		this.hotBgRes = hotBgRes;
		this.hotBgColor = 0;
		return this;
	}

	public FlowView setHotAttrColor(int hotColor,int hotBgColor){
		this.hotColor = getResources().getColor(hotColor);
		this.hotBgColor = getResources().getColor(hotBgColor);
		this.hotBgRes = 0;
		return this;
	}

	public FlowView setAttr(int color,int bgRes,int selectedColor,int selectedBgRes,int hotColor,int hotBgRes,int
			buttonColor,int buttonBgRes) {
		this.color = getResources().getColor(color);
		this.bgRes = bgRes;
		this.selectedColor = getResources().getColor(selectedColor);
		this.selectedBgRes = selectedBgRes;
		this.hotColor = getResources().getColor(hotColor);
		this.hotBgRes = hotBgRes;
		this.buttonColor = getResources().getColor(buttonColor);
		this.buttonBgRes = buttonBgRes;
		return this;
	}

	public FlowView setAutoUseGrid(boolean useAutoGrid){
		this.useAutoGrid = useAutoGrid;
		this.useGrid = this.useAutoGrid?false:this.useGrid;
		return this;
	}

	public FlowView setUseGrid(boolean useGrid){
		this.useGrid = useGrid;
		this.useAutoGrid = this.useGrid?false:this.useAutoGrid;
		return this;
	}

	public FlowView setOneLineCount(int oneLineCount){
		this.oneLineCount = oneLineCount;
		this.useGrid = true;
		return this;
	}

	public FlowView setLineCount(int lineCount){
		this.lineCount = lineCount;
		useGrid = false;
		useAutoGrid = true;
		horizon = true;
		return this;
	}

	public FlowView setLineType(int lineType){
		this.lineType = lineType;
		return this;
	}

	public FlowView setUseHLine(boolean useHLine) {
		this.useHLine = useHLine;
		return this;
	}

	public FlowView setUseVLine(boolean useVLine) {
		this.useVLine = useVLine;
		return this;
	}

	public FlowView setHLineAttr(int hLineColor,int hLineWidth) {
		this.hLineColor = hLineColor;
		this.hLineWidth = hLineWidth;
		return this;
	}

	public FlowView setVLineAttr(int vLineColor,int vLineWidth) {
		this.vLineColor = vLineColor;
		this.vLineWidth = vLineWidth;
		return this;
	}


	public FlowView setHorizionWidth(float horizionWidth){
		this.horizionWidth = getResources().getDisplayMetrics().density*horizionWidth;
		return this;
	}

	public FlowView setMaxTextLine(int maxTextLine){
		this.maxTextLine = maxTextLine;
		return this;
	}

	public FlowView setUseSelected(boolean useSelected){
		this.useSelected = useSelected;
		return this;
	}
	public FlowView setUseSelectedAttr(boolean useSelectedAttr){
		this.useSelectedAttr = useSelectedAttr;
		return this;
	}

	public FlowView setAnimationType(int animationType){
		this.animation_type = animationType;
		return this;
	}

	public FlowView  setHotSelecteds(int[] hotSelecteds){
		int length = getChildCount();
		int hotLength = hotSelecteds.length;
		this.hotSelecteds = new int[hotLength];
		int count = 0;
		for(int i = 0;i<length;i++){
			for(int j=0;j<hotLength;j++){
				if(i==hotSelecteds[j]){
					this.hotSelecteds[count] = hotSelecteds[j];
					count++;
					((TextView)getChildAt(i)).setTextColor(hotColor);
					if(hotBgColor>0)getChildAt(i).setBackgroundColor(hotBgColor);
					if(hotBgRes>0)getChildAt(i).setBackgroundResource(hotBgRes);
					break;
				}
			}

		}
		return this;
	}

	public FlowView setHorizonDivisor(int horizonDivisor) {
		this.horizonDivisor = horizonDivisor;
		horizionWidth = 0;
		return this;
	}

	public FlowView setOneLineTitle(boolean oneLineTitle) {
		this.oneLineTitle = oneLineTitle;
		return this;
	}



	public FlowView addViewOne(String content, int res,int type,int limitLines){
		addViewOne(content, res, type);
		this.limitLines = limitLines;
		return this;
	}

	public FlowView addViewOne(String content,int res,int type){
		removeAllViews();
		initSelectedAttr();
		if(content==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		selectedType = ONE;
		TextView tv = (TextView) View.inflate(getContext(), res, null);
		tv.setTag(R.id.fl_position,-1);
		tv.setTag(R.id.fl_value, content);
		tv.setTag(R.id.fl_type, type);
		tv.setText(content);
		tv.setOnClickListener(listener);
		addView(tv);
		return  this;
	}

	public FlowView addViewCommon(String[] contents,int res,int type){
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		useSelected = false;
		selectedType = -1;
		if(length>0){
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewCommonTitle(String[] contents,String title,int titleRes,int res,int
			type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = -1;
		if(length>=1) {
			this.titleIndex = 0;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewCommonClose(String[] contents,int res,int type,boolean closeStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = COMMON_WITH_CLOSE;
		if(length>=1) {
			this.titleIndex = 0;
			closeAtStart = closeStart;
			selectedClose = closeAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedClose){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0) tv.setBackgroundResource(buttonBgRes);
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewCommon(String[] contents,int res,int type,boolean horizon){
		addViewCommon(contents, res, type);
		this.horizon = horizon;
		if(horizon&&contents!=null){
			magnification = contents.length/horizonDivisor;
			magnification = magnification>0?magnification:1;
		}
		return this;
	}

	public FlowView addViewCommon(String[] contents, int res,int type,int limitLines){
		addViewCommon(contents, res, type);
		this.limitLines = limitLines;
		return this;
	}


	public FlowView addViewSingle(String[] contents,int res,int lastSelected,int
			type,boolean horizon) {
		addViewSingle(contents, res, lastSelected, type);
		this.horizon = horizon;
		if(horizon&&contents!=null){
			magnification = contents.length/horizonDivisor;
			magnification = magnification>0?magnification:1;
		}
		return this;
	}

	public FlowView addViewSingle(String[] contents,int res, int lastSelected, int
			type, int limitLines) {
		addViewSingle(contents, res, lastSelected, type);
		this.limitLines = limitLines;
		return this;
	}

	public FlowView addViewSingle(String[] contents,int res,int lastSelected,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_TITLE;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewSingleClose(String[] contents,int res,int lastSelected,int
			type,boolean closeStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_CLOSE;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			closeAtStart = closeStart;
			selectedClose = closeAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedClose){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewSingleConfirm(String[] contents,int res,int lastSelected,int type,boolean confirmStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_CONFIRM;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			confimrAtStart = confirmStart;
			selectedConfirm = confimrAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedConfirm){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewSingleTitle(String[] contents,String title,int titleRes,int res,int lastSelected, int
			type, int limitLines) {
		addViewSingleTitle(contents, title, titleRes, res, lastSelected, type);
		this.limitLines = limitLines;
		return this;
	}

	public FlowView addViewSingleTitle(String[] contents,String title,int titleRes,int res,int lastSelected,int
			type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_TITLE;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewSingleCloseWithTitle(String[] contents,String title,int titleRes,int res,int lastSelected,
											int type,boolean closeStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_TITLE_CLOSE;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			closeAtStart = closeStart;
			selectedClose = closeAtStart?1:length;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedClose){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewSingleConfirmWithTitle(String[] contents,String title,int titleRes,int res,int lastSelected,
												  int type,boolean confirmStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = SINGLE_WITH_TITLE_CONFIRM;
		if(length>=1) {
			this.titleIndex = 0;
			this.lastSelected = lastSelected;
			confimrAtStart = confirmStart;
			selectedConfirm=confimrAtStart?1:length;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedConfirm){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(i==lastSelected&&useSelectedAttr){
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0) tv.setBackgroundResource(selectedBgRes);
					lastView = tv;
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}
	public FlowView addViewMutile(String[] contents,int res,List<Integer> lastSelecteds,int type,boolean
			horizon) {
		addViewMutile(contents, res, lastSelecteds, type);
		this.horizon = horizon;
		if(horizon&&contents!=null){
			magnification = contents.length/horizonDivisor;
			magnification = magnification>0?magnification:1;
		}
		return this;
	}

	public FlowView addViewMutile(String[] contents,int res,List<Integer> lastSelecteds, int type, int
			limitLines) {
		addViewMutile(contents, res, lastSelecteds, type);
		this.limitLines = limitLines;
		return this;
	}

	public FlowView addViewMutile(String[] contents,int res,List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE;
		if(length>0) {
			selectedAll = allAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(lastSelecteds!=null&&lastSelecteds.contains(i)) {
					this.lastSelecteds.add(i);
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}

	public FlowView addViewMutileAll(String[] contents,int res,List<Integer> lastSelecteds,int type,boolean
			allStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE_WITH_ALL;
		if(length>0) {
			allAtStart = allStart;
			selectedAll = allAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(lastSelecteds!=null&&lastSelecteds.contains(i)){
					this.lastSelecteds.add(i);
					if(i==selectedAll){
						tv.setTextColor(buttonColor);
						if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
						if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
					}else {
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					}
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}

	public FlowView addViewMutileClose(String[] contents,int res,List<Integer> lastSelecteds,int type,
								   boolean closeStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE_WITH_CLOSE;
		if(length>0) {
			closeAtStart = closeStart;
			selectedClose = closeAtStart?0:length-1;
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedClose){
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(lastSelecteds!=null&&lastSelecteds.contains(i)){
					this.lastSelecteds.add(i);
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}

	public FlowView addViewMutileWithTitle(String[] contents,String title,int titleRes,int res,
									   List<Integer> lastSelecteds, int type, int limitLines) {
		addViewMutileWithTitle(contents, title, titleRes, res, lastSelecteds, type);
		this.limitLines = limitLines;
		return this;
	}

	public FlowView addViewMutileWithTitle(String[] contents,String title,int titleRes,int res,
									   List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE_WITH_TITLE;
		if(length>=1) {
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(lastSelecteds!=null&&lastSelecteds.contains(i)){
					this.lastSelecteds.add(i);
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}


	public FlowView addViewMutileAllWithTitle(String[] contents,String title,int titleRes,int res,
										  List<Integer> lastSelecteds,int type,boolean allStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE_WITH_TITLE_ALL;
		if(length>=1) {
			allAtStart = allStart;
			selectedAll = allAtStart?1:length;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(lastSelecteds!=null&&lastSelecteds.contains(i)){
					if(i==selectedAll){
						tv.setTextColor(buttonColor);
						if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
						if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
					}else {
						this.lastSelecteds.add(i);
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					}
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}
	public FlowView addViewMutileCloseWithTitle(String[] contents,String title,int titleRes,int res,
											List<Integer> lastSelecteds,int type,boolean closeStart) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType=MULTIPLE_WITH_TITLE_CLOSE;
		if(length>=1) {
			closeAtStart = closeStart;
			selectedClose = closeAtStart?1:length;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv = (TextView) View.inflate(getContext(), res, null);
				String content = contents[i];
				if(i==selectedClose) {
					tv.setTextColor(buttonColor);
					if(buttonBgColor>0)tv.setBackgroundColor(buttonBgColor);
					if(buttonBgRes>0)tv.setBackgroundResource(buttonBgRes);
				}else if(lastSelecteds!=null&&lastSelecteds.contains(i)){
					this.lastSelecteds.add(i);
					tv.setTextColor(selectedColor);
					if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
					if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
				}
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}

	public FlowView addViewMutileTwoButton(String[] contents,int selectedAllRes,int selectedInvertRes,int res,
						List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = MULTIPLE_WITH_TWO_BUTTON;
		allAtStart = false;
		confimrAtStart = false;
		//Log.d("haha","lastSice="+lastSelecteds.size());
		if(length>=2) {
			this.selectedAll = length-2;
			this.selectedInvert = length-1;
			for(int i=0;i<length;i++) {
				TextView tv;
				if(i<length-2){
					tv = (TextView) View.inflate(getContext(), res, null);
					if(lastSelecteds!=null&&lastSelecteds.contains(i)){
						this.lastSelecteds.add(i);
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);

					}
				}else if(i==length-2){
					tv = (TextView) View.inflate(getContext(), selectedAllRes, null);
				}else{
					tv = (TextView) View.inflate(getContext(), selectedInvertRes, null);
				}
				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}
	
	public FlowView addViewMutileThreeButton(String[] contents,int selectedAllRes,int selectedInvertRes,int
			confirmRes,int	res,List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = MULTIPLE_WITH_THREE_BUTTON;
		allAtStart = false;
		confimrAtStart = false;
		//Log.d("haha","lastSice="+lastSelecteds.size());
		if(length>=3) {
			this.selectedAll = length-3;
			this.selectedInvert = length-2;
			this.selectedConfirm = length-1;
			for(int i=0;i<length;i++) {
				TextView tv;
				if(i<length-3){
					tv = (TextView) View.inflate(getContext(), res, null);
					if(lastSelecteds!=null&&lastSelecteds.contains(i)){
						//Log.d("haha","包含i="+i);
						this.lastSelecteds.add(i);
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					}
				}else if(i==length-3){
					tv = (TextView) View.inflate(getContext(), selectedAllRes, null);
				}else if(i==length-2){
					tv = (TextView) View.inflate(getContext(), selectedInvertRes, null);
				}else{
					tv = (TextView) View.inflate(getContext(), confirmRes, null);
				}
				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}
		return this;
	}


	public FlowView addViewMutileTwoButtonWithTitle(String[] contents,String title ,int titleRes,int selectedAllRes,int
			selectedInvertRes,int res,List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = MULTIPLE_WITH_TITLE_TWO_BUTTON;
		allAtStart = false;
		confimrAtStart = false;
		if(length>=3) {
			this.selectedAll = length-2;
			this.selectedInvert = length-1;
			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv;
				if(i<length-2){
					tv = (TextView) View.inflate(getContext(), res, null);

					if(lastSelecteds!=null&&lastSelecteds.contains(i)){
						this.lastSelecteds.add(i);
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);

					}
				}else if(i==length-2){
					tv = (TextView) View.inflate(getContext(), selectedAllRes, null);
				}else{
					tv = (TextView) View.inflate(getContext(), selectedInvertRes, null);
				}

				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}

	public FlowView addViewMutileThreeButtonWithTitle(String[] contents,String title, int titleRes,int
			selectedAllRes,int selectedInvertRes,int confirmRes,int res,List<Integer> lastSelecteds,int type) {
		removeAllViews();
		initSelectedAttr();
		if(contents==null)return this;
		if(getVisibility()!= View.VISIBLE)setVisibility(View.VISIBLE);
		int length = contents.length;
		selectedType = MULTIPLE_WITH_TITLE_THREE_BUTTON;
		allAtStart = false;
		confimrAtStart = false;
		if(length>=3) {
			this.selectedAll = length-3;
			this.selectedInvert = length-2;
			this.selectedConfirm = length-1;

			TextView titleTV  = (TextView) View.inflate(getContext(), titleRes, null);
			titleTV.setText(title);
			addView(titleTV);
			for(int i=0;i<length;i++) {
				TextView tv;
				if(i<length-3){
					tv = (TextView) View.inflate(getContext(), res, null);
					if(lastSelecteds!=null&&lastSelecteds.contains(i)){
						this.lastSelecteds.add(i);
						tv.setTextColor(selectedColor);
						if(selectedBgColor>0)tv.setBackgroundColor(selectedBgColor);
						if(selectedBgRes>0)tv.setBackgroundResource(selectedBgRes);
					}
				}else if(i==length-3){
					tv = (TextView) View.inflate(getContext(), selectedAllRes, null);
				}else if(i==length-2){
					tv = (TextView) View.inflate(getContext(), selectedInvertRes, null);
				}else{
					tv = (TextView) View.inflate(getContext(), confirmRes, null);
				}

				String content = contents[i];
				tv.setTag(R.id.fl_position,i);
				tv.setTag(R.id.fl_value,content);
				tv.setTag(R.id.fl_type, type);
				tv.setText(content);
				tv.setOnClickListener(listener);
				addView(tv);
			}
		}

		return this;
	}

	
	private void initSelectedAttr() {
		lastSelected = -1;
		selectedAll = -1;
		selectedClose = -1;
		selectedInvert = -1;
		selectedConfirm = -1;
		titleIndex = -1;
		limitLines = -1;
		magnification = -1;
		horizon = false;
		allAtStart = false;
		confimrAtStart = false;
		closeAtStart = false;
		if(lastSelecteds!=null){
			lastSelecteds.clear();
		}else{
			lastSelecteds = new ArrayList<>();
		}
		if(groupLastSelecteds!=null){
			groupLastSelecteds.clear();
		}else{
			groupLastSelecteds = new ArrayList<>();
		}
		if(hotSelecteds!=null)hotSelecteds = null;
	}

	@Override
	public void addView(View child) {
		super.addView(child);
		if(animation_type<1)return;
		if(child!=null){
			int index = 0;
			for(int i=getChildCount()-1;i>=0;i--){
				if(child.equals(getChildAt(i))){
					index = i;
					break;
				}
			}
			child.setVisibility(View.INVISIBLE);
			if(animation_type==ANIMATION_ALPHA_DELAY) {
				FlowAnimateUtils.startAnimationCheckedAlphaForAll(child, 0.0f, 1.0f, 500, 250, index);
			}else if(animation_type == ANIMATION_ALPAH_RANDOM){
				FlowAnimateUtils.startAnimationAlphaOrScaleRandom
						(child, 0.0f, 1.0f, 1500 / getChildCount(),
								500, getChildCount(), FlowAnimateUtils.ALPAH_RANDOM);
			}else if(animation_type == ANIMATION_SCALE_RANDOM){
				FlowAnimateUtils.startAnimationAlphaOrScaleRandom
						(child, 0.0f, 1.0f, 1500 / getChildCount(),
								750, getChildCount(), FlowAnimateUtils.SCALE_RANDOM);
			}else if(animation_type == ANIMATION_FREE){
				itemListener.startAnimation(child,index);
			}
		}
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		allChildViewLines.clear();
		oddSpaceAllLines.clear();
		View view;
		oneLineMaxWidth = horizon? (int) (horizionWidth > 0 ? horizionWidth : Global.mScreenWidth * magnification) : MeasureSpec.getSize
				(widthMeasureSpec);
		List<View> oneLineViews = null;
		int count = getChildCount();
		int buttonCount = 0;
		boolean hasButtons = false;
		if(selectedType==MULTIPLE_WITH_THREE_BUTTON||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON){
			buttonCount = count-3;
			hasButtons = true;
		}else if(selectedType==MULTIPLE_WITH_TWO_BUTTON||selectedType==MULTIPLE_WITH_TITLE_TWO_BUTTON){
			buttonCount = count-2;
			hasButtons = true;
		}else if((selectedType==COMMON_WITH_CLOSE&&!closeAtStart)||(selectedType==SINGLE_WITH_CLOSE&&!closeAtStart)||
				(selectedType==SINGLE_WITH_CONFIRM&&!confimrAtStart)||(selectedType==MULTIPLE_WITH_ALL&&!allAtStart)||
				(selectedType==MULTIPLE_WITH_CLOSE&&!closeAtStart)){
			buttonCount = count-1;
			hasButtons = true;
		}

		if(!useGrid)
		oneLineCount = 0;
		if(lineCount>0&&count>0)
		oneLineCount = count%lineCount==0?count/lineCount:(count/lineCount+1);
		if(useAutoGrid||useGrid){

			int maxWidth = 0;
			for(int i=0;i<count;i++) {
				view = getChildAt(i);
				view.measure(0, 0);
				if(i!=titleIndex){
					maxWidth = (view.getMeasuredWidth())
							>maxWidth? (view.getMeasuredWidth()):maxWidth;
				}
			}
			if(horizon)
			oneLineMaxWidth	= maxWidth*oneLineCount;

			if(!horizon&&!useGrid&&maxWidth!=0)
			oneLineCount = (oneLineMaxWidth-padding_left-padding_right+h_Space)/(maxWidth+h_Space);
		}
		//Log.d("haha--","&&&&&&&&&&&oneLineMaxWidth="+oneLineMaxWidth);
		//Log.d("haha--","&&&&&&&&&&&useAutoGrid="+useAutoGrid);
		//Log.d("haha--","&&&&&&&&&&&oneLineCount="+oneLineCount);
		if(useGrid&&oneLineCount==0)oneLineCount=4;
		for(int i=0;i<count;i++){
			view =  getChildAt(i);
			view.measure(0, 0);
			int allChildLines = allChildViewLines.size();
			int oneLineSpace = oneLineMaxWidth-getOneLineViewWidth(allChildLines);
			//Log.d("haha--",i+"oneLineMaxWidth="+oneLineMaxWidth);
			//Log.d("haha--",i+"_oneLineSpace="+oneLineSpace);
			//Log.d("haha--",i+"_width="+(view.getMeasuredWidth()+view.getPaddingLeft()+view.getPaddingRight()
			// +h_Space));
			if(hasButtons&&i==buttonCount&&buttonCount>=0){
				if(i!=0)oddSpaceAllLines.add(oneLineSpace);
				oneLineViews = new ArrayList<>();
				allChildViewLines.add(oneLineViews);
			}else if(i==0||(i==1&&oneLineTitle)||
					((useGrid||useAutoGrid)&&oneLineCount!=0&&(i-(oneLineTitle?1:0))%oneLineCount==0)||
					(view.getMeasuredWidth()+h_Space)>oneLineSpace){
				if(i!=0){
					oddSpaceAllLines.add(oneLineSpace);
				}else if(i==1&&oneLineTitle){
					oddSpaceAllLines.add(oneLineSpace);
				}
				if(allChildLines==limitLines)break;

				oneLineViews = new ArrayList<>();
				allChildViewLines.add(oneLineViews);
			}
			oneLineViews.add(view);
			if(hasButtons&&i==count-1&&buttonCount>=0)
			oddSpaceAllLines.add(oneLineMaxWidth-getOneLineViewWidth(allChildLines));

		}
		int allLineMeasureHeight = 	getAllLinesHeight(count);

		widthMeasureSpec = horizon? MeasureSpec.makeMeasureSpec(oneLineMaxWidth, MeasureSpec.EXACTLY)
				: MeasureSpec.getSize(widthMeasureSpec);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(allLineMeasureHeight, MeasureSpec.UNSPECIFIED);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}
	private int getAllLinesHeight(int childCount) {
		if(childCount==0)
			return 0;
		if(oneLineTitle){
			View titleV =  allChildViewLines.get(0).get(0);
			View childV = allChildViewLines.get(1).get(0);
			int count = allChildViewLines.size();
			return titleV.getMeasuredHeight()
			+childV.getMeasuredHeight()*(count-1)+v_Space*(count-2)
			+padding_top+padding_bottom;
		}else{
			if(allChildViewLines.get(0).size()==1&&allChildViewLines.size()==1) {
				View v = allChildViewLines.get(0).get(0);
				int count = allChildViewLines.size();
				return v.getMeasuredHeight() * count+ v_Space * (count - 1)
						+ padding_top + padding_bottom;
			}else if(allChildViewLines.get(0).size()>1){
				View v = allChildViewLines.get(0).get(1);
				int count = allChildViewLines.size();
				return v.getMeasuredHeight() * count+ v_Space * (count - 1)
						+ padding_top + padding_bottom;
			}else {
				View v = allChildViewLines.get(1).get(0);
				int count = allChildViewLines.size();
				return v.getMeasuredHeight() * count + v_Space * (count - 1)
						+ padding_top + padding_bottom;
			}
		}
	}
	private int getOneLineViewWidth(int line) {
		if(line<1)return 0;
		int oneLineWidth = 0;
		List<View> oneLineViews = allChildViewLines.get(line - 1);
		int length = oneLineViews.size();
		for(int i = 0;i<length;i++){

			oneLineWidth += oneLineViews.get(i).getMeasuredWidth();
		}

		oneLineWidth += padding_left+padding_right+h_Space*(length-1);

		return oneLineWidth;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int left = 0;
		int top = 0;
		int right = 0;
		int bottom = 0;
		int preLineBottom = 0;
		int length = allChildViewLines.size();
		boolean hasButtons = false;
		boolean hasSingleButton = false;
		boolean singleBtnAtLast = false;
		boolean oneView = selectedType==ONE?true:false;
		if(selectedType==MULTIPLE_WITH_THREE_BUTTON||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON){
			hasButtons = true;
		}else if(selectedType==MULTIPLE_WITH_TWO_BUTTON||selectedType==MULTIPLE_WITH_TITLE_TWO_BUTTON){
			hasButtons = true;
		}else if((selectedType==COMMON_WITH_CLOSE)&&!closeAtStart||(selectedType==SINGLE_WITH_CLOSE&&!closeAtStart)||
				(selectedType==SINGLE_WITH_CONFIRM&&!confimrAtStart)
				||(selectedType==MULTIPLE_WITH_ALL&&!allAtStart)||(selectedType==MULTIPLE_WITH_CLOSE&&!closeAtStart)){
			hasButtons = true;
			hasSingleButton = true;
			singleBtnAtLast = true;
		}
		int lastOneLength = 0;
		for(int i=0;i<length;i++){
			List<View> oneLineViews = allChildViewLines.get(i);
			int preViewRight = 0;
			int oddSpaceOneLine = 0;
			if(oneLineViews.size()!=1&&oddSpaceAllLines.size()>i){
				if(hasButtons&&i==length-2){
					oddSpaceOneLine = 0;
				}else {
					oddSpaceOneLine = oddSpaceAllLines.get(i) / oneLineViews.size();
				}
			}

			int oneLength = oneLineViews.size();
			lastOneLength = lastOneLength<oneLength?oneLength:lastOneLength;
			for(int j = 0;j<oneLength;j++){
				View view = oneLineViews.get(j);
				//Log.d("haha","hasButtons="+hasButtons);
				//Log.d("haha","hasSingleBtn="+hasSingleButton);
				//Log.d("haha","singleBtnAtLast="+singleBtnAtLast);
				left = preViewRight+(j==0?(padding_left):h_Space);
				top = preLineBottom+(i==0?padding_top:v_Space);
				if(j==0) {
					if(oneView){
						right = r-padding_right;
					}else if(useGrid||useAutoGrid){
						right = left+(oneLineMaxWidth-padding_left-padding_right-h_Space*(lastOneLength-1))/oneLineCount;
					}else if(oneLineViews.size()>1) {
						right = view.getMeasuredWidth() + left + oddSpaceOneLine;
					}else if(oneLineViews.size()==1){
						if(hasButtons){
							if(hasSingleButton&&!singleBtnAtLast){
								if(i== length-1){
									right = view.getMeasuredWidth()+left+oddSpaceOneLine;
								}else{
									right = r-padding_right;
								}
							}else {
								if (i == length - 2) {
									right = view.getMeasuredWidth() + left + oddSpaceOneLine;
								}else {
									right = r - padding_right;
								}
							}
						}else{
							if(i==length-1){
								right = view.getMeasuredWidth()+left+oddSpaceOneLine;
							}else{
								right = r-padding_right;
							}
						}
					}
				}else if(j==oneLineViews.size()-1&&i!=length-1){
					if(useGrid||useAutoGrid){
						right = left+(oneLineMaxWidth-padding_left-padding_right-h_Space*(lastOneLength-1))/oneLineCount;
					}else if(hasButtons) {
						right = view.getMeasuredWidth()+left+oddSpaceOneLine/*+view.getPaddingLeft()
								+view.getPaddingRight()*/;
					}else{
						right = r - padding_right;
					}
				}else {
					if(useGrid||useAutoGrid){
						right = left+(oneLineMaxWidth-padding_left-padding_right-h_Space*(lastOneLength-1))/oneLineCount;
					}else {
						right = view.getMeasuredWidth() + left + oddSpaceOneLine/* + view.getPaddingLeft()
								+ view.getPaddingRight()*/;
					}
				}
				oneLineHeight = view.getMeasuredHeight()+v_Space;
				oneLineWidth  = right - left +h_Space;
				bottom = view.getMeasuredHeight()+preLineBottom+(i==0?padding_top:v_Space);
				preViewRight = right;
				int widthMeasureSpec = MeasureSpec.makeMeasureSpec(right - left, MeasureSpec.EXACTLY);
				int heightMeasureSpec = MeasureSpec.makeMeasureSpec(bottom - top, MeasureSpec.EXACTLY);
				view.measure(widthMeasureSpec, heightMeasureSpec);
				view.layout(left, top, right, bottom);
			}
			preLineBottom = bottom;
		}
		invalidate();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if(allChildViewLines==null||allChildViewLines.size()==0)return;
		if(paint==null){
			paint = new Paint();
			paint.setAntiAlias(true);
		}
		boolean hasButton = false;
		if(selectedType==MULTIPLE_WITH_THREE_BUTTON ||selectedType==MULTIPLE_WITH_TITLE_THREE_BUTTON){
			hasButton = true;
		}else if(selectedType==MULTIPLE_WITH_TWO_BUTTON||selectedType==MULTIPLE_WITH_TITLE_TWO_BUTTON){
			hasButton = true;
		}else if((selectedType==COMMON_WITH_CLOSE&&!closeAtStart)||(selectedType==SINGLE_WITH_CLOSE&&!closeAtStart)||
				(selectedType==SINGLE_WITH_CONFIRM&&!confimrAtStart)||(selectedType==MULTIPLE_WITH_ALL&&!allAtStart)||
				(selectedType==MULTIPLE_WITH_CLOSE&&!closeAtStart)){
			hasButton = true;
		}
		if(useHLine){
			paint.setColor(hLineColor);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(hLineWidth);
			int coverIndex = allChildViewLines.size()-(hasButton?1:0);

			for (int i=0;i<=allChildViewLines.size();i++) {
				if(i==0&&!firstHLine)continue;
				if(i==allChildViewLines.size()&&!lastHLine)continue;
				if(hasButton){
					if(i==allChildViewLines.size()-1&&!lastHLine)continue;
					if(i==allChildViewLines.size())continue;
				}

				float wFormat = hLineAllCover||i!=coverIndex?0:(oneLineCount-allChildViewLines.get(coverIndex-1)
						.size())*oneLineWidth;

				if (lineType == LTYPE_SOLID) {
					canvas.drawLine(padding_left + padding_leftLine-v_Space/2
							,oneLineHeight*i+padding_top-v_Space/2
							,getWidth() -padding_right-padding_rightLine+v_Space/2-(i==coverIndex?wFormat:0)
							,oneLineHeight*i+padding_top-v_Space/2,paint);
				} else {
					Path path = new Path();
					path.moveTo(padding_left + padding_leftLine - v_Space/2
							, oneLineHeight * i + padding_top - v_Space/2);
					path.lineTo(getWidth() - padding_right - padding_rightLine + v_Space / 2-(i==coverIndex?wFormat:0)
							, oneLineHeight * i + padding_top - v_Space/2);
					if (lineType == LTYPE_DASH&&dashedArray!=null) {
						paint.setPathEffect(new DashPathEffect(dashedArray, 1));
					} else if (lineType == LTYPE_CIRCLE) {
						path.addCircle(padding_left + padding_leftLine - v_Space/2, padding_top-v_Space/2-circleRadius
								, circleRadius, Path.Direction.CW);
						paint.setPathEffect(new PathDashPathEffect(path, circleInterval, 0, PathDashPathEffect.Style
								.ROTATE));
					}
					canvas.drawPath(path, paint);
				}
			}
		}

		if(useVLine){

			paint.setColor(vLineColor);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(vLineWidth);
			int index = allChildViewLines.size()-(hasButton?2:1);
			int coverIndex = allChildViewLines.get(index>0?index:0).size();
			for (int i=0;i<=oneLineCount;i++) {
				if(i==0&&!firstVLine)continue;
				if(i==oneLineCount&&!lastVLine)continue;

				float hFormat = vLineAllCover?0:((hasButton?2:1)*oneLineHeight);
				if (lineType == LTYPE_SOLID) {
					canvas.drawLine(padding_left+oneLineWidth*i-h_Space/2
							,padding_top-padding_topLine-h_Space/2+(titleLineAllCover?0:oneLineHeight)
							,padding_left +oneLineWidth*i-h_Space/2
							,getHeight()-padding_bottom-padding_bottomLine+h_Space/2-(i>coverIndex?hFormat:0),paint);
				}  else {
					Path path = new Path();
					path.moveTo(padding_left+oneLineWidth*i-h_Space/2
							,  padding_top-padding_topLine-h_Space/2+(titleLineAllCover?0:oneLineHeight));
					path.lineTo(padding_left +oneLineWidth*i-h_Space/2
							, getHeight()-padding_bottom-padding_bottomLine+h_Space/2-(i>coverIndex?hFormat:0));
					if (lineType == LTYPE_DASH&&dashedArray!=null) {
						paint.setPathEffect(new DashPathEffect(dashedArray, 1));
					} else if (lineType == LTYPE_CIRCLE) {
						path.addCircle(padding_left+oneLineWidth*i-h_Space/2, -(padding_top-padding_topLine-h_Space/2+circleRadius), circleRadius, Path.Direction.CW);
						paint.setPathEffect(new PathDashPathEffect(path, circleInterval, 0, PathDashPathEffect.Style.ROTATE));
					}
					canvas.drawPath(path, paint);
				}
			}
		}
	}

	public List<Integer> getSelecteds() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0,len = lastSelecteds==null?0:lastSelecteds.size(); i < len; i++) {
			list.add(lastSelecteds.get(i));
		}
		return list;
	}
}
