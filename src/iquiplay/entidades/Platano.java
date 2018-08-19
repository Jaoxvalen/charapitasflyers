package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class Platano extends ColisionEnte {
	Sprite platano;
	Body cuerpo;
	Nivel minivel;
	float x,y;
	boolean destruido;
	int idcolision=Constantes.platano;
	public Platano(Nivel minivel,float x,float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
	}
	public void alPintar()
	{
		platano=new Sprite(x, y, minivel.mibase.imagenes.platano.ImagenSimple);
		minivel.mibase.escena.attachChild(platano);
	}
	public void lanzar(Vector2 LinealVelocity)
	{
		cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(30, 50, 0.1f, 0.1f, 0.1f,BodyType.DynamicBody,this);
		cuerpo.setTransform(platano.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, platano.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(new PhysicsConnector(platano,cuerpo, true, true));
		cuerpo.setLinearVelocity(LinealVelocity);
		cuerpo.setAngularVelocity(0.3f);
	}
	@Override
	public int getIdcolision() {
		return idcolision;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		if(!destruido)
		{
			if(ente.getIdcolision()==Constantes.piso)
			{
				destruido=true;
				minivel.efectos.crearpolvo(platano.getX(), platano.getY());
				minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(minivel.mibase.FisicaAtlas.getPhysicsConnectorManager().findPhysicsConnectorByShape(platano));
				cuerpo.getFixtureList().get(0).setSensor(true);
				platano.detachSelf();
				//idcolision=0;
			}
		}
		
	}
}
