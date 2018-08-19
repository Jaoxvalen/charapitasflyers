package iquiplay.entidades;

import iquiplay.menus.Historia;
import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.FloatMath;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class Gavilan extends ColisionEnte {
	float x, y;
	Nivel minivel;
	AnimatedSprite cabeza,alapos,alasup;
	Sprite cuerpo,cola;
	int estadomov;
	public float vida=100;
	public Imagen gavicuerpo;
	public Imagen gavicola;
	public Imagen gavialapos;
	public Imagen gavialasup;
	public Imagen gavicabeza;
	public Body bcuerpo;
	public int tiempogavilan,tiempoini=30;
	float directriz,pivote,velocidad=50;
	public int numeroEnemigosactual=0,numerobombas;
	Vector2 velocidadve=new Vector2(),p;
	boolean cambio;
	public int enescena;
	private TimerHandler temporizadorgavilan;
	BarraVida vidagavilan;
	public Gavilan(Nivel minivel,float x, float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
		vidagavilan=new BarraVida(500, 600, this.minivel, vida);
	}
	public void alPintar()
	{
		minivel.mibase.sonidos.aleteo.sonido.setLooping(true);
		minivel.mibase.sonidos.aleteo.play();
		tiempogavilan=tiempoini;
		cuerpo=new Sprite(x, y,94,86 ,minivel.mibase.imagenes.gavicuerpo.ImagenSimple);
		cola=new Sprite(x+50, y+45,minivel.mibase.imagenes.gavicola.ImagenSimple);
		alapos=new AnimatedSprite(x-34, y-62,minivel.mibase.imagenes.gavialapos.ImagenAnimada);
		alasup=new AnimatedSprite(x-15, y-61 ,minivel.mibase.imagenes.gavialasup.ImagenAnimada);
		cabeza=new AnimatedSprite(x-40, y-25 ,minivel.mibase.imagenes.gavicabeza.ImagenAnimada);
		alapos.animate(100);
		alasup.animate(100);
		cabeza.setCurrentTileIndex(1);
		minivel.mibase.escena.attachChild(alapos);
		minivel.mibase.escena.attachChild(cola);
		minivel.mibase.escena.attachChild(cuerpo);
		minivel.mibase.escena.attachChild(cabeza);
		minivel.mibase.escena.attachChild(alasup);
		cargarFisica();
		temporizadorgavilan=new TimerHandler(1f, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler arg0) {
				tiempogavilan--;
				if(tiempogavilan>0)
				{
					temporizadorgavilan.reset();
				}
				if(tiempogavilan==0)
				{
					if(enescena!=4)
					enescena=1;
				}
			}
		});
		minivel.miescena.registerUpdateHandler(temporizadorgavilan);
		vidagavilan.Alpintar();
	}
	public void cargarFisica()
	{
		bcuerpo=minivel.mibase.FisicaAtlas.crearCuerpoCircular(cuerpo, 0.1f, 0.3f,0.1f,BodyType.DynamicBody,this);
		bcuerpo.setTransform(cuerpo.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, cuerpo.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		PhysicsConnector conexion=new PhysicsConnector(cuerpo,bcuerpo, true, false);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(conexion);
		conexion=null;
		velocidadve.x=MathUtils.random(-50, 50); 
		velocidadve.y=MathUtils.random(-50, 50);
	}
	public void PosicionarCuerpo()
	{
		cola.setPosition(cuerpo.getX()+50,cuerpo.getY()+45);
		alapos.setPosition(cuerpo.getX()-34,cuerpo.getY()-62);
		alasup.setPosition(cuerpo.getX()-15,cuerpo.getY()-61);
		cabeza.setPosition(cuerpo.getX()-40,cuerpo.getY()-25);
	}
	public void alCorrer()
	{
		Log.v("vida", vida+"");
		PosicionarCuerpo();
		desplazar();
	}
	public void desplazar()
	{
		//antigravedad
		final Vector2 vec = new Vector2(0, -minivel.mibase.FisicaAtlas.getGravity().y*bcuerpo.getMass());
		bcuerpo.applyForce(vec, bcuerpo.getWorldCenter());
		if(enescena==0)
		{
			if(estadomov==0)
			{
				if(cambio)
				{
					pivote-=0.1f;
				}
				else
				{
					pivote+=0.1f;
				}
				if(pivote>2)
				{
					cambio=true;
				}
				if(pivote<-2)
				{
					cambio=false;
				}
				bcuerpo.setLinearVelocity(0, pivote);
				if(MathUtils.random(0, 100)==0)
				{
					estadomov=MathUtils.random(0, 2);
				}
			}
			if(estadomov==1)
			{
				bcuerpo.setLinearVelocity(velocidadve.x,velocidadve.y);
				if(MathUtils.random(0,20)==0)
				{
					velocidadve.x=MathUtils.random(-10, 10); 
					velocidadve.y=MathUtils.random(-10, 10);
					estadomov=MathUtils.random(0, 2);
				}
			}
			if(estadomov==2)
			{
				float escala=10, tick=5;
				pivote+=MathUtils.degToRad(tick);
				if(pivote>=MathUtils.degToRad(360-tick))
				{
					estadomov=MathUtils.random(0, 2);
					pivote=0;
				}
				bcuerpo.setLinearVelocity(escala*FloatMath.cos(pivote),escala*FloatMath.sin(pivote));
			}
			
			//limitamos el area de vuelo
			Vector2 vector;
			if(bcuerpo.getPosition().x>1800/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT)
			{
				vector=new Vector2(1800/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,bcuerpo.getPosition().y);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().x<500/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT)
			{
				vector=new Vector2(500/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,bcuerpo.getPosition().y);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().y>400/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT)
			{
				vector=new Vector2(bcuerpo.getPosition().x,400/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().y<-600/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT)
			{
				vector=new Vector2(bcuerpo.getPosition().x,-600/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
		}
		if(enescena==1)// se da a la fuga x un rato
		{
			bcuerpo.setLinearVelocity(0,-15);
			if(cuerpo.getY()<-1200)
			{
				bcuerpo.setAwake(false);
				while(numeroEnemigosactual<1)//1 esla cantidad de libelulas que se crearan
				{
					if(numeroEnemigosactual==0)
					{
						if(numerobombas<10)//10 es la maxima cantidad de bombas que se crearan
						{
							LibelulaBomba bomba=new LibelulaBomba(minivel,MathUtils.random(600, 1800),(short) MathUtils.random(-500, 200), (short)MathUtils.random(0, 2),(short) MathUtils.random(0, 5));
							minivel.libelulasbomba[numerobombas]=bomba;
							minivel.libelulasbomba[numerobombas].alPintar();
							minivel.efectos.crearpolvo(bomba.cuerpo.getX(), bomba.cuerpo.getY());
							numerobombas++;
						}
					}
					generarChinchilejo();
				}
				enescena=2;
			}
		}
		if(enescena==2)
		{	
			numeroEnemigosactual=1;
			//generar los enemigos chinchilejos
			for(int i=0;i<minivel.libelulas.length;i++)
			{
				if(minivel.libelulas[i]!=null)
				{
					if(minivel.libelulas[i].muerto)
					{
						numeroEnemigosactual--;
					}
				}
			}
			if(numeroEnemigosactual==0)
			{
				enescena=3;
			}

		}
		if(enescena==3)
		{
			bcuerpo.setLinearVelocity(0,15);
			if(cuerpo.getY()>350)
			{
				enescena=0;
				numeroEnemigosactual=0;
				tiempogavilan=tiempoini;
				temporizadorgavilan.reset();
			}
		}
		if(enescena==4)
		{
			/*cabeza.setCurrentTileIndex(0);
			cabeza.stopAnimation();
			alapos.stopAnimation();
			alasup.stopAnimation();
			bcuerpo.setLinearVelocity(0, 20);
			if(cuerpo.getY()>400)
			{*/
				//
			if((minivel.ncharaini-minivel.lanzador.nlanzamiento)>0){
				minivel.datosHud.AddScore(2000*(minivel.ncharaini-minivel.lanzador.nlanzamiento));
			
			}
			if(minivel.tiempoparawin==70){
				minivel.datosHud.AddScore(200*(int)minivel.tiempo);
				
			}
			minivel.mibase.miBDcharapita.ActualizarPuntosNivel(minivel.mibase.estadojuego,minivel.datosHud.scoreactual);
			minivel.mibase.historia=new Historia(minivel.mibase,true);
			//}
		}
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte e,Body mipunto) {
		if(e!=null)
		{
			if(e.getIdcolision()==Constantes.charapavolando)
			{
				cabeza.animate(500,false);
				if(mipunto!=null)
				{
					minivel.mibase.sonidos.golpegavilan.play();
					vida-=mipunto.getLinearVelocity().len()/2;
					vidagavilan.Actualizarvida(vida);
				}
				if(vida<=0)
				{
					enescena=4;
					minivel.mibase.sonidos.aleteo.stop();
				}
			}
		}
	}
	@Override
	public int getIdcolision() {
		return Constantes.gavilan;
	}
	public void generarChinchilejo()
	{
		Libelula libe=new Libelula(minivel,MathUtils.random(600, 1800),(short) MathUtils.random(-200, 200), (short)MathUtils.random(0, 2),(short) MathUtils.random(0, 5));
		minivel.libelulas[numeroEnemigosactual]=libe;
		libe.alPintar();
		minivel.efectos.crearpolvo(libe.cuerpo.getX(), libe.cuerpo.getY());
		numeroEnemigosactual++;
	}
}
