package iquiplay.motor;


import org.anddev.andengine.entity.shape.Shape;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MundoFisico extends PhysicsWorld{
	Principal mibase;
	public AdminColision contacto;
    public MundoFisico (float gravedad, Principal mibase) {
    	super(new Vector2(0, gravedad), false);
    	this.mibase=mibase;
        contacto=new AdminColision(this.mibase);
        setContactListener(contacto);
    }
    public Body crearCuerpoRectangular(float ancho,float alto,float densidad,float restitucion,float friccion,BodyType tipocuerpo,ColisionEnte ente)
    {
    	mWorld.step(0f, getVelocityIterations(), getPositionIterations());
    	Body micuerpo=PhysicsFactory.createBoxBody(this, ancho*0.5f, alto*0.5f, ancho, alto, tipocuerpo,PhysicsFactory.createFixtureDef(densidad, restitucion, friccion));
    	micuerpo.setUserData(ente);
    	return micuerpo;
    }
    public Body crearCuerpoCircular(float radio,float densidad,float restitucion,float friccion,BodyType tipocuerpo,ColisionEnte ente)
    {
    	mWorld.step(0f, getVelocityIterations(), getPositionIterations());
    	Body micuerpo=PhysicsFactory.createCircleBody(this,radio, radio, radio, tipocuerpo, PhysicsFactory.createFixtureDef(densidad, restitucion, friccion));
    	micuerpo.setUserData(ente);
    	return micuerpo;
    }
    public Body crearCuerpoCircular(Shape sprite,float densidad,float restitucion,float friccion,BodyType tipocuerpo,ColisionEnte ente)
    {
    	mWorld.step(0f, getVelocityIterations(), getPositionIterations());
    	Body micuerpo=PhysicsFactory.createCircleBody(this,sprite, tipocuerpo, PhysicsFactory.createFixtureDef(densidad, restitucion, friccion));
    	micuerpo.setUserData(ente);
    	return micuerpo;
    }
    public Body crearCuerpoRectangular(Shape sprite,float densidad,float restitucion,float friccion,BodyType tipocuerpo,ColisionEnte ente)
    {
    	mWorld.step(0f, getVelocityIterations(), getPositionIterations());
    	Body micuerpo=PhysicsFactory.createBoxBody(this, sprite, tipocuerpo, PhysicsFactory.createFixtureDef(densidad, restitucion, friccion));
    	micuerpo.setUserData(ente);
    	return micuerpo;
    }
}