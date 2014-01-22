package com.snapyou.utils;

import java.io.File;
import java.io.FileInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

public class BitmapDecoder {

	public static Bitmap getBitmap(String filePath){
		System.gc();	
		//return getBitmapFromBitmapFactory(filePath);
		return decodeFileOriented(filePath);
	}

	private static Bitmap getBitmapFromBitmapFactory(String filePath){

		Bitmap photo = null;

		try{
			if(filePath != null && !filePath.equals("")){
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;

				BitmapFactory.decodeFile(filePath,o);

				//The new size we want to scale to
				final int REQUIRED_WIDTH=500;
				final int REQUIRED_HIGHT=500;
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

	private static Bitmap getBitmapSampleSize(String filePath){
		Bitmap bitmap = null;

		try{
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true; 
			FileInputStream fis = new FileInputStream(new File(filePath));
			BitmapFactory.decodeStream(fis, null, o);
			fis.close();
			final int REQUIRED_SIZE=500;
			int width_tmp=o.outWidth, height_tmp=o.outHeight;
			int scale=1;
			while(true){
				if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
					break;
				width_tmp/=2;
				height_tmp/=2;
				scale*=2;
			}
			BitmapFactory.Options op = new BitmapFactory.Options();
			op.inSampleSize = scale;
			fis = new FileInputStream(new File(filePath));
			bitmap = BitmapFactory.decodeStream(fis, null, op); 
			fis.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return bitmap;
	}
	
	public static Bitmap applyOrientation(Bitmap bitmap,String filePath) {
		try{
			
			System.gc();
			
			ExifInterface exif = null;
			exif = new ExifInterface(filePath);

			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			int rotate = 0;
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_270:
				rotate = 270;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				rotate = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_90:
				rotate = 90;
				break;
			default:
				return bitmap;
			}

			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			Matrix mtx = new Matrix();
			mtx.postRotate(rotate);
			Bitmap  bm_rotated = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
			bitmap.recycle();
			bitmap = null;
			System.gc();
			
		    return bm_rotated;
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		return bitmap;
	}
	
	public static Bitmap decodeFileOriented(String path) {
	    int orientation;
	    try {
	        if (path == null) {
	            return null;
	        }
	        // decode image size
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        // Find the correct scale value. It should be the power of 2.
	        
	        final int REQUIRED_SIZE = 1000;
	       
	        int width_tmp = o.outWidth, height_tmp = o.outHeight;
	        int scale = 1;
	        while (true) {
	            if (width_tmp / 2 < REQUIRED_SIZE
	                    || height_tmp / 2 < REQUIRED_SIZE)
	                break;
	            width_tmp /= 2;
	            height_tmp /= 2;
	            scale++;
	        }
	        // decode with inSampleSize
	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize = scale;
	        Bitmap bm = BitmapFactory.decodeFile(path, o2);
	        Bitmap bitmap = bm;

	        ExifInterface exif = new ExifInterface(path);
	        orientation = exif
	                .getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
	        Log.e("orientation", "" + orientation);
	        Matrix m = new Matrix();

	        if ((orientation == 3)) {
	            m.postRotate(180);
	            m.postScale((float) bm.getWidth(), (float) bm.getHeight());
	            // if(m.preRotate(90)){
	            Log.e("in orientation", "" + orientation);
	            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
	                    bm.getHeight(), m, true);
	            return bitmap;
	        } else if (orientation == 6) {
	            m.postRotate(90);
	            Log.e("in orientation", "" + orientation);
	            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
	                    bm.getHeight(), m, true);
	            return bitmap;
	        }
	        else if (orientation == 8) {
	            m.postRotate(270);
	            Log.e("in orientation", "" + orientation);
	            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
	                    bm.getHeight(), m, true);
	            return bitmap;
	        }
	        return bitmap;
	    } catch (Exception e) {
	    }
	    return null;
	}

}
