package com.nd.pad.view;

import java.util.ArrayList;

import com.nd.pad.adapter.ViewPagerAdapter;
import com.nd.pad.constPackage.ConstName;
import com.nd.pad.constPackage.PageIndexConst;
import com.nd.pad.interfacePackage.ChangeOftenVisitInterface;
import com.nd.pad.interfacePackage.ChangeTitleTxtInterface;
import com.nd.pad.interfacePackage.HandlerBtnInterface;
import com.nd.pad.interfacePackage.LoadWhiteBackInterface;
import com.nd.pad.interfacePackage.OpenWebViewInterface;
import com.nd.pad.interfacePackage.ViewPageChangeInterface;
import com.nd.pad.net.GetWhiteData;
import com.nd.pad.view.firstPage.FirstPageView;
import com.nd.pad.view.guideOnlie.GuideOnLineView;
import com.nd.pad.view.myCollect.MyCollectPage;
import com.nd.pad.vo.BasePageVo;
import com.nd.pad.vo.sqlDataBase.HandlerCollectTable;
import com.nd.pad.vo.sqlDataBase.HandlerOftenVisitTable;
import com.nd.pad.vo.sqlDataBase.HandlerWhiteNetTable;
import com.nd.pad.website.WebSiteLimit;
import com.nd.pad.GreenBrowser.R;
import com.nd.pad.GreenBrowser.util.LogUtil;
import com.nd.pad.GreenBrowser.util.SingleToolClass;
import com.nd.pad.GreenBrowser.util.WebViewTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * viewPager��ҳ��
 * @author Administrator
 *
 */
public class ViewPagerContent extends LinearLayout {

	private ViewPager viewPager;
	
	private ImageView firstImageView;
	
	private ImageView secondImageView;
	
	private ImageView lastImageView;
	
	private ImageView pageIndexPic;

	/**
	 * ����ҳ��
	 */
	private GuideOnLineView guideOnLineView;
	
	/**
	 * ��ҳҳ��
	 */
	private FirstPageView firstPageView;
	
	/**
	 * �ղ�ҳ��
	 */
	private MyCollectPage myCollectPage;
	
	
	/**
	 * ��ǰviewpagerҳ�������
	 */
	private int pageIndex = 0;
	
	private ViewPageChangeInterface viewPageChangeInterface;
	
	private WebView webView;
	
	private ChangeTitleTxtInterface changeTitleTxtInterface;
	
	/**
	 * ������
	 */
	public ProgressBar progressBar;
	
	private WebSiteLimit netlimit = new WebSiteLimit();
	
	private HandlerBtnInterface handlerBtnInterface;
	
	private ChangeOftenVisitInterface changeOftenVisitInterface;
	
