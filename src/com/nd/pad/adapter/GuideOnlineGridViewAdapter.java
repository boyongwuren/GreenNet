package com.nd.pad.adapter;

import java.util.ArrayList;

import com.nd.pad.vo.GuideOnlineItemVo;
import com.nd.pad.GreenBrowser.R;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ÍøÖ·µ¼º½
 * @author Administrator
 *
 */
public class GuideOnlineGridViewAdapter extends BaseAdapter {

     public ArrayList<GuideOnlineItemVo> gridViewArrayList;
  
	 private Context context;
	 
	 private OnClickListener itemClickInterface;
     
     public GuideOnlineGridViewAdapter(Context context,ArrayList<GuideOnlineItemVo> arrayList,OnClickListener itemClickInterface)
     {
     	this.context = context;
     	this.gridViewArrayList = arrayList;
     	this.itemClickInterface = itemClickInterface;
     }
     
		@Override
		public int getCount() 
		{
		   return gridViewArrayList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return gridViewArrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			GuideOnlineItemVo vo = gridViewArrayList.get(position);
			
			ImageView imageView;
			TextView textView;
			 
		    if(convertView == null)
		    {
		    	convertView = LayoutInflater.from(context).inflate(R.layout.guide_online_item, null);
		    	convertView.setOnClickListener(itemClickInterface);
		    	
		    	imageView = (ImageView)convertView.findViewById(R.id.image);
		    	textView = (TextView)convertView.findViewById(R.id.txt);

		    	TempView tempView = new TempView();
		    	tempView.imageView = imageView;
		    	tempView.textView = textView;
		    	
		    	convertView.setTag(R.id.tag_first, tempView);
		    }else 
		    {
		    	TempView tempView = (TempView) convertView.getTag(R.id.tag_first);
				imageView = tempView.imageView;
				textView = tempView.textView;
			}
		    
		    GuideOnlineItemVo oldVo = (GuideOnlineItemVo) convertView.getTag();
			textView.setText(vo.infoString);
			
			if(oldVo == null || oldVo.id != vo.id)
			{
				SingleToolClass.imageDownloader.download(vo.picUrl, imageView, R.drawable.fail_drawable, R.drawable.fail_drawable, true);
			}
			
			convertView.setTag(vo);
		    
			return convertView;
		}
		
		private class TempView
		{
			public ImageView imageView;
			
			public TextView textView;
		}
		 
}
