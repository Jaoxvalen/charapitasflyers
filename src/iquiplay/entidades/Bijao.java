package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Bijao extends ColisionEnte{
	Sprite hoja,tallo;
	Body cuerpohoja;
	Nivel minivel;
	float x,y;
	public Bijao(Nivel minivel,float x,float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
	}
	public void alPintar()
	{
		//PhysicsConnector(hoja,cuerpohoja,true,true,true,true)
		hoja=new Sprite(x, y, minivel.mibase.imagenes.hojaBijao.ImagenSimple);
		tallo=new Sprite(x+30, y+5, minivel.mibase.imagenes.talloBijao.ImagenSimple);
		cuerpohoja=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(78, 20, 0.1f, 2f, 0, BodyType.DynamicBody, this);
		cuerpohoja.setTransform(hoja.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, hoja.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector( new PhysicsConnector(hoja,cuerpohoja, true, true));
		Body groundbody=minivel.mibase.FisicaAtlas.createBody(new BodyDef());
		groundbody.setTransform(new Vector2(cuerpohoja.getWorldCenter().x+2,cuerpohoja.getWorldCenter().y), 0);
		RevoluteJointDef join=new RevoluteJointDef();
		join.initialize(cuerpohoja, groundbody, new Vector2(cuerpohoja.getWorldCenter().x+2,cuerpohoja.getWorldCenter().y));
		join.enableLimit=true;
		join.upperAngle=MathUtils.degToRad(10);
		join.lowerAngle=MathUtils.degToRad(-10);
		minivel.mibase.FisicaAtlas.createJoint(join);
		minivel.mibase.escena.attachChild(hoja);
		minivel.mibase.escena.attachChild(tallo);
	}
	@Override
	public int getIdcolision() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		minivel.mibase.sonidos.trampolin.play();
	}

}
