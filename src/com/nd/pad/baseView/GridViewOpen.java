package com.nd.pad.baseView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 默认的GridView显示全部内容
 * @author Administrator
 *
 */
public class GridViewOpen extends GridView {

	public GridViewOpen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GridViewOpen(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GridViewOpen(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{ 
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
		super.onMeasure(widthMeasureSpec, expandSpec); 
	} 

}
