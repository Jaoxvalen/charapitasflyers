package iquiplay.niveles;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.entidades.Racimo;
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


public class Nivel02 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																												
	public Nivel02(Principal mibase) {
		super(mibase,2048,600,2,0,3,0,0,1,0,0,0,0,0,0,200,447,1);
		mibase.estadojuego=2;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1000, 260,Constantes.Verde,(short)0);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1250, 260,Constantes.Verde,(short)0);
		libelulasCharapa[2]=new LibelulaCharapa(this, 2, 1500, 260,Constantes.Verde,(short)0);
		racimos[0]=new Racimo(this, 1260, 0);
	}
	public void alPintar() {
		mifondo.dibujarFondoNivel2();
		super.alPintar();	
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
