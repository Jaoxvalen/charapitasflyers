package iquiplay.niveles;
import iquiplay.entidades.Bijao;
import iquiplay.entidades.Huama;
import iquiplay.entidades.Libelula;
import iquiplay.entidades.LibelulaBomba;
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


public class Nivel06 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel06(Principal mibase) {
		super(mibase,2048,600,5,0,2,0,0,0,3,10,1,0,2,0,200,427,5);
		mibase.estadojuego=6;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1000, -535,Constantes.Verde,(short)0);
		libelulasbomba[0]=new LibelulaBomba(this, 1000, -640, Constantes.Verde, (short)2);
		libelulasbomba[1]=new LibelulaBomba(this, 1480, -10, Constantes.Verde, (short)0);
		troncos[0]=new Tronco(this, 1570, 150,true);
		troncos[1]=new Tronco(this, 1570, 71,true);
		troncos[2]= new Tronco(this, 1570,-8,true);
		troncos[3]=new Tronco(this, 1570, -87,true);
		troncos[4]=new Tronco(this, 1570, -166,true);
		//
		troncos[5]=new Tronco(this, 1420, 150,true);
		troncos[6]=new Tronco(this, 1420, 71,true);
		troncos[7]= new Tronco(this, 1420,-8,true);
		troncos[8]=new Tronco(this, 1420, -87,true);
		troncos[9]=new Tronco(this, 1420, -166,true);
		//
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1690, -120,Constantes.Verde,(short)0);
		flotante[0]=new PisoFlotante(this, 1500, 260);

	}
	
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel6();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
