package iquiplay.entidades;

import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

public class ControlDisparo{
	//=================
	public Imagen cargador;
	public Imagen boton;
	
	
	//============================
	boolean touche,solto,iniciarsonido;
	public boolean bloqueo=true;
	public float velocidad=4,r=0,v=1,limite,poder=0;
	Nivel minivel;
	Rectangle barra;
	Sprite scargador, sboton;
	public ControlDisparo(Nivel minivel)
	{
		this.minivel=minivel;
		
	}
	public void CrearControlDisparo(float x,float y){
		barra=new Rectangle(x+70, y+10, poder, 20);
		barra.setColor(r,v,0);
		scargador=new Sprite(x+60, y, minivel.mibase.imagenes.cargador.ImagenSimple);
		sboton=new Sprite(x-30, y-20, minivel.mibase.imagenes.boton.ImagenSimple){

			@Override
			public boolean onAreaTouched(TouchEvent e,
					float x, float y) {

				if(e.isActionDown())
				{
					
						
					iniciarsonido=true;
					touche=true;
					this.setScale(1.05f);
					minivel.mibase.camara.zoom=false;
					//minivel.mibase.camara.scroll=true;
				}					
				if(e.isActionUp())
				{
					if(touche)
					{
						minivel.mibase.sonidos.lanzadorgru.stop();
						touche=false;
						bloqueo=true;
						if(minivel.preparandovuelo)
						{
							solto=true;
							
						}
						this.setScale(1);
						
					}
					iniciarsonido=false;
					minivel.mibase.camara.zoom=true;
				}
					
				return true;
			}
		};
		limite=scargador.getWidth()-20;
	}

	public void alPintar()
	{

		
		minivel.mibase.hud.registerTouchArea(sboton);	
		minivel.mibase.hud.attachChild(sboton);
		minivel.mibase.hud.attachChild(barra);
		minivel.mibase.hud.attachChild(scargador);
	}
	public void aumentarpoder()
	{
		if(poder<limite)
		{
			r=poder/limite;
			v=1-r;
			poder+=velocidad;
			barra.setWidth(poder);
			barra.setColor(r,v,0);
		}
	}
	public void reiniciar()
	{
		poder=0;
		bloqueo=true;
		solto=false;
	}

	public void alCorrer()
	{
		if(touche&&!bloqueo)
		{
			if(iniciarsonido){
				minivel.mibase.sonidos.lanzadorgru.play();
				iniciarsonido=false;
			}
			aumentarpoder();
			if(minivel.lanzador.sLanzador.getCurrentTileIndex()>=6)
			{
				minivel.mibase.sonidos.lanzadorgru.stop();
			}
			
		}
	}

}
