package iquiplay.motor;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;



public class Imagen {
	public Principal mibase;
	public TiledTextureRegion ImagenAnimada;
	public TextureRegion ImagenSimple;
	
	public Imagen(Principal mibase,String imagen,BitmapTextureAtlas bitmap)
	{
		ImagenSimple=BitmapTextureAtlasTextureRegionFactory.createFromAsset(bitmap, mibase, imagen, 0, 0);
		this.ImagenAnimada=null;
	}	

	public Imagen(Principal mibase,String imagen,BuildableBitmapTextureAtlas buildable)
	{
		ImagenSimple=BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildable, mibase,imagen);
		this.ImagenAnimada=null;
	}
	public Imagen(Principal mibase,String imagen,int filas,int columnas,BuildableBitmapTextureAtlas buildable)
	{
		ImagenAnimada=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildable, mibase,imagen,columnas,filas);
		this.ImagenSimple=null;
	}
	

}
