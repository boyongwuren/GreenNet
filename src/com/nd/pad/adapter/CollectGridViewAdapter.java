package com.nd.pad.adapter;

import java.util.ArrayList;

import com.nd.pad.interfacePackage.OpenWebViewInterface;
import com.nd.pad.interfacePackage.ShowCloseBtnInterface;
import com.nd.pad.view.myCollect.CollectItemView;
import com.nd.pad.vo.BasePageVo;
import com.nd.pad.vo.CollectVo;
import com.nd.pad.GreenBrowser.util.SingleToolClass;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

/**
 *  ’≤ÿµƒ  ≈‰∆˜
 * @author zmp
 *
 */
public class CollectGridViewAdapter extends BaseAdapter
{

	private ArrayList<CollectVo> cVos;
	
	private ShowCloseBtnInterface showCloseBtnInterface;
	
	private OpenWebViewInterface itemClickInterface;
	
	public CollectGridViewAdapter(ArrayList<CollectVo> cvos,ShowCloseBtnInterface showCloseBtnInterface,OpenWebViewInterface itemClickInterface)
	{
		cVos = cvos;
		this.itemClickInterface = itemClickInterface;
		this.showCloseBtnInterface = showCloseBtnInterface;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return cVos.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		CollectItemView collectItemView;
		
		 if(convertView == null)
		 {
			 convertView = new CollectItemView(SingleToolClass.content,showCloseBtnInterface,itemClickInterface);
//			 convertView.setOnClickListener(itemClickInterface);
		 }
		 
		 collectItemView = (CollectItemView) convertView;
		 
		 CollectVo vo = cVos.get(position);
		 
		 collectItemView.setCollectVo(vo);
		 collectItemView.setTag(vo);
		 
		 return collectItemView;
	}

}
