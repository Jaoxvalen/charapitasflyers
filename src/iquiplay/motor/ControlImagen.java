package iquiplay.motor;

import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureBuilder;
import org.anddev.andengine.opengl.texture.atlas.buildable.builder.ITextureBuilder.TextureAtlasSourcePackingException;
import org.anddev.andengine.util.Debug;

public class ControlImagen {

	// /Variables de SubMenuJuego Ayuda
	public Imagen i_Ayudas;
	public Imagen AyudaN2;
	public Imagen AyudaN3;
	public Imagen AyudaN4;
	public Imagen AyudaN5;
	public Imagen i_adelante;
	public Imagen i_cancelar;
	// ===============================

	// CREDITOS
	//public Imagen creditos;
	// ====================0
	Principal mibase;
	public Imagen cargador;
	public Imagen boton;
	public Imagen polvo;
	public Imagen gavicuerpo;
	public Imagen gavicola;
	public Imagen gavialapos;
	public Imagen gavialasup;
	public Imagen gavicabeza;
	public Imagen huamaVerde;
	public Imagen huamaAzul;
	public Imagen huamaAmarilla;
	public Imagen lanzador;
	public Imagen piedra;
	public Imagen charapitaCorre;
	public Imagen charapitaEspera;
	public Imagen charapaSalto;
	public Imagen charapaVolando;
	public Imagen charapitaCapturada;
	public Imagen[] libelulaverde = new Imagen[2];
	public Imagen[] libelulaazul = new Imagen[2];
	public Imagen[] libelulaamarilla = new Imagen[2];
	public Imagen cabezamono;
	public Imagen cuerpoMono;
	public Imagen brazoMonoi;
	public Imagen brazoMonod;
	public Imagen colaMono;
/*	public Imagen n1piso;
	public Imagen n1fondo2;
	public Imagen n1fondo3;
	public Imagen n1fondo4;
	public Imagen n1fondo5;
	public Imagen n1nube1;
	public Imagen n1nube2;
	public Imagen n1nube3;
	public Imagen piso1;*/
	
	public Imagen libelulaalapos;
	public Imagen libelulaalasup;
	public Imagen explotaLibelula;
	public Imagen destello;
	public Imagen n1piso2;

	// variables del menu principal
	public Imagen fondomenup, btiniciar, btcreditos, btsalir, btsonido;//,btpunto
	// Variables de Menu Seleccion Nivel
	public Imagen bgselecionnivel, btnivel1, btnivel2, btnivel3, btatras,
			btnivelactivado, btniveldesactivado,botonNiveles;

	public Imagen fondoactivarsonido;
	public Imagen i_aceptar;

	public Imagen huamaRebote;

	public Imagen racimo, platano;
	public Imagen victoria;
	public Imagen hojaBijao, talloBijao;
	public Imagen tronco, troncoh, flotante;

	// Variables submenu juego
	public Imagen btReanudar;
	public Imagen btReiniciar;
	public Imagen btirmenunivel;
	public Imagen btAyuda;
	public Imagen bgfondopausa;

	// PAUSA
	public Imagen btpausa;

	// Variables GameOver y WIN
	public Imagen i_hombre;
	public Imagen i_charapitallorando;
	public Imagen i_libelulaconcharapa;
	public Imagen i_charapitaalegre;
	public Imagen i_fondobotones;
	public Imagen i_nivelcompleto;
	public Imagen i_perdiste;
	public Imagen i_siguientenivel;

	// imagen reloj
	public Imagen reloj, relojAguja;

	public Imagen hudCantidadCharapas;
	public Imagen hudHuamas, hudhuamaverde, hudhuamaazul, hudhuamaamarilla;
	
	//imagen Bomba
	public Imagen bomba;
	public Imagen explosion;
	public Imagen[]ele;
	public Imagen barravida;
	
	//Imagen de creditos;
	public Imagen i_creditos;
	
	//imagen de newecore
	public Imagen i_nescore;
	
