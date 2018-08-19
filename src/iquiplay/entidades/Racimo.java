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

public class Racimo extends ColisionEnte{
	Sprite racimo;
	Body cuerpo;
	Platano platano1,platano2,platano3;
	Nivel minivel;
	float x,y;
	boolean golpeado;
	PhysicsConnector conector;
	public Racimo(Nivel minivel,float x,float y)
	{
		this.minivel=minivel;
		this.x=x;
		this.y=y;
	}
	public void alPintar()
	{
		racimo=new Sprite(x, y, minivel.mibase.imagenes.racimo.ImagenSimple);
		cuerpo=minivel.mibase.FisicaAtlas.crearCuerpoRectangular(92, 152, 0, 0.1f, 0,BodyType.StaticBody,this);
		cuerpo.setTransform(racimo.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,racimo.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		conector=new PhysicsConnector(racimo,cuerpo, true, false);
		minivel.mibase.FisicaAtlas.registerPhysicsConnector(conector);
		minivel.mibase.escena.attachChild(racimo);
	}
	@Override
	public int getIdcolision() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte ente,Body mipunto) {
		if(!golpeado)
		{
			if(ente.getIdcolision()==Constantes.charapavolando)
			{
				minivel.mibase.sonidos.golpemadera.play();
				minivel.efectos.crearpolvo(x, y);
				minivel.efectos.crearpolvo(x-50, y);
				minivel.efectos.crearpolvo(x+50, y);
				golpeado=true;
				minivel.mibase.FisicaAtlas.unregisterPhysicsConnector(conector);
				cuerpo.getFixtureList().get(0).setSensor(true);
				racimo.detachSelf();
				platano1=new Platano(minivel, x, y);
				platano2=new Platano(minivel, x-50, y);
				platano3=new Platano(minivel, x+50, y);
				platano1.alPintar();
				platano2.alPintar();
				platano3.alPintar();
				platano1.lanzar(new Vector2(0, 15));
				platano2.lanzar(new Vector2(-15, 15));
				platano3.lanzar(new Vector2(15, 15));
			}
		}
	}
}
