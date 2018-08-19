package iquiplay.entidades;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.andengine.extension.physics.box2d.PhysicsConnector;

import android.util.FloatMath;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.EntidadParalaje;
import iquiplay.motor.FondoParalaje;
import iquiplay.motor.Imagen;
import iquiplay.motor.Principal;

public class Fondo {
	public Principal mibase;
	FondoParalaje fondoParalaje;
	Sprite piso;
	public Fondo(Principal base)
	{
		this.mibase=base;
		fondoParalaje = new FondoParalaje(mibase.camara.mCamera, true, 2024);	
		fondoParalaje.setParallaxChangePerSecond(8);
		fondoParalaje.setParallaxScrollFactor(1);
	}
	public void pintarPiso(Imagen imagen ,int inicio,int tamaño,float y)
	{
			for(int i=inicio;i<tamaño;i++)
			{
				piso=new Sprite(i*56, y,imagen.ImagenSimple.getWidth()*2,imagen.ImagenSimple.getHeight()*2, imagen.ImagenSimple);
				mibase.escena.attachChild(piso);
				Body cuerpo=mibase.FisicaAtlas.crearCuerpoRectangular(piso, 0.1f, 0f, 0, BodyType.StaticBody, new ColisionEnte() {
					
					@Override
					public int getIdcolision() {
						// TODO Auto-generated method stub
						return Constantes.piso;
					}
					
					@Override
					public void colisionEfecto(ColisionEnte ente, Body point) {
						// TODO Auto-generated method stub
						
					}
			});
			mibase.FisicaAtlas.registerPhysicsConnector( new PhysicsConnector(piso,cuerpo, true, true));
		}
	}
	public void pintarPisofinta(Imagen imagen ,int inicio,int tamaño,float y)
	{
			for(int i=inicio;i<tamaño;i++)
			{
				piso=new Sprite(i*56, y,imagen.ImagenSimple.getWidth()*2,imagen.ImagenSimple.getHeight()*2, imagen.ImagenSimple);
				mibase.escena.attachChild(piso);
				piso.setZIndex(10);
			}
	}
	public void  dibujarFondoNivel1()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[0], -350, 27);
		agregarItem(mibase.imagenes.ele[0], 1270, 27);
		agregarItem(mibase.imagenes.ele[9], -180, 20);
		agregarItem(mibase.imagenes.ele[29], 0, 250);
		agregarItem(mibase.imagenes.ele[2], 0, 360);
		agregarItem(mibase.imagenes.ele[4], 145, 400);
		agregarItem(mibase.imagenes.ele[10], 336, 340);
		agregarItem(mibase.imagenes.ele[3], 790, 390);
		//agregarItem(mibase.imagenes.ele[22], 912, 146);
		agregarItem(mibase.imagenes.ele[2], 1040, 360);
		agregarItem(mibase.imagenes.ele[10], 1310, 340);
		agregarItem(mibase.imagenes.ele[3], 1770, 390);
		//agregarItem(mibase.imagenes.ele[22], 1920, 146);
		pintarPiso(mibase.imagenes.ele[12],0, 40, 495);
		agregarItem(mibase.imagenes.ele[01],-60 , 550);
		poneryvoltear(mibase.imagenes.ele[01],1740 , 550);
	}
	public void  dibujarFondoNivel2()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);


		agregarItem(mibase.imagenes.ele[0], 1300, 30);
		agregarItem(mibase.imagenes.ele[19], -80, 255);
		agregarItem(mibase.imagenes.ele[19], 160, 255);
		agregarItem(mibase.imagenes.ele[24], 907, 285);
		agregarItem(mibase.imagenes.ele[24], 1175, 285);
		agregarItem(mibase.imagenes.ele[23], 704, -475);
		agregarItem(mibase.imagenes.ele[16], 1215, 386);
		agregarItem(mibase.imagenes.ele[20], 1880, 315);
		agregarItem(mibase.imagenes.ele[14], 0, 375);
		agregarItem(mibase.imagenes.ele[14], 40, 375);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 505);
	}
	public void  dibujarFondoNivel3()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);

		agregarItem(mibase.imagenes.ele[0], 1300, 40);
		poneryvoltear(mibase.imagenes.ele[0], -380 , 40);
		agregarItem(mibase.imagenes.ele[9], 35, 40);
		poneryvoltear(mibase.imagenes.ele[17], -22, 292);
		agregarItem(mibase.imagenes.ele[24], 907, 285);
		agregarItem(mibase.imagenes.ele[24], 1175, 285);
		agregarItem(mibase.imagenes.ele[23], 325, -475);
		agregarItem(mibase.imagenes.ele[16], 1215, 386);
		agregarItem(mibase.imagenes.ele[18], 1800, 340);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 510);
	}
	public void  dibujarFondoNivel4()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		
		agregarItem(mibase.imagenes.ele[0], 645, 40);
		agregarItem(mibase.imagenes.ele[4], 0, 398);
		agregarItem(mibase.imagenes.ele[4], 148, 398);
		agregarItem(mibase.imagenes.ele[3], 300, 398);
		agregarItem(mibase.imagenes.ele[3], 520, 398);
		agregarItem(mibase.imagenes.ele[6], 740, 336);
		poneryvoltear(mibase.imagenes.ele[6], 830, 336);
		agregarItem(mibase.imagenes.ele[15], 1153, 380);
		agregarItem(mibase.imagenes.ele[15], 1210, 380);
		agregarItem(mibase.imagenes.ele[15], 1385, 380);
		agregarItem(mibase.imagenes.ele[15], 1471, 380);
		agregarItem(mibase.imagenes.ele[5], 1848, 330);
		agregarItem(mibase.imagenes.ele[25], -5, 220);
		poneryvoltear(mibase.imagenes.ele[25], 1735, 220);

		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 510);
		
	}
	public void  dibujarFondoNivel5()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[0], 0, 40);
		agregarItem(mibase.imagenes.ele[19], 0, 240);
		//agregarItem(mibase.imagenes.ele[22], 616, 146);
		agregarItem(mibase.imagenes.ele[18], 535, 341);
		agregarItem(mibase.imagenes.ele[15], 906, 390);
		agregarItem(mibase.imagenes.ele[15], 1120, 390);
		poneryvoltear(mibase.imagenes.ele[9], 1481, 20);
		poneryvoltear(mibase.imagenes.ele[19], 1390, 240);
		agregarItem(mibase.imagenes.ele[15], 1456, 379);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 510);
	}
	public void  dibujarFondoNivel6()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[0], -76, 22);
		agregarItem(mibase.imagenes.ele[1], -56, 480);
		agregarItem(mibase.imagenes.ele[22], 49, 135);
		agregarItem(mibase.imagenes.ele[8], 190, 357);
		agregarItem(mibase.imagenes.ele[5], 322, 341);
		agregarItem(mibase.imagenes.ele[5], 420, 341);
		agregarItem(mibase.imagenes.ele[5], 596, 341);
		poneryvoltear(mibase.imagenes.ele[5], 772, 341);
		agregarItem(mibase.imagenes.ele[13], 954, 391);
		agregarItem(mibase.imagenes.ele[13], 1120, 391);
		agregarItem(mibase.imagenes.ele[13], 1275, 391);
		agregarItem(mibase.imagenes.ele[18], 1267, 321);
		agregarItem(mibase.imagenes.ele[18], 1338, 321);
		agregarItem(mibase.imagenes.ele[18], 1483, 321);
		agregarItem(mibase.imagenes.ele[7], 1700, 355);
		agregarItem(mibase.imagenes.ele[7], 1741, 350);
		agregarItem(mibase.imagenes.ele[6], 1800, 334);
		agregarItem(mibase.imagenes.ele[6], 1872, 334);
		agregarItem(mibase.imagenes.ele[17], 1827, 247);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 490);
		
	}
	public void  dibujarFondoNivel7()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[0], -120, 22);
		agregarItem(mibase.imagenes.ele[0], 430, 22);
		agregarItem(mibase.imagenes.ele[0], 1810, 22);
		poneryvoltear(mibase.imagenes.ele[2], -74, 188);
		agregarItem(mibase.imagenes.ele[1], 179, 168);
		poneryvoltear(mibase.imagenes.ele[1], -100, 168);
		agregarItem(mibase.imagenes.ele[1], 732, 168);
		poneryvoltear(mibase.imagenes.ele[1], 440, 168);
		agregarItem(mibase.imagenes.ele[1], 1292, 168);
		poneryvoltear(mibase.imagenes.ele[1], 1000, 168);
		agregarItem(mibase.imagenes.ele[1], 1837, 168);
		poneryvoltear(mibase.imagenes.ele[1],1565, 168);
		agregarItem(mibase.imagenes.ele[16], -107, 380);
		agregarItem(mibase.imagenes.ele[16], 1990, 380);
		agregarItem(mibase.imagenes.ele[7], 163, 353);
		agregarItem(mibase.imagenes.ele[7], 712, 353);
		agregarItem(mibase.imagenes.ele[7], 1272, 353);
		agregarItem(mibase.imagenes.ele[7], 1825, 353);
		agregarItem(mibase.imagenes.ele[10], 236, 333);
		agregarItem(mibase.imagenes.ele[10], 849, 333);
		agregarItem(mibase.imagenes.ele[10], 1378, 333);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 490);
	}
	public void  dibujarFondoNivel8()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[0], 850, 22);
		agregarItem(mibase.imagenes.ele[0], 1280, 22);
		poneryvoltear(mibase.imagenes.ele[17], -46, 185);
		poneryvoltear(mibase.imagenes.ele[17], 0, 250);
		poneryvoltear(mibase.imagenes.ele[6], -48, 320);
		agregarItem(mibase.imagenes.ele[10], 560, 340);
		agregarItem(mibase.imagenes.ele[10], 680, 340);
		agregarItem(mibase.imagenes.ele[19], 980, 235);
		agregarItem(mibase.imagenes.ele[19], 1190, 235);
		agregarItem(mibase.imagenes.ele[19], 1440, 235);
		agregarItem(mibase.imagenes.ele[19], 1670, 235);
		agregarItem(mibase.imagenes.ele[18], 1805, 320);
		
		pintarPiso(mibase.imagenes.ele[12],0, 37, 490);
		
	}
	public void  dibujarFondoNivel9()
	{
		fondoParalaje.attachParallaxEntity(new EntidadParalaje(-0.4f, agregarItemparalaje(mibase.imagenes.ele[11], 0, 220),true,1));
		mibase.escena.attachChild(fondoParalaje);
		agregarItem(mibase.imagenes.ele[26], 0, 390);
		agregarItem(mibase.imagenes.ele[27], -162, 183);
		agregarItem(mibase.imagenes.ele[30], 1500, 300);
		agregarItem(mibase.imagenes.ele[0], 1535, 0);
		
		agregarItem(mibase.imagenes.ele[1], -50, 172);
		poneryvoltear(mibase.imagenes.ele[20], 26, 198);
		agregarItem(mibase.imagenes.ele[10], -165, 342);
		agregarItem(mibase.imagenes.ele[10], -109, 342);
		

		pintarPiso(mibase.imagenes.ele[12],0, 21, 510);
		pintarPisofinta(mibase.imagenes.ele[12],21, 37, 510);
		agregarItem(mibase.imagenes.ele[28], 1097, 507).setZIndex(10);
		
	}
	public Sprite agregarItemparalaje(Imagen imagen, float x, float y)
	{
		Sprite item=new Sprite(x, y,imagen.ImagenSimple.getWidth()*2,imagen.ImagenSimple.getHeight()*2, imagen.ImagenSimple);
		return item;
		
	}
	public Sprite poneryvoltear(Imagen imagen, float x, float y)
	{
		TextureRegion a=imagen.ImagenSimple.deepCopy();
		a.setFlippedHorizontal(true);
		Sprite item=new Sprite(x, y,a.getWidth()*2,a.getHeight()*2, a);
		mibase.escena.attachChild(item);
		return item;
		
	}
	public Sprite agregarItem(Imagen imagen, float x, float y)
	{
		Sprite item=new Sprite(x, y,imagen.ImagenSimple.getWidth()*2,imagen.ImagenSimple.getHeight()*2, imagen.ImagenSimple);
		mibase.escena.attachChild(item);
		return item;
	}
}
