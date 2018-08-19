package iquiplay.motor;

import org.anddev.andengine.engine.handler.IUpdateHandler;

public class BucleCreditos implements IUpdateHandler {

	public Principal juego;
	public BucleCreditos(Principal juego)
	{
		this.juego=juego;
		
		
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {

        if (juego.menus.sletraCreditos.getHeight() + juego.menus.sletraCreditos.getY() < 0 || juego.menus.sletraCreditos.getY() > Constantes.camara_alto) {
        	juego.menus.sletraCreditos.setPosition(juego.menus.sletraCreditos.getX(),Constantes.camara_alto);
              }
              else {
            	  juego.menus.sletraCreditos.setPosition(juego.menus.sletraCreditos.getX(), juego.menus.sletraCreditos.getY() - (float) getDropDistance(8, pSecondsElapsed));
              }
            
      }		
		
	

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	//METODO QUE MIDE EL DESPLAZAMIENTO DE LETRA CREDITOS CON RESPECTO AL EYE Y
	  public static double getDropDistance(double dropRate, float mSecondsElapsed){
          /* dropRate is how fast in seconds it should take the mexican to travel the distance of the screen */
          return (Constantes.camara_alto * mSecondsElapsed) / dropRate;
  }  

}
