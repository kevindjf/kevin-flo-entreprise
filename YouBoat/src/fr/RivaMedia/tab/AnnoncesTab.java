package fr.RivaMedia.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.Annonces;
import fr.RivaMedia.fragments.AnnoncesFormulaire;
import fr.RivaMedia.fragments.ItemSelectedListener;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class AnnoncesTab extends Tab implements ItemSelectedListener{

	public static final int BATEAUX = 1;
	public static final int MOTEURS = 2;
	public static final int DIVERS = 3;

	private View v;
	Fragment fragment;

	public AnnoncesTab(String titre,Drawable icon) {
		super(titre,icon);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.tab_annonces, container, false);
		fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.emplacement_fragment);

		if(fragment instanceof Annonces){
			((Annonces)fragment).setListener(this);
		}

		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if(fragment == null)
			Log.e("fragment","null");
		if(fragment instanceof Annonces){
			((Annonces)fragment).setListener(this);
		}

	}

	public void afficherAnnoncesFormulaire(int item){
		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		AnnoncesFormulaire af = new AnnoncesFormulaire(item);
		ft.replace(R.id.emplacement_fragment, af);
		ft.commit(); 
	}


	@Override
	public void itemSelected(int item) {
		Log.e("itemSelected",""+item);

		switch(item){
		case BATEAUX:
		case MOTEURS:
		case DIVERS:
			afficherAnnoncesFormulaire(item);
			break;
		}


	}

}
