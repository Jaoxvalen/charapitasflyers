package iquiplay.motor;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.util.MathUtils;

import android.util.Log;

public class ControlSonido {
	public Principal mibase;
	public Music musicamenu;
	public Sonidos golpecharapa;
	public Sonidos[] gritoscortos = new Sonidos[3], gritoslargos = new Sonidos[2],
			gritosmedios = new Sonidos[3];
	public Sonidos angulo;
	public Sonidos resorte;
	public Sonidos lanzamiento;
	public Sonidos destello;
	public Sonidos trampolin;
	public Sonidos risalibelula;
	public Sonidos golpemadera;
	public Sonidos tick;
	public Sonidos golpelibelula;
	public Sonidos lanzadorgru;
	public Sonidos libelulaau;
	public Sonidos lanzar;
	public Sonidos golpegavilan;
	public Sonidos explosion;
	public Music[]temas=new Music[5];
	public Music[]fondo=new Music[4];
	public Sonidos lose,win;
	public Sonidos aleteo;
	public Music intro;
	public Music charaflydub;
	// gritoscharapa
	public ControlSonido(Principal base) {
		this.mibase = base;
		try {
			/*charaflydub=MusicFactory.createMusicFromAsset(this.mibase.getMusicManager(),
					this.mibase, "charaflydub.mp3");
	musicamenu.setLooping(true);*/	
			
			aleteo=new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "aleteo.mp3"));
			win=new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "winer.mp3"));
			lose=new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "lose.mp3"));
			explosion=new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "explosion.mp3"));
			golpegavilan=new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "gavilangolpe.mp3"));
			lanzar = new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "lanza.wav"));
			libelulaau = new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "aulibelula.mp3"));
			lanzadorgru =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "lanzador.mp3"));
			golpelibelula =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "golpelibelula.mp3"));
			tick =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "tick.mp3"));
			golpemadera =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "maderagolpe.mp3"));
			risalibelula =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "risalib.mp3"));
			trampolin =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "trampol.mp3"));
			destello =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "destello.mp3"));
			// musica munus
			RestarMusicMenus();
			
			///////////////////////////////////////////////////////////////////////////
			RestarMusicnivelevel();
			
			// golpe charapa
			golpecharapa = new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase, "golpe.mp3"));
			// gritos cortos
			gritoscortos[0] = new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gritocorto1.mp3"));
			gritoscortos[1] = new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gritocorto2.mp3"));
			gritoscortos[2] =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gritocorto3.mp3"));
			// gritos medios
			gritosmedios[0] =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gitomedio1.mp3"));
			gritosmedios[1] = new Sonidos(SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gitomedio2.mp3"));
			gritosmedios[2] =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gitomedio3.mp3"));
			// gritos largos
			gritoslargos[0] =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gritolargo1.mp3"));
			gritoslargos[1] =new Sonidos( SoundFactory.createSoundFromAsset(
					this.mibase.getSoundManager(), mibase,
					"gritoscharapa/gritolargo2.mp3"));	
			
			for(int i=0;i<fondo.length;i++)
			{
				fondo[i]=MusicFactory.createMusicFromAsset(
						this.mibase.miengine.getMusicManager(), mibase,
				"paisaje/paisaje"+i+".mp3");
				fondo[i].setLooping(true);	
			}
			
			charaflydub=MusicFactory.createMusicFromAsset(this.mibase.getMusicManager(),
					this.mibase, "charaflydub.mp3");
			charaflydub.setLooping(true);
			
		} catch (final IOException e) {
			Log.e("error sonido", " " + e);	
			e.printStackTrace();
		}
	}

	public Sonidos getGritoCorto() {
		short azar = (short) MathUtils.random(0, 2);
		return gritoscortos[azar];
	}

	public Sonidos getGritoMedio() {
		short azar = (short) MathUtils.random(0, 2);
		return gritosmedios[azar];
	}

	public Sonidos getGritoLargo() {
		short azar = (short) MathUtils.random(0, 1);
		return gritoslargos[azar];
	}

	public void RestarMusicMenus() {
		try {
			this.musicamenu = MusicFactory.createMusicFromAsset(
					this.mibase.miengine.getMusicManager(), mibase,
					"charapita.mp3");
			this.musicamenu.setLooping(true);
		} catch (final IOException e) {
			Log.e("error musica", " " + e);
		} finally {
			if(!Sonidos.silencio){
				//this.musicamenu.play();
			}
		}
	}
	public void RestarMusicnivelevel() {
		try {

			for(int i=0;i<temas.length;i++)
			{
				temas[i]=MusicFactory.createMusicFromAsset(this.mibase.getMusicManager(),this.mibase, "temas/tema"+i+".mid");
				temas[i].setLooping(true); 
			}
			charaflydub=MusicFactory
			.createMusicFromAsset(this.mibase.getMusicManager(),
					this.mibase, "charaflydub.mp3");
			charaflydub.setLooping(true);
			
			intro=MusicFactory.createMusicFromAsset(
					this.mibase.getMusicManager(), mibase, "intro.mid");
			intro.setLooping(true);
		} catch (final IOException e) {
			Log.e("error musica", " " + e);
		} finally {
			if(!Sonidos.silencio){
				//this.musicamenu.play();
			}
		}
	}
	


	public void PusarSonidoJuego() {
		for(int i=0;i<fondo.length;i++)
		{
			fondo[i].pause();
		}
		aleteo.pause();
		win.pause();
		lanzar.pause();
		libelulaau.pause();
		lanzadorgru.pause();
		golpelibelula.pause();
		tick.pause();
		golpemadera.pause();
		risalibelula.pause();
		trampolin.pause();
		destello.pause();
		// golpe charapa
		golpecharapa.pause();
		// gritos cortos
		gritoscortos[0].pause();
		gritoscortos[1].pause();
		gritoscortos[2].pause();
		// gritos medios
		gritosmedios[0].pause();
		gritosmedios[1].pause();
		gritosmedios[2].pause();
		// gritos largos
		gritoslargos[0].pause();
		gritoslargos[1].pause();
		for(int i=0;i<temas.length;i++)
		{
			temas[i].pause();
		}
		charaflydub.pause();
	}

	public void ReanudarSonidoJuego() {
		lanzadorgru.resume();
		lanzar.resume();
		libelulaau.resume();
		win.resume();
		aleteo.resume();
		
		for(int i=0;i<fondo.length;i++)
		{
			fondo[i].resume();
		}
		charaflydub.resume();
		golpelibelula.resume();
		tick.resume();
		golpemadera.resume();
		risalibelula.resume();
		trampolin.resume();
		destello.resume();
		// golpe charapa
		golpecharapa.resume();
		// gritos cortos
		gritoscortos[0].resume();
		gritoscortos[1].resume();
		gritoscortos[2].resume();
		// gritos medios
		gritosmedios[0].resume();
		gritosmedios[1].resume();
		gritosmedios[2].resume();
		// gritos largos
		gritoslargos[0].resume();
		gritoslargos[1].resume();
		for(int i=0;i<temas.length;i++)
		{
			temas[i].play();
		}
	}
	
	public void VolumenAll(float volumen){
		
		for(int i=0;i<fondo.length;i++)
		{
			fondo[i].setVolume(volumen);
		}
		aleteo.silenciar(volumen);
		win.silenciar(volumen);
		lanzadorgru.silenciar(volumen);
		lanzar.silenciar(volumen);
		libelulaau.silenciar(volumen);
		charaflydub.setVolume(volumen);
		golpelibelula.silenciar(volumen);
		tick.silenciar(volumen);
		golpemadera.silenciar(volumen);
		risalibelula.silenciar(volumen);
		trampolin.silenciar(volumen);
		destello.silenciar(volumen);
		// golpe charapa
		golpecharapa.silenciar(volumen);
		// gritos cortos
		gritoscortos[0].silenciar(volumen);
		gritoscortos[1].silenciar(volumen);
		gritoscortos[2].silenciar(volumen);
		// gritos medios
		gritosmedios[0].silenciar(volumen);
		gritosmedios[1].silenciar(volumen);
		gritosmedios[2].silenciar(volumen);
		// gritos largos
		gritoslargos[0].silenciar(volumen);
		gritoslargos[1].silenciar(volumen);
		for(int i=0;i<temas.length;i++)
		{
			if(volumen>0){
			temas[i].setVolume(0.3f);
			}else{
				temas[i].setVolume(volumen);
			}
		}
	}
	
	public void ReiniciarSonidos(){
		
		for(int i=0;i<fondo.length;i++)
		{
			fondo[i].stop();
		}
		charaflydub.stop();
		win.stop();
		lanzadorgru.stop();
		lanzar.stop();
		libelulaau.stop();
		aleteo.stop();
		golpelibelula.stop();
		tick.stop();
		golpemadera.stop();
		risalibelula.stop();
		trampolin.stop();
		destello.stop();
		// golpe charapa
		golpecharapa.stop();
		// gritos cortos
		gritoscortos[0].stop();
		gritoscortos[1].stop();
		gritoscortos[2].stop();
		// gritos medios
		gritosmedios[0].stop();
		gritosmedios[1].stop();
		gritosmedios[2].stop();
		// gritos largos
		gritoslargos[0].stop();
		gritoslargos[1].stop();
		for(int i=0;i<temas.length;i++)
		{
			temas[i].stop();
		}
		RestarMusicnivelevel();

	}
}
