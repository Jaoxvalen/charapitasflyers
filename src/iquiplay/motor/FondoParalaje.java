package iquiplay.motor;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.Entity;



public class FondoParalaje  extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<EntidadParalaje> mParallaxEntities = new ArrayList<EntidadParalaje>();
	private int mParallaxEntityCount;

	protected float mParallaxValue;
	protected float mParallaxScrollValue;
	
	protected float mParallaxChangePerSecond;
	
	protected float mParallaxScrollFactor = 0.2f;
	
	private Camera mCamera;
	
	private float mCameraPreviousX;
	private float mCameraOffsetX;
	
	private float	mLevelWidth = 0;
	
	private boolean mIsScrollable = false;

	
	// ===========================================================
	// Constructors
	// ===========================================================
	public FondoParalaje() {
	}

	public FondoParalaje(final Camera camera, final boolean mIsScrollable){
		this.mCamera = camera;
		this.mIsScrollable = mIsScrollable;
		
		mCameraPreviousX = camera.getCenterX();
	}
	
	public FondoParalaje(final Camera camera, final boolean mIsScrollable, final int mLevelWidth){
		this.mCamera = camera;
		this.mIsScrollable = mIsScrollable;
		this.mLevelWidth = mLevelWidth;
		
		mCameraPreviousX = camera.getCenterX();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setParallaxValue(final float pParallaxValue) {
		this.mParallaxValue = pParallaxValue;
	}
	
	public void setParallaxChangePerSecond(final float pParallaxChangePerSecond) {
		this.mParallaxChangePerSecond = pParallaxChangePerSecond;
	}

	public void setParallaxScrollFactor(final float pParallaxScrollFactor){
		this.mParallaxScrollFactor = pParallaxScrollFactor;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void onManagedDraw(GL10 pGL, Camera pCamera) {
		//super.onDraw(pGL, pCamera);

		
		final float parallaxValue = this.mParallaxValue;
		final float parallaxScrollValue = this.mParallaxScrollValue;
		final ArrayList<EntidadParalaje> parallaxEntities = this.mParallaxEntities;

		for(int i = 0; i < this.mParallaxEntityCount; i++) {
			if(parallaxEntities.get(i).mIsScrollable){
				parallaxEntities.get(i).onDraw(pGL, pCamera, parallaxScrollValue, mLevelWidth);
			} else {
				parallaxEntities.get(i).onDraw(pGL, pCamera, parallaxValue, mLevelWidth);
			}

		}
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		
		if(mIsScrollable && mCameraPreviousX != this.mCamera.getCenterX()){
				mCameraOffsetX = mCameraPreviousX - this.mCamera.getCenterX();
				mCameraPreviousX = this.mCamera.getCenterX();
				
				this.mParallaxScrollValue += mCameraOffsetX * this.mParallaxScrollFactor;
				mCameraOffsetX = 0;
		}
		
		this.mParallaxValue += this.mParallaxChangePerSecond * pSecondsElapsed;
		super.onManagedUpdate(pSecondsElapsed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void attachParallaxEntity(final EntidadParalaje parallaxEntity) {
		this.mParallaxEntities.add(parallaxEntity);
		this.mParallaxEntityCount++;
	}

	public boolean detachParallaxEntity(final EntidadParalaje pParallaxEntity) {
		this.mParallaxEntityCount--;
		final boolean success = this.mParallaxEntities.remove(pParallaxEntity);
		if(!success) {
			this.mParallaxEntityCount++;
		}
		return success;
	}
}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	
