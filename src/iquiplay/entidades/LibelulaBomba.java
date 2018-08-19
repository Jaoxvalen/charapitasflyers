package iquiplay.entidades;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.decorator.shape.CircleBitmapTextureAtlasSourceDecoratorShape;
import org.anddev.andengine.util.MathUtils;

import com.badlogic.gdx.physics.box2d.Body;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

public class LibelulaBomba extends Libelula {
	AnimatedSprite bomba;
	AnimatedSprite explosion,recblanco;
	float scale;
	boolean tickperder;
	public LibelulaBomba(Nivel minivel, float x, float y, short color,
			short movimiento) {
		super(minivel, x, y, color, movimiento);
		bloqueado=false;
	}
	@Override
	public void alPintar()
	{
		super.alPintar();
		bomba=new AnimatedSprite(x, y, minivel.mibase.imagenes.bomba.ImagenAnimada);
		bomba.animate(100);
		cargarCuerpo(bomba,-40,30);
	}
	@Override
	public void colisionEfecto(ColisionEnte e,Body mipunto) {
		if(e!=null)
		if(e.getIdcolision()==Constantes.charapavolando || e.getIdcolision()==Constantes.platano || e.getIdcolision()==Constantes.tronco  )
		{
			this.resistencia=-1;
			super.colisionEfecto(e, mipunto);
		}
	}
	@Override
	public void Almorir() {
		if(!muerto)
		{
			minivel.mibase.sonidos.explosion.play();
			explosion=new AnimatedSprite(cuerpo.getX(), cuerpo.getY(), minivel.mibase.imagenes.explosion.ImagenAnimada);
			explosion.animate(50);
			explosion.setScale(2);
			minivel.miescena.detachChild(bomba);
			minivel.miescena.attachChild(explosion);
		}
		morir();
	}
	@Override
	public void alCorrer() {
		super.alCorrer();
		if(explosion!=null )
		{
			minivel.mibase.camara.mCamera.setCenterDirect(minivel.mibase.camara.mCamera.getCenterX()+MathUtils.random(-50, 50), minivel.mibase.camara.mCamera.getCenterY()+MathUtils.random(-50, 50));
			scale+=5;
			explosion.setScale(scale);
			if(!tickperder && scale>100)
			{
				tickperder=true;
				minivel.GameOver(Constantes.gameoverseacabaronlascharapas);
			}
			if(explosion.getCurrentTileIndex()>=3)
			{
				explosion.stopAnimation();
			}
			/*if(explosion.getCurrentTileIndex()<15 &&!soltado)
			{
				soltado=true;
				
				recblanco=new AnimatedSprite(explosion.getX(), explosion.getY(),minivel.mibase.imagenes.polvo.ImagenAnimada.deepCopy());
				recblanco.setCurrentTileIndex(0);
				recblanco.setAlpha(0);
				recblanco.setZIndex(10);
				minivel.miescena.attachChild(recblanco);
			}*/
		}
		/*if(recblanco!=null)
		{
			scale+=5;
			recblanco.setAlpha(scale/10);
			recblanco.setScale(scale);
			if(!tickperder && scale>100)
			{
				tickperder=true;
				minivel.GameOver(Constantes.gameoverseacabaronlascharapas);
			}
		}*/
	}
	
}
