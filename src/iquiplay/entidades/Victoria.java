package iquiplay.entidades;
import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.Contact;

public class Victoria extends ColisionEnte {
	Sprite shuamaRebote;
	Body cuerpo;
	Nivel minivel;
	float x,y;
	public Victoria(Nivel minivel,float x, float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
		
	}
	public void alcorrer()
	{
		cuerpo.setTransform(cuerpo.getPosition(), cuerpo.getAngle()*0.9f);
	}
	public void alPintar()
	{
		shuamaRebote=new Sprite(x, y, minivel.mibase.imagenes.victoria.ImagenSimple);
		cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(133, 20, 0.2f, 1.5f, 0,BodyType.DynamicBody,this);
		cuerpo.setTransform(shuamaRebote.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, shuamaRebote.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(new PhysicsConnector(shuamaRebote,cuerpo, true, true));
		Body groundbody=minivel.mibase.FisicaAtlas.createBody(new BodyDef());
		groundbody.setTransform(cuerpo.getPosition(), 0);
		RevoluteJointDef join=new RevoluteJointDef();
		join.initialize(cuerpo, groundbody, cuerpo.getWorldCenter());
		join.enableLimit=true;
		join.upperAngle=MathUtils.degToRad(2);
		join.lowerAngle=MathUtils.degToRad(-2);
		minivel.mibase.FisicaAtlas.createJoint(join);
		minivel.mibase.escena.attachChild(shuamaRebote);
	}
	@Override
	public int getIdcolision() {
		return Constantes.huamarebote;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		minivel.mibase.sonidos.trampolin.play();
		mipunto.applyForce(MathUtils.random(-10, 10), MathUtils.random(-10, 10), mipunto.getWorldCenter().x, mipunto.getWorldCenter().y);
		//mipunto.applyForce(10, 0, mipunto.getWorldCenter().x,mipunto.getWorldCenter().y);
	}
}