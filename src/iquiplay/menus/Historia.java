package iquiplay.menus;

import iquiplay.motor.Imagen;
import iquiplay.motor.Principal;
import iquiplay.motor.Sonidos;
import iquiplay.niveles.Nivel01;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.util.MathUtils;

public class Historia {
	Imagen img;
	Principal mibase;
	BitmapTextureAtlas textura;
	Sprite sprite;
	float alpa=1;
	boolean toco,fin;
	int actual=1;
	float pivot=MathUtils.random(0, 3);
	public Historia(Principal mibase,boolean fin)
	{
		this.fin=fin;
		mibase.estadojuego=-100;
		this.mibase=mibase;
		mibase.camara.RestablecerCamara();				
		textura=new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		mibase.irAlnivel(new Scene());
		mibase.sonidos.ReiniciarSonidos();
		mibase.escena.setBackground(new ColorBackground(0,0,0));
		if(!fin)
		{
			if(!Sonidos.silencio){
			mibase.sonidos.charaflydub.stop();
			mibase.sonidos.musicamenu.stop();
			}
			automaticvineta(actual);
		}
		else
		{
			if(Sonidos.silencio){
			mibase.sonidos.temas[4].stop();
			mibase.sonidos.charaflydub.play();
			}
			
			actual=5;
			automaticvineta(actual);
		}
	}
	public void cargarVineta(String ruta,float x,float y)
	{
		toco=false;
		alpa=1;
		pivot=MathUtils.random(0, 3);
		if(sprite!=null)
		{
			sprite.detachSelf();
		}
		this.mibase.getEngine().getTextureManager().unloadTexture(textura);
		textura.clearTextureAtlasSources();
		img=new Imagen(mibase, ruta, textura);
		this.mibase.miengine.getTextureManager().loadTexture(textura);
		sprite=new Sprite(x,y,img.ImagenSimple);
		sprite.setScale(1.2f);
		mibase.escena.attachChild(sprite);
	}
	public void automaticvineta(int numero)
	{
		if(numero==1)
		{
			if(!Sonidos.silencio){
		    mibase.sonidos.intro.setVolume(0.3f);
			mibase.sonidos.intro.play();
			}
			cargarVineta("historia/1.png", 119, 150);
			
		}
		if(numero==2)
		{
			if(!Sonidos.silencio){
			mibase.sonidos.intro.stop();
			mibase.sonidos.temas[4].play();
			mibase.sonidos.temas[4].setVolume(0.3f);
			}
			cargarVineta("historia/2.png", 119, 250);
		}
		if(numero==3)
		{
			cargarVineta("historia/3.png", 119, 200);
		}
		if(numero==4)
		{
			cargarVineta("historia/4.png", 119, 10);
		}
		if(numero==5)
		{
			cargarVineta("historia/5.png", 101, 10);
		}
		if(numero==6)
		{
			cargarVineta("historia/6.png", 0, 10);
		}
		if(numero==7)
		{
			cargarVineta("historia/7.png", 0, 10);
		}
		actual++;
	}
	public void Altocar(Scene escena, TouchEvent e) {
		if(e.isActionDown())
		{
			toco=true;
		}
	}
	public void alCorrer()
	{
		if(sprite!=null)
		{
			if(alpa>0)
			{
				if(toco)
				{
					/*sprite.setAlpha(alpa);*/
					trancision(alpa);
					alpa-=0.05;
					
				}
			}
			else
			{
				if(!fin)//inicio del juego
				{
					if(actual<=4)
					{
						automaticvineta(actual);
					}
					else
					{
						if(!Sonidos.silencio){
						mibase.sonidos.temas[4].stop();
						}
						iralnivel();
					}
				}
				else//mostrar historia del fin
				{
					if(actual<=7)
					{
						automaticvineta(actual);
					}
					else
					{
						if(!Sonidos.silencio){
						mibase.sonidos.temas[4].stop();
						}
						creditos();
					}
				}

			}

		}
	}
	public void iralnivel()
	{
		mibase.minivel = new Nivel01(mibase);
		mibase.getTextureManager().unloadTexture(textura);
		mibase.irAlnivel(mibase.minivel.miescena);
	}
	public void creditos()
	{
		mibase.getTextureManager().unloadTexture(textura);
		mibase.menus.IraMenuCreditos();
	}
	////////////////////////efectos///////////////////////////////////
	public void trancision(float alpa)
	{
		if(pivot==0)
		{
			sprite.setScale(alpa);
		}
		else if(pivot==1)
		{
			sprite.setAlpha(alpa);
		}
		else if(pivot==2)
		{
			sprite.setPosition(sprite.getX()+alpa*10, sprite.getY());
			this.alpa+=1;
			if(sprite.getX()>820)
				this.alpa=-5;
		}
		else if(pivot==3)
		{
			sprite.setPosition(sprite.getX()-alpa*10, sprite.getY());
			this.alpa+=1;
			if(sprite.getX()<0-sprite.getWidth())
				this.alpa=-5;
		}
	}
}
