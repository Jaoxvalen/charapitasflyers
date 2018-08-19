package iquiplay.niveles;
import iquiplay.entidades.Bijao;
import iquiplay.entidades.Huama;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.entidades.PisoFlotante;
import iquiplay.entidades.Tronco;
import iquiplay.entidades.Victoria;
import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.EntidadParalaje;
import iquiplay.motor.FondoParalaje;
import iquiplay.motor.Principal;

import org.anddev.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;


public class Nivel05 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel05(Principal mibase) {
		super(mibase,2048,600,6,2,3,0,0,0,0,0,0,0,0,0,200,447,5);
		mibase.estadojuego=5;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1250, -140,Constantes.Verde,(short)0);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1000, 10,Constantes.Azul,(short)1);
		libelulasCharapa[2]=new LibelulaCharapa(this, 2, 900, 10,Constantes.Amarillo,(short)1);
		huamas[0]=new Huama(this, 1000, -100, Constantes.Azul);
		huamas[1]=new Huama(this, 1200, 300, Constantes.Amarillo);
	}
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel5();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
