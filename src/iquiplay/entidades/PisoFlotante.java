package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class PisoFlotante extends ColisionEnte {
	Sprite piso;
	Body cuerpo;
	Nivel minivel;
	float x,y;
	public PisoFlotante(Nivel minivel,float x,float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
	}
	public void alPintar()
	{
		piso=new Sprite(x, y, minivel.mibase.imagenes.flotante.ImagenSimple);
		cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(242, 140, 0f, 0.1f, 1,BodyType.StaticBody,this);
		cuerpo.setTransform(piso.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,piso.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(new PhysicsConnector(piso,cuerpo, true, true));
		minivel.mibase.escena.attachChild(piso);
	}
	@Override
	public int getIdcolision() {
		// TODO Auto-generated method stub
		return Constantes.pisoFlotante;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente, Body mipunto) {
		// TODO Auto-generated method stub
		
	}
}
