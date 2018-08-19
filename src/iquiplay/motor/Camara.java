package iquiplay.motor;

import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;

import android.util.Log;

public class Camara {

	// Pasar un mi base
	public Principal mibase;

	// Vector par la vista del enemigo
	public Vector2 posiciondeObjetivo;

	static int i = 0;
	int Camara_width = 800;
	public int Camara_heigth = 512, tempscrool = 0;;
	public SmoothCamera mCamera;
	public PinchZoomDetector mPinchZoomDetector;

	public float factorzoominicial = 1, factorzoomseguiro = 1,
			posicionminimadeY = 0, factoroffcenter = 0, factorzoomfinal = 1,
			factorregresarvistainicial = 0;
	public float anchoscena = 2048, factorregresar = 0, xregresar = 0,
			tiempodevistainicial = 0;
	public boolean regresar = false, lanzar = false, soltar = false,
			scroll = false, seguirobjeto = true, scrollmenu = false,
			moverscrollmenu = false, vistainicial = true, zoom = true,
			desactivarupdate;

	// scroll
	float mTouchX = 0, mTouchY = 0, newX = 0, newY = 0, mTouchOffsetX = 0,
			mTouchOffsetY = 0, newScrollX = 0, newScrollY = 0;
	private float factorlanzar = 0;
	
	public boolean envuelo = false;

	public boolean tocarcamara = true;

	public Camara(Principal mibase, float px, float py, int ancho, int alto) {

		this.mibase = mibase;

		Camara_width = ancho;
		Camara_heigth = alto;
		mCamera = new SmoothCamera(px, py, Camara_width, Camara_heigth, 3000,
				3000, 1.0f);
		this.mCamera.setBounds(0, anchoscena, -500, Camara_heigth);
		this.mCamera.setBoundsEnabled(true);
	}

	public void SeguirObjeto(float x, float y) {
		Log.v("pos", y + "");
		
		if (seguirobjeto) {
			if (y < posicionminimadeY) {
				if (mCamera.getZoomFactor() > (Camara_width / anchoscena)) {
					
					mCamera.setCenter(x, mCamera.getHeight() / 2);
					if(y<Camara_heigth/2){
					mCamera.setZoomFactor((Camara_heigth)
							/ (-y + (Camara_heigth)) );					
					factorzoomfinal = (Camara_heigth)
							/ (-y + (Camara_heigth));
					}else{
						mCamera.setZoomFactor(factorzoomseguiro);
						factorzoomfinal=factorzoomseguiro;
					}
					

				} else {
					mCamera.setCenter(x, mCamera.getHeight() / 2 + y);
				}
			} else if (x >= mCamera.getCenterX()) {
				mCamera.setCenter(x, mCamera.getHeight() / 2);
			}
			if (x > posiciondeObjetivo.x) {
				tocarcamara = true;
			}
		}
	}

	public void ObsevarObejo(float x, float y) {
		if (seguirobjeto) {			
			mCamera.setCenter(x, mCamera.getHeight() / 2);		
		}
		tocarcamara = true;
	}

	public void ReiniciarTurno() {
		mCamera.setMaxVelocityX(700);
		// mCamera.setCenter(Constantes.camara_ancho/2,
		// Constantes.camara_alto/2);
		mCamera.setCenter(mCamera.getWidth() / 2, mCamera.getHeight() / 2);
	}
	public void ReiniciarNivel(){
		mCamera.setMaxVelocityX(3000);
		factorregresar = -4;
		factorlanzar = 0;
		regresar =true;
		lanzar=false;
		desactivarupdate = false;
		seguirobjeto=false;
	}

	public void srollcamara(TouchEvent e) {
		if (scroll) {
			seguirobjeto = false;
			if (e.isActionDown()) {
				mCamera.setMaxVelocityX(3000);
				regresar = false;
				lanzar = false;
				mTouchX = e.getX();
				mTouchY = e.getY();
				if (mibase.estadojuego > 0) {
					vistainicial = false;
				}
			} else if (e.isActionMove()) {
				regresar = false;
				lanzar = false;
				factoroffcenter = 0;
				newX = e.getX();
				newY = e.getY();
				factorlanzar = newX - mTouchX;
				mCamera.offsetCenter(-(newX - mTouchX), 0);

			} else if (e.isActionUp()) {
				// seguirobjeto = true;
				if (factorlanzar > -3 && factorlanzar < 3)
					regresar = true;

				else {
					lanzar = true;
				}

				if (mCamera.getCenterX() < anchoscena / 2) {
					factorregresar = -e.getX() * 0.02f;
				}
				if (mCamera.getCenterX() >= anchoscena / 2) {
					factorregresar = (anchoscena - e.getX()) * 0.02f;
				}
			}
		}

	}

