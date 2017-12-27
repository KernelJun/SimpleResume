package com.jhj.resume;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class Pres extends PreferenceActivity  {

	private static final String OPT_MUSIC="music";
	private static final boolean OPT_MUSIC_DEF=true;
	
	private static final String OPT_BOOT = "bootlauncher";
	private static final boolean OPT_BOOT_DEF = false;
	
	private static final String PERFERENCE_KEY_Myyoudao = "Myyoudao";
	private static final String PERFERENCE_KEY_Screen = "pres_setting";
	private PackageInfo packageinfo;
			
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		
		inityoudao();
		
	}
	
	
	private void inityoudao()  {
		
		Preference myyoudao =(Preference) findPreference(PERFERENCE_KEY_Myyoudao);
		
		try {
			packageinfo = this.getPackageManager().getPackageInfo("com.youdao.note", 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			packageinfo = null;
			e.printStackTrace();
		}
		
		myyoudao.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub
				if(packageinfo == null){
					//((PreferenceScreen) findPreference(PERFERENCE_KEY_Screen)).removePreference(myyoudao);
					Intent nullPackageinfo = new Intent(Pres.this, Nullyoudao.class);
					//nullPackageinfo.getClass();
					startActivity(nullPackageinfo);
					return true;
				}else{
					Intent diao_youdao = new Intent();
					diao_youdao.setClassName("com.youdao.note", "com.youdao.note.activity2.HomeActivity");
					startActivity(diao_youdao);
					return true;
				}
			}
			
		});
		
		
	}
	
	public static boolean getMusic(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_MUSIC,OPT_MUSIC_DEF);
	}
	
	public static boolean getBootLauncher(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_BOOT, OPT_BOOT_DEF);
	}

	
	
}