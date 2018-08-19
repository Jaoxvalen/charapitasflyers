package iquiplay.entidades;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;

public class Huama{
	float x,y;
	Nivel minivel;
	Sprite huamas;
	float scala=0.1f;
	short color;
	
	public Imagen huamaVerde;
	public Imagen huamaAzul;
	public Imagen huamaAmarilla;
	
	public Huama(Nivel minivel,float x,float y,short color)
	{
	
		
		this.minivel=minivel;
		this.x=x;
		this.y=y;
		this.color=color;
		
		
		
	}
	public void alPintar()
	{
		if(color==Constantes.Verde)
		{
			huamas=new Sprite(x,y,minivel.mibase.imagenes. huamaVerde.ImagenSimple);
		}
		if(color==Constantes.Amarillo)
		{
			huamas=new Sprite(x,y, minivel.mibase.imagenes.huamaAmarilla.ImagenSimple);
			huamas.setVisible(false);
		}
		if(color==Constantes.Azul)
		{
			huamas=new Sprite(x,y, minivel.mibase.imagenes.huamaAzul.ImagenSimple);
			huamas.setVisible(false);
		}
		
		minivel.mibase.escena.attachChild(huamas);
	}
	public void alCorrer()
	{
		
		if(huamas!=null)
		{
			if(huamas.isVisible())
			{
				animacion();
			}
			if(minivel.huamaVerde&&!huamas.isVisible())
			{
				huamas.setVisible(true);
			}
		}
		
	}
	public void recoger()
	{
		if(huamas!=null)
		{
			minivel.efectos.crearDestello(huamas.getX(), huamas.getY());
			minivel.mibase.sonidos.destello.play();
			if(color==Constantes.Verde)
			{
				minivel.datosHud.AddScore(huamas.getX(), huamas.getY(), 500);
				minivel.huamaVerde=true;
				minivel.datosHud.Actualizarhuama();
				//no se ke rayos va ha hacer esta huama cuando le recogen
			}
			else if(color==Constantes.Amarillo)
			{
				minivel.datosHud.AddScore(huamas.getX(), huamas.getY(), 1000);
				minivel.huamaAmarilla=true;
				minivel.datosHud.Actualizarhuama();
			}
			else if(color==Constantes.Azul)
			{
				minivel.datosHud.AddScore(huamas.getX(), huamas.getY(), 1500);
				minivel.huamaAzul=true;
				minivel.datosHud.Actualizarhuama();
			}
			huamas.detachSelf();
		}
		
	}
	public void animacion()
	{
		if(huamas.getScaleX()>1.1)
		{
			scala=-0.02f;
		}
		if(huamas.getScaleX()<0.9)
		{
			scala=0.02f;
		}
		huamas.setScale(huamas.getScaleX()+scala);
	}
}
