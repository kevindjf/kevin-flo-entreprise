package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class ContactPro extends FragmentNormal implements View.OnClickListener{


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contact_pro,container, false);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

	@Override
	public void charger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remplir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterListeners() {
		// TODO Auto-generated method stub
		
	}

}
