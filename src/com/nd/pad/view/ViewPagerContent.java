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
 * viewPager的页面
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
	 * 导航页面
	 */
	private GuideOnLineView guideOnLineView;
	
	/**
	 * 首页页面
	 */
	private FirstPageView firstPageView;
	
	/**
	 * 收藏页面
	 */
	private MyCollectPage myCollectPage;
	
	
	/**
	 * 当前viewpager页面的索引
	 */
	private int pageIndex = 0;
	
	private ViewPageChangeInterface viewPageChangeInterface;
	
	private WebView webView;
	
	private ChangeTitleTxtInterface changeTitleTxtInterface;
	
	/**
	 * 进度条
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
			//数据库已有白名单
			netlimit.AddWhiteNet(whiteNets);
		}else 
		{
			//请求白名单
			GetWhiteData.getWhiteData(new LoadWhiteBackClass());
		}
	}
	
	public void setChangeOftenInterface(ChangeOftenVisitInterface changeOftenVisitInterface)
	{
		this.changeOftenVisitInterface = changeOftenVisitInterface;
	}
	
	
	/**
	 * 白名单返回
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
	 * webview加载进度
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
                //newProgress就是进度哦 
                //progressbar你自己调整吧 
                //在这里直接设置progress进度好像会出问题的说 
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
            //设置当前activity的标题栏 
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
	 * 控制viewpager跳转到第几个界面
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
	 * 打开浏览器
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
			// 判断是否是白名单
			if (netlimit.MatchWhiteNet(vo.webUrl))
			{
				webView.loadUrl(vo.webUrl);
				HandlerOftenVisitTable.insertDataToTable(vo.infoString, vo.picUrl, vo.webUrl);
				
				changeOftenVisitInterface.changeOftenVisit();
			}
			else 
			{
				//这个网站不在白名单内
				LogUtil.showTip("禁止访问该网站");
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
	 * 更新首页的 经常访问表
	 */
	public void handlerFirstOftenVisit()
	{
		firstPageView.handlerOftenVisitData();
	}

	/**
	 * 获取webView的标题
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
	 * 侧滑页切换的时候
	 * 改变底部按钮的状态
	 */
	public void updataBtnState()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			//webview可见
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
	 * 执行页面切换的操作
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
		
		// 页面滑动了。通知外部
		if(viewPageChangeInterface != null)
		{
			viewPageChangeInterface.viewPageChange(arg0);
		}
		
		if(arg0 == PageIndexConst.FIRST_PAGE)
		{
			//显示首页 主页按钮变成不可用
			handlerBtnInterface.setHomeBtnEnable(false);
		}else 
		{
			//主页按钮可用
			handlerBtnInterface.setHomeBtnEnable(true);
		}
	}
 

	/**
	 * 回到首页
	 */
	public void goBackHomePage()
	{
		webViewBackView();
		pageGotoIndex(PageIndexConst.FIRST_PAGE);
	}
	
	/**
	 * 刷新界面
	 */
	public void flushWebView()
	{
		if(webView.getVisibility() == View.VISIBLE)
		{
			webView.reload();
		}
	}
	
	/**
	 * 当前界面回退
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
				
				// 页面滑动了。通知外部
				if (viewPageChangeInterface != null)
				{
					viewPageChangeInterface.viewPageChange(pageIndex);
				}
			}
		}
		
	}
	
	/**
	 * 从网页退到viewPager做的事情
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
	 * 当前界面前进
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
	 * 收藏当前页面
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
			Toast toast = Toast.makeText(SingleToolClass.content, "收藏成功", Toast.LENGTH_SHORT);
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
