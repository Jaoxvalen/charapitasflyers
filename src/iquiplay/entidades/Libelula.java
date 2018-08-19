package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
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

public class Libelula extends ColisionEnte{
	
	float x,y,directriz,pivote,velocidad=50,limiteVuelox=10,limiteVueloy=10,poscargax,poscargay;
	public boolean cambio,soltado,muerto;
	Vector2 velocidadve=new Vector2(),p;
	Nivel minivel;
	Sprite cola,cargado;
	public AnimatedSprite alapos,alasup,cargadoA,cuerpo;
	Body bcuerpo;
	short color=0,resistencia,resistenciainical,movimiento;
	boolean bloqueado=true;
	public Libelula(Nivel minivel,float x, float y,short color,short movimiento)
	{
		this.movimiento=movimiento;
		this.color=color;
		this.x=x;
		this.y=y;
		this.minivel=minivel;
	}
	public void alPintar()
	{
		if(color==Constantes.Verde){
			crearlibelula(x, y, minivel.mibase.imagenes.libelulaverde);
			resistencia=Constantes.resistencialibVerde;
			resistenciainical=resistencia;
		}
		if(color==Constantes.Azul)
		{
			crearlibelula(x, y, minivel.mibase.imagenes.libelulaazul);
			resistencia=Constantes.resistencialibAzul;
			resistenciainical=resistencia;
		}
		if(color==Constantes.Amarillo)
		{
			crearlibelula(x, y, minivel.mibase.imagenes.libelulaamarilla);
			resistencia=Constantes.resistencialibAmarilla;
			resistenciainical=resistencia;
		}

	}
	public void crearlibelula(float x,float y,Imagen[] colorlibelula){
		this.x=x;
		this.y=y;

		cuerpo=new AnimatedSprite(x, y, colorlibelula[0].ImagenAnimada.deepCopy());
		cola=new Sprite(x+46, y-2, colorlibelula[1].ImagenSimple);
		alapos=new AnimatedSprite(x-16, y-67,minivel.mibase.imagenes.libelulaalapos.ImagenAnimada);
		alasup=new AnimatedSprite(x+30, y-70, minivel.mibase.imagenes.libelulaalasup.ImagenAnimada);
		alapos.animate(70);
		alasup.animate(70);
		minivel.mibase.escena.attachChild(alapos);
		minivel.mibase.escena.attachChild(cola);
		minivel.mibase.escena.attachChild(cuerpo);
		minivel.mibase.escena.attachChild(alasup);
		cargarFisica();
	}
	public void cargarFisica()
	{
		bcuerpo=minivel.mibase.FisicaAtlas.crearCuerpoCircular(25, 0f, .5f,0.1f,BodyType.DynamicBody,this);
		PhysicsConnector conexion=new PhysicsConnector(cuerpo,bcuerpo, true, false);
		bcuerpo.setTransform(cuerpo.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, cuerpo.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(conexion);
		conexion=null;
	}
	
	
	public void alCorrer()
	{
		if(!muerto)
		{
			//los esprites de adorno
			cola.setPosition(cuerpo.getX()+46, cuerpo.getY()-2);
			alapos.setPosition(cuerpo.getX()-16, cuerpo.getY()-67);
			alasup.setPosition(cuerpo.getX()+30, cuerpo.getY()-70);
			//para ke no gire la cabeza
			bcuerpo.setTransform(bcuerpo.getPosition(), 0);
			//algoritmo de desplazamiento
			desplazamiento();
			if(cargado!=null)
				cargado.setPosition(cuerpo.getX()+poscargax,cuerpo.getY()+poscargay);
			if(cargadoA!=null)
				cargadoA.setPosition(cuerpo.getX()+poscargax,cuerpo.getY()+poscargay);
		}
		
	}
	public void desplazamiento()
	{
		if(movimiento==5)
		{
			if(cambio)
			{
				pivote-=0.3f;
			}
			else
			{
				pivote+=0.3f;
			}
			if(pivote>12)
			{
				cambio=true;
			}
			if(pivote<-12)
			{
				cambio=false;
			}

			bcuerpo.setLinearVelocity(0, pivote);
			//antigravedad
			final Vector2 vec = new Vector2(0, -minivel.mibase.FisicaAtlas.getGravity().y*bcuerpo.getMass());
			bcuerpo.applyForce(vec, bcuerpo.getWorldCenter());
			
		}
		if(movimiento==4)
		{
			if(cambio)
			{
				pivote+=0.3f;
			}
			else
			{
				pivote-=0.3f;
			}
			if(pivote>12)
			{
				cambio=false;
			}
			if(pivote<-12)
			{
				cambio=true;
			}

			bcuerpo.setLinearVelocity(0, pivote);
			//antigravedad
			final Vector2 vec = new Vector2(0, -minivel.mibase.FisicaAtlas.getGravity().y*bcuerpo.getMass());
			bcuerpo.applyForce(vec, bcuerpo.getWorldCenter());
			
		}
		if(movimiento==3)
		{
			float escala=10, tick=5;
			pivote-=MathUtils.degToRad(tick);
			if(pivote>=MathUtils.degToRad(360-tick))
			{
				pivote=0;
			}
			//champasoo
			final Vector2 vec = new Vector2(0, -minivel.mibase.FisicaAtlas.getGravity().y*bcuerpo.getMass());
			bcuerpo.applyForce(vec, bcuerpo.getWorldCenter());
			bcuerpo.setLinearVelocity(escala*FloatMath.cos(pivote),escala*FloatMath.sin(pivote));
		}
		if(movimiento==2)
		{
			float escala=10, tick=5;
			pivote+=MathUtils.degToRad(tick);
			if(pivote>=MathUtils.degToRad(360-tick))
			{
				pivote=0;
			}
			//champasoo
			final Vector2 vec = new Vector2(0, -minivel.mibase.FisicaAtlas.getGravity().y*bcuerpo.getMass());
			bcuerpo.applyForce(vec, bcuerpo.getWorldCenter());
			bcuerpo.setLinearVelocity(escala*FloatMath.cos(pivote),escala*FloatMath.sin(pivote));
		}
		if(movimiento==1)
		{
			velocidadve.x*=.97;
			velocidadve.y*=.97;
			bcuerpo.setLinearVelocity(velocidadve);
			pivote=MathUtils.random(0, 20);
			if(!cambio&&MathUtils.random(0, 100)==0)
			{
				cambio=true;
			}
			if(pivote==0&&cambio)
			{
				directriz=MathUtils.degToRad(MathUtils.random(0, 360));
				velocidad=MathUtils.random(10, 20);
				bcuerpo.setAwake(true);
				velocidadve.x=(float)(velocidad*Math.cos(directriz));
				velocidadve.y=(float)(velocidad*Math.sin(directriz));
			}
			if(pivote==20)
			{
				bcuerpo.setAwake(false);
				velocidadve.x=0;
				velocidadve.y=0;
				cambio=false;
			}
			Vector2 vector;
			//limitamos el area de vuelo
			if(bcuerpo.getPosition().x>x/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+limiteVuelox)
			{
				vector=new Vector2(x/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+limiteVuelox,bcuerpo.getPosition().y);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().x<x/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-limiteVuelox)
			{
				vector=new Vector2(x/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-limiteVuelox,bcuerpo.getPosition().y);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().y>y/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+limiteVueloy)
			{
				vector=new Vector2(bcuerpo.getPosition().x,y/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+limiteVueloy);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
			if(bcuerpo.getPosition().y<y/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-limiteVueloy)
			{
				vector=new Vector2(bcuerpo.getPosition().x,y/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-limiteVueloy);
				bcuerpo.setTransform(vector, 0);
				vector=null;
			}
		}
		if(movimiento==0)
		{
			bcuerpo.setAwake(false);	
		}
		
	}
	public void cargarCuerpo(AnimatedSprite Spritec,float x, float y)
	{
		this.cargadoA=Spritec;
		minivel.mibase.escena.attachChild(cargadoA);
		poscargax=x;
		poscargay=y;
	}
	public void cargarCuerpo(Sprite Spritec,float x,float y)
	{
		this.cargado=Spritec;
		minivel.mibase.escena.attachChild(cargado);
		poscargax=x;
		poscargay=y;
	}
	public void soltar()
	{
		soltado=true;
		if(cargado!=null)
		{
			minivel.mibase.basurero.add(cargado);
			cargado=null;
		}	
		if(cargadoA!=null)
		{
			minivel.mibase.basurero.add(cargadoA);
			cargadoA=null;
		}
			
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte e,Body mipunto) {
		if(!muerto)
		{
			float resultante=0;
			if(mipunto!=null)
				resultante=mipunto.getLinearVelocity().len();
			
			if(((color==Constantes.Amarillo&&minivel.huamaAmarilla)||(color==Constantes.Verde&&minivel.huamaVerde)||(color==Constantes.Azul&&minivel.huamaAzul))||!bloqueado)
			{
				if(e.getIdcolision()==Constantes.charapavolando)
				{
					if(resultante>10)
					{
						minivel.mibase.sonidos.libelulaau.play();
					}
					float r=mipunto.getLinearVelocity().len();
					resistencia-=r;
					cuerpo.setCurrentTileIndex(calcularSprite(4));
				}
				else if(e.getIdcolision()==Constantes.tronco)
				{
					if(resultante>10)
					{
						minivel.mibase.sonidos.libelulaau.play();
					}
					float r=mipunto.getLinearVelocity().len();
					resistencia-=r;
					cuerpo.setCurrentTileIndex(calcularSprite(4));
				}
				else if(e.getIdcolision()==Constantes.platano)
				{
					if(resultante>10)
					{
						minivel.mibase.sonidos.libelulaau.play();
					}
					resistencia=-1;
					cuerpo.setCurrentTileIndex(calcularSprite(4));
				}
				if(resistencia<0){
					Almorir();
				}

			}
			else
			{
				if(e.getIdcolision()==Constantes.charapavolando)
				{
					minivel.mibase.sonidos.risalibelula.play();//hacer que se ria el chinchilejo
				}
			}
		}
		
		
	}
	@Override
	public int getIdcolision() {
		return Constantes.libelula;
	}
	public void morir()
	{
		if(!muerto)
		{
			minivel.mibase.sonidos.golpelibelula.play();
			minivel.datosHud.AddScore(cuerpo.getX(), cuerpo.getY(), 1500);
			minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(minivel.mibase.FisicaAtlas.getPhysicsConnectorManager().findPhysicsConnectorByShape(cuerpo));
			bcuerpo.getFixtureList().get(0).setSensor(true);
			minivel.efectos.crearExplotalibelula(cuerpo.getX(), cuerpo.getY());
			cuerpo.detachSelf();
			cola.detachSelf();
			alapos.detachSelf();
			alasup.detachSelf();
			muerto=true;
		}
		
	}
	public int calcularSprite(int numerofotogramas)
	{
		if((numerofotogramas-Math.round(resistencia/resistenciainical*numerofotogramas))-1<numerofotogramas)
		{
			return (numerofotogramas-Math.round(resistencia/resistenciainical*numerofotogramas))-1;
		}
		return 4;
	}
	public void Almorir()
	{
		morir();
	}

}