	public ViewPagerContent(Context context,ChangeTitleTxtInterface changeTitleTxtInterface,HandlerBtnInterface handlerBtnInterface)
	{
		super(context);
		
		this.handlerBtnInterface = handlerBtnInterface;
		
		LayoutInflater.from(context).inflate(R.layout.green_net_viewpager, this,true);
		
		viewPager = (ViewPager)findViewById(R.id.viewPager);
		
		firstImageView = (ImageView)findViewById(R.id.firstPage);
		secondImageView = (ImageView)findViewById(R.id.secondPage);
		lastImageView = (ImageView)findViewById(R.id.lastPage);
		pageIndexPic = (ImageView)findViewById(R.id.pageIndexPic);
		
		viewPager.setAdapter(createViewPagerData());
		viewPager.setOnPageChangeListener(new ViewPagerOnPageChangeListener());
		
		webView = (WebView) findViewById(R.id.webView);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);  
		webView.setVisibility(View.GONE);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);   
		webView.setWebViewClient(new WebViewClientClass());
		webView.setWebChromeClient(new WebChromeClientClass());
		WebViewTool.setZoomControlGone(webView);  
		
		this.changeTitleTxtInterface = changeTitleTxtInterface;		
		
		ArrayList<String> whiteNets = HandlerWhiteNetTable.getDataFromTable();
		if(whiteNets!=null&&whiteNets.size()>0)
		{
			//���ݿ����а�����
			netlimit.AddWhiteNet(whiteNets);
		}else 
		{
			//���������
			GetWhiteData.getWhiteData(new LoadWhiteBackClass());
		}
	}
	
	public void setChangeOftenInterface(ChangeOftenVisitInterface changeOftenVisitInterface)
	{
		this.changeOftenVisitInterface = changeOftenVisitInterface;
	}
	
	
	/**
	 * ����������
	 * @author zmp
	 *
	 */
	private class LoadWhiteBackClass implements LoadWhiteBackInterface
	{
		@Override
		public void loadWhiteBack(ArrayList<String> whites)
		{
			netlimit.AddWhiteNet(whites);
			HandlerWhiteNetTable.insertDataToTable(whites);
		}
	}
	
	
	
	/**
	 * webview���ؽ���
	 * @author Administrator
	 *
	 */
	private class WebChromeClientClass extends WebChromeClient
	{
		@Override
        public void onProgressChanged(WebView view, int newProgress) 
        { 
                // TODO Auto-generated method stub 
                super.onProgressChanged(view, newProgress); 
                //newProgress���ǽ���Ŷ 
                //progressbar���Լ������� 
                //������ֱ������progress���Ⱥ����������˵ 
                System.out.println("newProgress = "+newProgress);
                progressBar.setMax(100);
                progressBar.setProgress(newProgress);
                if(newProgress == 100)
                {
                	progressBar.setVisibility(View.INVISIBLE);
                }else 
                {
                	if(ViewPagerContent.this.getVisibility() == View.VISIBLE)
                	{
                		progressBar.setVisibility(View.VISIBLE);
                	}
				}
        } 
		
		@Override 
        public void onReceivedTitle(WebView view, String title) 
		{ 
            //���õ�ǰactivity�ı����� 
            super.onReceivedTitle(view, title); 
            changeTitleTxtInterface.changeTitleTxt(title, ViewPagerContent.this);
        } 

		@Override
		public void onReceivedIcon(WebView view, Bitmap icon)
		{
			super.onReceivedIcon(view, icon);
			changeTitleTxtInterface.changeTitleWebPic(icon);
		}

	}
	
	private class WebViewClientClass extends WebViewClient
	{
		@Override
		public void onReceivedError(WebView view, int errorCode,String description, String failingUrl)
		{
			webView.loadUrl("file:///android_asset/errorPage.html");  
	    }
		
		@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
		{ 
            view.loadUrl(url); 
            return true; 
        } 
	}
	
	
	public void setViewPageChangeInf(ViewPageChangeInterface viewPageChangeInterface)
	{
		this.viewPageChangeInterface = viewPageChangeInterface;
		
		pageGotoIndex(PageIndexConst.FIRST_PAGE);
	}
	
	/**
	 * ����viewpager��ת���ڼ�������
	 * @param index
	 */
	public void pageGotoIndex(int index)
	{
		viewPager.setCurrentItem(index);
		doPageSelected(index);
	}

	public ViewPagerContent(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private ViewPagerAdapter createViewPagerData()
	{
		ArrayList<View> pages = new ArrayList<View>();
		
		firstPageView = new FirstPageView(getContext(),itemClickOpenWebView);
		pages.add(firstPageView);
		
		guideOnLineView = new GuideOnLineView(getContext(),itemClickOpenWebView);
		pages.add(guideOnLineView);

		myCollectPage = new MyCollectPage(getContext(),itemClickOpenWebView);
		pages.add(myCollectPage);
		
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pages);
		
		return viewPagerAdapter;
	}
	
	private ItemClickOpenWebView itemClickOpenWebView = new ItemClickOpenWebView();
	
	/**
	 * �������
	 * @author zmp
	 *
	 */
	private class ItemClickOpenWebView implements OnClickListener , OpenWebViewInterface
	{
		private void itemClick(BasePageVo vo)
		{
			webView.clearView();
			webView.clearHistory();
			webView.setVisibility(View.VISIBLE);
			// �ж��Ƿ��ǰ�����
			if (netlimit.MatchWhiteNet(vo.webUrl))
			{
				webView.loadUrl(vo.webUrl);
				HandlerOftenVisitTable.insertDataToTable(vo.infoString, vo.picUrl, vo.webUrl);
				
				changeOftenVisitInterface.changeOftenVisit();
			}
			else 
			{
				//�����վ���ڰ�������
				LogUtil.showTip("��ֹ���ʸ���վ");
				webView.loadUrl("file:///android_asset/errorPage.html");  
			}
			
			handlerBtnInterface.setHomeBtnEnable(true);
			
			handlerBtnInterface.setFlushBtnEnable(true);
			handlerBtnInterface.setCollectBtnEnable(true);
			
			handlerBtnInterface.setBackBtnEnable(true);
		}

		@Override
		public void onClick(View v)
		{
			BasePageVo vo = (BasePageVo) v.getTag();
			itemClick(vo);
		}

		@Override
		public void openWebView(BasePageVo vo)
		{
			itemClick(vo);
		}
	}	
	
	/**
	 * ������ҳ�� �������ʱ�
	 */
	public void handlerFirstOftenVisit()
	{
		firstPageView.handlerOftenVisitData();
	}

	/**
	 * ��ȡwebView�ı���
	 * @return
	 */
	public String getWebViewTitle()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			return webView.getTitle();
		}else
		{
			return ConstName.VIEW_PAGER_TITLE[pageIndex];
		}
	}

	private class ViewPagerOnPageChangeListener implements OnPageChangeListener
	{
		@Override
		public void onPageScrollStateChanged(int arg0)
		{
	    }
		
		@Override
		public void onPageScrolled(int arg0,float arg1,int arg2)
		{
		}
		
		@Override
		public void onPageSelected(int arg0)
		{
			doPageSelected(arg0);
		}
	}
	
	/**
	 * �໬ҳ�л���ʱ��
	 * �ı�ײ���ť��״̬
	 */
	public void updataBtnState()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			//webview�ɼ�
			handlerBtnInterface.setHomeBtnEnable(true);
			handlerBtnInterface.setFlushBtnEnable(true);
			handlerBtnInterface.setCollectBtnEnable(true);
			handlerBtnInterface.setBackBtnEnable(true);
			if(webView.canGoForward())
			{
				handlerBtnInterface.setNextBtnEnable(true);
			}else {
				handlerBtnInterface.setNextBtnEnable(false);
			}
		}else 
		{
			if(pageIndex == PageIndexConst.FIRST_PAGE)
			{
				handlerBtnInterface.setHomeBtnEnable(false);
			}else 
			{
				handlerBtnInterface.setHomeBtnEnable(true);
			}
			
			handlerBtnInterface.setFlushBtnEnable(false);
			handlerBtnInterface.setCollectBtnEnable(false);
			handlerBtnInterface.setBackBtnEnable(false);
			handlerBtnInterface.setNextBtnEnable(false);
			
		}
	}
	
	
	/**
	 * ִ��ҳ���л��Ĳ���
	 * @param arg0
	 */
	private void doPageSelected(int arg0)
	{
		pageIndex = arg0;
		
		android.widget.RelativeLayout.LayoutParams lpLayoutParams = (android.widget.RelativeLayout.LayoutParams) pageIndexPic.getLayoutParams();
		
		if(arg0 == PageIndexConst.FIRST_PAGE)
		{
			lpLayoutParams.setMargins(345, 0, 0, 0);
		}else if(arg0 == PageIndexConst.GUIDE_ONLINE_PAGE)
		{
			lpLayoutParams.setMargins(385, 0, 0, 0);
		}else 
		{
			lpLayoutParams.setMargins(420, 0, 0, 0);
		}
		
		pageIndexPic.setLayoutParams(lpLayoutParams);
		
		// ҳ�滬���ˡ�֪ͨ�ⲿ
		if(viewPageChangeInterface != null)
		{
			viewPageChangeInterface.viewPageChange(arg0);
		}
		
		if(arg0 == PageIndexConst.FIRST_PAGE)
		{
			//��ʾ��ҳ ��ҳ��ť��ɲ�����
			handlerBtnInterface.setHomeBtnEnable(false);
		}else 
		{
			//��ҳ��ť����
			handlerBtnInterface.setHomeBtnEnable(true);
		}
	}
 

	/**
	 * �ص���ҳ
	 */
	public void goBackHomePage()
	{
		webViewBackView();
		pageGotoIndex(PageIndexConst.FIRST_PAGE);
	}
	
	/**
	 * ˢ�½���
	 */
	public void flushWebView()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			webView.reload();
		}
	}
	
	/**
	 * ��ǰ�������
	 */
	public void goBack()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			if(webView.canGoBack())
			{
				webView.goBack();
				 
	            handlerBtnInterface.setNextBtnEnable(true);
	             
			}
			else 
			{
				webViewBackView();
				if(pageIndex == PageIndexConst.FIRST_PAGE)
				{
					handlerBtnInterface.setHomeBtnEnable(false);
				}else 
				{
					handlerBtnInterface.setHomeBtnEnable(true);
				}
				
				// ҳ�滬���ˡ�֪ͨ�ⲿ
				if (viewPageChangeInterface != null)
				{
					viewPageChangeInterface.viewPageChange(pageIndex);
				}
			}
		}
		
	}
	
	/**
	 * ����ҳ�˵�viewPager��������
	 */
	private void webViewBackView()
	{
		webView.stopLoading();
		webView.clearHistory();
		webView.clearView();
		webView.setVisibility(View.GONE);
		
		handlerBtnInterface.setFlushBtnEnable(false);
		handlerBtnInterface.setCollectBtnEnable(false);
		handlerBtnInterface.setBackBtnEnable(false);
		handlerBtnInterface.setNextBtnEnable(false);
	}

	/**
	 * ��ǰ����ǰ��
	 */
	public void goForword()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			if(webView.canGoForward())
			{
				webView.goForward();
				if(webView.canGoForward()==false)
				{
					handlerBtnInterface.setNextBtnEnable(false);
				}
			} 
		}
	}
	
	/**
	 * �ղص�ǰҳ��
	 */
	public void collectCurPage()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			String titleString = webView.getTitle();
			
			String url = webView.getUrl();
			
			if(titleString == null || titleString.equals(""))
			{
				titleString = url;
			}
			
			String picUrl = url+"/favicon.ico";
			HandlerCollectTable.insertDataToTable(titleString, picUrl, url);
			Toast toast = Toast.makeText(SingleToolClass.content, "�ղسɹ�", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	@Override
	public void setVisibility(int visibility)
	{
		super.setVisibility(visibility);
		
		if(visibility == View.VISIBLE && 
		   webView.getVisibility() == View.VISIBLE && 
		   progressBar != null && 
		   progressBar.getProgress() != progressBar.getMax())
		{
			 progressBar.setVisibility(View.VISIBLE);
		}
	}
}