	//Numero de niveles
	public Imagen i_numeroniveles;
	
	
	public ControlImagen(Principal mibase) {
		this.mibase = mibase;
		ele=new Imagen[31];
		
		// ========IMAGENES SUBMENUJUEGO AYUDA


		// =========================================================================

		// =============================CREDITOS=============================
		/*creditos = new Imagen(mibase, "menu/creditos.png",
				mibase.textura2FondoN1);*/

		// =======================================================================================
		/*cabezamono = new Imagen(mibase, "mono/cabeza.png", mibase.Textura4);
		cuerpoMono = new Imagen(mibase, "mono/cuerpo.png", mibase.Textura4);
		brazoMonoi = new Imagen(mibase, "mono/brazoi.png", mibase.Textura4);
		brazoMonod = new Imagen(mibase, "mono/brazod.png", mibase.Textura4);
		colaMono = new Imagen(mibase, "mono/cola.png", mibase.Textura4);*/

	}

	public void CargarNivel() {
		this.mibase.Textura1.clearTextureAtlasSources();
		this.mibase.Textura2.clearTextureAtlasSources();
		this.mibase.Textura3.clearTextureAtlasSources();
		this.mibase.Textura4.clearTextureAtlasSources();
		this.mibase.Textura5.clearTextureAtlasSources();
		mibase.TextureScore.clearTextureAtlasSources();
		mibase.TexturaPuntos.clearTextureAtlasSources();
		this.mibase.miengine.getTextureManager().unloadTextures(mibase.Textura1,mibase.Textura2, mibase.Textura3,mibase.Textura4,mibase.Textura5,mibase.TextureScore,mibase.TexturaPuntos,mibase.TexturaPuntosNiveles,mibase.TexturaAyudas);
		//this.mibase.miengine.getTextureManager().reloadTextures();
		//mibase.TexturaPuntosNiveles.clearTextureAtlasSources();
		////Cargar Ayuda
		CargarAyuda();
		
		//Imgenes de NIvel 6
		
		barravida=new Imagen(mibase, "gavilan/vida.png",1,6, mibase.Textura1);
		bomba=new Imagen(mibase, "items/bomba.png",2,1, mibase.Textura1);
		explosion=new Imagen(mibase, "efectos/explo.png",4,4, mibase.Textura4);
		// /////////////////////Cargar imagenes Item1
		// /cargar imagenes lanzador	
		piedra = new Imagen(mibase, "lanzador/piedra.png", mibase.Textura1);
		// /===Item1==
		huamaVerde = new Imagen(mibase, "items/huamaverde.png", mibase.Textura1);
		huamaAzul = new Imagen(mibase, "items/huamaazul.png", mibase.Textura1);
		huamaAmarilla = new Imagen(mibase, "items/huamaamarilla.png",
				mibase.Textura1);
		racimo = new Imagen(this.mibase, "items/racimo.png", mibase.Textura1);
		platano = new Imagen(this.mibase, "items/platano.png", mibase.Textura1);
		huamaRebote = new Imagen(this.mibase, "items/huamarebote.png", 1, 6,
				mibase.Textura1);
		victoria = new Imagen(this.mibase, "items/victoria.png",
				mibase.Textura1);
		hojaBijao = new Imagen(this.mibase, "items/bijao.png", mibase.Textura1);
		talloBijao = new Imagen(this.mibase, "items/tallo.png", mibase.Textura1);
		tronco = new Imagen(this.mibase, "items/tronco.png", mibase.Textura1);
		troncoh = new Imagen(this.mibase, "items/troncoho.png", mibase.Textura1);
		flotante = new Imagen(this.mibase, "items/flotante.png",
				mibase.Textura1);
		// /Cargar imagenes HUds
		hudCantidadCharapas = new Imagen(mibase, "huds/cantidad.png",
				mibase.Textura1);
		hudHuamas = new Imagen(mibase, "huds/huamas.png", mibase.Textura1);
		hudhuamaverde = new Imagen(mibase, "huds/huamaverde.png",
				mibase.Textura1);
		hudhuamaazul = new Imagen(mibase, "huds/huamaazul.png", mibase.Textura1);
		hudhuamaamarilla = new Imagen(mibase, "huds/huamaamarilla.png",
				mibase.Textura1);
		reloj = new Imagen(this.mibase, "huds/reloj.png", 1, 5, mibase.Textura1);
		relojAguja = new Imagen(this.mibase, "huds/relojaguja.png",
				mibase.Textura1);
	

		// /cargar imagenes de libelulas
		// libelula verde
		libelulaverde[0] = new Imagen(mibase, "libelula/cabeza.png", 4, 1,mibase.Textura1);
		libelulaverde[1] = new Imagen(mibase, "libelula/cola.png",mibase.Textura1);
		// libelula azul
		libelulaazul[0] = new Imagen(mibase, "libelula/cabezaazul.png", 4, 1,mibase.Textura1);
		libelulaazul[1] = new Imagen(mibase, "libelula/colaazul.png",mibase.Textura1);
		// libelula amarilla
		libelulaamarilla[0] = new Imagen(mibase, "libelula/cabezaamarilla.png",4,1,mibase.Textura1);
		libelulaamarilla[1] = new Imagen(mibase, "libelula/colaamarilla.png",
				mibase.Textura1);
		libelulaalapos = new Imagen(mibase, "libelula/alapost.png", 1, 2,
				mibase.Textura1);
		libelulaalasup = new Imagen(mibase, "libelula/alasup.png", 1, 2,
				mibase.Textura1);

		// //cargar imagenes de charapita
		charapitaCorre = new Imagen(mibase, "charapita/correr.png", 1, 4,
				mibase.Textura1);
		charapitaEspera = new Imagen(mibase, "charapita/espera.png", 1, 5,
				mibase.Textura1);
		charapaSalto = new Imagen(mibase, "charapita/salto.png", 1, 5,
				mibase.Textura1);
		charapaVolando = new Imagen(mibase, "charapita/volando.png",
				mibase.Textura1);
		charapitaCapturada = new Imagen(mibase, "charapita/capturada.png",
				mibase.Textura1);

		// cargar imagenes de cargador
		cargador = new Imagen(mibase, "cargador/barra.png", mibase.Textura1);
		boton = new Imagen(mibase, "cargador/boton.png", mibase.Textura1);

		// cargar imagnes de botones
		// =====================================SUBMENU JUEGO
		bgfondopausa = new Imagen(mibase, "botones/fondopausa2.png",
				mibase.Textura1);
		btReanudar = new Imagen(mibase, "botones/play.png", mibase.Textura1);
		btReiniciar = new Imagen(mibase, "botones/reiniciar.png",
				mibase.Textura1);
		btirmenunivel = new Imagen(mibase, "botones/menu.png", mibase.Textura1);
		btAyuda = new Imagen(mibase, "botones/ayuda.png", mibase.Textura1);
		i_siguientenivel = new Imagen(mibase, "botones/siguientenivel.png",
				mibase.Textura1);
		// ==============
		btpausa = new Imagen(mibase, "botones/pausa.png", mibase.Textura1);
		// boton de menu de sonido
		i_aceptar = new Imagen(mibase, "botones/aceptar.png", mibase.Textura1);
		i_adelante = new Imagen(mibase, "botones/adelante.png",
				mibase.Textura1);
		i_cancelar = new Imagen(mibase, "botones/cancelar.png",
				mibase.Textura1);
		btatras = new Imagen(this.mibase, "botones/bt_atras.png",
				mibase.Textura1);
		btsonido = new Imagen(this.mibase, "botones/sonido.png", 1, 2,
				mibase.Textura1);

		// /cargar imagenes de wingameover
		i_charapitallorando = new Imagen(mibase,
				"wingameover/charapallorando.png", mibase.Textura1);
		i_libelulaconcharapa = new Imagen(mibase, "wingameover/libelulaconcharapa.png",
				mibase.Textura1);

		// /===Item2==
		// /cargar imagenes de wingameover
		// ===========Game Over y WIn=========
		i_hombre = new Imagen(mibase, "wingameover/hombre.png", mibase.Textura2);
		i_charapitaalegre = new Imagen(mibase, "wingameover/charapaalegre.png",
				mibase.Textura2);
		i_fondobotones = new Imagen(mibase, "wingameover/fondoperdiste.png",
				mibase.Textura2);
		i_nivelcompleto = new Imagen(mibase, "wingameover/nivelcompleto.png",
				mibase.Textura2);
		i_perdiste = new Imagen(mibase, "wingameover/perdiste.png",
				mibase.Textura2);

		// cargar imagenes de efectos
		polvo = new Imagen(mibase, "efectos/polvo.png", 1, 4, mibase.Textura2);
		destello = new Imagen(mibase, "efectos/destello.png", 1, 12,
				mibase.Textura2);
		explotaLibelula = new Imagen(mibase, "efectos/explotalibelula.png", 1,
				4, mibase.Textura2);

		// cargar imagen gavilan
		gavicuerpo = new Imagen(mibase, "gavilan/cuerpo.png", mibase.Textura2);
		gavicola = new Imagen(mibase, "gavilan/cola.png", mibase.Textura2);
		gavialapos = new Imagen(mibase, "gavilan/alapo.png", 1, 2,
				mibase.Textura2);
		gavialasup = new Imagen(mibase, "gavilan/alas.png", 1, 2,
				mibase.Textura2);
		gavicabeza = new Imagen(mibase, "gavilan/cabeza.png", 1, 2,
				mibase.Textura2);

		// /===Item3==


		// Cargar Imagen de fondo1
		/*n1fondo4 = new Imagen(mibase, "fondo1/fondo4.png", mibase.Textura3);
		n1fondo2 = new Imagen(mibase, "fondo1/fondo2.png", mibase.Textura3);		
		n1fondo5 = new Imagen(mibase, "fondo1/fondo5.png", mibase.Textura3);
		n1nube1 = new Imagen(mibase, "fondo1/nube1.png", mibase.Textura3);
		n1nube2 = new Imagen(mibase, "fondo1/nube2.png", mibase.Textura3);
		n1nube3 = new Imagen(mibase, "fondo1/nube3.png", mibase.Textura3);
		n1piso = new Imagen(mibase, "piso/piso.png", mibase.Textura3);
		n1piso2 = new Imagen(mibase, "piso/pisob.png", mibase.Textura3);
		n1fondo3 = new Imagen(mibase, "fondo1/fondo3.png", mibase.Textura4);*/
		
		//fondooooooooooooooooos
		 ele[0]=new Imagen(mibase, "tileds/ele0.png", mibase.Textura3);
		 for(int i=1;i<11;i++)
		 {
			 ele[i]=new Imagen(mibase, "tileds/ele"+i+".png", mibase.Textura5);
		 }
		 ele[22]=new Imagen(mibase, "tileds/ele22.png", mibase.Textura5);
		 for(int i=11;i<22;i++)
		 {
			 ele[i]=new Imagen(mibase, "tileds/ele"+i+".png", mibase.Textura3);
		 }
		 ele[23]=new Imagen(mibase, "tileds/ele23.png", mibase.Textura3);
		 ele[24]=new Imagen(mibase, "tileds/ele24.png", mibase.Textura3);
		 ele[25]=new Imagen(mibase, "tileds/ele25.png", mibase.Textura3);
		 ele[26]=new Imagen(mibase, "tileds/ele26.png", mibase.Textura3);
		 ele[27]=new Imagen(mibase, "tileds/ele27.png", mibase.Textura3);
		 ele[28]=new Imagen(mibase, "tileds/ele28.png", mibase.Textura3);
		 ele[29]=new Imagen(mibase, "tileds/ele29.png", mibase.Textura3);
		 ele[30]=new Imagen(mibase, "tileds/ele30.png", mibase.Textura3);
		 // ===Item4==		
		//Cargar Imagen de lanzador
		lanzador = new Imagen(mibase, "lanzador/lanzado.png", 8, 1,
				mibase.Textura4);
		
		i_nescore=new Imagen(mibase, "wingameover/newscore.png", mibase.Textura5);

		
		try {
			this.mibase.Textura1.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			this.mibase.Textura2.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			this.mibase.Textura3.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			this.mibase.Textura4.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			this.mibase.Textura5.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));

		} catch (final TextureAtlasSourcePackingException e) {
			Debug.e(e);
		}
		
		this.mibase.miengine.getTextureManager().loadTextures(mibase.Textura1,
				mibase.Textura2, mibase.Textura3,mibase.Textura4,mibase.Textura5,mibase.TextureScore,mibase.TexturaPuntos,mibase.TexturaPuntosNiveles);
		
	}
	public void CargarMenu() {
		//this.mibase.Textura1.clearTextureAtlasSources();
		this.mibase.TexturaPuntosNiveles.clearTextureAtlasSources();
		this.mibase.miengine.getTextureManager().unloadTextures(mibase.Textura1,mibase.Textura2, mibase.Textura3,mibase.Textura4,mibase.Textura5,mibase.TextureScore,mibase.TexturaPuntos,mibase.TexturaPuntosNiveles,mibase.TexturaAyudas);
		//this.mibase.miengine.getTextureManager().reloadTextures();
		
		btiniciar = new Imagen(this.mibase, "menu/btiniciarjuego.png",
				mibase.Textura1);
		btcreditos = new Imagen(this.mibase, "menu/btcreditos.png", mibase.Textura1);
		//btpunto = new Imagen(this.mibase, "menu/btpuntos.png", mibase.Textura1);
		btsalir = new Imagen(this.mibase, "menu/btsalir.png", mibase.Textura1);
		
		botonNiveles=new Imagen(this.mibase, "menu/botonnivel.png",1,2,mibase.Textura1);
		
		fondoactivarsonido = new Imagen(mibase, "menu/fondoactivarsonido.png",
				mibase.Textura1);
		
		i_aceptar = new Imagen(mibase, "botones/aceptar.png", mibase.Textura1);


		i_cancelar = new Imagen(mibase, "botones/cancelar.png",
						mibase.Textura1);

			btatras = new Imagen(this.mibase, "botones/bt_atras.png",
						mibase.Textura1);
				btsonido = new Imagen(this.mibase, "botones/sonido.png", 1, 2,
						mibase.Textura1);
				
		i_creditos = new Imagen(mibase, "menu/credito.png",
						mibase.Textura2);
		
		i_numeroniveles=new Imagen(mibase, "menu/nroniveles.png",9,1, mibase.Textura2);
		
		fondomenup=new Imagen(mibase, "menu/bg_menup.png", mibase.Textura2);
		bgselecionnivel=new Imagen(mibase, "menu/bg_selecionnivel.png", mibase.Textura2);
		

			try {
			this.mibase.Textura1.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			this.mibase.Textura2.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
			//this.mibase.Textura3.build(new BlackPawnTextureBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(1));
		} catch (final TextureAtlasSourcePackingException e) {
			Debug.e(e);
		}
		
		
		this.mibase.miengine.getTextureManager().loadTextures(mibase.Textura1,mibase.Textura2,mibase.TexturaPuntosNiveles);
		
	}
	public void CargarAyuda(){
		
		i_Ayudas = new Imagen(mibase, "ayuda/ayuda1.png",
				mibase.TexturaAyudas);
		mibase.menus.estdoayuda=1;
		
		this.mibase.miengine.getTextureManager().loadTexture(mibase.TexturaAyudas);

	}
	public void CargarSiguienteAyuda(String numeroayuda){
		
		mibase.TexturaAyudas.clearTextureAtlasSources();
		//i_Ayudas = new Imagen(mibase, "ayuda/ayuda"+numeroayuda+".png",	mibase.TexturaAyudas);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(mibase.TexturaAyudas, mibase, "ayuda/ayuda"+numeroayuda+".png", 0, 0);

	}

}
