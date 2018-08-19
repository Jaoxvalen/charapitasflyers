package iquiplay.motor;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.Entity;

public class BucleJuego implements IUpdateHandler {
	Principal mibase;
	public BucleJuego(Principal mibase)
	{
		this.mibase=mibase;
	}
	@Override
	public void onUpdate(float arg0) {
		for(Entity deta:mibase.basurero)
		{
			deta.detachSelf();
		}
		mibase.basurero.clear();
		if (mibase.mouseJoint!=null) {
			mibase.mouseJoint.setTarget(mibase.miMouse);
		}else{					
			mibase.camara.Update();
		}
		mibase.AlCorrer();
	}
	@Override
	public void reset() {
		mibase.AlReiniciar();
		
	}

}
