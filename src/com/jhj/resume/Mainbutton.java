package com.jhj.resume;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class Mainbutton extends Activity implements OnClickListener{
	private static final Context Context = null;
	private Button work_experience1, study_experience1, boke, my_expection, my_introduction, jhjtelephone, photo_show;
	//private TextView jhjtelephone;
	private String num = "13760317901"; 
	private long mExittime = 0 ;
	private TextView imagerSwitherTextview;
	private ImageSwitcher imageSwitcher;
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.mainbutton);
    	 
    	imagerSwitherTextview = (TextView)findViewById(R.id.imageswitcher_button);
    	imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswithcer);
    	imagerSwitherTextview.setOnClickListener(this);
    	
    	initbutton();
    	//jhjtelephone = (TextView) findViewById(R.id.telephone);
    	//jhjtelephone = (Button) findViewById(R.id.telephone);
    	//jhjtelephone.setOnClickListener(this);
    }
    
    private void initbutton(){
		int MainId[] = new int[] {R.id.work_experience, R.id.study_experience, R.id.my_boke, R.id.my_expects, R.id.my_introducts, R.id.telephone, R.id.my_photoshow};
		Button button[] = new Button[] {work_experience1, study_experience1, boke, my_expection, my_introduction, jhjtelephone, photo_show};
		for (int i=0; i<=6; i++){
			button[i] = (Button) findViewById(MainId[i]);  
			button[i].setOnClickListener(this);
		}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
		
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.settings:
			Intent intent = new Intent(this,Pres.class);
			startActivity(intent);
			return true;
		case R.id.my_apps:
			Intent gratitude1 = new Intent(this,Gratitude.class);
			startActivity(gratitude1);
			return true;
		}
		return false;
		
		
		//save the current puzzle	
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Music.play(this, R.raw.music);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Music.stop(this);
		
		//save the current puzzle	
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		System.exit(0);	
	}
	
	@Override
	public boolean onKeyDown(int keycode, KeyEvent event){
		if(keycode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			if((System.currentTimeMillis() - mExittime) > 2000){
				Toast.makeText(getApplicationContext(), R.string.button_exit, Toast.LENGTH_SHORT).show();
				mExittime = System.currentTimeMillis();
			}else{
				finish();
				//System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keycode, event);
		
	}
	
	protected boolean CheckNetwork() {
		boolean flag=false;
		final String ACTION_SETTINGS = "android.settings.SETTINGS";
		ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if(mConnectivityManager.getActiveNetworkInfo() != null){
			flag = mConnectivityManager.getActiveNetworkInfo().isAvailable();
		}
	
		if(!flag){
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setTitle(R.string.networktitle);
			builder.setMessage(R.string.network_message);
			builder.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
			        // TODO Auto-generated method stub
		        	Intent mIntent=new Intent(ACTION_SETTINGS);
		        	//Intent mIntent=new Intent();
			        //ComponentName comp=new ComponentName("com.android.settings","com.android.settings.Settings");
		        	//ComponentName comp=new ComponentName("com.android.settings","com.android.settings.HWSettings");
			        //mIntent.setComponent(comp); 
			        //mIntent.setAction("android.intent.action.MAIN");//��Ҫ���ɲ�Ҫ��
			        startActivity(mIntent); 	
		        }
		   });
		   builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) { 
					// TODO Auto-generated method stub dialog.cancel(); 
				   dialog.cancel();
					} 
		   });
		   builder.create(); 
		   builder.show();
		 }
		 return flag; 
		}
	
	private void openBrowser(){
		Uri uri = Uri.parse("http://blog.csdn.net/u012898667");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	
	private void telePhone(){
		Uri uri = Uri.parse("tel:"+ num);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(intent);
	}
	
	public void onClick(View view){
		switch(view.getId()){
		case R.id.my_boke:
			if(!CheckNetwork()){
				return;
			}else{
				openBrowser();
			} 
			break;
		case R.id.telephone:
			telePhone();
			break;
		case R.id.work_experience:
			Intent experience = new Intent(this,Tabhost.class);
			startActivity(experience);
			break;
		case R.id.study_experience:
			Intent study = new Intent(this, StudyExperience.class);
			startActivity(study);
			break;
		case R.id.my_introducts:
			Intent introduct = new Intent(this, MyIntroduction.class);
			startActivity(introduct);
			break;
		case R.id.my_expects:
			Intent expects = new Intent(this, Expection.class);
			startActivity(expects);
			break;
		case R.id.imageswitcher_button:
    		Toast.makeText(this, "Switching", Toast.LENGTH_SHORT).show();
    		imageSwitcher.showNext();
    		break;
		case R.id.my_photoshow:
			boolean test = true;
			if(test){
				Toast.makeText(this, "KernelJun is developing the item", Toast.LENGTH_SHORT).show();
				return;
			}
			Intent photo = new Intent(this, MyPhotoShowGridView.class);
			startActivity(photo);
			break;
			
		}
	}
}
