package fr.RivaMedia.fragments;


import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.factory.BateauFactory;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Bateau;
import fr.RivaMedia.net.NetNews;
import fr.RivaMedia.net.NetRecherche;
import fr.RivaMedia.net.core.Net;

@SuppressLint("ValidFragment")
public class AnnonceDetail extends Fragment implements View.OnClickListener{

	View _view;
	Object _annonce;
	String _id;
	String _type;
	View screen;
	TextView titre_type;
	TextView subtitre_type;
	TextView type_bateau;
	TextView longeur_text;
	TextView largeur_text;
	TextView cabines;
	TextView couchettes;
	TextView salle_de_bain;
	TextView annee;
	TextView etat_text;
	TextView moteur_text;
	TextView puissance_text;
	TextView propulsion_text;
	TextView nb_heures;
	TextView prix;
	TextView description_text;
	TextView nomVendeur;
	TextView rueVendeur;
	TextView postaleVendeur;
	TextView tel_principal;
	TextView tel_secondaire;
	ImageView imageprincipale;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	View relative_tel_secondaire;
	public AnnonceDetail(String id, String type){
		this._id = id;
		this._type = type;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonce_detail,container, false);

		ImageLoaderCache.load(getActivity());

		((MainActivity)getActivity()).afficherProgress(true);
		new ChargerAnnonceTask().execute();

		return _view;
	}

	protected void charger(){
		screen = _view.findViewById(R.id.screen);
		relative_tel_secondaire = _view.findViewById(R.id.relative_tel_2);
		titre_type = (TextView) _view.findViewById(R.id.type_title);	
		subtitre_type = (TextView) _view.findViewById(R.id.sous_type_title);
		type_bateau = (TextView) _view.findViewById(R.id.bateau_type);
		longeur_text = (TextView) _view.findViewById(R.id.longueur_text);
		largeur_text = (TextView) _view.findViewById(R.id.largeur_text);
		cabines = (TextView) _view.findViewById(R.id.cabines_text);
		couchettes = (TextView) _view.findViewById(R.id.couchettes_text);
		salle_de_bain = (TextView) _view.findViewById(R.id.salle_de_bain_text);
		annee = (TextView) _view.findViewById(R.id.annee_text);
		etat_text = (TextView) _view.findViewById(R.id.etat_text);
		moteur_text = (TextView) _view.findViewById(R.id.moteur_text);
		puissance_text = (TextView) _view.findViewById(R.id.puissance_text);
		propulsion_text = (TextView) _view.findViewById(R.id.propulsion);
		nb_heures = (TextView) _view.findViewById(R.id.nb_heure_text);
		prix = (TextView) _view.findViewById(R.id.prix);
		description_text = (TextView) _view.findViewById(R.id.description_annonce);
		nomVendeur = (TextView) _view.findViewById(R.id.nom_vendeur);
		rueVendeur = (TextView) _view.findViewById(R.id.rue_vendeur);
		postaleVendeur = (TextView) _view.findViewById(R.id.postale_vendeur);
		tel_principal = (TextView) _view.findViewById(R.id.telephone_principal);
		tel_secondaire = (TextView) _view.findViewById(R.id.telephone_secondaire);
		imageprincipale = (ImageView) _view.findViewById(R.id.principal_picture);
		image1 = (ImageView) _view.findViewById(R.id.other_picture_1);
		image2 = (ImageView) _view.findViewById(R.id.other_picture_2);
		image3 = (ImageView) _view.findViewById(R.id.other_picture_3);
		image4 = (ImageView) _view.findViewById(R.id.other_picture_4);

	}
	protected void remplir(){
		if(_annonce instanceof Bateau){
			Bateau bateau = (Bateau) _annonce;
			titre_type.setText(bateau.getTitle());
			subtitre_type.setText(bateau.getMoteur().getInfoMoteur());
			
			type_bateau.setText(bateau.getType() + " > " +bateau.getCategorie());
			longeur_text.setText(bateau.getLongueur());
			largeur_text.setText(bateau.getLargeur());
			cabines.setText(bateau.getNbCabines());
			couchettes.setText(bateau.getNbCouchettes());
			salle_de_bain.setText(bateau.getNbSallesDeBain());
			annee.setText(bateau.getAnnee());
			etat_text.setText(bateau.getEtat());
			moteur_text.setText(bateau.getMoteur().getMarqueMoteur());
			puissance_text.setText(bateau.getMoteur().getPuissanceMoteur());
			propulsion_text.setText(bateau.getMoteur().getPropulsion());
			nb_heures.setText(bateau.getMoteur().getHeureMoteur());
			prix.setText(bateau.getPrix() + " " + bateau.getTaxePrix());
			description_text.setText(bateau.getCommentaire());
			
			ImageLoaderCache.charger(bateau.getLien().getUrl(), imageprincipale);

			if(bateau.getPhotos().size() > 1){
				image1.setVisibility(View.VISIBLE);
				ImageLoaderCache.charger(bateau.getPhotos().get(0).getUrl(), image1);

			}
			if(bateau.getPhotos().size() > 2){
				image2.setVisibility(View.VISIBLE);
				ImageLoaderCache.charger(bateau.getPhotos().get(1).getUrl(), image2);

			}
			if(bateau.getPhotos().size() > 3){
				image3.setVisibility(View.VISIBLE);
				ImageLoaderCache.charger(bateau.getPhotos().get(2).getUrl(), image3);

			}
			if(bateau.getPhotos().size() > 4){
				image4.setVisibility(View.VISIBLE);
				ImageLoaderCache.charger(bateau.getPhotos().get(3).getUrl(), image4);

			}
			if(bateau.getVendeur() != null){
			nomVendeur.setText(bateau.getVendeur().getNom());
			rueVendeur.setText(bateau.getVendeur().getAdresse());
			postaleVendeur.setText(bateau.getVendeur().getCodePostal() + " " + bateau.getVendeur().getVille());
			tel_principal.setText(bateau.getVendeur().getTel1());
			if(!bateau.getVendeur().getTel2().equals("")){
			relative_tel_secondaire.setVisibility(View.VISIBLE);
			tel_secondaire.setText(bateau.getVendeur().getTel2());
			}
			}
		}
		screen.setVisibility(View.VISIBLE);

	}
	protected void ajouterListeners(){
	}




	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}


	protected void chargerAnnonce(){
		charger();
		remplir();
		ajouterListeners();
	}

	protected String recupererUrl(){
		String url = null;

		if(_type.equals(Constantes.BATEAU_A_MOTEUR))
			url = "xml-detail-bateau.php?";
		else if(_type.equals(Constantes.VOILIER))
			url = "xml-detail-voilier.php?";
		else if(_type.equals(Constantes.PNEU))
			url = "xml-detail-pneuma.php?";
		else if(_type.equals(Constantes.MOTEURS))
			url = "xml-detail-moteur.php?";
		else if(_type.equals(Constantes.ACCESSOIRES))
			url = "xml-detail-accessoire.php?";

		return url;
	}

	protected void chargerDetailAnnonce(){
		Log.e("AnnonceDetail",recupererUrl());
		_annonce = NetRecherche.rechercherAnnonce(recupererUrl(), Net.construireDonnes("idad",_id));
	}


	/* --------------------------------------------------------------------------- */

	class ChargerAnnonceTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests

			chargerDetailAnnonce();

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonce();
					((MainActivity)getActivity()).afficherProgress(false);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
