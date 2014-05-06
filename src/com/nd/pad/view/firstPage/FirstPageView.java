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
	 * �ص��Ƽ�������
	 */
	private GridView keyCallGridView;
	
	/**
	 * �������ʵ�����
	 */
	private GridView oftenVisitGridView;
	
	/***
	 * ��û�о������ʵ�ҳ��
	 */
	private TextView noHasOftenVisitTxt;
	
	private OnClickListener itemClickInterface;
	
	/**
	 * �������
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
		
		//��ȡ���������������Ϊ�ص��Ƽ�������
//		String keyCallJson = SingleToolClass.sharedPreferences.getString(SharedPreferenceKey.KEY_CALL_CONTENT, ConstName.DEFAUL_KEY_CALL);
//		parseKeyCallData(keyCallJson);
		
	    //�������ȡ�ص��Ƽ�������
		ArrayList<KeyCallItemVo> kVos = HandlerKeyCallTable.getDataFromTable();
		if(kVos.size()>0)
		{
			//���ݱ� ������
			setKeyCallData(kVos);
		}else
		{
			GetKeyCallData.getKeyCallData(new LoadKeyCallBackClass());
		}
		
	    
	    //��ȡ�������ʵ���վ
	    handlerOftenVisitData();
	}
	
	
	/**
	 * �����ص��Ƽ�����
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
	 * �����ص��Ƽ�������
	 * @param vos
	 */
	private void setKeyCallData(ArrayList<KeyCallItemVo> vos)
	{
		BasePageVo[] bpvos = new BasePageVo[vos.size()];
		bpvos = vos.toArray(bpvos);
		keyCallGridView.setAdapter(new KeyCallGridViewAdapter(getContext(),bpvos, itemClickInterface));
	}
	
	/**
	 * �Ծ������ʵ���ַ ��������
	 * �ߵ���
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
