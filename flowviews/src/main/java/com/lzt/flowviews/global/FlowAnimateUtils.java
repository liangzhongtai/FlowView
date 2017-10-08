package com.lzt.flowviews.global;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;


import java.util.Random;


public class FlowAnimateUtils {
	private static int duration = 250;
	public final static int ALPAH_RANDOM = 1;
	public final static int SCALE_RANDOM = 2;

	public static void startAnimationAlphaOrScaleRandom(final View view,float start,float end,long
			delay,long dutation,int position,int animatType) {
		if(view == null) return;
		if(animatType==ALPAH_RANDOM) {
			ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", start, end);
			animator.setDuration(dutation);
			animator.setInterpolator(new DecelerateInterpolator(0.5f));
			animator.setStartDelay(delay * new Random().nextInt(position + 1));
			animator.addListener(new Animator.AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animator) {
					view.setVisibility(view.VISIBLE);
				}

				@Override
				public void onAnimationEnd(Animator animator) {}

				@Override
				public void onAnimationCancel(Animator animator) {}

				@Override
				public void onAnimationRepeat(Animator animator) {}
			});
			animator.start();
		}else if(animatType==SCALE_RANDOM){
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 0.0f,1.0f);
			ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 0.0f,1.0f);
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
			AnimatorSet animSet = new AnimatorSet();
			animSet.setInterpolator(new OvershootInterpolator(1.5f));
			animSet.play(anim1).with(anim2);
			animSet.play(anim1).with(anim3);
			animSet.setDuration(dutation);
			animSet.setStartDelay(delay * new Random().nextInt(position + 1));
			view.setVisibility(View.GONE);
			animSet.addListener(new Animator.AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animator) {
						view.setVisibility(View.VISIBLE);
				}

				@Override
				public void onAnimationEnd(Animator animator) {

				}

				@Override
				public void onAnimationCancel(Animator animator) {

				}

				@Override
				public void onAnimationRepeat(Animator animator) {

				}
			});
			animSet.start();
		}
	}

	public static ObjectAnimator startAnimationCheckedAlphaForAll(final
			View view, float start, float end, int dutation, int delay, int position) {
		if(view == null) return null;
		ObjectAnimator animator = ObjectAnimator.ofFloat(view,"alpha",start,end);
		animator.setDuration(dutation);
		animator.setStartDelay(delay * position);
		animator.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				view.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		animator.start();
		return animator;
	}

	public static ObjectAnimator tada(View view) {
		if(view==null)return null;
		return tada(view, 1f);
	}

	public static ObjectAnimator tada(View view, float shakeFactor) {

		PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
				Keyframe.ofFloat(0f, 1f),
				Keyframe.ofFloat(.1f, .9f),
				Keyframe.ofFloat(.2f, .9f),
				Keyframe.ofFloat(.3f, 1.1f),
				Keyframe.ofFloat(.4f, 1.1f),
				Keyframe.ofFloat(.5f, 1.1f),
				Keyframe.ofFloat(.6f, 1.1f),
				Keyframe.ofFloat(.7f, 1.1f),
				Keyframe.ofFloat(.8f, 1.1f),
				Keyframe.ofFloat(.9f, 1.1f),
				Keyframe.ofFloat(1f, 1f)
		);

		PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
				Keyframe.ofFloat(0f, 1f),
				Keyframe.ofFloat(.1f, .9f),
				Keyframe.ofFloat(.2f, .9f),
				Keyframe.ofFloat(.3f, 1.1f),
				Keyframe.ofFloat(.4f, 1.1f),
				Keyframe.ofFloat(.5f, 1.1f),
				Keyframe.ofFloat(.6f, 1.1f),
				Keyframe.ofFloat(.7f, 1.1f),
				Keyframe.ofFloat(.8f, 1.1f),
				Keyframe.ofFloat(.9f, 1.1f),
				Keyframe.ofFloat(1f, 1f)
		);

		PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
				Keyframe.ofFloat(0f, 0f),
				Keyframe.ofFloat(.1f, -3f * shakeFactor),
				Keyframe.ofFloat(.2f, -3f * shakeFactor),
				Keyframe.ofFloat(.3f, 3f * shakeFactor),
				Keyframe.ofFloat(.4f, -3f * shakeFactor),
				Keyframe.ofFloat(.5f, 3f * shakeFactor),
				Keyframe.ofFloat(.6f, -3f * shakeFactor),
				Keyframe.ofFloat(.7f, 3f * shakeFactor),
				Keyframe.ofFloat(.8f, -3f * shakeFactor),
				Keyframe.ofFloat(.9f, 3f * shakeFactor),
				Keyframe.ofFloat(1f, 0)
		);

		return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
				setDuration(500);
	}
	public static ObjectAnimator nope(View view) {
		if(view==null)return null;
		int delta = (int) (view.getResources().getDisplayMetrics().density*8);
		PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
				Keyframe.ofFloat(0f, 0),
				Keyframe.ofFloat(.10f, -delta),
				Keyframe.ofFloat(.26f, delta),
				Keyframe.ofFloat(.42f, -delta),
				Keyframe.ofFloat(.58f, delta),
				Keyframe.ofFloat(.74f, -delta),
				Keyframe.ofFloat(.90f, delta),
				Keyframe.ofFloat(1f, 0f)
		);
		return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
				setDuration(500);
	}

	public static AnimatorSet startNopeAndTada(Context mContext,View view){
		if(view==null||mContext==null)return null;
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setInterpolator(AnimationUtils.loadInterpolator(mContext, android.R.anim
				.bounce_interpolator));
		animatorSet.play(FlowAnimateUtils.nope(view))
				.with(FlowAnimateUtils.tada(view));
		animatorSet.setDuration(500)
				.start();
		return animatorSet;
	}
}
