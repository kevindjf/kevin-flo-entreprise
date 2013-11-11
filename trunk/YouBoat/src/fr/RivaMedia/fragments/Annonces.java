package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class Annonces extends Fragment implements View.OnClickListener{

	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;
	
	private ItemSelectedListener listener;
	
	public static final int BATEAUX = 1;
	public static final int MOTEURS = 2;
	public static final int DIVERS = 3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.annonces,container, false);

		_ajourdhuiAnnonces = (TextView)view.findViewById(R.id.annonces_aujourd_hui);
		_boutonBateauxVoiliers = view.findViewById(R.id.annonces_bouton_bateaux_et_voiliers);
		_boutonMoteurs = view.findViewById(R.id.annonces_bouton_moteurs);
		_boutonAccessoiresDivers = view.findViewById(R.id.annonces_bouton_accessoires_et_divers);

		_boutonBateauxVoiliers.setOnClickListener(this);
		_boutonMoteurs.setOnClickListener(this);
		_boutonAccessoiresDivers.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_bouton_bateaux_et_voiliers:
			listener.itemSelected(BATEAUX);
			break;
		case R.id.annonces_bouton_moteurs:
			listener.itemSelected(MOTEURS);
			break;
		case R.id.annonces_bouton_accessoires_et_divers:
			listener.itemSelected(DIVERS);
			break;
		}
	}

	public void setListener(ItemSelectedListener listener) {
		this.listener = listener;
	}


}
