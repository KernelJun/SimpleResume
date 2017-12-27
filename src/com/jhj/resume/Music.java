package com.jhj.resume;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class Music {

	private static MediaPlayer mp;
	 
	// Stop old song and start new one
	public static void play(Context context, int resource){
		stop(context);
		if (Pres.getMusic(context)){
		mp = MediaPlayer.create(context, resource);
		mp.setLooping(true);
		mp.start();
		}
	}
	
	//stop the music
	public static void stop(Context context){
		if (mp!=null){
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}
