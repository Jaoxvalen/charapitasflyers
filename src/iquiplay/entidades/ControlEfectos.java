package iquiplay.entidades;

import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;

public class ControlEfectos {

	
	public Imagen polvo;
	public Imagen explotaLibelula;
	public Imagen destello;
	
	Nivel minivel;
	public ControlEfectos(Nivel nivel){
		this.minivel=nivel;
		
	}
	
	public void crearpolvo(float x, float y){
		AnimatedSprite temps=new AnimatedSprite(x,y,minivel.mibase.imagenes.polvo.ImagenAnimada);	   	
	   temps.animate(100,false,new IAnimationListener() {	
		@Override
		public void onAnimationEnd(AnimatedSprite e) {
			minivel.mibase.basurero.add(e);
		}
	});
	   temps.setCurrentTileIndex(0);
	   minivel.mibase.escena.attachChild(temps);  
	   temps=null;
	}
	public void crearExplotalibelula(float x, float y){
		AnimatedSprite temps=new AnimatedSprite(x,y,minivel.mibase.imagenes.explotaLibelula.ImagenAnimada);	   	
	   temps.animate(100,false,new IAnimationListener() {	
		@Override
		public void onAnimationEnd(AnimatedSprite e) {
			minivel.mibase.basurero.add(e);
		}
	});
	   temps.setCurrentTileIndex(0);
	   minivel.mibase.escena.attachChild(temps);  
	   temps=null;
	}
	public void crearDestello(float x, float y){
		AnimatedSprite temps=new AnimatedSprite(x,y,minivel.mibase.imagenes.destello.ImagenAnimada);	   	
	   temps.animate(100,false,new IAnimationListener() {	
		@Override
		public void onAnimationEnd(AnimatedSprite e) {
			minivel.mibase.basurero.add(e);
		}
	});
	   temps.setCurrentTileIndex(0);
	   minivel.mibase.escena.attachChild(temps);  
	   temps=null;
	}
	
}
