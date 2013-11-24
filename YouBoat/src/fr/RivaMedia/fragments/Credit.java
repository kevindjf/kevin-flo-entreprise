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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.credit,container, false);
		
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}
	

	@Override
	public void onClick(View v) {
		envoyerEmail(getString(R.string.EMAIL_FLORENT_KEVIN));
	}

	@Override
	public void charger() {
		_email = _view.findViewById(R.id.credits_email);
		
	}

	@Override
	public void remplir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterListeners() {
		_email.setOnClickListener(this);
	}

}
