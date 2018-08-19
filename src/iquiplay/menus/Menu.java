package iquiplay.menus;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import iquiplay.motor.BucleCreditos;
import iquiplay.motor.Constantes;
import iquiplay.motor.Imagen;
import iquiplay.motor.Inicio;
import iquiplay.motor.Principal;
import iquiplay.motor.Sonidos;
import iquiplay.niveles.Nivel01;
import iquiplay.niveles.Nivel02;
import iquiplay.niveles.Nivel03;
import iquiplay.niveles.Nivel04;
import iquiplay.niveles.Nivel05;
import iquiplay.niveles.Nivel06;
import iquiplay.niveles.Nivel07;
import iquiplay.niveles.Nivel08;
import iquiplay.niveles.Nivel09;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.scene.background.EntityBackground;
import org.anddev.andengine.entity.scene.background.IBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.controller.SingleTouchControler;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.constants.MIMETypes;
import org.anddev.andengine.util.modifier.IModifier;

import android.graphics.Color;
import android.util.Log;

public class Menu {
	public Scene miescena;
	// /Variables de SubMenuJuego Ayuda
	public Sprite sAyudas;
	public int estdoayuda = 0;
	public Sprite sAdelante;
	public Sprite sCancelar;
	// ===============================
	// Variables de MenuCreditos
	public Sprite sletraCreditos;
	public BucleCreditos bucleletracreditos;
	// =================================

	// variable del menuactivarsonido

	public Sprite sfondoactivarsonido, sbtsi, sbtno;
	// ========================================
	// variables del menu principal
	public Sprite sfondomenup, sbtiniciar, sbtCreditos, sbtpunto, sbtsalir;
	public AnimatedSprite sbtsonido;
	// ============================Niveles
	public Principal juego;
	// Variables de Menu Seleccion Nivel

	public Sprite sbgselecionnivel, sbtatras;
	public AnimatedSprite sbtnivel1, sbtnivel2, sbtnivel3, sbtnivel4,
			sbtnivel5, sbtnivel6, sbtnivel7, sbtnivel8, sbtnivel9,numerodenivele1,numerodenivele2,numerodenivele3,numerodenivele4,numerodenivele5,numerodenivele6,numerodenivele7,numerodenivele8,numerodenivele9;
	public StrokeFont puntosporniveles;
	// ================================
	// ====== Variables MenuTerminarJuego
	// menu Game Over
	public Sprite humano;
	public Sprite charapallorando;
	public Sprite charapaalegre;
	public Sprite lebelulaconcharapa;
	public Sprite fondobotones;
	public Sprite nivelcompleto;
	public Sprite perdiste;
	public Sprite newscore;
	// SUBMENU JUEGO
	// SPRITES
	public Sprite reanudarjuego;
	public Sprite reiniciarnivel;
	public Sprite irmenunivel;
	public Sprite sAyuda;
	// //
	public Sprite pausarnivel;
	public Sprite sFondopausa;
	// menu ganar
	public Sprite siguientenivel;
	//Imagen de los Creditos;
	public Sprite s_credito;
	
	//puntos de los niveles
	public ArrayList<Text> basurerodepuntosdeniveles=new ArrayList<Text>();

	public Menu(Principal juego) {

		this.juego = juego;
		//miescena = new Scene();
		juego.imagenes.CargarMenu();
		this.puntosporniveles = FontFactory.createStrokeFromAsset(
				juego.TexturaPuntosNiveles, juego, "score.ttf", 35, true,
				Color.WHITE, 2, Color.GRAY, false);
		juego.miengine.getFontManager().loadFont(puntosporniveles);

	}

	public void RiniciarMenu() {
		
		juego.imagenes.CargarMenu();
		
		this.puntosporniveles = FontFactory.createStrokeFromAsset(
				juego.TexturaPuntosNiveles, juego, "score.ttf", 35, true,
				Color.WHITE, 2, Color.GRAY, false);
		juego.miengine.getFontManager().loadFont(puntosporniveles);
		miescena = new Scene();
		juego.irAlmenu(miescena);

		
		
	
	}

	// public void CrearMenus

	/*
	 * public void CrearMenus(){ //SPRITES DE SUBMENUJUEO AYUDA
	 * 
	 * //-------------EFECTOS SPRITE
	 * 
	 * /* sReanudarMenuItem.registerEntityModifier(new
	 * MoveModifier(0.6f,sReanudarMenuItem.getX(), sReanudarMenuItem.getX(),
	 * -10,sReanudarMenuItem.getY())); sAyuda.registerEntityModifier(new
	 * MoveModifier(0.6f, -10, sAyuda.getX(), sAyuda.getY(),sAyuda.getY()));
	 * sReiniciar.registerEntityModifier(new MoveModifier(0.6f, 810,
	 * sReiniciar.getX(), sReiniciar.getY(),sReiniciar.getY()));
	 * squitMenuItem.registerEntityModifier(new
	 * MoveModifier(0.6f,squitMenuItem.getX() ,squitMenuItem.getX() ,
	 * 600,squitMenuItem.getY()));
	 * 
	 * 
	 * 
	 * //=============================================================
	 * 
	 * 
	 * //================ }
	 */
	public void MenuActivarSonido() {
		juego.estadojuego = 0;
		//juego.escena.setBackground(new EntityBackground(new Sprite(0, 0,Constantes.camara_ancho,Constantes.camara_alto, juego.imagenes.fondomenup.ImagenSimple)));
		cargar(sfondomenup, false);
		cargar(sfondoactivarsonido, false);
		cargar(sbtsi, true);
		cargar(sbtno, true);
	}

