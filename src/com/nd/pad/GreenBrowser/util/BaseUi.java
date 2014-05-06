package com.nd.pad.GreenBrowser.util;

import android.R;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;

public class BaseUi {
	
	protected Drawable mGenericFavicon;
	
	Activity mActivity;
	
    private static final int MSG_HIDE_TITLEBAR = 1;
    public static final int HIDE_TITLEBAR_DELAY = 1500; // in ms
	
    public BaseUi(Activity browser) {
        mActivity = browser;
		// TODO Auto-generated constructor stub
		Resources res = mActivity.getResources();
		
        mGenericFavicon = res.getDrawable(R.drawable.btn_default);
	}
	
    public Drawable getFaviconDrawable(Bitmap icon) {
        Drawable[] array = new Drawable[3];
        array[0] = new PaintDrawable(Color.BLACK);
        PaintDrawable p = new PaintDrawable(Color.WHITE);
        array[1] = p;
        if (icon == null) {
            array[2] = mGenericFavicon;
        } else {
            array[2] = new BitmapDrawable(icon);
        }
        
        LayerDrawable d = new LayerDrawable(array);
        d.setLayerInset(1, 1, 1, 1, 1);
        d.setLayerInset(2, 2, 2, 2, 2);
        return d;
    }
}
