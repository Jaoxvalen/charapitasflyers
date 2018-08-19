package iquiplay.entidades;

import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.math.Vector2;

public class LibelulaCharapa extends Libelula {
	
	int charapa;
	Sprite Scharapa;
	
	public Imagen charapitaCapturada;
	public LibelulaCharapa(Nivel minivel,int indCha,float x, float y,short color,short movimiento) {
		super(minivel,x,y,color,movimiento);
		this.charapa=indCha;
	}
	public void soltarCharapa()
	{
		if(!soltado)
		{
			this.soltar();
			minivel.charapitasColgadas[charapa]=new Charapa(minivel,true);
			minivel.charapitasColgadas[charapa].CrearCharapafisica(new Vector2(this.cuerpo.getX(), this.cuerpo.getY()+50), new Vector2(), 0);
			if(minivel.charapitasColgadas[charapa].capturada)
			{
					minivel.sueltasAire++;
			}
		}
		
	}
	@Override
	public void alPintar()
	{
		super.alPintar();
		Scharapa=new Sprite(x, y,minivel.mibase.imagenes.charapitaCapturada.ImagenSimple);
		cargarCuerpo(Scharapa,0,40);
	}
	@Override
	public void Almorir() {
		morir();
		soltarCharapa();
	}
}
