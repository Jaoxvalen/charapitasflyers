package iquiplay.entidades;

import iquiplay.niveles.Nivel;

import java.util.ArrayList;

import org.anddev.andengine.entity.primitive.Rectangle;

public class Rastro {
	ArrayList<Rectangle> rastros=new ArrayList<Rectangle>();
	Nivel minivel;
	int espacio;
	public Rastro(Nivel minivel,int espacio)
	{
		this.minivel=minivel;
		this.espacio=espacio;
	}
	public void dejarRastro(float x, float y,float angle)
	{
		Rectangle rastro=new Rectangle(x, y, 4, 4);
		rastro.setRotation(angle);
		rastro.setColor(1,1,1);
		if(rastros.size()!=0)
		{
			if(Math.sqrt(Math.pow(rastros.get(rastros.size()-1).getX()-x,2)+Math.pow(rastros.get(rastros.size()-1).getY()-y,2))>espacio)
			{
				rastros.add(rastro);
				minivel.mibase.escena.attachChild(rastro);
			}
		}
		else
		{
			rastros.add(rastro);
			minivel.mibase.escena.attachChild(rastro);
		}
	}
	public void quitarRastro()
	{
		for(Rectangle mirectangulo:rastros)
		{
			minivel.mibase.basurero.add(mirectangulo);
		}
		rastros.clear();
	}
}
