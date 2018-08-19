package iquiplay.motor;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.input.touch.TouchEvent;

import iquiplay.menus.Historia;
import iquiplay.menus.Menu;

public class Inicio extends Principal {
	@Override
	protected void AlCargarRecursos() {	
		menus=new Menu(this);
	}
	@Override
	protected void AlPintar() {
		if(estadojuego<=0)
		{
			
			menus.CrearMenuActivarSonido();
			menus.CrearMenuPrincipal();
			menus.CrearMenuNiveles();
			menus.CrearMenuCreditos();
			
			if(estadojuego==0){
				menus.MenuActivarSonido();
			}else if(estadojuego==-5){
				if(!Sonidos.silencio){
					this.sonidos.musicamenu.play();
				}
				menus.MenuNiveles();
			}else if(estadojuego==-3){
				menus.MenuCreditos();
			}

		}
		else if(estadojuego>0)
		{
			minivel.alPintar();
		}
	}
	@Override
	protected void AlCorrer() {
		
		if(estadojuego>0)
		{
			if(minivel!=null)
			{
				minivel.alCorrer();
			}
				
		}
		else if(estadojuego==-100)
		{
			if(historia!=null)
			historia.alCorrer();
		}

	}	
	@Override
	protected void AlReiniciar() {
		
	}
	@Override
	protected void Altocar(Scene escena, TouchEvent e) {
		if(estadojuego==-100)
		{
			if(historia!=null)
			historia.Altocar(escena, e);
		}
	}
	

}
