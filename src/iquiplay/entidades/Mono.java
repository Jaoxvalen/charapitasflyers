package iquiplay.entidades;

import iquiplay.motor.ColisionEnte;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public class Mono extends ColisionEnte{
	
	public Imagen cabezamono;
	public Imagen cuerpoMono;
	public Imagen brazoMonoi;
	public Imagen brazoMonod;
	public Imagen colaMono;
	
	
	float teta=0;
	boolean sr;
	Nivel minivel;
	boolean apli=false;
	Sprite cabeza,cuerpo,brazoi,brazod,cola;
	public Body bcabeza,bcuerpo,bbrazoi,bbrazod,bcola;
	public Mono(Nivel minivel)
	{
		this.minivel=minivel;
		
		
	}
	public void Alpintar(float x, float y)
	{
		
		
		
		
		cabeza=new Sprite(x-6, y+26,102,74,minivel.mibase.imagenes.cabezamono.ImagenSimple);
		cuerpo=new Sprite(x, y,109,102,minivel.mibase.imagenes.cuerpoMono.ImagenSimple);
		brazoi=new Sprite(x+67, y+74,38,67,minivel.mibase.imagenes.brazoMonoi.ImagenSimple);
		brazod=new Sprite(x-3.5f, y+74,38,67,minivel.mibase.imagenes.brazoMonod.ImagenSimple);
		cola=new Sprite(x+45, y-210,17,222,minivel.mibase.imagenes.colaMono.ImagenSimple);
		minivel.mibase.escena.attachChild(cola);
		minivel.mibase.escena.attachChild(cuerpo);
		minivel.mibase.escena.attachChild(brazoi);
		minivel.mibase.escena.attachChild(brazod);
		minivel.mibase.escena.attachChild(cabeza);
		ConectarFisica(x, y);
	}
	public void ConectarFisica(float x,float y)
	{
		/*fisicaConector conexion;
		bcabeza=minivel.mibase.FisicaAtlas.AgregarCuerpo(x+45, y+60, minivel.mibase.FisicaAtlas.crearCuerpoCircular(36, 0.1f, 0.3f,0.1f, false), true);
		bcuerpo=minivel.mibase.FisicaAtlas.AgregarCuerpo(x+55, y+50, minivel.mibase.FisicaAtlas.crearCuerpoCircular(51, 0.1f, 0.3f,0.1f, false), true, this);//este sera mi pivote
		bbrazoi=minivel.mibase.FisicaAtlas.AgregarCuerpo(x+86, y+104, minivel.mibase.FisicaAtlas.crearCuerpoRectangular(38, 67, 0.1f, 0.3f,0.1f, true), true);
		bbrazod=minivel.mibase.FisicaAtlas.AgregarCuerpo(x+17, y+104, minivel.mibase.FisicaAtlas.crearCuerpoRectangular(38, 67, 0.1f, 0.3f,0.1f, true), true);
		bcola=minivel.mibase.FisicaAtlas.AgregarCuerpo(x+50, y-100, minivel.mibase.FisicaAtlas.crearCuerpoRectangular(17, 222, 0.1f, 0.3f,0.1f, false), true);
		conexion=new fisicaConector(bcabeza,cabeza, true, true);
		minivel.mibase.FisicaAtlas.conectar(conexion);
		conexion=null;
		conexion=new fisicaConector(bcuerpo,cuerpo, true, true);
		minivel.mibase.FisicaAtlas.conectar(conexion);
		conexion=null;
		conexion=new fisicaConector(bbrazoi,brazoi, true, true);
		minivel.mibase.FisicaAtlas.conectar(conexion);
		conexion=null;
		conexion=new fisicaConector(bbrazod,brazod, true, true);
		minivel.mibase.FisicaAtlas.conectar(conexion);
		conexion=null;
		conexion=new fisicaConector(bcola,cola, true, true);
		minivel.mibase.FisicaAtlas.conectar(conexion);
		conexion=null;
		
		//union cabeza cuerpo
		RevoluteJointDef join=new RevoluteJointDef();
		minivel.mibase.FisicaAtlas.crearunionRevolute(join, bcabeza, bcuerpo, new Vec2(x+45,y+60),0.5f,-0.5f);
		join=null;
		join=new RevoluteJointDef();
		minivel.mibase.FisicaAtlas.crearunionRevolute(join, bbrazoi, bcuerpo, new Vec2(x+84,y+80),0.5f,-0.5f);
		join=null;
		join=new RevoluteJointDef();
		minivel.mibase.FisicaAtlas.crearunionRevolute(join, bbrazod, bcuerpo, new Vec2(x+16,y+80),0.5f,-0.5f);
		join=null;
		join=new RevoluteJointDef();
		minivel.mibase.FisicaAtlas.crearunionRevolute(join, bcola, bcuerpo, new Vec2(x+50,y-3),0,0);
		join=null;
		join=new RevoluteJointDef();
		minivel.mibase.FisicaAtlas.crearunionRevolute(join, bcola, minivel.mibase.FisicaAtlas.world.getGroundBody(), new Vec2(x+50,y-205));
		join=null;*/
	}
	public void Alcorrer()
	{
		/*if(sr)
		{
			teta=0;
			teta+=10;
		}
		else
		{
			teta=0;
			teta-=10;
		}
		if(bcola.getAngle()>0.2)
		{
			sr=false;
		}
		if(bcola.getAngle()<-0.2)
		{
			sr=true;
		}
		bcola.setAngularVelocity(teta);*/
	}
	@Override
	public synchronized void colisionEfecto(ColisionEnte e,Body mipunto) {
		
	}
	@Override
	public int getIdcolision() {
		return Constantes.mono;
	}
}
