package com.nd.pad.view.guideOnlie;

import java.util.ArrayList;
import java.util.Map;

import com.nd.pad.adapter.GuideOnlineGridViewAdapter;
import com.nd.pad.net.GetGuideData;
import com.nd.pad.vo.GuideOnlineItemVo;
import com.nd.pad.vo.sqlDataBase.HandlerGuideDataTable;
import com.nd.pad.GreenBrowser.R;
import com.nd.pad.baseView.GridViewOpen;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class GuideOnlineLittle extends RelativeLayout 
{
	private GridViewOpen gridView;
	
	private OnClickListener itemClickInterface;
	
	private int categoryId;
	
	private ArrayList<GuideOnlineItemVo> gVos;
	
	private GuideOnlineGridViewAdapter guideOnlineGridViewAdapter;
	
	public GuideOnlineLittle(Context context,int categoryId,OnClickListener itemClickInterface) 
	{
		super(context);
		this.itemClickInterface = itemClickInterface;
		this.categoryId = categoryId;
		
		LayoutInflater.from(context).inflate(R.layout.guide_online_little, this, true);
		gridView = (GridViewOpen)findViewById(R.id.guideOnlineGridView);	
		
		getData();
	}
	
	public void setGridArrayList( )
	{
		if(guideOnlineGridViewAdapter == null)
		{
			guideOnlineGridViewAdapter = new GuideOnlineGridViewAdapter(getContext(), gVos, itemClickInterface);
			gridView.setAdapter(guideOnlineGridViewAdapter);
		}else 
		{
			guideOnlineGridViewAdapter.gridViewArrayList = gVos;
			guideOnlineGridViewAdapter.notifyDataSetChanged();
		}
	}
	
	public interface LoadGuideDataBackInterface 
	{
		public void loadGuideDataBack(ArrayList<GuideOnlineItemVo> gvos);
	}

	/**
	 * ��������µ����� ����
	 * @author zmp
	 *
	 */
	public class LoadGuideDataBackClass implements LoadGuideDataBackInterface
	{
		@Override
		public void loadGuideDataBack(ArrayList<GuideOnlineItemVo> gvos)
		{
			gVos = gvos;
			setGridArrayList();
			
			//��������
			HandlerGuideDataTable.insertDataToTable(gVos);
		}
	}
	
	public void updataData(int categoryId)
	{
	   if(categoryId == this.categoryId)
	   {
		   //������
	   }else 
	   {
		   this.categoryId = categoryId;
		   getData();
	   }
	}
	
	/**
	 * ��ȡ����
	 */
	private void getData()
	{
		gVos = HandlerGuideDataTable.getDataFromTable(categoryId);
		if(gVos.size()>0)
		{
			//��ֵ
			setGridArrayList();
		}else 
		{
			//ûֵ
			GetGuideData.getGuideData(this.categoryId,new LoadGuideDataBackClass());
		}
	}

}
