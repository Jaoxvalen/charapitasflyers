/*
 * Clase de Apoyo para abstraer todo lo concerniente a Andengine y limpiar la clase INICIO
 * Para el programador---By jaoxPinheiro
 */
package iquiplay.motor;
import iquiplay.menus.Historia;
import iquiplay.menus.Menu;
import iquiplay.niveles.Nivel;

import java.util.ArrayList;

import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.FixedStepEngine;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.CameraScene;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.controller.SingleTouchControler;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.ITextureBuilder.TextureAtlasSourcePackingException;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.view.KeyEvent;
import android.widget.Toast;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class Principal extends BaseGameActivity implements IOnSceneTouchListener,IPinchZoomDetectorListener{
	//Variables de Loading.....
	
	public Historia historia;
	
	public CameraScene escenacargando;
	int TextureCount;
	public BitmapTextureAtlas TextureEscenaCargando;
	public ITexture[] mAllTextures;
	public TextureRegion TextureRegionImagen;
	public boolean imagenesnavegacioncargado = false,
			imagenesnivelescargado = false,cargandorecursos=false;;
	// ========================
	// Varriable para el scrol de la camara seleccionar Nivel

	public boolean move = true;
	public Nivel minivel;
	// variables globales
	public BucleJuego mibucle;
	public BucleCandoRe mibucleCargandorecursos;
	public ArrayList<Entity> basurero = new ArrayList<Entity>();
	public Vector2 miMouse = new Vector2();
	int anchoLienzo = 1024;
	int altoLienzo = 1024;
	public ControlImagen imagenes;
	public boolean activarManipular = true;
	public BuildableBitmapTextureAtlas  Textura1,Textura2,Textura3,Textura4,Textura5; 
	public BitmapTextureAtlas TexturaAyudas,TextureScore,TexturaPuntos,TexturaPuntosNiveles;
	public Camara camara;
	public Scene escena;
	public MouseJoint mMouseJointActive;
	public Body mGroundBody;
	
	public void setEscena(Scene escena) {
		this.escena=null;
		this.escena=escena;
		getEngine().setScene(escena);
	}

	public HUD hud;
	//variables para la manipulacion de cuerpos
	public MouseJoint mouseJoint;
	public ArrayList<Body>cuerposManipulabes=new ArrayList<Body>();
	//estdo de juego..... nivel del juego, menus,
	public short estadojuego=0;
	//Base de datos Charapita
	public AccesoBD miBDcharapita;
	///menus
	public Menu menus;
	public MundoFisico FisicaAtlas;
	public Engine miengine;
//	sonidos 
	public ControlSonido sonidos;
	//esecena para el submenu juego
	public CameraScene MenuScene;
	@Override
	public void onLoadComplete() {

	}
	@Override
	public Engine onLoadEngine() {
		camara = new Camara(this, 0, 0, Constantes.camara_ancho,
				Constantes.camara_alto);
		EngineOptions engotion = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						Constantes.camara_ancho, Constantes.camara_alto),
				this.camara.mCamera);
		engotion.getTouchOptions().setRunOnUpdateThread(true);
		engotion.setNeedsMusic(true);
		engotion.setNeedsSound(true);
		miengine = new FixedStepEngine(engotion, 30);


		return miengine;
	}

	@Override
	public void onLoadResources() {
		this.Textura1 =  new BuildableBitmapTextureAtlas(anchoLienzo, altoLienzo, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Textura2 =  new BuildableBitmapTextureAtlas(anchoLienzo, altoLienzo, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Textura3 =  new BuildableBitmapTextureAtlas(2048, altoLienzo, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Textura4 =  new BuildableBitmapTextureAtlas(anchoLienzo, altoLienzo, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Textura5 =  new BuildableBitmapTextureAtlas(anchoLienzo, altoLienzo, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.TexturaAyudas=new BitmapTextureAtlas(1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//Textura de la escena cargando
		this.TextureEscenaCargando = new BitmapTextureAtlas(512, 512, TextureOptions.NEAREST);
		
		//texturara pa puntos
		
		TextureScore = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TexturaPuntos=new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TexturaPuntosNiveles=new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//===================================================
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		MusicFactory.setAssetBasePath("mfx/");
		SoundFactory.setAssetBasePath("mfx/");
		FontFactory.setAssetBasePath("fuente/");
		imagenes=new ControlImagen(this);
		AlCargarRecursos();
		sonidos=new ControlSonido(this);
		//PAQUETE DE TODAS LAS TEXTURAS
		/*mAllTextures = new BuildableBitmapTextureAtlas[]
		        {
				this.Textura1,Textura2,this.Textura3
				};*/
		//=========================================
	    //this.mEngine.getTextureManager().loadTexture(TexturaPuntosNiveles);		
		this.mAllTextures=new ITexture[]{Textura1,Textura2,Textura3,Textura4,Textura5,TexturaAyudas,TexturaPuntos,TexturaPuntosNiveles,TextureEscenaCargando,TextureScore};
		miBDcharapita=new AccesoBD(this);
	}

	@Override
	public Scene onLoadScene() {
		
		this.escena = new Scene();
		cargarescenamenu();
		//AlPintar();
		return escena;
	}
	@Override
	public void onPauseGame() {
		// TODO Auto-generated method stub
		super.onPauseGame();

		if (estadojuego <= 0) {
			 sonidos.musicamenu.pause();
		} else if (estadojuego > 0) {
			if (minivel.enpartida && !minivel.pausado) {
				sonidos.PusarSonidoJuego();	
				hud.setChildrenVisible(false);
				//hud.detachChildren();
				//hud.clearTouchAreas();
				minivel.reloj.Pausa();
				menus.submenujuego();
			}
		}
		if (minivel != null) {
			minivel.pausado = true;
		}
	}

	@Override
	public void onResumeGame() {
		// TODO Auto-generated method stub
		super.onResumeGame();
		if (estadojuego < 0) {
			// if(estadojuego==-3||estadojuego==-5 ||estadojuego==-1 ||
			// estadojuego==-2 || estadojuego==-4)
			if(!Sonidos.silencio){
				sonidos.musicamenu.play();
			}
		} else if (estadojuego > 0) {

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if (estadojuego > 0) {
				
				if (!minivel.pausado) {
					onPauseGame();

				} else if (minivel.pausado) {
					//menus.IraMenuNiveles();
				}

			} else if (estadojuego == -5) {
				menus.OcultarMenuNiveles();
				menus.MenuPrincipal();
			} else if (estadojuego == -1 || estadojuego == 0) {
				finish();
			}

			return true;
		} else {
			// return super.onKeyDown(keyCode, event);
		}
		return false;
	}

	public void cargarEscena()
	{
		eliminarUnionMouse();
		MenuScene = new CameraScene(camara.mCamera);
		escena.setBackground(new ColorBackground(0.835f,0.980f,1));
		escena.detachChildren();
		
		hud=new HUD();
		camara.mCamera.setHUD(hud);
		mibucle=new BucleJuego(this);
	
		//escena.setBackground(new ColorBackground(0.4f,0.56f,0.698f));
		escena.setOnAreaTouchTraversalFrontToBack();
		escena.setTouchAreaBindingEnabled(true);		
		//activar el multitouch
		try {
			//miengine.setTouchController(new MultiTouchController());
			camara.mPinchZoomDetector= new  PinchZoomDetector(this);			
		} catch (MultiTouchException e) {
			e.printStackTrace();
		}
		FisicaAtlas=null;
		FisicaAtlas =new MundoFisico(10,this);
		this.mGroundBody = this.FisicaAtlas.createBody(new BodyDef());
		escena.registerUpdateHandler(FisicaAtlas);
		
		mibucle = new BucleJuego(this);	
		//escena.registerUpdateHandler(mibucleCargandorecursos);
	
		escena.registerUpdateHandler(mibucle);
		escena.setOnSceneTouchListener(this);
		camara.ReiniciarNivel();
		AlPintar();
	}
	public void cargarescenamenu()
	{
		eliminarUnionMouse();
		MenuScene = new CameraScene(camara.mCamera);
		//escena.setBackground(new ColorBackground(0.4f,0.56f,0.698f));
		escena.detachChildren();
		hud=new HUD();
		camara.mCamera.setHUD(hud);
		mibucle=new BucleJuego(this);
		
		//escena.setBackground(new ColorBackground(0.4f,0.56f,0.698f));
		escena.setOnAreaTouchTraversalFrontToBack();
		escena.setTouchAreaBindingEnabled(true);		
		camara.mPinchZoomDetector= null;
		
		miengine.setTouchController(new SingleTouchControler());	
		
		
		FisicaAtlas=null;
		FisicaAtlas =new MundoFisico(10,this);
		this.mGroundBody = this.FisicaAtlas.createBody(new BodyDef());
		escena.registerUpdateHandler(FisicaAtlas);
		
		mibucleCargandorecursos = new BucleCandoRe(this);
		escena.registerUpdateHandler(mibucleCargandorecursos);
		
		escena.registerUpdateHandler(mibucle);
		escena.setOnSceneTouchListener(this);
		//miengine.
		AlPintar();
	
	}
	
	
	@Override
	public boolean onSceneTouchEvent(Scene escena, TouchEvent e) {	
		Altocar(escena, e);
		/////Scroll de Camara
		if(mouseJoint==null&&camara.tocarcamara){
			camara.Tocarcamara(e);				
		}
		return false;
	}
	public void Manipular(Body micuerpo)
	{
		cuerposManipulabes.add(micuerpo);
	}
	public void eliminarUnionMouse()
	{
		if (mouseJoint!=null) {				
			FisicaAtlas.destroyJoint(mouseJoint);
			mouseJoint = null;
		}
	}
	public void irAlnivel(Scene miescena)
	{
		cuerposManipulabes.clear();
		MenuScene = new CameraScene(camara.mCamera); 
		camara.desactivarupdate=false;
		camara.tocarcamara=true;
		this.escena=miescena;
		hud.detachChildren();
		hud.clearTouchAreas();
		setEscena(miescena);

		cargarEscena();
	}

	public void irAlmenu(Scene miescena)
	{
		cuerposManipulabes.clear();
		MenuScene = new CameraScene(camara.mCamera); 
		camara.desactivarupdate=true;
		this.escena=miescena;
		setEscena(miescena);
		minivel=null;
		//AlPintar();
		cargarescenamenu();
	}
	public Body GetBodyAtMouse(boolean incluirestaticos,float mouseX,float mouseY)
	{
		if(activarManipular)
		for(Body micuerpox:cuerposManipulabes)
		{
			Fixture tShape=micuerpox.getFixtureList().get(0);
			if(tShape.testPoint( miMouse))
			{
				return micuerpox;
			}
			
		}
		return null;
	}

	//----------------------------------------------------------------------------------
	protected void AlCargarRecursos()
	{
		//implementar en Inicio
	}
	protected void AlPintar()
	{
		//implementar en Inicio 
	}
	protected void AlCorrer()
	{
		//implementar en Inicio 
	}
	protected void AlReiniciar()
	{
		//implementar en Inicio 
	}
	protected void Altocar(Scene escena, TouchEvent e) {
		//implementar en lo ke kieras
	}
	@Override
	public void onPinchZoom(PinchZoomDetector arg0, TouchEvent arg1, float pZoomFactor) {		
			camara.limitemultitouchzoom(pZoomFactor);		
	}

	@Override
	public void onPinchZoomFinished(PinchZoomDetector arg0, TouchEvent arg1,
			float pZoomFactor) {
		camara.limitemultitouchzoom(pZoomFactor);
		
	}

	@Override
	public void onPinchZoomStarted(PinchZoomDetector arg0, TouchEvent arg1) {
		camara.factorzoominicial=camara.mCamera.getZoomFactor();
		
	}
	
	public void LimpiarScena(){
		escena.clearTouchAreas();
		escena.detachChildren();
	}
}
