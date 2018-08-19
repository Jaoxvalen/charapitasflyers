package iquiplay.motor;

import java.util.ArrayList;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.util.HorizontalAlign;

import android.graphics.Color;



public class ControlHud {
	public Principal mibase;
	
	///===========para el score
	public  StrokeFont Score;
	public  ChangeableText Scoretex,hiScoretex;
	ArrayList<Text> PuntosColision=new ArrayList<Text>();
	public int scoreactual=0;
	float scalapuntos=0;
	public 	StrokeFont PuntosCR;	
	
	//===========valirables para la vida del gavilan==========================
	Rectangle barra;
	Sprite SpriteVidaCargada;
	public float velocidadvidagavi=70,r=0,v=1,limite,poder=190;
	
	//cantidad de charapas lanzadas
	public Sprite CantidadLanzada;
	Text textoCantidadCharapas;

	//public Rectangle barrat;
	public float ro=0,ve=1,limitet,podert=0, velocidad = 0.169f;
	//public Sprite SpriteBarraIvan,SpriteIvan, SpriteCharapita;

	
	
	//para el color de las huamas
	Sprite cajoneshuama,hudhuamag,hudhuamab,hudhuamay;
	
	public ControlHud(Principal mibase){
		this.mibase=mibase;
		
		//this.mibase.TextureScore = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//mibase.TexturaPuntos=new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mibase.TextureScore.clearTextureAtlasSources();
		mibase.TexturaPuntos.clearTextureAtlasSources();
		
		this.Score= FontFactory.createStrokeFromAsset(mibase.TextureScore, mibase, "score.ttf", 35, true, Color.WHITE, 2, Color.GRAY, false);
		this.PuntosCR =  FontFactory.createStrokeFromAsset(mibase.TexturaPuntos, mibase, "score.ttf", 35, true, Color.WHITE, 2, Color.GRAY, false);
		mibase.miengine.getFontManager().loadFonts(this.Score,this.PuntosCR);		
		//mibase.miengine.getTextureManager().loadTextures(mibase.TextureScore,mibase.TexturaPuntos);
	}
	public void CrearDatosHud(){
		if(mibase.miBDcharapita.ObtenerPuntajeNivel(mibase.estadojuego)>0){
			Scoretex=new ChangeableText(10, 20, Score, "Score:     0");
			this.hiScoretex=new ChangeableText(10,Scoretex.getY()+Scoretex.getHeight()+5, Score,"H. Score: "+mibase.miBDcharapita.ObtenerPuntajeNivel(mibase.estadojuego));
		
		}else{
			this.Scoretex=new ChangeableText(10, 20, Score, "Score:     0");
		}
	}
	
	public void PintarDatosHud(){
		if(mibase.hud.getChildIndex(Scoretex)<0){
			mibase.hud.setTouchAreaBindingEnabled(true);
			
			if(mibase.miBDcharapita.ObtenerPuntajeNivel(mibase.estadojuego)>0){
				mibase.hud.attachChild(this.Scoretex);
				mibase.hud.attachChild(this.hiScoretex);
			
			}else{
				mibase.hud.attachChild(this.Scoretex);
			}

			CantidadLanzada=new Sprite(300, 10, mibase.imagenes.hudCantidadCharapas.ImagenSimple);
			mibase.hud.attachChild(CantidadLanzada);
			cajoneshuama=new Sprite(470, 20, mibase.imagenes.hudHuamas.ImagenSimple);
			mibase.hud.attachChild(cajoneshuama);
			dibujarTextoCantidadCharapa();
			mibase.minivel.reloj.Alpintar(Constantes.camara_ancho-100, 0);
			hudhuamag=new Sprite(cajoneshuama.getX()+2, cajoneshuama.getY()+4, mibase.imagenes.hudhuamaverde.ImagenSimple);
			hudhuamab=new Sprite(cajoneshuama.getX()+77.5f, cajoneshuama.getY()+2, mibase.imagenes.hudhuamaazul.ImagenSimple);
			hudhuamay=new Sprite(cajoneshuama.getX()+152, cajoneshuama.getY()+2, mibase.imagenes.hudhuamaamarilla.ImagenSimple);
			mibase.hud.attachChild(hudhuamag);
			mibase.hud.attachChild(hudhuamab);
			mibase.hud.attachChild(hudhuamay);
			Actualizarhuama();
			mibase.minivel.control.alPintar();
			mibase.menus.PintarPausa();
		}else {
			mibase.hud.setChildrenVisible(true);
		}
		
	
	}
	public void AddScore(float x, float y,int Puntos){
		scoreactual+=Puntos;		
		Scoretex.setText("Score: "+scoreactual);
		MostrarPuntos(x, y-50,""+Puntos);
	}
	public void AddScore(int Puntos){
		scoreactual+=Puntos;		
		Scoretex.setText("Score: "+scoreactual);
	}
	
