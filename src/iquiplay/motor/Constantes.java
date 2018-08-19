package iquiplay.motor;

import com.badlogic.gdx.math.Vector2;


public class Constantes {
	///////////////Puntos de las coliciones
	//public final static int =500;
	
	//ide de las colisiones--------------------------------------------------
	public final static short charapavolando=1; 
	public final static short charapacaminando=2;
	public final static short charapacapturada=8;
	public final static short charapaliberada=9;
	public final static short huamarebote=10;
	public final static short mono=3;
	public final static short libelula=4;
	public final static short huama=5;
	public final static short piso=6;
	public final static short gavilan=7;
	public final static short platano=11;
	public final static short tronco=12;
	public final static short pisoFlotante=13;
	//-----------------------------------------------------------------------
	public static final int grupoMono = -1;
	//colores entre nosotros
	public static final short Verde = 0;
	public static final short Azul = 1;
	public static final short Amarillo = 2;
	//resistencia de libelulas
	public static final short resistencialibVerde = 10;
	public static final short resistencialibAzul = 20;
	public static final short resistencialibAmarilla = 30;
	//razon de gameover
	public static final short gameoverseacabaronlascharapas = 0;
	public static final short gameoverseacaboeltiempo = 1;
	//Razon Win
	public static final short winnivel = 2;
	//
	public static final int limitedecharapaabajo = 800;
	public static final int limitedecharapacostado = 2100;
	//

	public static int camara_ancho = 880;
	public static int camara_alto = 660;
	public static float escalamundo=10;
	public static Vector2 VecttoMundo(Vector2 pos) {
	    pos.x = pos.x/escalamundo;
	    pos.y =(camara_alto/escalamundo)-(pos.y/escalamundo);
	    return pos;
	}
	public static float valtoMundo(float val) {
		val=val/escalamundo;
	    return val; 
	}
	public static Vector2 Vecttoscreeen(Vector2 pos) {
	    pos.x = pos.x*escalamundo;
	    pos.y =camara_alto-(pos.y*escalamundo);
	    return pos;
	}

}
