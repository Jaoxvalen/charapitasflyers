package iquiplay.motor;






import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.ui.activity.BaseSplashActivity;

import android.app.Activity;


public class Portada extends BaseSplashActivity  {
	
private static int veces=0;
	
	private static final int SPLASH_DURATION = 1;
	public static  float SPLASH_SCALE_FROM = 1.0f;
	
	AssetBitmapTextureAtlasSource temp;
	 
	@Override
	protected Class<? extends Activity> getFollowUpActivity() {
		if(veces<2){
			veces++;
			return iquiplay.motor.Portada.class;
			
		}
		else
		{
			veces=0;
			return Inicio.class;
		}
		
	}

	@Override
	protected ScreenOrientation getScreenOrientation() {
		// TODO Auto-generated method stub
		return ScreenOrientation.LANDSCAPE;
	}

	@Override
	protected float getSplashDuration() {
		// TODO Auto-generated method stub
		return SPLASH_DURATION;
	}

	@Override
	protected IBitmapTextureAtlasSource onGetSplashTextureAtlasSource() {
		// TODO Auto-generated method stub
				
		
		if(veces==0)
		{
		 temp= new AssetBitmapTextureAtlasSource(this, "gfx/menu/splash1.png");
		}
		if(veces==1)
		{
			SPLASH_SCALE_FROM=1.5f;
			 temp= new AssetBitmapTextureAtlasSource(this, "gfx/menu/splash2.png");	
		}
		if(veces==2)
		{
			SPLASH_SCALE_FROM=1.0f;
			 temp= new AssetBitmapTextureAtlasSource(this, "gfx/menu/splash3.png");	
		}
		
		return 	temp;

		
	}
	 
	@Override
	protected float getSplashScaleFrom() {
		return SPLASH_SCALE_FROM;
		}
}
