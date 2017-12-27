package com.jhj.resume;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyIntroduction extends Activity implements  android.view.View.OnClickListener, OnPageChangeListener {
	
    TextView introduction1,introduction2,introduction3;
	ViewPager introduct_viewPager;
	private ArrayList<View> introduct_views;
	View introduct_view1, introduct_view2, introduct_view3; 
	private ImageView imageview;
	private int imageWidth, screenWidth;
	private int offset = 0;
	
	public static final String INTRODUCT_INFO = "Introduct_info";
	public static final String INTRODUCT_EDIT1 = "Introduc_edit1";
	public static final String INTRODUCT_EDIT2 = "Introduc_edit2";
	public static final String INTRODUCT_EDIT3 = "Introduc_edit3";
	
	private EditText introductedit1, introductedit2, introductedit3;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myintroduction);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		initTextViewIntroduct();
		initImageViewIntroduct();
		initViewPagerIntroduct();
		dataeditIntroduct();
		

	}
	
    @Override 
    public void onStop(){ 
        super.onStop(); 
        SharedPreferences studyinfo = getSharedPreferences(INTRODUCT_INFO, 0); //首先获取一个SharedPreferences对象 
        studyinfo.edit().putString(INTRODUCT_EDIT1, introductedit1.getText().toString()).putString(INTRODUCT_EDIT2, introductedit2.getText().toString()).putString(INTRODUCT_EDIT3, introductedit3.getText().toString()).commit(); 
    } 
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {    
        switch (item.getItemId()) 
        {        
            case android.R.id.home:            
                // special case: app icon in Action Bar clicked; go to launch activity            
                Intent intent = new Intent(this, Mainbutton.class);            
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);          // clear the activity stack  
                startActivity(intent);            
                return true;        
            default:            
                return super.onOptionsItemSelected(item);    
        }
    }
	
	private void initTextViewIntroduct(){
		int Id[] = new int[] {R.id.introduct_text1, R.id.introduct_text2, R.id.introduct_text3};
		TextView textView[] = new TextView[] {introduction1, introduction2, introduction3};
		for (int i=0; i<=2; i++){
		textView[i] = (TextView) findViewById(Id[i]);  
		textView[i].setOnClickListener(this);
		}
	}
	
	private void initImageViewIntroduct(){
		imageview = (ImageView)findViewById(R.id.cursor_introduct);
		
		/*
		 *计算资源文件中的图片的像素 
		 */
		imageWidth = BitmapFactory.decodeResource(getResources(), R.drawable.strip).getWidth();
		
		/*
		 *计算每个设备的像素
		 */
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenWidth =  displaymetrics.widthPixels;
		
		/*
		 * 计算偏移量
		 */
		offset = (screenWidth / 3 - imageWidth) / 2;
		
		/*
		 * 对所调用的资源文件做平移
		 */
		Matrix matric = new Matrix();
		matric.postTranslate(offset, 0);
		imageview.setImageMatrix(matric);
		
	}
	
	private void initViewPagerIntroduct(){
		introduct_viewPager = (ViewPager) findViewById(R.id.viewpager_introduct);
		introduct_views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		introduct_view1 = inflater.inflate(R.layout.introduction1, null);
		introduct_view2 = inflater.inflate(R.layout.introduction2, null);
		introduct_view3 = inflater.inflate(R.layout.introduction3, null);
		introduct_views.add(introduct_view1);
		introduct_views.add(introduct_view2);
		introduct_views.add(introduct_view3);
		introduct_viewPager.setAdapter(new IntroductPagerAdapter(introduct_views));
		introduct_viewPager.setCurrentItem(0);
		introduct_viewPager.setOnPageChangeListener(this);	
	}
	
	private void dataeditIntroduct(){
		
		//Log.d()
		introductedit1 = (EditText) introduct_view1.findViewById(R.id.introduct_edit1);
		introductedit2 = (EditText) introduct_view2.findViewById(R.id.introduct_edit2);
		introductedit3 = (EditText) introduct_view3.findViewById(R.id.introduct_edit3);
		
		SharedPreferences studyinfo = getSharedPreferences(INTRODUCT_INFO, 0);
		String introduct_name1 = studyinfo.getString(INTRODUCT_EDIT1, ""); 
		String introduct_name2 = studyinfo.getString(INTRODUCT_EDIT2, ""); 
		String introduct_name3 = studyinfo.getString(INTRODUCT_EDIT3, ""); 
		
		introductedit1.setText(introduct_name1);   
		introductedit2.setText(introduct_name2); 
		introductedit3.setText(introduct_name3); 
		
	}
	
	public class IntroductPagerAdapter extends PagerAdapter{
		private ArrayList<View> mListViews;
		
		public IntroductPagerAdapter ( ArrayList<View> mListViews){
			this.mListViews = mListViews;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{	
			container.removeView(mListViews.get(position));
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {			
			 container.addView(mListViews.get(position), 0);
			 return mListViews.get(position);
		}

		@Override
		public int getCount() {			
			return  mListViews.size();
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {			
			return arg0==arg1;
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		int apartfrom = imageWidth + offset*2;
		int currentIndex = 0;
		TranslateAnimation animation = new TranslateAnimation(currentIndex*apartfrom, arg0*apartfrom, 0, 0);
		currentIndex = arg0;
		animation.setFillAfter(true);
		imageview.startAnimation(animation);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.introduct_text1:
			introduct_viewPager.setCurrentItem(0);
			break;
		case R.id.introduct_text2:
			introduct_viewPager.setCurrentItem(1);
			break;
		case R.id.introduct_text3:
			introduct_viewPager.setCurrentItem(2);
			break;
		}
		
	}
}
