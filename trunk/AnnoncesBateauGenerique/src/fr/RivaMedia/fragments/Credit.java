package fr.RivaMedia.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class Credit extends FragmentNormal implements View.OnClickListener{

	View _view;
	View _email;
	View _emailRivamedia;
	View _photoPetite;
	View _photoGrande;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.credit_rivamedia,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.credits_email:
			envoyerEmailDirect(getString(R.string.EMAIL_FLORENT_KEVIN));
			break;
		case R.id.credits_email_rivamedia:
			envoyerEmailDirect(getString(R.string.EMAIL));
			break;
		case R.id.credits_photo_petite:
			afficherPhotoGrande();
			break;
		case R.id.credits_photo_grande:
			cacherPhotoGrande();
			break;
		}

	}

	private void cacherPhotoGrande() {
		_photoGrande.setVisibility(View.GONE);
	}


	private void afficherPhotoGrande() {
		_photoGrande.setVisibility(View.VISIBLE);
	}


	@Override
	public void charger() {
		_emailRivamedia = _view.findViewById(R.id.credits_email_rivamedia);
		_email = _view.findViewById(R.id.credits_email);
		_photoPetite = _view.findViewById(R.id.credits_photo_petite);
		_photoGrande = _view.findViewById(R.id.credits_photo_grande);

	}

	@Override
	public void remplir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterListeners() {
		_email.setOnClickListener(this);
		_emailRivamedia.setOnClickListener(this);
		_photoPetite.setOnClickListener(this);
		_photoGrande.setOnClickListener(this);
	}

}
