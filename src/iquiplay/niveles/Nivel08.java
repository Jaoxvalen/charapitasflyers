package iquiplay.niveles;
import iquiplay.entidades.Bijao;
import iquiplay.entidades.Huama;
import iquiplay.entidades.Libelula;
import iquiplay.entidades.LibelulaBomba;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.entidades.LibelulaHuama;
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

import android.util.Log;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;


public class Nivel08 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel08(Principal mibase) {
		super(mibase,2048,600,5,5,2,0,0,0,0,0,0,4,2,0,200,427,5);
		mibase.estadojuego=8;
		huamaVerde=true;
		libelulas[0]=new Libelula(this, 800, 160, Constantes.Amarillo,(short)4);
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 950, -40, Constantes.Verde, (short)0);
		libelulas[1]=new Libelula(this, 1100, -240, Constantes.Amarillo,(short)5);
		//
		libelulasbomba[0]=new LibelulaBomba(this, 1600, 160, Constantes.Amarillo,(short)4);
		libelulasCharapa[1]=new LibelulaCharapa(this, 0, 1750, -40, Constantes.Verde, (short)0);
		libelulasbomba[1]=new LibelulaBomba(this, 1900, -240, Constantes.Amarillo,(short)5);
		//
		huamas[0]=new Huama(this, 1100, -440, Constantes.Verde);
		huamas[1]=new Huama(this, 1200, -450, Constantes.Verde);
		huamas[2]=new Huama(this, 1300, -460, Constantes.Verde);
		huamas[3]=new Huama(this, 1400, -450, Constantes.Verde);
		huamas[4]=new Huama(this, 1500, -440, Constantes.Verde);
	}
	
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel8();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{

		super.alCorrer();		
	}
}
