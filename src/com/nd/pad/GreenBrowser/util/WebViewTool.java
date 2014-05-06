package com.nd.pad.GreenBrowser.util;

import java.lang.reflect.Field;

import android.view.View;
import android.webkit.WebView;
import android.widget.ZoomButtonsController;

public class WebViewTool
{

	public static void setZoomControlGone(View view) {  
	    Class classType;  
	    Field field;  
	    try {  
	        classType = WebView.class;  
	        field = classType.getDeclaredField("mZoomButtonsController");  
	        field.setAccessible(true);  
	        ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(view);  
	        mZoomButtonsController.getZoomControls().setVisibility(View.GONE);  
	        try {  
	            field.set(view, mZoomButtonsController);  
	        } catch (IllegalArgumentException e) {  
	            e.printStackTrace();  
	        } catch (IllegalAccessException e) {  
	            e.printStackTrace();  
	        }  
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (NoSuchFieldException e) {  
	        e.printStackTrace();  
	    }  
	}  

}
