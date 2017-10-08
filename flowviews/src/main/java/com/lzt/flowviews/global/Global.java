package com.lzt.flowviews.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Global {
	
	public static Context mContext;
	public static int mScreenWidth;
	public static int mScreenHeight;
	public static float mDensity;
	public static float mScaledDensity;
	public static int MAXMEMONRY;
	
	//public static int systemType;
	public static void init(Context context) {
		if(mContext!=null)return;
		mContext = context;
		initScreenSize();
		MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);
	}

	public static void initScreenSize() {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		mDensity = dm.density;
		mScaledDensity = dm.scaledDensity;
	}
	
	 public static LruCache<String, Bitmap> mBitmapLruCache;

	 public static void initBitmapLruCache(){
	            if (mBitmapLruCache == null)
	                mBitmapLruCache = new LruCache<String, Bitmap>(Global.MAXMEMONRY / 64) {
	                    @Override
	                    protected int sizeOf(String key, Bitmap bitmap) {
	                        return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
	                    }
	                    @Override
	                    protected void entryRemoved(boolean evicted, String key,
	                            Bitmap oldValue, Bitmap newValue) {
	                    }
	                };
	 }


		public static void clearCache() {
			if (mBitmapLruCache != null) {
				if (mBitmapLruCache.size() > 0) {
					mBitmapLruCache.evictAll();
				}
				mBitmapLruCache = null;
			}
		}

		public static synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
			if (mBitmapLruCache.get(key) == null) {
				if (key != null && bitmap != null)
					mBitmapLruCache.put(key, bitmap);
			}
		}

		public static synchronized Bitmap getBitmapFromMemCache(String key) {
			Bitmap bm = mBitmapLruCache.get(key);
			if (key != null) {
				return bm;
			}
			return null;
		}

	public static synchronized void removeImageCache(String key) {
		if (key != null) {
			if (mBitmapLruCache != null) {
				Bitmap bm = mBitmapLruCache.remove(key);
				if (bm != null)
					bm.recycle();
			}
		}
	}

	public static int dp2px(int dp) {
		return (int) (dp * mDensity + 0.5f) ;
	}
	public static float dp2px(float dp) {
		return dp * mDensity + 0.5f ;
	}

	public static int sp2px(int sp){
		return (int)(sp*mScaledDensity+0.5f);
	}
	public static float sp2px(float sp){
		return sp*mScaledDensity+0.5f;
	}
	
	/** 判断当前是否是在主线程运行 */
	public static boolean isInMainThread() {
		return Looper.getMainLooper() == Looper.myLooper();
	}

	public static View inflate(int resource, ViewGroup parent) {
		return LayoutInflater.from(mContext).inflate(resource, parent, false);
	}


}














