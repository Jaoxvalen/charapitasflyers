package iquiplay.entidades;

import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;

import android.util.Log;

public class BarraVida {
	AnimatedSprite barra;
	Nivel minivel;
	float x,y,total;
	public BarraVida(float x, float y,Nivel minivel,float total)
	{
		this.x=x;
		this.y=y;
		this.minivel=minivel;
		this.total=total;
	}
	public void Alpintar()
	{
		barra=new AnimatedSprite(x,y,minivel.mibase.imagenes.barravida.ImagenAnimada);
		AnimatedSprite cabeza=new AnimatedSprite(x-80,y-25,minivel.mibase.imagenes.gavicabeza.ImagenAnimada.deepCopy());
		cabeza.setScale(0.5f);
		cabeza.setCurrentTileIndex(0);
		minivel.mibase.hud.attachChild(cabeza);
		minivel.mibase.hud.attachChild(barra);
		barra.setCurrentTileIndex(0);
	}
	public void Actualizarvida(float vida)
	{
		if(vida>0)
		{
			barra.setCurrentTileIndex(5-Math.round((5/(total/vida))));
		}
		else
		{
			barra.setCurrentTileIndex(5);
		}
		
	}
}
