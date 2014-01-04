package fr.RivaMedia.AnnoncesAutoGenerique.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ImageColorChanger {

	public static Bitmap getBitmapFromView(View view) {
		//Define a bitmap with the same size as the view
		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
		//Bind a canvas to it
		Canvas canvas = new Canvas(returnedBitmap);
		//Get the view's background
		Drawable bgDrawable =view.getBackground();
		if (bgDrawable!=null) 
			//has background drawable, then draw it on the canvas
			bgDrawable.draw(canvas);
		else 
			//does not have background drawable, then draw white background on the canvas
			canvas.drawColor(Color.WHITE);
		// draw the view on the canvas
		view.draw(canvas);
		//return the bitmap
		return returnedBitmap;
	}

	public static Bitmap changerCouleurBitmap(Bitmap bitmap, int couleurAChanger, int nouvelleCouleur){
		int [] allpixels = new int [ bitmap.getHeight()*bitmap.getWidth()];
		Bitmap newBitmap = bitmap.copy(bitmap.getConfig(), true);

		bitmap.getPixels(allpixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),bitmap.getHeight());

		for(int i =0; i<bitmap.getHeight()*bitmap.getWidth();i++){

			if( allpixels[i] == couleurAChanger)
				allpixels[i] = nouvelleCouleur;
		}

		newBitmap.setPixels(allpixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		return newBitmap;
	}

	@SuppressWarnings("deprecation")
	public static void changerCouleursImage(View view, int couleurAChanger, int nouvelleCouleur){
		try{
			Bitmap bitmap = getBitmapFromView(view);
			changerCouleurBitmap(bitmap, couleurAChanger, nouvelleCouleur);
			view.setBackgroundDrawable(new BitmapDrawable(bitmap));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
