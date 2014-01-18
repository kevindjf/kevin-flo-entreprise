package com.snapyou.utils;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapDecoder {

	public static Bitmap getBitmap(String filePath){
		Bitmap photo = null;
		
		try{
		if(filePath != null && !filePath.equals("")){
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;

			BitmapFactory.decodeFile(filePath,o);

			//The new size we want to scale to
			final int REQUIRED_WIDTH=1000;
			final int REQUIRED_HIGHT=1000;
			//Find the correct scale value. It should be the power of 2.
			int scale=1;
			while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
				scale*=2;

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize=scale;

			photo = BitmapFactory.decodeFile(filePath,o2);
			photo = ImageResizer.applyOrientation(photo,new File(filePath));

		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return photo;
	}
	
	
}