	public void Update() {

		if (mibase.estadojuego > 0 && !desactivarupdate) {

			Log.v("lanzar", lanzar+" regresar "+regresar+ " seguir "+seguirobjeto+ " flanzar"+factorlanzar);

			if (vistainicial) {///cuanddo inicia el nivel
				tiempodevistainicial--;
				if (tiempodevistainicial <= 0) {
					mCamera.offsetCenter(factorregresarvistainicial, 0);
					if (mCamera.getCenterX() < posiciondeObjetivo.x) {
						vistainicial = false;
						regresar=true;
						factorregresar=-3;
					}
					if (factorregresarvistainicial < 0) {
						factorregresarvistainicial += 10;
					} else {
						factorregresarvistainicial = -2;
					}
					
				}else{
					mCamera.setCenter(posiciondeObjetivo.x, Camara_heigth/2);
				}
			}			
			
			
			if (factorregresar > 0) {
				if (factorregresar < factorregresar / 10)
					factorregresar /= 10;
			}
			if (factorregresar < 0) {
				if (factorregresar > factorregresar / 10)
					factorregresar /= 10;
			}
			// /para regresar la camara
			if (regresar && !seguirobjeto) {
				mCamera.offsetCenter(factorregresar, 0);
			} else if (lanzar) {// pra lanzar la camara con un fuerza
				if (factorlanzar>=0) {
					if (factorlanzar < 3){
						factorlanzar = 3;
					}
				}
				if (factorlanzar <0) {
					if (factorlanzar > -3){
						factorlanzar = -3;
					}
				}
				mCamera.offsetCenter(-factorlanzar, 0);
			}

			if (factorzoomfinal != mCamera.getZoomFactor()) {
				mCamera.setCenter(mCamera.getCenterX(), 330);
			}
			
			if (!seguirobjeto) {
				
				if(factorzoomseguiro != mCamera.getZoomFactor()){
				factorzoomseguiro = mCamera.getZoomFactor();
				}				
				if(posicionminimadeY != mCamera.getMinY()){
					posicionminimadeY = mCamera.getMinY();
				}
			}
			
			if(mCamera.getCenterX()>posiciondeObjetivo.x){
				tocarcamara=true;
			}
			
			
			factorregresar *= .99;
			factorlanzar *= .99;
		}

	}

	public void Tocarcamara(TouchEvent e) {

		// distinguir el tipo de scroll segun el estado de juego
		if (mibase.estadojuego > 0) {
			if (this.mPinchZoomDetector != null) {
				if (this.zoom) {
					this.mPinchZoomDetector.onTouchEvent(e);
				}
				if (this.mPinchZoomDetector.isZooming()) {
					this.scroll = false;
				} else {
					this.scroll = true;
					this.srollcamara(e);
				}
			}
		}

	}

	public void limitemultitouchzoom(float factorzoom) {

		if (mibase.estadojuego > 0) {

			float factor = factorzoominicial * factorzoom;
			if (factor < Camara_width / anchoscena) {
				mCamera.setZoomFactor(Camara_width / anchoscena);
				factorzoomfinal = Camara_width / anchoscena;
			} else if (factor > 1) {
				mCamera.setZoomFactor(1);
				factorzoomfinal = 1;
			} else {
				mCamera.setZoomFactor(factor);
				factorzoomfinal = factor;
			}
			mCamera.setCenter(mCamera.getCenterX(), Constantes.camara_alto / 2);
			factorzoomseguiro = mCamera.getZoomFactor();
			posicionminimadeY = mCamera.getMinY();

		}
	}

	public void RestablecerCamara() {
		mCamera.setZoomFactorDirect(1);
		mCamera.setCenterDirect(mCamera.getWidth() / 2, mCamera.getHeight() / 2);
		/*
		 * factorzoominicial = 1; factorzoomseguiro = 1; posicionminimadeY = 0;
		 * factoroffcenter = 0; factorzoomfinal = 1; factorregresar = 0;
		 * xregresar = 0; regresar = false; lanzar = false; soltar = false;
		 * scroll = false; seguirobjeto = true;scrollmenu=false;
		 * moverscrollmenu=false;vistainicial=true;
		 */
		mCamera.setMaxVelocityX(3000);
		factorregresar = 0;
		factorlanzar = 0;
		regresar =true;
		lanzar=false;
		desactivarupdate = false;
		seguirobjeto=false;
		;

	}

	// /SCROll de seleccionar nivel
	public void srollcamaraSeleccionarNivel(TouchEvent e) {
		if (scroll) {
			this.mCamera.setBounds(20, 2400, 0, Camara_heigth);
			// this.mCamera.setBounds(0,2400 ,-500, Camara_heigth);
			if (e.isActionDown()) {
				scrollmenu = false;
				regresar = false;
				lanzar = false;
				mTouchX = e.getX();
				mTouchY = e.getY();
				tempscrool = 0;

			}
			if (e.isActionMove()) {

				regresar = false;
				lanzar = false;
				factorlanzar = e.getX() - mTouchX;
				mCamera.offsetCenter(-factorlanzar, 0);
				tempscrool++;
				if (tempscrool > -5 && tempscrool < 5) {
					moverscrollmenu = false;
				} else {
					moverscrollmenu = true;
				}

			}
			if (e.isActionUp()) {

				// mCamera.setCenterDirect(-1200, 0);
				scrollmenu = true;
				mCamera.setMaxVelocityX(900);

				if (factorlanzar < 0) {
					if (mCamera.getCenterX() <= 600) {
						mCamera.setCenter(420, 0);
					} else if (mCamera.getCenterX() > 600
							&& mCamera.getCenterX() <= 1400) {
						mCamera.setCenter(1200, 0);
					} else if (mCamera.getCenterX() > 1400) {
						mCamera.setCenter(2400, 0);
					}
				} else if (factorlanzar > 0) {

					if (mCamera.getCenterX() <= 1000) {
						mCamera.setCenter(420, 0);
					} else if (mCamera.getCenterX() > 1000
							&& mCamera.getCenterX() <= 1800) {
						mCamera.setCenter(1200, 0);
					} else if (mCamera.getCenterX() > 1800) {
						mCamera.setCenter(2400, 0);
					}

				}

			}
		}

	}

	public void VistaInicial(float vistainicialX, float vistainicialY,
			float tiempodevista) {
		mCamera.setCenterDirect(vistainicialX, Constantes.camara_alto / 2);
		this.tiempodevistainicial = tiempodevista;
		vistainicial = true;
		factorregresarvistainicial = (Constantes.camara_ancho / 2 - vistainicialX) / 2;
		posiciondeObjetivo = new Vector2(vistainicialX, vistainicialY);
	}
}
