# abstract
This project will be display how to use the widget FlowView.
## FlowView
---------------

> About me,
  Blog：[jianshu](http://www.jianshu.com/users/8d01db870d4a/timeline) wechat：[lzt橘子](18520660170)

#### Photo sample:



### Caracter
- FlowViewView offer 17way show type;
- COMMON_WITH_CLOSE   = -2     can't select , offer a close tag to hide the FlowView;
- SINGLE 			  = 0	   can select only one tag;
- SINGLE_WITH_CLOSE   = 1      can select only one tag, offer a close tag to hide the FlowView;
- SINGLE_WITH_CONFIRM = 2      can select only one tag, offer a confirm tag to hide the FlowView;

- SINGLE_WITH_TITLE = 3		   can select only one tag, offer a title tag at the first index or first line;
- SINGLE_WITH_TITLE_CLOSE = 4  can select only one tag, offer a title tag and a close tag;
- SINGLE_WITH_TITLE_CONFIRM = 5  can select only one tag, offer a title tag and a confirm tag;

- MULTIPLE = 6				   can select multi tags;
- MULTIPLE_WITH_CLOSE = 7	   can select multi tags, offer a close tag for hide the FlowView;
- MULTIPLE_WITH_ALL = 8        can select multi tags, offer a select all tag at the first index or last line;

- MULTIPLE_WITH_TITLE = 9      can select multi tags, offser a title tag at the first index or first line;
- MULTIPLE_WITH_TITLE_CLOSE = 10  can select multi tags, offer a title tag and a close tag;
- MULTIPLE_WITH_TITLE_ALL = 11 can select multi tags, offer a title tag and a select all tag;

- MULTIPLE_WITH_TWO_BUTTON = 12     can select multi tags,offer a select tag and a revers select tag;
- MULTIPLE_WITH_THREE_BUTTON = 13   can select multi tags,offer a select tag , a revers select tag and a confirm tag;
- MULTIPLE_WITH_TITLE_TWO_BUTTON = 14   can select multi tags,offer a title tag,a select all tag and a revers tag;
- MULTIPLE_WITH_TITLE_THREE_BUTTON = 15 can select multi tags,offer a title tag,a select all tag,a revers tag and a
confirm tag;

### Theory explain

FlowView
first int the onMeasure(), meaure the text width of child view, and count the eatch line can fill how many childs and
the how many lines can fill all the tag,
then int the layout(), count the eatch view's left ,top,right,bottom by the first step's result,
last use the child's layout to fill the child into their parent.

### download install
Project_Gradle
``` xml
allprojects {
		    repositories {
			    maven { url 'https://jitpack.io' }
		    }
	}
```
App_Gradle:
``` xml
dependencies {
	         compile 'com.github.liangzhongtai:FlowView:v1.0'
	}
```

### how to use

## 1.attrs

(1).This attrs can define int the res file
		//if true , the FlowView will auto count the max childs in eatch line;
 		<attr name="fv_useAutoGrid"         format="boolean"/>
 		//if true , you must set the fv_onrLineCount a value which > 0, this priority higher the fv_useAutoGrid;
        <attr name="fv_useGrid"             format="boolean"/>
        //you can set how many childs et eatch line;
        <attr name="fv_oneLineCount"        format="integer"/>
        //you can set how many line in the FlowView, but first ni must layout the FlowView whitd the
        HoriztionScroollView;
        <attr name="fv_lineCount"           format="integer"/>
        //you can set how width the horizion;
        <attr name="fv_horizionWidth"       format="float"/>
        //this attr is not effetive for the moment
        <attr name="fv_maxTextLine"         format="integer"/>
        //if true ,will draw the horizion line int the FlowView.
        <attr name="fv_useHLine"            format="boolean"/>
        //if true ,will draw the vertical line int the FlowView.
        <attr name="fv_useVLine"            format="boolean"/>
        //the horizion line padding left.
        <attr name="fv_padding_leftLine"    format="dimension"/>
        //the horizion line padding right.
        <attr name="fv_padding_rightLine"   format="dimension"/>
        //the vertical line padding top.
        <attr name="fv_padding_topLine"     format="dimension"/>
        //the vertical line padding bottom.
        <attr name="fv_padding_bottomLine"  format="dimension"/>
        //the horizion line's' color.
        <attr name="fv_hLineColor"          format="color"/>
        //the vertical line's color.
        <attr name="fv_vLineColor"          format="color"/>
        //the horizion line's stroke width.
        <attr name="fv_hLineWidth"          format="dimension"/>
        //the vertical line's stroke width.
        <attr name="fv_vLineWidth"          format="dimension"/>
        //if true, the first horizion line will draw.
        <attr name="fv_firstHLine"          format="boolean"/>
        //if true,the last horizion line will draw.
        <attr name="fv_lastHLine"           format="boolean"/>
        //if true,the first vertical line will draw.
        <attr name="fv_firstVLine"          format="boolean"/>
        //if true,the last vertical line will draw.
        <attr name="fv_lastVLine"           format="boolean"/>
        //if true,the horizion line will draw under all the tag, include the close tag, confirm tag ,select all tag or
        rever
        tag.;
        <attr name="fv_hLineAllCover"       format="boolean"/>
        //if true, the verticall line will draw heigher all the tag;
        <attr name="fv_vLineAllCover"       format="boolean"/>
        //if true the horizion line and vertical line will draw the title tag;
        <attr name="fv_titleLineAllCover"   format="boolean"/>
        //you can set the line to solid or dash,now circle is has issue.
        <attr name="fv_lineType"            format="enum">
                    <enum name="solid"              value="0"/>
                    <enum name="dash"               value="1"/>
                    <enum name="circle"             value="2"/>
        </attr>
        //you can set the dash interval,like value:'4,4,4,4'
        <attr name="fv_dash"                format="string"/>
        //you can set the circle's radius ,now this attr is has a issue.
        <attr name="fv_circleRadius"        format="dimension"/>
        //you can set the circle's interval.
        <attr name="fv_circleInterval"      format="dimension"/>
        //this is the child tag bg res shape at the not select state.
        <attr name="fv_bg"                  format="reference"/>
        //this is the child tag bg res color at the not select state.
        <attr name="fv_bgColor"             format="color"/>
        //this is the child tag text color at the not select state.
        <attr name="fv_color"               format="color"/>
        //this is the child tag bg res shape at the select state.
        <attr name="fv_selected_bg"         format="reference"/>
        //this is the child tag bg res color at the select state.
        <attr name="fv_selected_bgColor"    format="color"/>
        //this is the child tag text color at the select sttae.
        <attr name="fv_selected_color"      format="color"/>
        //this is the button bg res shape like close tag,confirm tag,all select tag and revers tag.
        <attr name="fv_button_bg"           format="reference"/>
        //this is the button bg res color like close tag,confirm tag,all select tag and revers tag.
        <attr name="fv_button_bgColor"      format="color"/>
        //this is the button text color like close tag,confirm tag,all select tag and revers tag.
        <attr name="fv_button_color"        format="color"/>
        //this is the hot tag bg res shape
        <attr name="fv_hot_bg"              format="reference"/>
        //this is the hot tag bg res color
        <attr name="fv_hot_bgColor"         format="color"/>
        //this is the hot tag text color.
        <attr name="fv_hot_color"           format="color"/>
        //if true, the tag will has the  select state.
        <attr name="fv_use_selected"        format="boolean"/>
        //this four attr is the padding of the FlowView
        <attr name="fv_padding"             format="dimension"/>
        <attr name="fv_padding_left"        format="dimension"/>
        <attr name="fv_padding_top"         format="dimension"/>
        <attr name="fv_padding_right"       format="dimension"/>
        <attr name="fv_padding_bottom"      format="dimension"/>
        //this is horizion interval between the child tag.
        <attr name="fv_h_space"             format="dimension"/>
        //this is vertical interval between the child tag.
        <attr name="fv_v_space"             format="dimension"/>
        //if true ,the all select tag will at the first index;
        <attr name="fv_all_at_start"        format="boolean"/>
        //if true, the colse tag will at the first index.
        <attr name="fv_colse_at_start"      format="boolean"/>
        //if true, the confir, will at the first index.
        <attr name="fv_confirm_at_start"    format="boolean"/>
        //this attr will affect the horition type how many child tags at the eatch line.
        <attr name="fv_horizonDivisor"      format="integer"/>
        //if true, the title tag will alone at the first line.
        <attr name="fv_oneLineTitle"        format="boolean"/>
        //you can set the animation when add the tags.
        <attr name="fv_animation_type"           format="enum">
                    <enum name="anima_alpha_delay"  value="1"/>
                    <enum name="anima_alpha_random" value="2"/>
                    <enum name="anima_scale_random" value="3"/>
                    <enum name="anima_free"         value="4"/>
        </attr>
        //this 0~15 is effective.
        <attr name="fv_selected_type"       format="enum">
                    <enum name="single"                             value="0"/>
                    <enum name="sigle_with_close"                   value="1"/>
                    <enum name="sigle_with_confirm"                 value="2"/>
                    <enum name="sigle_with_title_close"             value="3"/>
                    <enum name="sigle_with_title_confirm"           value="4"/>
                    <enum name="multiple"                           value="5"/>
                    <enum name="multiple_with_close"                value="6"/>
                    <enum name="multiple_with_all"                  value="7"/>
                    <enum name="multiple_with_title_close"          value="8"/>
                    <enum name="multiple_with_title_all"            value="9"/>
                    <enum name="single_with_title"                  value="10"/>
                    <enum name="multiple_with_title"                value="11"/>
                    <enum name="multiple_with_two_button"           value="12"/>
                    <enum name="multiple_with_three_button"         value="13"/>
                    <enum name="multiple_with_title_two_button"     value="14"/>
                    <enum name="multiple_with_title_three_button"   value="15"/>
                    <enum name="multiple_group"                     value="16"/>
                    <enum name="multiple_group_with_title"          value="17"/>
        </attr>

(2).You can set the attr of the FlowView in the java,eatch Type will has the corresponding method,like this:
SINGLE:
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/fv1.png)
MULTIPLE:
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/fv2.png)
MULTIPLE and useGrid:
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/fv3.png)
MULTIPLE and useHorizion:
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/fv4.png)

### Matters need attention

-if you want to tht FlowView show like the Horiztion,must layout it into the HorizionScrollView.
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/fv0.png)
## License

