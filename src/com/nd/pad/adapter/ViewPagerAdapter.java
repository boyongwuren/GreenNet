package com.nd.pad.adapter;

import java.util.ArrayList;

import android.R.array;
import android.R.integer;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter 
{
   private ArrayList<View> dataArrayList;
   
	
	public ViewPagerAdapter(ArrayList<View> data) 
	{
		dataArrayList = data;
	}

	@Override
	public int getCount() {

		if(dataArrayList != null)
		{
			return dataArrayList.size();
		}else 
		{
			return 0;
		}
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container,int position,Object object)
	{
		container.removeView(dataArrayList.get(position));
	}
	
	@Override
	public int getItemPosition(Object object)
	{
		if(dataArrayList != null)
		{
			return dataArrayList.indexOf(object);
		}else 
		{
			return 0;
		}
	}
	
	
	@Override
	public Object instantiateItem(ViewGroup container,int position)
	{
		View pager = null;
		if(dataArrayList != null)
		{
			 pager = dataArrayList.get(position);
			 container.addView(pager);
		} 
		
		return pager;
	}
	

}
