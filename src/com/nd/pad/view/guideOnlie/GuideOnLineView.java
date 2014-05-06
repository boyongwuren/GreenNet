package com.nd.pad.view.guideOnlie;

import java.util.ArrayList;

import com.nd.pad.adapter.GuideOnlineAdapter;
import com.nd.pad.net.GetCategoryData;
import com.nd.pad.vo.CategoryVo;
import com.nd.pad.vo.sqlDataBase.HandlerCategoryTable;
import com.nd.pad.GreenBrowser.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

/**
 * 导航页面
 * @author Administrator
 * 
 */
public class GuideOnLineView extends LinearLayout
{
	private ExpandableListView guideOnlineView;

	private Context context = null;

	private OnClickListener itemClickInterface;

	public GuideOnLineView(Context context, OnClickListener itemClickInterface)
	{
		super(context);
		LayoutInflater.from(context).inflate(R.layout.guide_online, this, true);

		this.context = context;
		this.itemClickInterface = itemClickInterface;

		guideOnlineView = (ExpandableListView) findViewById(R.id.guideOnlineView);

		guideOnlineView.setGroupIndicator(null);
		guideOnlineView.setDivider(null);

		ArrayList<CategoryVo> cVos = HandlerCategoryTable.getDataFromTable();
        if(cVos.size()!= 0)
        {
        	//已经取过数据了
        	guideOnlineView.setAdapter(new GuideOnlineAdapter(context, cVos, itemClickInterface));
        	expendGroup(cVos.size());
        }else 
        {
        	GetCategoryData.getCategoryData(new LoadCategoryBackClass());
		}
	}

	
    public interface LoadCategoryBackInterface
    {
    	public void loadCategoryBack(ArrayList<CategoryVo> cvos);
    }
    
    /**
     * 请求类别数据返回
     * @author zmp
     *
     */
    public class LoadCategoryBackClass implements LoadCategoryBackInterface
    {
		@Override
		public void loadCategoryBack(ArrayList<CategoryVo> cvos)
		{
			HandlerCategoryTable.insertDataToTable(cvos);
			cvos = HandlerCategoryTable.getDataFromTable();
			guideOnlineView.setAdapter(new GuideOnlineAdapter(context, cvos, itemClickInterface));
			
			expendGroup(cvos.size());
		}
    }
    
    private void expendGroup(int size)
    {
    	//GuideOnlineAdapter expandableListAdapter =  (GuideOnlineAdapter) guideOnlineView.getAdapter();
//    	for(int i = 0; i < size; i++)
//    	{  
//    		guideOnlineView.expandGroup(i);  
//    	}  

    }

}
