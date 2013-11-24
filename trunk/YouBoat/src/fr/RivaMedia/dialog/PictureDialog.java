package fr.RivaMedia.dialog;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.Vendre;

public class PictureDialog extends AlertDialog implements View.OnClickListener {
	Vendre _context;
	String _titre;
	TextView _titreView;

	View from_camera;
	View from_gallery;
	public File _photoCamera;


	public PictureDialog(Vendre context, String titre,File photoCamera) {
		super(context.getActivity());
		_context = context;
		_titre = titre;
		_photoCamera = photoCamera;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_holo);
		ajouterVues();
		ajouterListeners();

		super.setCancelable(true);
	}

	protected void ajouterVues(){
		_titreView = (TextView)findViewById(R.id.titre);
		from_camera = findViewById(R.id.from_camera);
		from_gallery = findViewById(R.id.from_gallery);

		_titreView.setText(_titre);

	}

	protected void ajouterListeners(){
		from_camera.setOnClickListener(this);
		from_gallery.setOnClickListener(this);
	}

	public void onClick(View v){
		switch(v.getId()){
		case R.id.from_camera:
			getPhotoFromCamera();
			break;

		case R.id.from_gallery:
			getPhotoFromAlbum();
			break;
		}
	}
	
	public void getPhotoFromCamera(){
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoCamera));
		_context.startActivityForResult(intent, Vendre.CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
		dismiss();
	}
	public void getPhotoFromAlbum(){		
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		_context.startActivityForResult(intent, Vendre.IMAGE_REQUEST);
		dismiss();

	}
	
}
