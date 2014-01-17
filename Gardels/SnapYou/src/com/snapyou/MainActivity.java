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


public class MainActivity extends Activity implements View.OnClickListener{

	String key = null;
	Bitmap photo = null;

	View _ajouterImage;
	View _ajouterImageLayout;
	CropImageView _image;
	View _envoyer;

	public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1;
	private File _photoCamera;
	
    // Static final constants
    private static final int DEFAULT_ASPECT_RATIO_VALUES = 10;
    private static final String ASPECT_RATIO_X = "ASPECT_RATIO_X";
    private static final String ASPECT_RATIO_Y = "ASPECT_RATIO_Y";

    // Instance variables
    private int _aspectRatioX = DEFAULT_ASPECT_RATIO_VALUES;
    private int _aspectRatioY = DEFAULT_ASPECT_RATIO_VALUES;
	
    // Saves the state upon rotating the screen/restarting the activity
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(ASPECT_RATIO_X, _aspectRatioX);
        bundle.putInt(ASPECT_RATIO_Y, _aspectRatioY);
    }

    // Restores the state upon rotating the screen/restarting the activity
    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        _aspectRatioX = bundle.getInt(ASPECT_RATIO_X);
        _aspectRatioY = bundle.getInt(ASPECT_RATIO_Y);
    }

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
	}

	private void remplir(){
		_image.setAspectRatio(DEFAULT_ASPECT_RATIO_VALUES, DEFAULT_ASPECT_RATIO_VALUES);
	}

	private void ajouterListeners(){
		_ajouterImage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.ajouter_photo){
			getPhotoFromCamera();
		}else if(v.getId() == R.id.envoyer){
			envoyer();
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

	private void recupererPhoto(String path){
		try{
			photo = BitmapFactory.decodeFile(path);
			_ajouterImageLayout.setVisibility(View.GONE);

			_image.setImageBitmap(photo);
			_image.setVisibility(View.VISIBLE);
			
			activerEnvoyer();

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{ 
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode == Activity.RESULT_OK){  
			switch(requestCode){
			case CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE:
				if(_photoCamera != null && !_photoCamera.equals(""))
					recupererPhoto(_photoCamera.getAbsolutePath());		
				break;
			}
		}
	}
	
	private void envoyer(){
		
	}
}
