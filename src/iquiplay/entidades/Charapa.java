package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class Charapa extends ColisionEnte{
	//==========================charapa==================
	public Imagen charapitaCorre;
	public Imagen charapitaEspera;
	public Imagen charapaSalto;
	public Imagen charapaVolando;
	public Imagen charapitaCapturada;
	//===================================================
	public int colisionID=Constantes.charapavolando,fuerza=1;
	Nivel minivel;
	public Sprite charapavolando;
	public AnimatedSprite charapacaminando;
	public Body cuerpocharapa;
	PhysicsConnector conector;
	public boolean eliminado,capturada,colisiono;
	public Vector2 puntocolicion;
	public Charapa(Nivel minivel,boolean escapturada)
	{
		this.minivel=minivel;
		this.capturada=escapturada;
		
		
	}
	public void setFuerza(int fuerza)
	{
		this.fuerza=fuerza;
	}
	public void CrearCharapafisica(Vector2 pos,Vector2 velocidad,float angle)
	{
		minivel.mibase.escena.sortChildren();
		charapavolando=new Sprite(pos.x,pos.y,62,49,minivel.mibase.imagenes.charapaVolando.ImagenSimple);
		cuerpocharapa=minivel.mibase.FisicaAtlas.crearCuerpoCircular(25, 0.1f, 0.5f,1f, BodyType.DynamicBody, this);
		cuerpocharapa.setTransform(charapavolando.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,charapavolando.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,angle);
		conector=new PhysicsConnector(charapavolando,cuerpocharapa, true, true);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(conector);
		cuerpocharapa.setLinearVelocity(velocidad);
		charapavolando.setZIndex(1);
		minivel.mibase.escena.attachChild(charapavolando);
		minivel.mibase.escena.sortChildren();
	}
	public void ponerAcaminar()
	{
		if(charapavolando!=null)
		{
			colisionID=Constantes.charapacaminando;
			minivel.mibase.sonidos.golpecharapa.play();
			charapacaminando=new AnimatedSprite(-500,0, minivel.mibase.imagenes.charapitaCorre.ImagenAnimada);
			charapacaminando.animate(100);
			charapacaminando.setZIndex(1);
			minivel.mibase.escena.attachChild(charapacaminando);
			minivel.miescena.sortChildren();
			minivel.efectos.crearpolvo(charapavolando.getX(), charapavolando.getY());
			minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(conector);
			minivel.mibase.escena.detachChild(charapavolando);
			//charapavolando=null;
			conector=new PhysicsConnector(charapacaminando, cuerpocharapa,true,false);
			minivel.mibase.FisicaAtlas.registerPhysicsConnector(conector);
		}
	}
	public void comprobarcolisionconhuama(Huama[] mishuamas)
	{
		if(mishuamas!=null)
		for(int i=0;i<mishuamas.length;i++)
		{
			if(mishuamas[i]!=null && charapavolando!=null)
				if(mishuamas[i].huamas.isVisible())
					if(charapavolando.collidesWith(mishuamas[i].huamas))
					{
						mishuamas[i].recoger();
						mishuamas[i]=null;
					}
		}
	}
	@Override
	public int getIdcolision() {
		return colisionID;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte e,Body mipunto) {
		//if(e.getIdcolision()==Constantes.piso && (colisionID==Constantes.charapavolando||colisionID==Constantes.charapacapturada))
		if(e.getIdcolision()==Constantes.piso  && (colisionID==Constantes.charapavolando))
		{
			ponerAcaminar();
			minivel.mibase.camara.seguirobjeto=false;
		}
		puntocolicion=mipunto.getPosition();
		colisiono=true;
		
	}
	public void alcorrer()
	{
		if(!eliminado)
		{
			if(getIdcolision()==Constantes.charapacaminando)
			{
				cuerpocharapa.setLinearVelocity(new Vector2(5,1));
			}
			if(cuerpocharapa.getLinearVelocity().len()<0.01f)
			{
				//cuerpocharapa.setLinearVelocity(new Vector2(10,-10));
				//ponerAcaminar();
				if(charapavolando!=null)
				{
					minivel.efectos.crearpolvo(charapavolando.getX(), charapavolando.getY());
					minivel.mibase.sonidos.golpecharapa.play();
					minivel.datosHud.AddScore(charapavolando.getX(), charapavolando.getY(), -20);;
					descartar();
				}
			}
			if(conector.getShape().getY()>Constantes.limitedecharapaabajo || conector.getShape().getX()>Constantes.limitedecharapacostado)
			{
				descartar();
				
			}
		}

	}
	public void descartar()
	{
		minivel.mibase.camara.seguirobjeto=false;
		eliminado=true;
		minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(conector);
		if(charapavolando!=null)
		{
			charapavolando.detachSelf();
		}
		if(charapacaminando!=null)
		{
			charapacaminando.detachSelf();
		}
		if(capturada)
		{
			minivel.sueltasAire--;
			minivel.numerocharapascapturadas--;	
		}
		else
		{
			minivel.numeroCharapas--;
		}
		if(minivel.numeroCharapas==0)
		{
			minivel.envuelo=false;
		}
	}
}