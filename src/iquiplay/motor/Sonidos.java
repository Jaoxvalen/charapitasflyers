package iquiplay.motor;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundLibrary;

import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.view.SoundEffectConstants;



public class Sonidos{

	public  Sound sonido;
	public static boolean silencio=false;
	public static float volumenpordefecto=0;
	public boolean pausado=false;
	public boolean playing=false;
	
	public Sonidos(Sound fabricasonido){
		sonido=fabricasonido;
		volumenpordefecto=sonido.getVolume();
		//OnLoadCompleteListener
			
		
	}
	
	public void play(){
		if(sonido!=null&&!silencio){
			sonido.play();
			playing=true;
		}
	}
	public void stop(){
		if(sonido!=null&&!silencio){
			sonido.stop();
			playing=false;
			pausado=false;
		}
	}
	public void pause(){
		if(sonido!=null&&!silencio){
			sonido.pause();
			pausado=true;
			playing=false;
		}
	}
	public void resume(){
		if(sonido!=null){
			sonido.resume();
			pausado=false;
			playing=true;
		}
	}
	public void silenciar(float volumen){
		if(sonido!=null){
			sonido.setVolume(volumen);
		}
	}
}
