package fr.RivaMedia.AnnoncesBateauGenerique.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.AnnoncesBateauGenerique.R;

public class Vide extends Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_vide, container, false);
		
	}

}
