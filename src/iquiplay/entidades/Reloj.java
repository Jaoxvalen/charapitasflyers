package iquiplay.entidades;
import iquiplay.motor.Constantes;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;

public class Reloj {
	public AnimatedSprite reloj;
	Sprite relojaguja;
	Nivel minivel;
	TimerHandler temporizador;
	float tinicial;
	boolean pausa;
	public Reloj(Nivel minivel)
	{
		this.minivel=minivel;
	}
	public void Alpintar(float x, float y)
	{
		reloj=new AnimatedSprite(x, y, minivel.mibase.imagenes.reloj.ImagenAnimada);
		relojaguja=new Sprite(x+40, y, minivel.mibase.imagenes.relojAguja.ImagenSimple);
		relojaguja.setRotationCenter(11, 45);
		minivel.mibase.hud.attachChild(reloj);
		minivel.mibase.hud.attachChild(relojaguja);
		reloj.animate(100);
	}
	public void start()
	{
		minivel.tiempo*=60;
		tinicial=minivel.tiempo;
		temporizador=new TimerHandler(1f, new ITimerCallback() {			
			@Override
			public void onTimePassed(TimerHandler e) {
				minivel.tiempo--;
				if(minivel.envuelo)
				{
					minivel.datosHud.AddScore(10);
				}
				float angulo=(tinicial-minivel.tiempo)*360/tinicial;
				relojaguja.setRotation(angulo);
				if(minivel.tiempo<=10&&!pausa)
				{
					minivel.datosHud.MostrarTexto(reloj.getX()+30, reloj.getY()+30, minivel.tiempo+"",false);
					minivel.mibase.sonidos.tick.play();
				}
				if(minivel.tiempo>0&&!pausa)
				{
					temporizador.reset();
				}
				if(minivel.tiempo==0&&!pausa)
				{
					minivel.GameOver(Constantes.gameoverseacaboeltiempo);
				}
			}
		});
		minivel.mibase.escena.registerUpdateHandler(temporizador);
	}
	public void Pausa(){
		pausa=true;
		reloj.stopAnimation();
		
	}
	public void Reaunudar(){
		pausa=false;
		reloj.animate(100);
	}
}