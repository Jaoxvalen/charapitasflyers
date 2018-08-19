package iquiplay.motor;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;



public class AdminColision implements ContactListener{
	Principal mibase;
	public AdminColision(Principal mibase)
	{
		this.mibase=mibase;
	}

	@Override
	public synchronized void beginContact(Contact point) {
		try
		{
			ColisionEnte miente1=null;
			ColisionEnte miente2=null;
			if(point!=null)
			{
				if(point.getFixtureA()!=null)
				{
					if( point.getFixtureA().getBody()!=null)
					 miente1= (ColisionEnte) point.getFixtureA().getBody().getUserData();
				}
				
				if(point.getFixtureB()!=null)
				{
					if( point.getFixtureB().getBody()!=null)
					 miente2= (ColisionEnte) point.getFixtureB().getBody().getUserData();
				}
				
			}

			if(miente1!=null && miente2!=null)
			{
				if(miente2.getIdcolision()!=0)
				{
					 
					 miente1.colisionEfecto(miente2,point.getFixtureB().getBody());
				}
				if(miente1.getIdcolision()!=0)
				{
					 miente2.colisionEfecto(miente1,point.getFixtureA().getBody());
				}
			}
		}
		catch (Exception e) {
			//Log.e("colision", e.getMessage());
		}

	}
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
