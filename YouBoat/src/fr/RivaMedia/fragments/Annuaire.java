package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;

public class Annuaire extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.annuaire, container, false);
		
		return v;
	}

}
