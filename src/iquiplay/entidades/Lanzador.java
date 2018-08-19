package iquiplay.entidades;

import iquiplay.motor.Imagen;
import iquiplay.niveles.Nivel;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.MathUtils;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.FloatMath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

//nota: los booleanos ticks son para hacer una sola vez una acción en el evento "Alcorrer" y no se repita muchas veces(se hacen false y listo)
public class Lanzador {
	// ========================
	public Imagen lanzador;
	public Imagen piedra;

	// ======================

	public int numerocharapas, nlanzamiento;
	ControlDisparo micontrol;
	boolean tick = true, tick2 = true, boolSalto = true;
	float x, y, l1 = 0, l2 = 1.30f, consSalto = 0, factorFoto = 188 / 7, dif,
			consCamina;
	int fotResorte;
	Sprite sPiedra;
	public AnimatedSprite sLanzador;
	AnimatedSprite sCharaEspera, sCharapaSalto, sCharacorrers;
	public Body bLanzador, bPiedra;
	public Body[] bCharapas;
	Nivel minivel;
	PhysicsConnector[] conectorescharapas;
	private float fac = 0.15f;

	public Imagen charapitaEspera;
	public Imagen charapaSalto;
	public Imagen charapitaCorre;

	public Lanzador(Nivel nivel, ControlDisparo micontrol, int numerocharapas) {
		this.minivel = nivel;
		this.numerocharapas = numerocharapas;
		this.micontrol = micontrol;
		bCharapas = new Body[numerocharapas];
		conectorescharapas = new PhysicsConnector[numerocharapas];
	}

