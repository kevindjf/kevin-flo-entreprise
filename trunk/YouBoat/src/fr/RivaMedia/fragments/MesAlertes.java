package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MesAlertes extends Fragment implements View.OnClickListener{


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mes_alertes,container, false);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

}
