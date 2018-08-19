package iquiplay.niveles;
import iquiplay.entidades.Bijao;
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


public class Nivel04 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel04(Principal mibase) {
		super(mibase,2048,600,3,0,3,0,0,0,2,9,1,0,0,0,200,447,4);
		mibase.estadojuego=4;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1250, -140,Constantes.Verde,(short)0);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1250, 10,Constantes.Verde,(short)0);
		libelulasCharapa[2]=new LibelulaCharapa(this, 2, 1250, 160,Constantes.Verde,(short)0);
		bijaos[0]=new Bijao(this,900, 435);
		bijaos[1]=new Bijao(this,1100, 435);
		troncos[0]=new Tronco(this, 1100, 150,true);
		troncos[1]=new Tronco(this, 1150, 150,true);
		troncos[2]= new Tronco(this, 1125,120,false);
		troncos[3]=new Tronco(this, 1100, 40,true);
		troncos[4]=new Tronco(this, 1150, 40,true);
		troncos[5]= new Tronco(this, 1125,10,false);
		troncos[6]=new Tronco(this, 1100, -70,true);
		troncos[7]=new Tronco(this, 1150, -70,true);
		troncos[8]= new Tronco(this, 1125,-100,false);
		flotante[0]=new PisoFlotante(this, 1100, 260);
		//reloj=new Temporizador(3, this);
	}
	
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel4();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