	public void MenuPrincipal() {

		juego.estadojuego = -1;
		//juego.escena.setBackground(new EntityBackground(new Sprite(0, 0,Constantes.camara_ancho,Constantes.camara_alto, juego.imagenes.fondomenup.ImagenSimple)));

		cargar(sfondomenup, false);
		cargar(sbtiniciar, true);
		cargar(sbtCreditos, true);
		//cargar(sbtpunto, true);
		cargar(sbtsalir, true);
		cargar(sbtsonido, true);

		if (Sonidos.silencio) {
			sbtsonido.setCurrentTileIndex(0);
		} else {
			sbtsonido.setCurrentTileIndex(1);
		}

	}

	public void MenuPuntuacion() {
		juego.estadojuego = -2;

	}

	public void MenuCreditos() {
		juego.estadojuego = -3;
	
		cargar(sbgselecionnivel, false);	
		cargar(sbtatras, true);		
		cargar(sletraCreditos, false);
		juego.escena.registerUpdateHandler(bucleletracreditos);
	
		sbtatras.setSize(52, 57.5f);
		sbtatras.setPosition(20, Constantes.camara_alto - sbtatras.getHeight());
		//cargar(s_credito, true);		
		//s_credito.registerEntityModifier(new MoveYModifier(30, Constantes.camara_alto/2, -s_credito.getHeight()));
	}

	public void MenuSalir() {
		juego.estadojuego = -4;

	}