	public void alPintar(float x, float y) {
		this.x = x;
		this.y = y;
		sPiedra = new Sprite(x, y, 90, 71,
				minivel.mibase.imagenes.piedra.ImagenSimple);
		sLanzador = new AnimatedSprite(x + 45, y - 50, 179.2f, 89.6f,
				minivel.mibase.imagenes.lanzador.ImagenAnimada) {

			@Override
			public boolean onAreaTouched(TouchEvent e, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				minivel.mibase.miMouse = new Vector2(e.getX()
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
						e.getY()
								/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
				Body body = null;
				if (e.isActionDown() && minivel.mibase.activarManipular) {
					body = bLanzador;// minivel.mibase.GetBodyAtMouse(true,
										// e.getX(), e.getY());
					if (body != null) {
						MouseJointDef mouse_joint = new MouseJointDef();
						minivel.mibase.mGroundBody.setTransform(
								minivel.mibase.miMouse, 0);
						mouse_joint.bodyA = minivel.mibase.mGroundBody;
						mouse_joint.bodyB = body;
						mouse_joint.target.set(minivel.mibase.miMouse.x,
								minivel.mibase.miMouse.y);
						mouse_joint.maxForce = 40000;
						minivel.mibase.mouseJoint = (MouseJoint) minivel.mibase.FisicaAtlas
								.createJoint(mouse_joint);
					}
				}
				if (e.isActionMove()) {
					if (minivel.mibase.mMouseJointActive != null) {
						minivel.mibase.mMouseJointActive
								.setTarget(minivel.mibase.miMouse);
					}
				}
				if (e.isActionUp()) {
					minivel.mibase.eliminarUnionMouse();
					bLanzador.setAwake(false);
				}
				return true;
			}

		};
		minivel.mibase.escena.registerTouchArea(sLanzador);
		sCharaEspera = new AnimatedSprite(x + 110, y + 20,
				minivel.mibase.imagenes.charapitaEspera.ImagenAnimada);
		sCharapaSalto = new AnimatedSprite(x + 110, y + 20,
				minivel.mibase.imagenes.charapaSalto.ImagenAnimada);
		sCharacorrers = new AnimatedSprite(-50, y + 20,
				minivel.mibase.imagenes.charapitaCorre.ImagenAnimada);
		minivel.mibase.escena.attachChild(sLanzador);
		minivel.mibase.escena.attachChild(sPiedra);
		minivel.mibase.escena.attachChild(sCharaEspera);
		minivel.mibase.escena.attachChild(sCharapaSalto);
		minivel.mibase.escena.attachChild(sCharacorrers);
		sCharacorrers.animate(100);
		sCharaEspera.setVisible(false);
		sCharapaSalto.setVisible(false);
		cargarFisica();
	}

	public void cargarFisica() {
		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(10,
				0.2f, 0.5f);
		bLanzador = PhysicsFactory.createBoxBody(minivel.mibase.FisicaAtlas,
				sLanzador, BodyType.DynamicBody, objectFixtureDef);
		bLanzador.getFixtureList().get(0).setSensor(true);
		bPiedra = PhysicsFactory.createBoxBody(minivel.mibase.FisicaAtlas,
				sPiedra, BodyType.StaticBody, objectFixtureDef);
		bPiedra.getFixtureList().get(0).setSensor(true);
		minivel.mibase.FisicaAtlas
				.registerPhysicsConnector(new PhysicsConnector(sPiedra,
						bPiedra, true, true));
		minivel.mibase.FisicaAtlas
				.registerPhysicsConnector(new PhysicsConnector(sLanzador,
						bLanzador, true, true));
		final RevoluteJointDef union = new RevoluteJointDef();
		union.initialize(bPiedra, bLanzador, bPiedra.getWorldCenter());
		union.enableMotor = true;
		union.motorSpeed = 0;
		// union.maxMotorTorque=10000;
		union.collideConnected = false;
		union.enableLimit = true;
		union.upperAngle = 0;
		union.lowerAngle = MathUtils.degToRad(-70);
		minivel.mibase.FisicaAtlas.createJoint(union);
		minivel.mibase.Manipular(bLanzador);
	}

	public void Animacionsalto() {
		if (consSalto > 7) {
			boolSalto = false;
		}
		if (boolSalto) {
			consSalto += 0.5;
		} else {
			consSalto -= 0.5;
		}
		sCharapaSalto.setPosition(sCharapaSalto.getX(), sCharapaSalto.getY()
				- consSalto);
	}

	public void introCharapa() {
		if (minivel.numerocharapascapturadas != 0) {
			if (nlanzamiento < numerocharapas) {
				sLanzador.setCurrentTileIndex(7);
				if (sCharacorrers.getX() < x + 110) {
					minivel.mibase.activarManipular = false;
					minivel.mibase.eliminarUnionMouse();
					sCharacorrers
							.setPosition(sCharacorrers.getX() + 5f, y + 20);
				} else {
					if (tick) {
						sCharacorrers.setVisible(false);
						sCharaEspera.setVisible(true);
						sCharaEspera.animate(100);
						minivel.mibase.activarManipular = false;
						bLanzador.setActive(true);
						bLanzador.setAngularVelocity(30);
					}
					tick = false;
				}
				if (sCharaEspera.isVisible()
						&& sCharaEspera.getCurrentTileIndex() == 4) {
					sCharaEspera.stopAnimation();
					if (tick2) {
						sCharaEspera.setVisible(false);
						sCharapaSalto.setVisible(true);
						sCharapaSalto.animate(50);
					}
					tick2 = false;
				}
				if (sCharapaSalto.isVisible()
						&& sCharapaSalto.getCurrentTileIndex() == 4) {
					sCharapaSalto.stopAnimation();
					Animacionsalto();
					if (consSalto < 0 && sCharapaSalto.getY() > y - 50) {
						sCharapaSalto.setVisible(false);
						sCharapaSalto.setPosition(x + 110, y + 20);
						sCharacorrers.setPosition(-2000, y + 20);
						sCharacorrers.setVisible(false);
						tick = true;
						tick2 = true;
						activarLanzamiento();
					}
				}
			}
		}
	}

	public void activarLanzamiento() {
		minivel.envuelo = false;
		micontrol.bloqueo = false;
		minivel.mibase.activarManipular = true;
		sLanzador.setCurrentTileIndex(0);
		minivel.preparandovuelo = true;
	}

	public void lanzamiento() {
		if (!minivel.envuelo && minivel.preparandovuelo) {
			efectoResorte();
			if (micontrol.solto) {

				sLanzador.setCurrentTileIndex(7);
				if (tick) {
					minivel.rastrocharapa.quitarRastro();
					LanzarCharapa();
					tick = false;
					minivel.reiniciarTurno();
				}
			}
		}
	}

	public void efectoResorte() {
		fotResorte = Math.round(micontrol.poder / factorFoto) - 1;
		if (fotResorte >= 0 && fotResorte < 7) {
			sLanzador.setCurrentTileIndex(fotResorte);
		}
	}

	public void LanzarCharapa() {
		if (nlanzamiento < numerocharapas) {
			minivel.mibase.sonidos.lanzar.play();
			if (micontrol.poder > 0 && micontrol.poder <= micontrol.limite / 3) {
				minivel.mibase.sonidos.getGritoCorto().play();
			}
			if (micontrol.poder > micontrol.limite / 3
					&& micontrol.poder <= 2 * micontrol.limite / 3) {
				minivel.mibase.sonidos.getGritoMedio().play();
			}
			if (micontrol.poder > 2 * micontrol.limite / 3
					&& micontrol.poder <= micontrol.limite + 10) {
				minivel.mibase.sonidos.getGritoLargo().play();
			}
			Vector2 mivelocidad = new Vector2(micontrol.poder * fac
					* (float) FloatMath.cos(bLanzador.getAngle()),
					micontrol.poder * fac
							* (float) FloatMath.sin(bLanzador.getAngle()));
			minivel.charapitas[nlanzamiento] = new Charapa(minivel, false);
			Vector2 pos = new Vector2(sLanzador.getX() + 100,
					sLanzador.getY() + 27);// ///////////////////////////////////////////////////////
			minivel.charapitas[nlanzamiento].CrearCharapafisica(pos,
					mivelocidad, bLanzador.getAngle());
			mivelocidad = null;
			nlanzamiento++;
			minivel.datosHud.dibujarTextoCantidadCharapa();
			minivel.envuelo = true;
			minivel.preparandovuelo = false;

			if (micontrol.solto) {
				minivel.mibase.camara.tocarcamara = false;
			}

			// para la camara
			minivel.mibase.camara.seguirobjeto = true;
			minivel.mibase.camara.tocarcamara = false;
		}
	}

	public void reiniciartiro() {
		tick = true;
		tick2 = true;
		boolSalto = true;
		sCharapaSalto.reset();
		sCharaEspera.reset();
		sCharacorrers.reset();
		sCharaEspera.setCurrentTileIndex(0);
		sCharapaSalto.setCurrentTileIndex(0);
		consSalto = 0;
		sCharaEspera.setVisible(false);
		sCharapaSalto.setVisible(false);
		sCharacorrers.setVisible(true);
	}

	public void alCorrer() {
		if (minivel.preparandovuelo) {
			lanzamiento();
		} else {
			introCharapa();
		}
	}

}
