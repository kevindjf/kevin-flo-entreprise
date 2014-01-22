package com.snapyou;


import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.edmodo.cropper.CropImageView;
import com.snapyou.rest.EnvoyerPhotoTask;
import com.snapyou.utils.BitmapDecoder;
import com.snapyou.utils.ImageResizer;


public class MainActivity extends Activity implements View.OnClickListener{

	private String key = null;
	private Bitmap photo = null;

	View _ajouterImage;
	View _ajouterImageLayout;
	CropImageView _image;
	View _imageSupprimer;
	View _envoyer;

	public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1;
	private File _photoCamera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		charger();
		remplir();
		ajouterListeners();
		
	}

	private void charger(){
		_ajouterImage = findViewById(R.id.ajouter_photo);
		_envoyer = findViewById(R.id.envoyer);
		_ajouterImageLayout = findViewById(R.id.ajouter_photo_layout);
		_image = (CropImageView)findViewById(R.id.image);
		_imageSupprimer = findViewById(R.id.image_supprimer);
	}

	private void remplir(){
	}

	private void ajouterListeners(){
		_ajouterImage.setOnClickListener(this);
		_imageSupprimer.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.ajouter_photo){
			getPhotoFromCamera();
		}else if(v.getId() == R.id.envoyer){
			envoyer();
		}else if(v.getId() == R.id.image_supprimer){
			supprimerImage();
		}
	}

	public void getPhotoFromCamera(){
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		_photoCamera = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoCamera));
		startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
	}
	
	private void activerEnvoyer(){
		_envoyer.setBackgroundResource(R.drawable.cercle_rose_selector);
		_envoyer.setOnClickListener(this);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{ 
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode == Activity.RESULT_OK){  
			switch(requestCode){
			case CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE:
				if(_photoCamera != null && !_photoCamera.equals("")){
					this.photo = BitmapDecoder.getBitmap(_photoCamera.getAbsolutePath());
					_ajouterImageLayout.setVisibility(View.GONE);

					_image.setImageBitmap(photo);
					_image.setVisibility(View.VISIBLE);
					_imageSupprimer.setVisibility(View.VISIBLE);
					
					activerEnvoyer();
				}
					
				break;
			}
		}
	}
	
	private void envoyer(){
		String key = "key";
		Bitmap image = this.photo;
		new EnvoyerPhotoTask().execute(key,image);
	}
	
	
	private void supprimerImage(){
		_image.setVisibility(View.GONE);
		_imageSupprimer.setVisibility(View.GONE);
		_ajouterImageLayout.setVisibility(View.VISIBLE);
		this.photo.recycle();
		this.photo = null;
	}
	
}