	public void MenuNiveles() {

		
		juego.estadojuego = -5;
		//juego.escena.setBackground(new EntityBackground(new Sprite(0, 0,Constantes.camara_ancho,Constantes.camara_alto, juego.imagenes.bgselecionnivel.ImagenSimple)));
		// seleccionar Nivel
		cargar(sbgselecionnivel, false);
		
		cargar(sbtnivel1, true);
		cargar(numerodenivele1, false);
		cargar(sbtnivel2, true);
		cargar(numerodenivele2, false);
		cargar(sbtnivel3, true);
		cargar(numerodenivele3, false);
		cargar(sbtnivel4, true);
		cargar(numerodenivele4, false);
		cargar(sbtnivel5, true);
		cargar(numerodenivele5, false);
		cargar(sbtnivel6, true);
		cargar(numerodenivele6, false);
		cargar(sbtnivel7, true);
		cargar(numerodenivele7, false);
		cargar(sbtnivel8, true);
		cargar(numerodenivele8, false);
		cargar(sbtnivel9, true);
		cargar(numerodenivele9, false);
		cargar(sbtatras, true);

		// Log.v("", msg)
		if (juego.miBDcharapita.ObtenerPuntajeNivel(1) != 0) {
			PintarPuntosNiveles(sbtnivel1.getX() + sbtnivel1.getWidth() / 2,
					sbtnivel2.getY() + sbtnivel1.getHeight(), ""
							+ juego.miBDcharapita.ObtenerPuntajeNivel(1));
		}
		numerodenivele1.setCurrentTileIndex(0);
		if (juego.miBDcharapita.NivelBloqueado(2)) {
			sbtnivel2.setCurrentTileIndex(1);
		} else {
			sbtnivel2.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(2) != 0) {
				PintarPuntosNiveles(
						sbtnivel2.getX() + sbtnivel2.getWidth() / 2,
						sbtnivel2.getY() + sbtnivel2.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(2));
			}
		}
		numerodenivele2.setCurrentTileIndex(1);
		if (juego.miBDcharapita.NivelBloqueado(3)) {
			sbtnivel3.setCurrentTileIndex(1);
		} else {
			sbtnivel3.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(3) != 0) {
				PintarPuntosNiveles(
						sbtnivel3.getX() + sbtnivel3.getWidth() / 2,
						sbtnivel3.getY() + sbtnivel3.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(3));
			}
		}
		numerodenivele3.setCurrentTileIndex(2);
		if (juego.miBDcharapita.NivelBloqueado(4)) {
			sbtnivel4.setCurrentTileIndex(1);
		} else {
			sbtnivel4.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(4) != 0) {
				PintarPuntosNiveles(
						sbtnivel4.getX() + sbtnivel4.getWidth() / 2,
						sbtnivel4.getY() + sbtnivel4.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(4));
			}
		}
		numerodenivele4.setCurrentTileIndex(3);
		if (juego.miBDcharapita.NivelBloqueado(5)) {
			sbtnivel5.setCurrentTileIndex(1);
		} else {
			sbtnivel5.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(5) != 0) {
				PintarPuntosNiveles(
						sbtnivel5.getX() + sbtnivel5.getWidth() / 2,
						sbtnivel5.getY() + sbtnivel5.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(5));
			}
		}
		numerodenivele5.setCurrentTileIndex(4);
		if (juego.miBDcharapita.NivelBloqueado(6)) {
			sbtnivel6.setCurrentTileIndex(1);
		} else {
			sbtnivel6.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(6) != 0) {
				PintarPuntosNiveles(
						sbtnivel6.getX() + sbtnivel6.getWidth() / 2,
						sbtnivel6.getY() + sbtnivel6.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(6));
			}
		}
		numerodenivele6.setCurrentTileIndex(5);
		if (juego.miBDcharapita.NivelBloqueado(7)) {
			sbtnivel7.setCurrentTileIndex(1);
		} else {
			sbtnivel7.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(7) != 0) {
				PintarPuntosNiveles(
						sbtnivel7.getX() + sbtnivel7.getWidth() / 2,
						sbtnivel7.getY() + sbtnivel7.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(7));
			}
		}
		numerodenivele7.setCurrentTileIndex(6);
		if (juego.miBDcharapita.NivelBloqueado(8)) {
			sbtnivel8.setCurrentTileIndex(1);
		} else {
			sbtnivel8.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(8) != 0) {
				PintarPuntosNiveles(
						sbtnivel8.getX() + sbtnivel8.getWidth() / 2,
						sbtnivel8.getY() + sbtnivel8.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(8));
			}
		}
		numerodenivele8.setCurrentTileIndex(7);
		if (juego.miBDcharapita.NivelBloqueado(9)) {
			sbtnivel9.setCurrentTileIndex(1);
		} else {
			sbtnivel9.setCurrentTileIndex(0);
			if (juego.miBDcharapita.ObtenerPuntajeNivel(9) != 0) {
				PintarPuntosNiveles(
						sbtnivel9.getX() + sbtnivel9.getWidth() / 2,
						sbtnivel9.getY() + sbtnivel9.getHeight(), ""
								+ juego.miBDcharapita.ObtenerPuntajeNivel(9));
			}
		}
		numerodenivele9.setCurrentTileIndex(8);
		sbtatras.setSize(52, 57.5f);
		sbtatras.setPosition(20, Constantes.camara_alto - sbtatras.getHeight());

		// pintar puntos por niveles;

	}

	public void cargar(IEntity simagen, boolean toucharea) {
		if(juego.escena.getChildIndex(simagen)<0){			
			juego.escena.attachChild(simagen);
		}
		else{
			simagen.setVisible(true);
			
		}
		if (toucharea) {
			juego.escena.registerTouchArea((ITouchArea) simagen);
		}
	}

	public void MenuTerminarNivel(short motivoterminacion) {

		if (motivoterminacion == 0) {
			GameOver1();
		} else if (motivoterminacion == 1) {
			GameOver2();
		} else if (motivoterminacion == 2) {
			Win();

		}

	}

	public void submenujuego() {
		// eventos cuando ocruure el pausa;

		// /////////////////////////////////////////
		juego.MenuScene.setTouchAreaBindingEnabled(true);
		juego.escena.setChildScene(juego.MenuScene, false, true, true);

		irmenunivel.setPosition(Constantes.camara_ancho / 2
				- juego.imagenes.btirmenunivel.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2 + 150);
		reiniciarnivel.setPosition(Constantes.camara_ancho / 2
				- juego.imagenes.btReiniciar.ImagenSimple.getWidth() / 2 + 200,
				Constantes.camara_alto / 2);

		juego.MenuScene.attachChild(sFondopausa);
		juego.MenuScene.attachChild(reanudarjuego);
		juego.MenuScene.attachChild(reiniciarnivel);
		juego.MenuScene.attachChild(irmenunivel);
		juego.MenuScene.attachChild(sAyuda);
		juego.MenuScene.attachChild(sbtsonido);
		
		Text nivel=new Text(reanudarjuego.getX()+reanudarjuego.getWidth()/2, reanudarjuego.getY()+reanudarjuego.getHeight()/2+(irmenunivel.getY()-reanudarjuego.getY())/2, juego.minivel.datosHud.PuntosCR, juego.estadojuego+"/9");
		nivel.setScale(2.5f);
		nivel.setPosition(nivel.getX()-nivel.getWidth()/2, nivel.getY());
		juego.MenuScene.attachChild(nivel);
		
		if (Sonidos.silencio) {
			sbtsonido.setCurrentTileIndex(0);
		} else {
			sbtsonido.setCurrentTileIndex(1);
		}

		reanudarjuego.registerEntityModifier(new MoveModifier(0.2f,
				reanudarjuego.getX(), reanudarjuego.getX(), -10, reanudarjuego
						.getY()));
		sAyuda.registerEntityModifier(new MoveModifier(0.2f, -10,
				sAyuda.getX(), sAyuda.getY(), sAyuda.getY()));
		reiniciarnivel.registerEntityModifier(new MoveModifier(0.2f, 810,
				reiniciarnivel.getX(), reiniciarnivel.getY(), reiniciarnivel
						.getY()));
		irmenunivel.registerEntityModifier(new MoveModifier(0.2f, irmenunivel
				.getX(), irmenunivel.getX(), 600, irmenunivel.getY()));

		juego.MenuScene.registerTouchArea(reanudarjuego);
		juego.MenuScene.registerTouchArea(reiniciarnivel);
		juego.MenuScene.registerTouchArea(irmenunivel);
		juego.MenuScene.registerTouchArea(sAyuda);
		juego.MenuScene.registerTouchArea(sbtsonido);
		juego.MenuScene.setBackgroundEnabled(false);
	}

	public void PintarPausa() {
		juego.hud.attachChild(pausarnivel);
		juego.hud.registerTouchArea(pausarnivel);
	}

	public void GameOver() {
		fondobotones.setPosition(400, 250);
		fondobotones.setScale(1);

		juego.hud.clearTouchAreas();
		juego.hud.detachChildren();

		juego.escena.setChildScene(juego.MenuScene, false, true, true);

		juego.MenuScene.attachChild(reiniciarnivel);
		juego.MenuScene.attachChild(irmenunivel);

		reiniciarnivel.setPosition(440, 300);
		irmenunivel.setPosition(570, 300);
		juego.MenuScene.registerTouchArea(reiniciarnivel);
		juego.MenuScene.registerTouchArea(irmenunivel);
		juego.MenuScene.registerTouchArea(sAyuda);

		juego.MenuScene.setBackgroundEnabled(false);

	}

	public void GameOver1() {
		juego.MenuScene.attachChild(sFondopausa);
		//juego.MenuScene.attachChild(charapallorando);
		juego.MenuScene.attachChild(lebelulaconcharapa);
		juego.MenuScene.attachChild(perdiste);
		juego.MenuScene.attachChild(fondobotones);
		GameOver();
	}

	public void GameOver2() {

		juego.MenuScene.attachChild(sFondopausa);
		juego.MenuScene.attachChild(humano);
		juego.MenuScene.attachChild(perdiste);
		juego.MenuScene.attachChild(fondobotones);
		GameOver();

	}

	public void Win() {
		juego.hud.clearTouchAreas();
		juego.hud.detachChildren();

		juego.escena.setChildScene(juego.MenuScene, false, true, true);
		juego.MenuScene.detachChildren();
		juego.MenuScene.attachChild(sFondopausa);

		reiniciarnivel.setPosition(410, 340);
		irmenunivel.setPosition(600, 340);
		fondobotones.setPosition(390, 330);
		fondobotones.setScale(1.2f);

		juego.MenuScene.attachChild(nivelcompleto);
		juego.MenuScene.attachChild(fondobotones);
		juego.MenuScene.attachChild(charapaalegre);


		juego.MenuScene.attachChild(reiniciarnivel);
		juego.MenuScene.attachChild(irmenunivel);
		

		juego.MenuScene.registerTouchArea(reiniciarnivel);
		juego.MenuScene.registerTouchArea(irmenunivel);
		if(juego.estadojuego<=8){
			juego.MenuScene.attachChild(siguientenivel);
			juego.MenuScene.registerTouchArea(siguientenivel);
		}else {
			// poner boton que  va mostrar la historia... cuandpo a ganado..
		}
		Text puntos;
		
		puntos = new Text(300, 255, puntosporniveles,juego.minivel.datosHud.scoreactual+" pt", HorizontalAlign.CENTER);
		puntos.setScale(2);
		juego.MenuScene.attachChild(puntos);
		
		if(juego.miBDcharapita.ObtenerPuntajeNivel(juego.estadojuego)<juego.minivel.datosHud.scoreactual){
			

			juego.MenuScene.attachChild(newscore);
			newscore.setPosition(puntos.getX()+puntos.getWidth()+5, puntos.getY()-3);
			//newscore.setRotation(1.4f);

		}
	
		
		juego.MenuScene.setBackgroundEnabled(false);
	}

	public void subMenuJuegoAyuda() {

		juego.MenuScene.detachChildren();
		juego.MenuScene.clearTouchAreas();

		juego.MenuScene.attachChild(sFondopausa);
		juego.MenuScene.attachChild(sAyudas);

		juego.MenuScene.attachChild(sAdelante);
		juego.MenuScene.attachChild(sbtatras);
		juego.MenuScene.attachChild(sCancelar);

		sbtatras.setVisible(false);
		sAyudas.setVisible(true);

		sbtatras.setPosition(Constantes.camara_ancho / 2
				- juego.imagenes.i_Ayudas.ImagenSimple.getWidth() / 2 + 20,
				Constantes.camara_alto / 2
						+ juego.imagenes.i_Ayudas.ImagenSimple.getHeight() / 2
						- 10);

		// toucharea
		juego.MenuScene.registerTouchArea(sAdelante);
		juego.MenuScene.registerTouchArea(sbtatras);
		juego.MenuScene.registerTouchArea(sCancelar);

	}

	public void CrearMenuActivarSonido() {

		sfondomenup=new  Sprite(0, 0,Constantes.camara_ancho,Constantes.camara_alto, juego.imagenes.fondomenup.ImagenSimple);
		
		sfondoactivarsonido = new Sprite(
				Constantes.camara_ancho
						/ 2
						- juego.imagenes.fondoactivarsonido.ImagenSimple.getWidth()
						/ 2,
				Constantes.camara_alto
						/ 2
						- juego.imagenes.fondoactivarsonido.ImagenSimple
								.getHeight() / 2,
				juego.imagenes.fondoactivarsonido.ImagenSimple);

		sbtsi = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.i_aceptar.ImagenSimple.getWidth() / 2 - 80,
				Constantes.camara_alto / 2
						- juego.imagenes.i_aceptar.ImagenSimple.getHeight() / 2
						+ 25, 83, 85, juego.imagenes.i_aceptar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float tx, float ty) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtsi.setScale(1.3f);
				}
				if (e.isActionUp()) {
					sbtsi.setScale(1f);
					juego.escena.detachChildren();
					juego.escena.clearTouchAreas();
					Sonidos.silencio = false;
					juego.sonidos.musicamenu.play();
					MenuPrincipal();
				}

				return true;
			}

			protected boolean isOutside(final float pX, final float pY) {
				if (pX < 5 || pX > this.getWidth() - 5 || pY < 5
						|| pY > this.getHeight() - 5)
					return true;
				return false;
			}

		};
		sbtno = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.i_cancelar.ImagenSimple.getWidth() / 2 + 80,
				Constantes.camara_alto / 2
						- juego.imagenes.i_cancelar.ImagenSimple.getHeight()
						/ 2 + 25, 78, 88,
				juego.imagenes.i_cancelar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtno.setScale(1.3f);
				}

				if (e.isActionUp()) {
					sbtno.setScale(1f);
					juego.escena.detachChildren();
					juego.escena.clearTouchAreas();
					Sonidos.silencio = true;
					MenuPrincipal();
				}

				return true;
			}

		};

	}

	public void CrearMenuPrincipal() {
		// =======================================================================================
		// MenuPrincipal

		// sfondomenup = new Sprite(0, 0,
		// juego.imagenes.fondomenup.ImagenSimple);

		sbtiniciar = new Sprite(Constantes.camara_ancho - 444,
				Constantes.camara_alto - 465,
				juego.imagenes.btiniciar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtiniciar.setScale(1.3f);
				}

				if (e.isActionUp()) {
					sbtiniciar.setScale(1f);
					//juego.LimpiarScena();
					OcultarMenuPrincipal();
					MenuNiveles();
				}

				return true;
			}
		};

		sbtCreditos = new Sprite(Constantes.camara_ancho - 418,
				Constantes.camara_alto - 285,
				juego.imagenes.btcreditos.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtCreditos.setScale(1.3f);
				}

				if (e.isActionUp()) {
					sbtCreditos.setScale(1f);
					OcultarMenuPrincipal();
					MenuCreditos();
				}
				return true;
			}

		};

		/*sbtpunto = new Sprite(Constantes.camara_ancho - 126,
				Constantes.camara_alto - 409,
				juego.imagenes.btpunto.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtpunto.setScale(1.3f);
				}

				if (e.isActionUp()) {
					sbtpunto.setScale(1f);
				}

				return true;
			}

		};*/

		sbtsalir = new Sprite(Constantes.camara_ancho - 484,
				Constantes.camara_alto - 145,
				juego.imagenes.btsalir.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtsalir.setScale(1.3f);
				}

				if (e.isActionUp()) {
					sbtsalir.setScale(1f);
					juego.finish();
				}
				return true;
			}

		};

		CrearBotonSonido();

	}

	public void OcultarMenuPrincipal() {
		// TODO Auto-generated method stub
		sfondomenup.setVisible(false);
		sbtiniciar.setVisible(false);
		sbtCreditos.setVisible(false);
		//sbtpunto.setVisible(false);
		sbtsalir.setVisible(false);
		sbtsonido.setVisible(false);
		juego.escena.clearTouchAreas();
	}
	public void OcultarMenuNiveles(){
		sbgselecionnivel.setVisible(false);
		sbtnivel1.setVisible(false);
		sbtnivel2.setVisible(false);
		sbtnivel3.setVisible(false);
		sbtnivel4.setVisible(false);
		sbtnivel5.setVisible(false);
		sbtnivel6.setVisible(false);
		sbtnivel7.setVisible(false);
		sbtnivel8.setVisible(false);
		sbtnivel9.setVisible(false);
		sbtatras.setVisible(false);
		
		numerodenivele1.setVisible(false);
		numerodenivele2.setVisible(false);
		numerodenivele3.setVisible(false);
		numerodenivele4.setVisible(false);
		numerodenivele5.setVisible(false);
		numerodenivele6.setVisible(false);
		numerodenivele7.setVisible(false);
		numerodenivele8.setVisible(false);
		numerodenivele9.setVisible(false);
		
		juego.escena.clearTouchAreas();
		
		for (Text temtex:basurerodepuntosdeniveles) {
			temtex.setVisible(false);
		}
		basurerodepuntosdeniveles.clear();
	}

	public void CrearMenuNiveles() {
		// ==========================SELECCIONAR
		// NIVEL=============================
		// sbgselecionnivel = new Sprite(0, 0,bgselecionnivel.ImagenSimple);

		//fondo;
		sbgselecionnivel=new  Sprite(0, 0,Constantes.camara_ancho,Constantes.camara_alto, juego.imagenes.bgselecionnivel.ImagenSimple);
		
		
		sbtnivel1 = new AnimatedSprite(Constantes.camara_ancho / 2 - Constantes.camara_ancho / 4- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/ 4, Constantes.camara_alto - 620,
				juego.imagenes.botonNiveles.ImagenAnimada) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel1.setScale(1.5f);
					numerodenivele1.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel1.setScale(1f);
					numerodenivele1.setScale(1);
				
					juego.historia=new Historia(juego,false);
					/*juego.minivel = new Nivel01(juego);
					juego.irAlnivel(juego.minivel.miescena);
					juego.sonidos.musicamenu.stop();
					*/
				
				}
				return true;
			}
		};
		numerodenivele1=new AnimatedSprite(sbtnivel1.getX()+sbtnivel1.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel1.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada);
				

		sbtnivel2 = new AnimatedSprite(Constantes.camara_ancho / 2- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 620,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel2.setScale(1.5f);
					numerodenivele2.setScale(1.5f);
				}

				if (e.isActionUp()) {

					sbtnivel2.setScale(1f);					
					numerodenivele2.setScale(1f);
					if (sbtnivel2.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(2)) {

						juego.minivel = new Nivel02(juego);
						juego.irAlnivel(juego.minivel.miescena);
						juego.sonidos.musicamenu.stop();
				
					}
				}

				return true;
			}
		};
		numerodenivele2=new AnimatedSprite(sbtnivel2.getX()+sbtnivel2.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel2.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel3 = new AnimatedSprite(Constantes.camara_ancho / 2 + Constantes.camara_ancho / 4-juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 620,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtnivel3.setScale(1.5f);
					numerodenivele3.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel3.setScale(1f);
					numerodenivele3.setScale(1);
					if (sbtnivel3.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(3)) {

						//juego.hud.detachChild(sbtatras);
						//juego.hud.clearTouchAreas();
						juego.minivel = new Nivel03(juego);
						juego.irAlnivel(juego.minivel.miescena);
						juego.sonidos.musicamenu.stop();
						//PintarPausa();
					}
				}
				return true;
			}
		};
		numerodenivele3=new AnimatedSprite(sbtnivel3.getX()+sbtnivel3.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel3.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel4 = new AnimatedSprite(
				Constantes.camara_ancho / 2 - Constantes.camara_ancho / 4- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/ 4, Constantes.camara_alto - 411,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel4.setScale(1.5f);
					numerodenivele4.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel4.setScale(1f);
					numerodenivele4.setScale(1f);
					if (sbtnivel4.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(4)) {
					

					juego.minivel = new Nivel04(juego);
					juego.irAlnivel(juego.minivel.miescena);
					juego.sonidos.musicamenu.stop();
					
					}

				}

				return true;
			}
		};
		numerodenivele4=new AnimatedSprite(sbtnivel4.getX()+sbtnivel4.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel4.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel5 = new AnimatedSprite(Constantes.camara_ancho / 2- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 411,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel5.setScale(1.5f);
					numerodenivele5.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel5.setScale(1f);
					numerodenivele5.setScale(1f);
					if (sbtnivel5.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(5)) {
						juego.minivel = new Nivel05(juego);
						juego.irAlnivel(juego.minivel.miescena);
						juego.sonidos.musicamenu.stop();
						//PintarPausa();
					}

				}
				return true;
			}
		};
		numerodenivele5=new AnimatedSprite(sbtnivel5.getX()+sbtnivel5.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel5.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel6 = new AnimatedSprite(Constantes.camara_ancho / 2 + Constantes.camara_ancho / 4-juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 411,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel6.setScale(1.5f);
					numerodenivele6.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel6.setScale(1f);
					numerodenivele6.setScale(1f);
					if (sbtnivel6.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(6)) {

						juego.minivel = new Nivel06(juego);
						juego.irAlnivel(juego.minivel.miescena);
						juego.sonidos.musicamenu.stop();
						//PintarPausa();
					}
				}

				return true;
			}
		};
		numerodenivele6=new AnimatedSprite(sbtnivel6.getX()+sbtnivel6.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel6.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel7 = new AnimatedSprite(Constantes.camara_ancho / 2 - Constantes.camara_ancho / 4- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/ 4, Constantes.camara_alto - 202,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel7.setScale(1.5f);
					numerodenivele7.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel7.setScale(1f);
					numerodenivele7.setScale(1f);
					if (sbtnivel7.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(7)) {
									
					  juego.minivel=new Nivel07(juego);
					  juego.irAlnivel(juego.minivel.miescena);
					  juego.sonidos.musicamenu.stop();
					}
					 
				}
				return true;
			}
		};
		numerodenivele7=new AnimatedSprite(sbtnivel7.getX()+sbtnivel7.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel7.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());
		
		sbtnivel8 = new AnimatedSprite(Constantes.camara_ancho / 2- juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 202,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel8.setScale(1.5f);
					numerodenivele8.setScale(1.5f);
				}

				if (e.isActionUp()) {
					sbtnivel8.setScale(1f);
					numerodenivele8.setScale(1f);
					if (sbtnivel8.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(8)) {
									
					 juego.minivel=new Nivel08(juego);
					 juego.irAlnivel(juego.minivel.miescena);
					 juego.sonidos.musicamenu.stop(); 
					}
					
					 
				}

				return true;
			}
		};
		numerodenivele8=new AnimatedSprite(sbtnivel8.getX()+sbtnivel8.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel8.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		sbtnivel9 = new AnimatedSprite(Constantes.camara_ancho / 2 + Constantes.camara_ancho / 4-juego.imagenes.botonNiveles.ImagenAnimada.getWidth()/4,
				Constantes.camara_alto - 202,
				juego.imagenes.botonNiveles.ImagenAnimada.deepCopy()) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {

				if (e.isActionDown()) {
					sbtnivel9.setScale(1.5f);
					numerodenivele9.setScale(1.5f);
					
					
				}

				if (e.isActionUp()) {
					sbtnivel9.setScale(1f);
					numerodenivele9.setScale(1f);
					if (sbtnivel9.getCurrentTileIndex() == 0&&!juego.miBDcharapita.NivelBloqueado(9)) {
						 juego.minivel=new Nivel09(juego);
						 juego.irAlnivel(juego.minivel.miescena);
						 juego.sonidos.musicamenu.stop();
					
					}
					
				}
				return true;
			}
		};
		numerodenivele9=new AnimatedSprite(sbtnivel9.getX()+sbtnivel9.getWidth()/2-juego.imagenes.i_numeroniveles.ImagenAnimada.getWidth()/2, sbtnivel9.getY(), juego.imagenes.i_numeroniveles.ImagenAnimada.deepCopy());

		CrearBotonAtras();

	}

	public void CrearBotonAtras() {
		sbtatras = new Sprite(Constantes.camara_ancho / 2,
				Constantes.camara_alto - 101,
				juego.imagenes.btatras.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (e.isActionDown()) {
					sbtatras.setScale(1.3f);
				}
				if (e.isActionUp()) {
					sbtatras.setScale(1f);
					if (juego.estadojuego == -3) {
						juego.escena
								.unregisterUpdateHandler(bucleletracreditos);
						//juego.escena.detachChildren();
						//juego.escena.clearTouchAreas();
						//juego.hud.detachChild(sbtatras);
						//juego.hud.clearTouchAreas();
						OcultarMenuCreditos();
						MenuPrincipal();

					}
					if (juego.estadojuego == -5) {
						OcultarMenuNiveles();
						//juego.escena.detachChildren();
						//juego.escena.clearTouchAreas();
						//juego.hud.detachChild(sbtatras);
						//juego.hud.clearTouchAreas();
						MenuPrincipal();

					}
					if (juego.estadojuego > 0) {
						if (estdoayuda == 2) {
							sbtatras.setVisible(false);
							estdoayuda = 1;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}
						if (estdoayuda == 3) {
							estdoayuda = 2;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}
						if (estdoayuda == 4) {
							estdoayuda = 3;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}

						if (estdoayuda == 5) {

							sAdelante.setVisible(true);
							estdoayuda = 4;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}

					}
				}

				return true;
			}
		};

	}

	public void CrearBotonPausa() {
		// /PAUSA
		pausarnivel = new Sprite(Constantes.camara_ancho
				- juego.imagenes.btpausa.ImagenSimple.getWidth(),
				Constantes.camara_alto
						- juego.imagenes.btpausa.ImagenSimple.getHeight(),
				juego.imagenes.btpausa.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					this.setScale(1.1f);
					juego.camara.zoom = false;
				}
				if (e.isActionUp()) {
					this.setScale(1f);

					// reloj.cancel(); //mibase.miengine.stop();
					juego.minivel.mibase.onPauseGame();
					juego.minivel.pausado = true;
					// juego.MenuScene.setAlpha(0.5f);
					// juego.escena.setAlpha(0.5f);

					juego.camara.zoom = true;
				}

				return true;
			}
		};
	}

	public void CrearSubMenuPausa() {
		// SPRITE FONDO TRANSPARENTE PAUSA
		sFondopausa = new Sprite(0, 0, Constantes.camara_ancho, Constantes.camara_alto,
				juego.imagenes.bgfondopausa.ImagenSimple);

		// SUBMENU PARTIDA

		// SPRITEMENU RESET
		reiniciarnivel = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.btReiniciar.ImagenSimple.getWidth() / 2 + 200,
				Constantes.camara_alto / 2,
				juego.imagenes.btReiniciar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					reiniciarnivel.setScale(1.3f);
				} else if (e.isActionUp()) {
					reiniciarnivel.setScale(1f);
					juego.MenuScene.back();
					juego.escena.clearChildScene();
					juego.minivel.mibase.MenuScene.detachChildren();
					juego.minivel.mibase.MenuScene.clearTouchAreas();
					// juego.escena.clearChildScene();
					// Para no Volver a Cargar las Imagenes
					juego.imagenesnivelescargado = true;
					if (juego.estadojuego == 1) {
						juego.minivel = new Nivel01(juego);
					} else if (juego.estadojuego == 2) {
						juego.minivel = new Nivel02(juego);
					} else if (juego.estadojuego == 3) {
						juego.minivel = new Nivel03(juego);
					} else if (juego.estadojuego == 4) {
						juego.minivel = new Nivel04(juego);
					} else if (juego.estadojuego == 5) {
						juego.minivel = new Nivel05(juego);
					} else if (juego.estadojuego == 6) {
						juego.minivel = new Nivel06(juego);
					} else if (juego.estadojuego == 7) {
						 juego.minivel = new Nivel07(juego);
					} else if (juego.estadojuego == 8) {
						 juego.minivel = new Nivel08(juego);
					} else if (juego.estadojuego == 9) {
						 juego.minivel = new Nivel09(juego);
					}
					juego.irAlnivel(juego.minivel.miescena);
					

				}

				return true;
			}

		};

		// SPRITEMENU SALIR
		irmenunivel = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.btirmenunivel.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2 + 150,
				juego.imagenes.btirmenunivel.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					irmenunivel.setScale(1.3f);
				}
				if (e.isActionUp()) {
					
					IraMenuNiveles();
					irmenunivel.setScale(1f);


				}

				return true;
			}

		};

		reanudarjuego = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.btReanudar.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2 - 150,
				juego.imagenes.btReanudar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					reanudarjuego.setScale(1.3f);
				}
				if (e.isActionUp()) {
					reanudarjuego.setScale(1f);

					juego.MenuScene.back();
					// juego.hud.clearChildScene();

					juego.MenuScene.detachChildren();
					juego.MenuScene.clearTouchAreas();
					
					
					juego.sonidos.ReanudarSonidoJuego();					
					
					juego.minivel.datosHud.PintarDatosHud();					
					
					juego.minivel.reloj.Reaunudar();
					juego.minivel.pausado = false;

				}

				return true;
			}

		};

		sAyuda = new Sprite(Constantes.camara_ancho / 2 - 200
				- juego.imagenes.btAyuda.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2, juego.imagenes.btAyuda.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sAyuda.setScale(1.3f);
				}
				if (e.isActionUp()) {
					sAyuda.setScale(1f);
					subMenuJuegoAyuda();
					estdoayuda = 1;

				}

				return true;
			}

		};
		// //strite para ir al siguiente nivel cuando gana
		siguientenivel = new Sprite(505, 426,
				juego.imagenes.i_siguientenivel.ImagenSimple) {

			@Override
			public boolean onAreaTouched(TouchEvent e, float x, float y) {

				if (e.isActionDown()) {
				}

				if (e.isActionUp()) {
					juego.imagenesnivelescargado = true;
					if (juego.estadojuego == 1) {
						juego.minivel = new Nivel02(juego);
					} else if (juego.estadojuego == 2) {
						juego.minivel = new Nivel03(juego);
					} else if (juego.estadojuego == 3) {
						juego.minivel = new Nivel04(juego);
					} else if (juego.estadojuego == 4) {
						juego.minivel = new Nivel05(juego);
					} else if (juego.estadojuego == 5) {
						juego.minivel = new Nivel06(juego);
					} else if (juego.estadojuego == 6) {
						juego.minivel = new Nivel07(juego);
					} else if (juego.estadojuego == 7) {
						 juego.minivel=new Nivel08(juego);
					} else if (juego.estadojuego == 8) {
						 juego.minivel=new Nivel09(juego);
					}else if(juego.estadojuego == 8){
						//poner las imagens cuando cuando gana el juego
					}
					
					
					juego.MenuScene.back();
					juego.escena.clearChildScene();
					// juego.MenuScene=new sce
					juego.MenuScene.detachChildren();
					juego.MenuScene.clearTouchAreas();
					juego.irAlnivel(juego.minivel.miescena);
					//PintarPausa();

				}
				return true;
			}

		};

		CrearBotonAtras();

		CrearBotonSonido();

	}

	public void CrearSubMenuWinGameover() {

		// ===========Para Game Over Y WIn
		humano = new Sprite(15, 230, juego.imagenes.i_hombre.ImagenSimple);
		charapallorando = new Sprite(20, 205,
				juego.imagenes.i_charapitallorando.ImagenSimple);
		charapaalegre = new Sprite(12, 300,
				juego.imagenes.i_charapitaalegre.ImagenSimple);
		lebelulaconcharapa = new Sprite(20, 205,
				juego.imagenes.i_libelulaconcharapa.ImagenSimple);
		fondobotones = new Sprite(400, 250,
				juego.imagenes.i_fondobotones.ImagenSimple);
		nivelcompleto = new Sprite(Constantes.camara_ancho/2-juego.imagenes.i_nivelcompleto.ImagenSimple.getWidth()/2, 97, juego.imagenes.i_nivelcompleto.ImagenSimple);
		perdiste = new Sprite(250, 120, juego.imagenes.i_perdiste.ImagenSimple);
		
		newscore=new Sprite(600, 130, juego.imagenes.i_nescore.ImagenSimple);

	}

	public void CrearSubMenuAyuda() {

		sAyudas = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.i_Ayudas.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2
						- juego.imagenes.i_Ayudas.ImagenSimple.getHeight() / 2,
				juego.imagenes.i_Ayudas.ImagenSimple);

		sCancelar = new Sprite(Constantes.camara_ancho / 2
				- juego.imagenes.i_cancelar.ImagenSimple.getWidth() / 2,
				Constantes.camara_alto / 2
						+ juego.imagenes.i_Ayudas.ImagenSimple.getHeight() / 2
						- 10, juego.imagenes.btReanudar.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sCancelar.setScale(1.3f);
				}
				if (e.isActionUp()) {
					sCancelar.setScale(1f);


					juego.MenuScene.back();
					// juego.hud.clearChildScene();

					juego.MenuScene.detachChildren();
					juego.MenuScene.clearTouchAreas();
					
					
					juego.sonidos.ReanudarSonidoJuego();					
					
					juego.minivel.datosHud.PintarDatosHud();					
					
					juego.minivel.reloj.Reaunudar();
					juego.minivel.pausado = false;

				}

				return true;
			}

		};

		sAdelante = new Sprite(Constantes.camara_ancho / 2
				+ juego.imagenes.i_Ayudas.ImagenSimple.getWidth() / 2
				- juego.imagenes.i_adelante.ImagenSimple.getWidth() - 20,
				Constantes.camara_alto / 2
						+ juego.imagenes.i_Ayudas.ImagenSimple.getHeight() / 2
						- 10, juego.imagenes.i_adelante.ImagenSimple) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					this.setScale(1.3f);

				}
				if (e.isActionUp()) {
					this.setScale(1f);
					if (juego.estadojuego > 0) {
						if (estdoayuda == 1) {

							sbtatras.setVisible(true);
							estdoayuda = 2;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}else 
						if (estdoayuda == 2) {
							estdoayuda = 3;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}else
						if (estdoayuda == 3) {
							estdoayuda = 4;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);

						}else 
						if (estdoayuda == 4) {
							estdoayuda = 5;
							juego.imagenes
									.CargarSiguienteAyuda("" + estdoayuda);
							sAdelante.setVisible(false);

						}
					}

				}

				return true;
			}
		};

	}

	public void CrearBotonSonido() {

		sbtsonido = new AnimatedSprite(101, Constantes.camara_alto - 101,
				juego.imagenes.btsonido.ImagenAnimada) {
			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				// TODO Auto-generated method stub

				if (e.isActionDown()) {
					sbtsonido.setScale(1.3f);
				}
				if (e.isActionUp()) {
					sbtsonido.setScale(1f);
					if (!Sonidos.silencio) {
						sbtsonido.setCurrentTileIndex(0);
						Sonidos.silencio = true;
						if(juego.estadojuego<=0){
						juego.sonidos.musicamenu.pause();
						}
						if (juego.estadojuego > 0) {
							juego.sonidos.VolumenAll(0);
						}
					} else {
						sbtsonido.setCurrentTileIndex(1);
						Sonidos.silencio = false;
						juego.sonidos.musicamenu.play();
						if (juego.estadojuego > 0) {
							juego.sonidos.VolumenAll(Sonidos.volumenpordefecto);
						}
					}
					sbtsonido.setScale(1f);
				}

				return true;
			}

		};
	}

	public void iralmenu() {

	}

	public void PintarPuntosNiveles(float x, float y, String spuntos) {
		Text temptext = new Text(x, y, puntosporniveles, spuntos,
				HorizontalAlign.CENTER);
		temptext.setPosition(x - temptext.getWidth() / 2,
				y - temptext.getHeight() - 20);
		juego.escena.attachChild(temptext);
		basurerodepuntosdeniveles.add(temptext);
	}
	
	public void IraMenuNiveles(){
		
		if(juego.escena.hasChildScene()){
			
		}
		juego.MenuScene.back();
		juego.escena.clearChildScene();
		juego.minivel.mibase.MenuScene.detachChildren();
		juego.minivel.mibase.MenuScene.clearTouchAreas();
		
		
		juego.imagenesnivelescargado = false;// para que carge de
		// nuego las
		// imagenes del
		// nivel
		juego.estadojuego = -5;	
		juego.camara.RestablecerCamara();				
		juego.minivel.SalirdeJuego();
		RiniciarMenu();

		
		
	}
	public void IraMenuCreditos(){
		juego.imagenesnivelescargado = false;// para que carge de
		// nuego las
		// imagenes del
		// nivel
		juego.estadojuego = -3;	
		juego.camara.RestablecerCamara();				
		juego.minivel.SalirdeJuego();
		juego.sonidos.charaflydub.play();
		RiniciarMenu();		
	}
	
	public void CrearMenuCreditos(){
	/*	s_credito=new Sprite(Constantes.camara_ancho/2-juego.imagenes.i_creditos.ImagenSimple.getWidth(),Constantes.camara_alto/2, juego.imagenes.i_creditos.ImagenSimple){

			@Override
			public boolean onAreaTouched(TouchEvent e,
					float ax, float ay) {
				// TODO Auto-generated method stub
				if(e.isActionDown()){
					
				}
				if(e.isActionMove()){
					
				}
				if(e.isActionUp()){
					this.registerEntityModifier(new MoveYModifier(30, e.getY(), -this.getHeight()));
				}
				
				return true;
			}
			
		};*/
		  //Creditos
		 
		 sletraCreditos= new Sprite(Constantes.camara_ancho/2-juego.imagenes.i_creditos.ImagenSimple.getWidth()/2, Constantes.camara_alto+10, juego.imagenes.i_creditos.ImagenSimple);
		
		 bucleletracreditos= new  BucleCreditos(juego);

	}
	

	public void OcultarMenuCreditos(){
		sbgselecionnivel.setVisible(false);
		sletraCreditos.setVisible(false);
		
		sbtatras.setVisible(false);
		//s_credito.setVisible(false);
		juego.escena.clearTouchAreas();
	}

}