	public void MostrarPuntos(float x, float y,String spuntos){
		Text temptext=new Text(x, y, PuntosCR, spuntos, HorizontalAlign.CENTER);
		temptext.setUserData(true);
		temptext.setScale(1+(1-mibase.camara.mCamera.getZoomFactor()));
		
		PuntosColision.add(temptext);	
		mibase.escena.attachChild(temptext);		
	}
	public void MostrarTexto(float x, float y,String spuntos,boolean afectadoporcamara){
		Text temptext=new Text(x, y, PuntosCR, spuntos, HorizontalAlign.CENTER);
		temptext.setUserData(afectadoporcamara);
		PuntosColision.add(temptext);
		mibase.hud.attachChild(temptext);		
	}
	public void dibujarTextoCantidadCharapa()
	{
		int cantidad=mibase.minivel.ncharaini-mibase.minivel.lanzador.nlanzamiento;
		mibase.hud.detachChild(textoCantidadCharapas);
		textoCantidadCharapas=new Text(CantidadLanzada.getX()+80, CantidadLanzada.getY()+15, PuntosCR, "X"+cantidad+"", HorizontalAlign.CENTER);
		mibase.hud.attachChild(textoCantidadCharapas);
	}
	public void Actualizarhuama()
	{
		hudhuamag.setVisible(mibase.minivel.huamaVerde);
		hudhuamab.setVisible(mibase.minivel.huamaAzul);
		hudhuamay.setVisible(mibase.minivel.huamaAmarilla);
	}
	public void AlCorrer(){
		
		EfectosPuntosCo();
	}
	///Para los efectos de los puntos...
	public void EfectosPuntosCo(){
		boolean eliminar=false;
		for(Text puntos:PuntosColision){
			
			if((Boolean)puntos.getUserData()){
				if(puntos.getScaleX()<(1.8+(1-mibase.camara.mCamera.getZoomFactor()))){
					scalapuntos=0.03f;
					puntos.setScale(puntos.getScaleX()+scalapuntos);
				}
				else {
					mibase.escena.detachChild(puntos);
					mibase.hud.detachChild(puntos);
					PuntosColision.remove(puntos);	
					eliminar=true;
				}

			}else{
				if(puntos.getScaleX()<1.8){
					scalapuntos=0.03f;
					puntos.setScale(puntos.getScaleX()+scalapuntos);
				}
				else {
					mibase.escena.detachChild(puntos);
					mibase.hud.detachChild(puntos);
					PuntosColision.remove(puntos);	
					eliminar=true;
				}
			}
			if(eliminar)
				break;
		}
	}
	

	public void DisminuirVidaGavilan ()
	{
		if(limite>poder)
		{
			v=poder/limite;
			r=v-60;
			poder-=velocidadvidagavi;
			barra.setWidth(poder);
			barra.setColor(r,v,0);
		}
	}
	
	public void TerminarNivel(){
		int mHighScore=mibase.miBDcharapita.ObtenerPuntajeNivel(mibase.estadojuego);
		if (scoreactual > mHighScore)
			{
				mHighScore = scoreactual;				
				mibase.miBDcharapita.ActualizarPuntosNivel(mibase.estadojuego, scoreactual);
			}
	   	}
	
	public void MostarPuntosExtras(float x, float y, String valor,boolean afectadoporcamara){
		
		//BitmapTextureAtlas bt
		//StrokeFont texturatexttemp =  FontFactory.createStrokeFromAsset(mibase, mibase, "score.ttf", 35, true, Color.RED, 2, Color.GRAY, false);
		Text temptext=new Text(x, y, Score,valor, HorizontalAlign.CENTER);
		temptext.setPosition(x-temptext.getWidth()/2, y);
		temptext.setUserData(afectadoporcamara);
		mibase.hud.attachChild(temptext);
		PuntosColision.add(temptext);
	}
}
