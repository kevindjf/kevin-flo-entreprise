package fr.RivaMedia.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.Annonces;
import fr.RivaMedia.activity.AnnoncesFormulaire;
import fr.RivaMedia.fragments.ItemSelectedListener;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class AnnoncesTab extends Tab implements View.OnClickListener{

	public static final int BATEAUX = 1;
	public static final int MOTEURS = 2;
	public static final int DIVERS = 3;
	
	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	public AnnoncesTab(String titre,Drawable icon) {
		super(titre,icon);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.annonces, container, false);
		
		_ajourdhuiAnnonces = (TextView)v.findViewById(R.id.annonces_aujourd_hui);
		_boutonBateauxVoiliers = v.findViewById(R.id.annonces_bouton_bateaux_et_voiliers);
		_boutonMoteurs = v.findViewById(R.id.annonces_bouton_moteurs);
		_boutonAccessoiresDivers = v.findViewById(R.id.annonces_bouton_accessoires_et_divers);

		_boutonBateauxVoiliers.setOnClickListener(this);
		_boutonMoteurs.setOnClickListener(this);
		_boutonAccessoiresDivers.setOnClickListener(this);

		return v;
	}

	public void afficherAnnoncesFormulaire(int item){
		Intent i = new Intent(getActivity(),AnnoncesFormulaire.class);
		i.putExtra(AnnoncesFormulaire.TYPE,item);
		getActivity().startActivity(i);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_bouton_bateaux_et_voiliers:
			afficherAnnoncesFormulaire(AnnoncesTab.BATEAUX);
			break;
		case R.id.annonces_bouton_moteurs:
			afficherAnnoncesFormulaire(AnnoncesTab.MOTEURS);
			break;
		case R.id.annonces_bouton_accessoires_et_divers:
			afficherAnnoncesFormulaire(AnnoncesTab.DIVERS);
			break;
		}
	}

}
