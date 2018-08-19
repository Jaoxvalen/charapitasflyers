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


public class Nivel07 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel07(Principal mibase) {
		super(mibase,2048,600,12,1,2,0,0,0,1,11,2,0,1,2,200,427,5);
		mibase.estadojuego=7;
		libelulaHuama[0]=new LibelulaHuama(this, 1256, -368, Constantes.Verde,(short) 0 , Constantes.Azul);
		libelulaHuama[1]=new LibelulaHuama(this, 738, -219, Constantes.Verde,(short) 0 , Constantes.Amarillo);
		//
		troncos[0]=new Tronco(this, 1150, 8, true);
		troncos[1]=new Tronco(this, 1196, 8, true);
		troncos[2]=new Tronco(this, 1336, 8, true);
		troncos[3]=new Tronco(this, 1382, 8, true);
		troncos[4]=new Tronco(this, 1170, -47, false);
		troncos[5]=new Tronco(this, 1356, -47, false);
		troncos[6]=new Tronco(this, 1198, 0, false);
		troncos[7]=new Tronco(this, 1326, -77, false);
		troncos[8]=new Tronco(this, 1260, -105, false);
		troncos[9]=new Tronco(this, 1178, -130, true);
		troncos[10]=new Tronco(this, 1345, -130, true);
		//
		flotante[0]=new PisoFlotante(this, 1148,100);
		flotante[1]=new PisoFlotante(this, 1386,100);
		//
		huamas[0]=new Huama(this, 1230, -90, Constantes.Verde);
		//
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1259, 170, Constantes.Amarillo, (short) 2);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1750, 335, Constantes.Azul, (short) 0);
		//
		libelulasbomba[0]=new LibelulaBomba(this, 1750, 210, Constantes.Verde, (short)3);
		//
		bijaos[0]=new Bijao(this,1750, 415);
	
	}

	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel7();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{

		super.alCorrer();		
	}
}
