package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;

public class Informations extends Fragment implements View.OnClickListener{


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.informations,container, false);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

}
