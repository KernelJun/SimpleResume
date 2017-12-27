package com.jhj.resume;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class Tabhost extends TabActivity implements  OnTabChangeListener{

	private static TabHost mTabHost;
	private EditText edit1, edit2, edit3;
	public static final String TAB1_TEXT = "Tab1_text";
	public static final String TEXT01 = "Text01";
	public static final String TEXT02 = "Text02";
	public static final String TEXT03 = "Text03";
	
    private int mCurrentTab;
    static final int TAB1_INDEX = 0;
    static final int TAB2_INDEX = 1;
    static final int TAB3_INDEX = 2;
    private ArrayList<View> mPagers = new ArrayList<View>(TAB3_INDEX);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		mTabHost=getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main, mTabHost.getTabContentView(), true);
		int []tabids=new int[]{R.id.tab1,R.id.tab2,R.id.tab3}; 
		String tabnames[] = new String[]{"tab1","tab2","tab3"};
//		Integer[] titles_int = new Integer[] {R.string.experience_title, R.string.project_tile , R.string.tools_title};
		String[]  titles = new String[] {"经验概述", "项目经验", "工具经验"};
		
		for (int i=0; i<3 ; i++){
			mTabHost.addTab(mTabHost.newTabSpec(tabnames[i]).setIndicator(titles[i]).setContent(tabids[i])); 
		}
		
		//mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
		mTabHost.setOnTabChangedListener(this);
		
		edit1 = (EditText) findViewById(R.id.text1);
		edit2 = (EditText) findViewById(R.id.text2);
		edit3 = (EditText) findViewById(R.id.text3);
		SharedPreferences settings = getSharedPreferences(TAB1_TEXT,0);
		String name = settings.getString(TEXT01, ""); 
		String name1 = settings.getString(TEXT02, ""); 
		String name2 = settings.getString(TEXT03, ""); 
		edit1.setText(name);   
		edit2.setText(name1); 
		edit3.setText(name2); 
			
		}
	
    public void onTabChanged(String tabId) {
    	return;
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	
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
    
    @Override 
    protected void onStop(){ 
        super.onStop(); 
        SharedPreferences settings = getSharedPreferences(TAB1_TEXT, 0); //���Ȼ�ȡһ��SharedPreferences���� 
        settings.edit().putString(TEXT01, edit1.getText().toString()).putString(TEXT02, edit2.getText().toString()).putString(TEXT03, edit3.getText().toString()).commit(); 
    } 
    
}
