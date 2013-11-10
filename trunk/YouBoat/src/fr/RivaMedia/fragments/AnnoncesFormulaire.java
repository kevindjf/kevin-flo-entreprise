package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends Fragment implements View.OnClickListener{

	private OnItemSelectedListener listener;
	private int typeAnnonces;
	
	public AnnoncesFormulaire(int type){
		this.typeAnnonces = type;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.annonces_formulaire,container, false);

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}


}
