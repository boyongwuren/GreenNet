package com.nd.pad.view.firstPage;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nd.pad.adapter.KeyCallGridViewAdapter;
import com.nd.pad.constPackage.ConstName;
import com.nd.pad.constPackage.SharedPreferenceKey;
import com.nd.pad.constPackage.ThreadConst;
import com.nd.pad.constPackage.URLConst;
import com.nd.pad.interfacePackage.LoadKeyCallBackInterface;
import com.nd.pad.interfacePackage.LoadWhiteBackInterface;
import com.nd.pad.net.GetKeyCallData;
import com.nd.pad.net.GetWhiteData;
import com.nd.pad.vo.BasePageVo;
import com.nd.pad.vo.KeyCallItemVo;
import com.nd.pad.vo.OftenVisitVo;
import com.nd.pad.vo.sqlDataBase.HandlerKeyCallTable;
import com.nd.pad.vo.sqlDataBase.HandlerOftenVisitTable;
import com.nd.pad.website.WebSiteLimit;
import com.nd.pad.GreenBrowser.R;

public class FirstPageView extends LinearLayout 
{

	/**
	 * 重点推荐的网格
	 */
	private GridView keyCallGridView;
	
	/**
	 * 经常访问的网格
	 */
	private GridView oftenVisitGridView;
	
	/***
	 * 还没有经常访问的页面
	 */
	private TextView noHasOftenVisitTxt;
	
	private OnClickListener itemClickInterface;
	
	/**
	 * 最近访问
	 */
	private ArrayList<OftenVisitVo> ofVos;
		
	public FirstPageView(Context context,OnClickListener itemClickInterface) 
	{
		super(context);
		
		this.itemClickInterface = itemClickInterface;
		
		LayoutInflater.from(context).inflate(R.layout.first_page, this,true);
		
		keyCallGridView = (GridView)findViewById(R.id.keyCallGridView);
		oftenVisitGridView = (GridView)findViewById(R.id.oftenVisitGridView);
		noHasOftenVisitTxt = (TextView)findViewById(R.id.nooftenVisitTxt);
		
		//先取缓存里面的数据作为重点推荐的内容
//		String keyCallJson = SingleToolClass.sharedPreferences.getString(SharedPreferenceKey.KEY_CALL_CONTENT, ConstName.DEFAUL_KEY_CALL);
//		parseKeyCallData(keyCallJson);
		
	    //从网络获取重点推荐的数据
		ArrayList<KeyCallItemVo> kVos = HandlerKeyCallTable.getDataFromTable();
		if(kVos.size()>0)
		{
			//数据表 有数据
			setKeyCallData(kVos);
		}else
		{
			GetKeyCallData.getKeyCallData(new LoadKeyCallBackClass());
		}
		
	    
	    //获取经常访问的网站
	    handlerOftenVisitData();
	}
	
	
	/**
	 * 请求重点推荐回来
	 * @author zmp
	 *
	 */
	private class LoadKeyCallBackClass implements LoadKeyCallBackInterface
	{
		@Override
		public void loadKeyCallBack(ArrayList<KeyCallItemVo> vos)
		{
			setKeyCallData(vos);
			
			HandlerKeyCallTable.insertDataToTable(vos);
		}
	}
	
	/**
	 * 设置重点推荐的数据
	 * @param vos
	 */
	private void setKeyCallData(ArrayList<KeyCallItemVo> vos)
	{
		BasePageVo[] bpvos = new BasePageVo[vos.size()];
		bpvos = vos.toArray(bpvos);
		keyCallGridView.setAdapter(new KeyCallGridViewAdapter(getContext(),bpvos, itemClickInterface));
	}
	
	/**
	 * 对经常访问的网址 进行排序
	 * 高到低
	 * @param ovvos
	 */
	public void handlerOftenVisitData( )
	{
		ofVos = HandlerOftenVisitTable.getDataFromTable();

		if(ofVos.size() == 0 )
		{
			noHasOftenVisitTxt.setVisibility(View.VISIBLE);
	    	oftenVisitGridView.setVisibility(View.INVISIBLE);
		}else 
		{
			noHasOftenVisitTxt.setVisibility(View.INVISIBLE);
			oftenVisitGridView.setVisibility(View.VISIBLE);
			
			BasePageVo[] bpvos = new BasePageVo[ofVos.size()];
			bpvos = ofVos.toArray(bpvos);
			
			oftenVisitGridView.setAdapter(new KeyCallGridViewAdapter(getContext(),  bpvos, itemClickInterface));		
		}
	}
}
