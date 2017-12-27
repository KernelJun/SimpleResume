package com.jhj.resume;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class BootLauncher extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		android.util.Log.d("jhj","jhj00");
		if (Pres.getBootLauncher(context)) {
			if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
				android.util.Log.d("jhj","jhj01");
				Intent iboot = new Intent();
				ComponentName comp = new ComponentName(context.getPackageName(), Resume.class.getName());
				iboot.setComponent(comp).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(iboot);
			} else {
				android.util.Log.d("jhj","Resume,Boot launcher was failed");
			}
		}

	}

}
