package iquiplay.niveles;
import java.util.ArrayList;

import iquiplay.entidades.BarraVida;
import iquiplay.entidades.Bijao;
import iquiplay.entidades.Gavilan;
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
import iquiplay.motor.Sonidos;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;


public class Nivel09 extends Nivel {
	Sprite piso1,fondo2,fondo3,fondo4,fondo5,nube1,nube2,nube3,piso2;
	Gavilan gavilan;

	public Nivel09(Principal mibase) {
		super(mibase,2048,600,30,0,1,0,3,0,8,0,0,5,10,0,200,447,20);
		mibase.estadojuego=9;
		gavilan=new Gavilan(this,1500,200);
		huamaVerde=true;
		huamaAmarilla=true;
		huamaAzul=true;
		bijaos[0]=new Bijao(this,500, 435);
	}
	@Override
	public void alPintar() {
		mifondo.dibujarFondoNivel9();
		super.alPintar();	
		gavilan.alPintar();
		mibase.camara.VistaInicial(1000,500,50);
		mibase.sonidos.ReiniciarSonidos();
		if(!Sonidos.silencio){
		mibase.sonidos.temas[4].play();
		mibase.sonidos.temas[4].setVolume(0.3f);
		}
		//temporizador del gavilan
	}
	@Override
	public void alCorrer()
	{
		super.alCorrer();
		gavilan.alCorrer();
	}
}
