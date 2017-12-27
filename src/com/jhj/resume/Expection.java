package com.jhj.resume;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class Expection extends Activity{
	
	private EditText expection;
	public static final String EXPECTION_INFO = "Expection_info";
	public static final String EXPECTION = "Expection";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expection);
		
		dataeditExpection();
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
	}
	
	private void dataeditExpection(){
		
		expection = (EditText) findViewById(R.id.my_expection);
		SharedPreferences studyinfo = getSharedPreferences(EXPECTION_INFO, 0);
		String expection_name1 = studyinfo.getString(EXPECTION, ""); 
		expection.setText(expection_name1);   
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
    public void onStop(){ 
        super.onStop(); 
        SharedPreferences studyinfo = getSharedPreferences(EXPECTION_INFO, 0); //首先获取一个SharedPreferences对象 
        studyinfo.edit().putString(EXPECTION, expection.getText().toString()).commit(); 
    } 
	
}
