package iquiplay.niveles;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.EntidadParalaje;
import iquiplay.motor.FondoParalaje;
import iquiplay.motor.Principal;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;


public class Nivel01 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;																																											
	public Nivel01(Principal mibase) {
		super(mibase,2048,600,3,0,2,0,0,0,0,0,0,0,0,0,200,430,1);
		mibase.estadojuego=1;
		huamaVerde=true;
		libelulasCharapa[0]=new LibelulaCharapa(this, 0, 1000, 50,Constantes.Verde,(short)0);
		libelulasCharapa[1]=new LibelulaCharapa(this, 1, 1000, 300,Constantes.Verde,(short)0);
		
	}
	
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel1();
		super.alPintar();
		mibase.camara.VistaInicial(1000,500,50);
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();		
	}
}
