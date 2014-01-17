package com.snapyou.rest;

import java.io.ByteArrayOutputStream;

import org.codegist.crest.CRest;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;

public class EnvoyerPhotoTask extends AsyncTask<Object, Void, Void> {

	String key = null;
	Bitmap photo = null;
	
	public static byte[] compresser(Bitmap image){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, bos);
		return bos.toByteArray();
	}

	protected void envoyerPhoto(){
		CRest crest = CRest.getInstance();
		SnapYouService serviceRest = crest.build(SnapYouService.class);
		
		String response = serviceRest.ajouterPhoto(key, compresser(photo));
		System.out.println(response);
	}	

	@Override
	protected Void doInBackground(Object... params) {
		this.key = (String)params[0];
		this.photo = (Bitmap)params[1];

		envoyerPhoto();
		return null;
	}
}
