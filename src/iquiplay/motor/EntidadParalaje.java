package iquiplay.motor;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.IShape;

public class EntidadParalaje {
	final float mParallaxFactor;
	final IShape mAreaShape;
	final boolean mIsScrollable;

	final float shapeWidthScaled;

	// ===========================================================
	// Constructors
	// ===========================================================

	public EntidadParalaje(final float pParallaxFactor, final IShape pAreaShape) {
		this.mParallaxFactor = pParallaxFactor;
		this.mAreaShape = pAreaShape;
		this.mIsScrollable = false;
		shapeWidthScaled = this.mAreaShape.getWidthScaled();
	}
	
	public EntidadParalaje(final float pParallaxFactor, final IShape pAreaShape, final boolean mIsScrollable) {
		this.mParallaxFactor = pParallaxFactor;
		this.mAreaShape = pAreaShape;
		this.mIsScrollable = mIsScrollable;
		shapeWidthScaled = this.mAreaShape.getWidthScaled();
	}
	
	public EntidadParalaje(final float pParallaxFactor, final IShape pAreaShape, final boolean mIsScrollable, final int mReduceFrequency) {
		this.mParallaxFactor = pParallaxFactor;
		this.mAreaShape = pAreaShape;
		this.mIsScrollable = mIsScrollable;
		shapeWidthScaled = this.mAreaShape.getWidthScaled() * mReduceFrequency;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void onDraw(final GL10 pGL, final Camera pCamera, final float pParallaxValue, final float mLevelWidth) {
		
		pGL.glPushMatrix();
		{
			float widthRange;
			
			if(mLevelWidth != 0){
				widthRange = mLevelWidth;
			} else {
				widthRange = pCamera.getWidth();
			}

			float baseOffset = (pParallaxValue * this.mParallaxFactor) % shapeWidthScaled;

			while(baseOffset > 0) {
				baseOffset -= shapeWidthScaled;
			}
			 pGL.glTranslatef(baseOffset, 0, 0);

			float currentMaxX = baseOffset;
			
			do {
				this.mAreaShape.onDraw(pGL, pCamera);
				pGL.glTranslatef(shapeWidthScaled - 1, 0, 0);
				currentMaxX += shapeWidthScaled;
			} while(currentMaxX < widthRange);
		}
		 pGL.glPopMatrix();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}

