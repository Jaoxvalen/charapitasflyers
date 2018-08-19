package iquiplay.motor;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.CameraScene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

public class BucleCandoRe implements IUpdateHandler {

	public Principal mibase;
	
public  BucleCandoRe(Principal mibase)
{
	this.mibase=mibase;

}

@Override
public void onUpdate(float arg0) {
	
	if(mibase.TextureEscenaCargando.isLoadedToHardware()){
        mibase.escena.unregisterUpdateHandler(this);
        createLoadScreen();
        loadTextures(false);
     // boolean true if resuming - more on this later
	}
	
}

@Override
public void reset() {
	// TODO Auto-generated method stub
	
}

//Creamos la escena de loading screen
public void createLoadScreen(){
    mibase.escenacargando= new CameraScene(mibase.camara.mCamera);
    mibase.escenacargando.setBackgroundEnabled(false);
	
    
    mibase.TextureRegionImagen = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mibase.TextureEscenaCargando, mibase, "cargando/cargando.png", 0, 0);

	Sprite sImagen = new Sprite(Constantes.camara_ancho/2- mibase.TextureRegionImagen.getWidth()/2, Constantes.camara_alto/2- mibase.TextureRegionImagen.getHeight()/2,  mibase.TextureRegionImagen);
	
	mibase.escenacargando.attachChild(sImagen);
   
    mibase.escena.setChildScene(mibase.escenacargando, false, true, true);
    
}

//Cargamos de uno en uno nuesstros recursos.
public void loadTextures(final boolean pResume){
    mibase.TextureCount = 0;
    mibase.escenacargando.registerUpdateHandler(new IUpdateHandler() {
        @Override
        public void onUpdate(float pSecondsElapsed) {
            if(mibase.TextureCount < mibase.mAllTextures.length){	      	
                if(mibase.mAllTextures[mibase.TextureCount].isLoadedToHardware()){
                    if(mibase.TextureCount == mibase.mAllTextures.length - 1){
                    	mibase.escenacargando.unregisterUpdateHandler(this);
                    	 mibase.miengine.getFontManager().reloadFonts();
                        if(pResume){
                            mibase.escena.back();
                          
                        } else {
                           if(mibase.estadojuego==0)
                           {
                            mibase.escena.clearChildScene();
                            mibase.menus.MenuActivarSonido();
                           }
                           if(mibase.estadojuego>0)
                           {
                        	   mibase.escena.setBackground(new ColorBackground(0.4f,0.56f,0.698f));
                        	   mibase.escena.clearChildScene();
                           }
                           
                           // set scene to main menu or however your project is setup
                        }
                    } else {
                    	 mibase.TextureCount++;
                       
                       
          
                     }
                }
            }
        }
        @Override
        public void reset() {        	
        	
        }
    });
    
    
}

}
