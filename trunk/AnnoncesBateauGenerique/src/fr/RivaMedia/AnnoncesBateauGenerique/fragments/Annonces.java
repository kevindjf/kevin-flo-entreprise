package fr.RivaMedia.AnnoncesBateauGenerique.fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;

public class Annonces extends FragmentNormal implements View.OnClickListener, OnTouchListener{

	View _view;

	//TODO recuperer le nombre d'annonces du jour
	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	View  _logo;
	View  _layoutLogo;

	MediaPlayer mp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces,container, false);

		_ajourdhuiAnnonces = (TextView)_view.findViewById(R.id.annonces_aujourd_hui_nombre);
		_boutonBateauxVoiliers = _view.findViewById(R.id.annonces_bouton_bateaux_et_voiliers);
		_boutonMoteurs = _view.findViewById(R.id.annonces_bouton_moteurs);
		_boutonAccessoiresDivers = _view.findViewById(R.id.annonces_bouton_accessoires_et_divers);
		_logo = _view.findViewById(R.id.annonces_logo);
		_layoutLogo = _view.findViewById(R.id.annonces_layout_logo);

		ajouterListeners();
		charger();
		mp = MediaPlayer.create(getActivity(), R.raw.sound);

		return _view;
	}
	
	

	public void ajouterListeners(){
		if(_boutonBateauxVoiliers != null && _boutonMoteurs != null && _boutonAccessoiresDivers != null){
			_boutonBateauxVoiliers.setOnClickListener(this);
			_boutonMoteurs.setOnClickListener(this);
			_boutonAccessoiresDivers.setOnClickListener(this);
			_logo.setOnTouchListener(this);
		}
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void charger() {
		if(Donnees.nbAnnonces.size() > 0){


			Integer nbBateaux = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_BATEAUX);
			Integer nbMoteurs = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_MOTEURS);
			Integer nbDivers = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_DIVERS);

			Long nbAnnonces = Long.valueOf(nbBateaux.intValue()+nbMoteurs.intValue()+nbDivers.intValue());

			String nbAnnoncesString = String.format("%,8d", nbAnnonces).trim();

			_ajourdhuiAnnonces.setText(nbAnnoncesString);
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_bateaux)).setText(nbBateaux.toString());
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_moteurs)).setText(nbMoteurs.toString());
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_accessoires)).setText(nbDivers.toString());

		}
	}

	@Override
	public void remplir() {
	}

	public void afficherFormulaireBateaux(String item){
		ajouterFragment(new AnnoncesFormulaire(item));
	}



	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.annonces_bouton_bateaux_et_voiliers) {
			afficherFormulaireBateaux(Constantes.BATEAUX);
		} else if (id == R.id.annonces_bouton_moteurs) {
			afficherFormulaireBateaux(Constantes.MOTEURS);
		} else if (id == R.id.annonces_bouton_accessoires_et_divers) {
			afficherFormulaireBateaux(Constantes.ACCESSOIRES);
		}
	}

	private int p=0;

	@Override
	public boolean onTouch(View arg0, MotionEvent e) {
		switch(e.getAction()){
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			_layoutLogo.setPadding(p,p,p,p);

			mp.pause();
			mp.seekTo(0);

			return true;
		case MotionEvent.ACTION_DOWN:
			p = _layoutLogo.getPaddingTop();
			
			int p2 = p+20;
			_layoutLogo.setPadding(p2,p2,p2,p2);
			
			mp.start();

			return true;

		}
		return false;
	}

}
