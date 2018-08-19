package iquiplay.entidades;



import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;

public class HuamaRebote extends ColisionEnte {
	AnimatedSprite shuamaRebote;
	Body cuerpo;
	Nivel minivel;
	float x,y;
	public HuamaRebote(Nivel minivel,float x, float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
		
	}
	public void alPintar()
	{
		shuamaRebote=new AnimatedSprite(x, y, minivel.mibase.imagenes.huamaRebote.ImagenAnimada.deepCopy());
		cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(100f, 20f, 0f, 1.5f, 0,BodyType.DynamicBody,this);
		cuerpo.setTransform(shuamaRebote.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, shuamaRebote.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(new PhysicsConnector(shuamaRebote,cuerpo, true, false));
		minivel.mibase.escena.attachChild(shuamaRebote);
	}
	@Override
	public int getIdcolision() {
		return Constantes.huamarebote;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		shuamaRebote.animate(50,false);
		minivel.mibase.sonidos.trampolin.play();
	}
}