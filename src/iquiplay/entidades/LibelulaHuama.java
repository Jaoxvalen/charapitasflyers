package iquiplay.entidades;

import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;

public class LibelulaHuama extends Libelula {
	short colorhuama;
	public LibelulaHuama(Nivel minivel, float x, float y,short color,short movimiento,short colorhuama) {
		super(minivel, x, y, color,movimiento);
		this.colorhuama=colorhuama;
	}

	@Override
	public void alPintar() {
		super.alPintar();
		if(colorhuama==Constantes.Verde)
		{
			this.bloqueado=false;
			cargarCuerpo(new Sprite(x, y, minivel.mibase.imagenes.huamaVerde.ImagenSimple),0,40);
		}
		else if(colorhuama==Constantes.Amarillo)
		{
			cargarCuerpo(new Sprite(x, y, minivel.mibase.imagenes.huamaAmarilla.ImagenSimple),0,40);
		}
			
		else if(colorhuama==Constantes.Azul)
		{
			cargarCuerpo(new Sprite(x, y, minivel.mibase.imagenes.huamaAzul.ImagenSimple),0,40);
		}
			
	}
	@Override
	public void Almorir() {
		soltar();
		morir();
		if(colorhuama==Constantes.Verde)
		{
			minivel.huamaVerde=true;
		}
		else if(colorhuama==Constantes.Amarillo)
		{
			minivel.huamaAmarilla=true;
		}
		else if(colorhuama==Constantes.Azul)
		{
			minivel.huamaAzul=true;
		}
		minivel.datosHud.Actualizarhuama();
		minivel.efectos.crearDestello(cuerpo.getX(), cuerpo.getY());
		minivel.mibase.sonidos.destello.play();
	}
}
