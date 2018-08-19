package iquiplay.niveles;

import iquiplay.entidades.Bijao;
import iquiplay.entidades.Charapa;
import iquiplay.entidades.ControlDisparo;
import iquiplay.entidades.ControlEfectos;
import iquiplay.entidades.Fondo;
import iquiplay.entidades.Gavilan;
import iquiplay.entidades.Huama;
import iquiplay.entidades.HuamaRebote;
import iquiplay.entidades.Lanzador;
import iquiplay.entidades.Libelula;
import iquiplay.entidades.LibelulaBomba;
import iquiplay.entidades.LibelulaCharapa;
import iquiplay.entidades.LibelulaHuama;
import iquiplay.entidades.PisoFlotante;
import iquiplay.entidades.Racimo;
import iquiplay.entidades.Rastro;
import iquiplay.entidades.Reloj;
import iquiplay.entidades.Tronco;
import iquiplay.entidades.Victoria;
import iquiplay.motor.Constantes;
import iquiplay.motor.ControlHud;
import iquiplay.motor.Principal;
import iquiplay.motor.Sonidos;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.Log;

public class Nivel{
	//variables de informacion basica
	String nombre;
	public Scene miescena;
	public Reloj reloj;
	public int numeroCharapas,numerocharapascapturadas,sueltasAire,ncharaini,tiempoparawin=0;
	public long tiempo=1;
	public float ancho,alto,xlanza,ylanza;
	public Principal mibase;
	//controladores
	public ControlDisparo control;
	public ControlEfectos efectos;
	public ControlHud datosHud;	
	//objetos comunes por nivel
	public Lanzador lanzador;
	public Huama huamas[];
	public Charapa[]charapitas;
	public Charapa[]charapitasColgadas;
	public LibelulaCharapa[] libelulasCharapa;
	public LibelulaHuama[] libelulaHuama;
	public boolean envuelo,preparandovuelo,enpartida=true,pausado;
	//variables de logica de juego
	public boolean huamaAzul,huamaAmarilla,huamaVerde;
	/////////Ojo solo pa presentar
	public  Scene mMenuScene;
	Sprite atras,gameover,ganaste;
	public Rastro rastrocharapa;
	public HuamaRebote[] HuamaRebotes;
	public Victoria[]victoriaRebotes;
	public Racimo[] racimos;
	public Bijao[] bijaos;
	public Tronco[] troncos;
	public PisoFlotante[] flotante;
	public Fondo mifondo;
	public Libelula[]libelulas;
	public LibelulaBomba[]libelulasbomba;
	public Nivel(Principal mibase, float ancho,float alto,int numeroCharapas,int numeroHuamas,int numeroCharapasColgadas,int numeroHuemaRebote,int numeroVictorias,int numeroRacimos,int numeroBijao,int numeroTroncos,int numeroFlotantes,int libelulassimples,int nlibelulasbomba,int nlibelulahuama,float xlanza,float ylanza,long tiempo)
	{
		miescena=new Scene();
		if(!mibase.imagenesnivelescargado){			
			mibase.imagenes.CargarNivel();			
			//crear submenus

		}

		this.tiempo=tiempo;
		this.xlanza=xlanza;
		this.ylanza=ylanza;
		this.mibase=mibase;
		this.ancho=ancho;
		this.alto=alto;
		this.numeroCharapas=ncharaini=numeroCharapas;
		this.numerocharapascapturadas=numeroCharapasColgadas;
		control=new ControlDisparo(this);
		efectos= new ControlEfectos(this);
		datosHud=new ControlHud(mibase);
		lanzador=new Lanzador(this,control,numeroCharapas);
		if(numeroTroncos!=0)
		{
			troncos=new Tronco[numeroTroncos];
		}
		if(numeroHuamas!=0)
		{
			huamas=new Huama[numeroHuamas];
		}
		if(libelulassimples!=0)
		{
			libelulas=new Libelula[libelulassimples];
		}
		if(numeroVictorias!=0)
		{
			victoriaRebotes=new Victoria[numeroVictorias];
		}
		if(numeroHuemaRebote!=0)
		{
			HuamaRebotes=new HuamaRebote[numeroHuemaRebote];
		}
		if(numeroRacimos!=0)
		{
			racimos=new Racimo[numeroRacimos];
		}
		if(numeroBijao!=0)
		{
			bijaos=new Bijao[numeroBijao];
		}
		if(numeroFlotantes!=0)
		{
			flotante=new PisoFlotante[numeroFlotantes];
		}
		if(nlibelulasbomba!=0)
		{
			libelulasbomba=new LibelulaBomba[nlibelulasbomba];
		}
		if(nlibelulahuama!=0)
		{
			libelulaHuama=new LibelulaHuama[nlibelulahuama];
		}
		charapitas=new Charapa[numeroCharapas];
		charapitasColgadas=new Charapa[numeroCharapasColgadas];
		libelulasCharapa=new LibelulaCharapa[numeroCharapasColgadas];
		rastrocharapa=new Rastro(this,30);
		reloj=new Reloj(this);
	
		mifondo=new Fondo(mibase);
		
		mibase.menus.CrearBotonPausa();
		mibase.menus.CrearSubMenuWinGameover();
		mibase.menus.CrearSubMenuPausa();
		mibase.menus.CrearSubMenuAyuda();
		mibase.sonidos.RestarMusicnivelevel();
	}
	public void alPintar()
	{	
		mibase.camara.seguirobjeto=false;
		datosHud.CrearDatosHud();
		control.CrearControlDisparo(60,590);
		lanzador.alPintar(xlanza, ylanza);
		//control.alPintar();
		if(huamas!=null)
		for(int i=0;i<huamas.length;i++)
		{
			if(huamas[i]!=null)
				huamas[i].alPintar();
		}
		if(libelulasbomba!=null)
			for(int i=0;i<libelulasbomba.length;i++)
			{
				if(libelulasbomba[i]!=null)
					libelulasbomba[i].alPintar();
			}
		if(libelulas!=null)
			for(int i=0;i<libelulas.length;i++)
			{
				if(libelulas[i]!=null)
					libelulas[i].alPintar();
			}
		if(HuamaRebotes!=null)
			for(int i=0;i<HuamaRebotes.length;i++)
			{
				if(HuamaRebotes[i]!=null)
					HuamaRebotes[i].alPintar();
					
			}
		if(victoriaRebotes!=null)
			for(int i=0;i<victoriaRebotes.length;i++)
			{
				if(victoriaRebotes[i]!=null)
					victoriaRebotes[i].alPintar();
					
			}
		if(racimos!=null)
			for(int i=0;i<racimos.length;i++)
			{
				if(racimos[i]!=null)
					racimos[i].alPintar();
					
			}
		if(bijaos!=null)
			for(int i=0;i<bijaos.length;i++)
			{
				if(bijaos[i]!=null)
					bijaos[i].alPintar();
					
			}
		if(troncos!=null)
			for(int i=0;i<troncos.length;i++)
			{
				if(troncos[i]!=null)
					troncos[i].alPintar();					
			}
		if(libelulasCharapa!=null)
		for(int i=0;i<libelulasCharapa.length;i++)
		{
			if(libelulasCharapa[i]!=null)
				libelulasCharapa[i].alPintar();
		}
		if(flotante!=null)
		for(int i=0;i<flotante.length;i++)
		{
			if(flotante[i]!=null)
				flotante[i].alPintar();
		}
		if(libelulaHuama!=null)
			for(int i=0;i<libelulaHuama.length;i++)
			{
				if(libelulaHuama[i]!=null)
					libelulaHuama[i].alPintar();
			}

		datosHud.PintarDatosHud();
		reloj.start();
		Music m= mibase.sonidos.temas[MathUtils.random(0, 3)];
		if(!Sonidos.silencio){
			m.play();
			m.setVolume(0.3f);
			mibase.sonidos.fondo[MathUtils.random(0, 3)].play();
		}
		
	}
	public void alCorrer()
	{
		if(enpartida)//toda la logica ocurre solo si esta enpartida
		{ 
			if(numeroCharapas==0&&numerocharapascapturadas!=0)
			{
				if(sueltasAire<=0)
				{
					GameOver(Constantes.gameoverseacabaronlascharapas);
				}
			}
			if(numerocharapascapturadas==0&&!envuelo)
			{
					if(tiempoparawin==0){	
						
					control.bloqueo=true;
					preparandovuelo=false;
					mibase.camara.tocarcamara=false;
					
					reloj.Pausa();
					mibase.camara.desactivarupdate=true;
					mibase.camara.mCamera.setMaxVelocityX(4000);
					mibase.camara.mCamera.setZoomFactor(1);
					mibase.camara.mCamera.setCenter(Constantes.camara_ancho/2, Constantes.camara_alto/2);	
					
					
					
				}
				if((ncharaini-lanzador.nlanzamiento)>0&&tiempoparawin==60){
					datosHud.AddScore(2000*(ncharaini-lanzador.nlanzamiento));
					datosHud.MostarPuntosExtras(datosHud.CantidadLanzada.getX()+datosHud.CantidadLanzada.getWidth()/2, datosHud.CantidadLanzada.getY()+datosHud.CantidadLanzada.getHeight(), +(ncharaini-lanzador.nlanzamiento)+"x2000",false);
					mibase.sonidos.destello.play();
				}
				if(tiempoparawin==70){
					datosHud.AddScore(200*(int)tiempo);
					datosHud.MostarPuntosExtras(reloj.reloj.getX()+reloj.reloj.getWidth()/2-30, reloj.reloj.getY()+reloj.reloj.getHeight(),tiempo+"x200",false);
					mibase.sonidos.destello.play();
				}
				
				if(tiempoparawin>150){
					win();					
				}
				tiempoparawin++;
			}			
			lanzador.alCorrer();
			control.alCorrer();
			if(huamas!=null)
			for(int i=0;i<huamas.length;i++)
			{
				if(huamas[i]!=null)
					huamas[i].alCorrer();
			}
			if(libelulasbomba!=null)
				for(int i=0;i<libelulasbomba.length;i++)
				{
					if(libelulasbomba[i]!=null)
						libelulasbomba[i].alCorrer();
				}
			if(libelulas!=null)
				for(int i=0;i<libelulas.length;i++)
				{
					if(libelulas[i]!=null)
						libelulas[i].alCorrer();
				}
			if(victoriaRebotes!=null)
			for(int i=0;i<victoriaRebotes.length;i++)
			{
				if(victoriaRebotes[i]!=null)
					victoriaRebotes[i].alcorrer();
			}
			if(libelulaHuama!=null)
				for(int i=0;i<libelulaHuama.length;i++)
				{
					if(libelulaHuama[i]!=null)
						libelulaHuama[i].alCorrer();
				}
			for(int i=0;i<libelulasCharapa.length;i++)
			{
				if(libelulasCharapa[i]!=null)
				{
					libelulasCharapa[i].alCorrer();
				}
				if(charapitasColgadas[i]!=null)
				{
					charapitasColgadas[i].alcorrer();
					charapitasColgadas[i].comprobarcolisionconhuama(huamas);
				}
			}

			if(envuelo)//mientras una charapa este en vuelo
			{
				if(lanzador.nlanzamiento>=0)
				{

					if(charapitas[lanzador.nlanzamiento-1].charapavolando!=null)
					{
						//mibase.camara.SeguirObjeto(charapitas[lanzador.nlanzamiento-1].charapavolando.getX(),charapitas[lanzador.nlanzamiento-1].charapavolando.getY());
						if(!charapitas[lanzador.nlanzamiento-1].colisiono){
							mibase.camara.SeguirObjeto(charapitas[lanzador.nlanzamiento-1].charapavolando.getX(),charapitas[lanzador.nlanzamiento-1].charapavolando.getY());
						}else {
							mibase.camara.ObsevarObejo(charapitas[lanzador.nlanzamiento-1].puntocolicion.x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,charapitas[lanzador.nlanzamiento-1].puntocolicion.y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
						}						
						charapitas[lanzador.nlanzamiento-1].comprobarcolisionconhuama(huamas);
						rastrocharapa.dejarRastro(charapitas[lanzador.nlanzamiento-1].charapavolando.getX()+charapitas[lanzador.nlanzamiento-1].charapavolando.getWidth()/2, charapitas[lanzador.nlanzamiento-1].charapavolando.getY()+charapitas[lanzador.nlanzamiento-1].charapavolando.getHeight()/2, charapitas[lanzador.nlanzamiento-1].charapavolando.getRotation());
					}
					
				}
			}
			for(int i=0;i<charapitas.length;i++)
			{
				if(charapitas[i]!=null)
					charapitas[i].alcorrer();
			}
			datosHud.AlCorrer();
		}
	}
	public void reiniciarTurno()
	{
		//envuelo=false;
		preparandovuelo=false;
		lanzador.reiniciartiro();
		control.reiniciar();
		mibase.camara.ReiniciarTurno();
	}
	public void GameOver(short razon){
		mibase.FisicaAtlas.reset();
		enpartida=false;
		mibase.menus.MenuTerminarNivel(razon);
		
		SalirdeJuego();
		mibase.sonidos.lose.play();
	}
	public void win()
	{
		mibase.FisicaAtlas.reset();
		enpartida=false;		
		mibase.menus.MenuTerminarNivel(Constantes.winnivel);		
		

		SalirdeJuego();
		mibase.sonidos.win.play();
		
		if(mibase.estadojuego>=1&&mibase.estadojuego<=8){
			mibase.miBDcharapita.ActualizarBloqueoNivel(mibase.estadojuego+1, false);	
		}
		mibase.miBDcharapita.ActualizarPuntosNivel(mibase.estadojuego,mibase.minivel.datosHud.scoreactual);
	}
	
	public void SalirdeJuego(){
		mibase.sonidos.ReiniciarSonidos();
		mibase.sonidos.RestarMusicnivelevel();
		mibase.sonidos.RestarMusicMenus();
	}

}
