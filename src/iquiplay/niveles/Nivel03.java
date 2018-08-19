package iquiplay.niveles;
import iquiplay.entidades.Bijao;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.entidades.Racimo;
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


public class Nivel03 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel03(Principal mibase) {
		super(mibase,2048,600,3,0,4,0,0,1,3,0,0,0,0,0,200,447,1);
		mibase.estadojuego=3;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 700, 120,Constantes.Verde,(short)0);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 950, 320,Constantes.Verde,(short)0);
		libelulasCharapa[2]=new LibelulaCharapa(this, 2, 1200, 120,Constantes.Verde,(short)0);
		libelulasCharapa[3]=new LibelulaCharapa(this, 3, 1500, 320,Constantes.Verde,(short)0);
		bijaos[0]=new Bijao(this,700, 435);
		bijaos[1]=new Bijao(this,900, 435);
		bijaos[2]=new Bijao(this,1100, 435);
		racimos[0]=new Racimo(this, 900, 60);
	}
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel3();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
