package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class Tronco extends ColisionEnte {
	Sprite tronco;
	Body cuerpo;
	Nivel minivel;
	float x,y;
	boolean destruido,v;
	int idcolision=Constantes.tronco;
	PhysicsConnector conector;
	public Tronco(Nivel minivel,float x,float y,boolean vertical)
	{
		this.v=vertical;
		this.minivel=minivel;
		this.x=x;
		this.y=y;
	}
	public void alPintar()
	{
		tronco=new Sprite(x, y, minivel.mibase.imagenes.tronco.ImagenSimple);
		if(v)
		{
			tronco=new Sprite(x, y, minivel.mibase.imagenes.tronco.ImagenSimple);
			cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(32, 79, 0.05f, 0.1f, 2, BodyType.DynamicBody, this);
		}
		else
		{
			tronco=new Sprite(x, y, minivel.mibase.imagenes.troncoh.ImagenSimple);
			cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(79,32, 0.05f, 0.1f, 2, BodyType.DynamicBody, this);
		}
		cuerpo.setTransform(tronco.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,tronco.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		conector=new PhysicsConnector(tronco,cuerpo, true, true);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(conector);
		cuerpo.setAwake(false);
		minivel.mibase.escena.attachChild(tronco);
	}
	@Override
	public int getIdcolision() {
		// TODO Auto-generated method stub
		return idcolision;
	}

	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		
		if(!destruido)
		{
			if(mipunto.getLinearVelocity().len()>5 || cuerpo.getLinearVelocity().len()>5)
			minivel.mibase.sonidos.golpemadera.play();
			if(ente.getIdcolision()==Constantes.piso)
			{
				destruido=true;
				minivel.efectos.crearpolvo(tronco.getX(), tronco.getY());
				minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(conector);
				cuerpo.getFixtureList().get(0).setSensor(true);
				tronco.detachSelf();
				idcolision=0;
			}
		}
		
	}
	
}
