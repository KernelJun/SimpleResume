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

public class StudyExperience extends Activity implements  android.view.View.OnClickListener, OnPageChangeListener {
	
	TextView textview1,textview2,textview3;
	ViewPager viewPager;
	private ArrayList<View> views;
	View view1, view2, view3; 
	private ImageView imageview;
	private int imageWidth, screenWidth;
	private int offset = 0;
	
	public static final String STUDY_INFO = "Study_info";
	public static final String STUDY_EDIT1 = "Study_edit1";
	public static final String STUDY_EDIT2 = "Study_edit2";
	public static final String STUDY_EDIT3 = "Study_edit3";
	
	private EditText studyedit1, studyedit2, studyedit3;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studyexperience);
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);	
		
		initTextView();
		initImageView();
		initViewPager();
		dataedit();
		

		
	}
	
    @Override 
    public void onStop(){ 
        super.onStop(); 
        SharedPreferences studyinfo = getSharedPreferences(STUDY_INFO, 0); //首先获取一个SharedPreferences对象 
        studyinfo.edit().putString(STUDY_EDIT1, studyedit1.getText().toString()).commit();
        studyinfo.edit().putString(STUDY_EDIT2, studyedit2.getText().toString()).commit();
        studyinfo.edit().putString(STUDY_EDIT3, studyedit3.getText().toString()).commit(); 
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
	
	private void initTextView(){
		int Id[] = new int[] {R.id.study_text1, R.id.study_text2, R.id.study_text3};
		TextView textView[] = new TextView[] {textview1, textview2, textview3};
		for (int i=0; i<=2; i++){
		textView[i] = (TextView) findViewById(Id[i]);  
		textView[i].setOnClickListener(this);
		}
	}
	
	private void initImageView(){
		imageview = (ImageView)findViewById(R.id.cursor);
		
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
	
	private void initViewPager(){
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.study1, null);
		view2 = inflater.inflate(R.layout.study2, null);
		view3 = inflater.inflate(R.layout.study3, null);
		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new StudyPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(this);	
	}
	
	private void dataedit(){
		
		//Log.d()
		studyedit1 = (EditText) view1.findViewById(R.id.study_edit1);
		studyedit2 = (EditText) view2.findViewById(R.id.study_edit2);
		studyedit3 = (EditText) view3.findViewById(R.id.study_edit3);
		
		SharedPreferences studyinfo = getSharedPreferences(STUDY_INFO, 0);
		String study_name1 = studyinfo.getString(STUDY_EDIT1, ""); 
		String study_name2 = studyinfo.getString(STUDY_EDIT2, ""); 
		String study_name3 = studyinfo.getString(STUDY_EDIT3, ""); 
		
		studyedit1.setText(study_name1);   
		studyedit2.setText(study_name2); 
		studyedit3.setText(study_name3); 
		
	}
	
	public class StudyPagerAdapter extends PagerAdapter{
		private ArrayList<View> mListViews;
		
		public StudyPagerAdapter ( ArrayList<View> mListViews){
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
		case R.id.study_text1:
			viewPager.setCurrentItem(0);
			break;
		case R.id.study_text2:
			viewPager.setCurrentItem(1);
			break;
		case R.id.study_text3:
			viewPager.setCurrentItem(2);
			break;
		}
		
	}
}
