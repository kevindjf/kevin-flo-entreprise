package fr.RivaMedia.fragments;


import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.factory.BateauFactory;
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

	public AnnonceDetail(String id, String type){
		this._id = id;
		this._type = type;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonce_detail,container, false);

		((MainActivity)getActivity()).afficherProgress(true);
		new ChargerAnnonceTask().execute();

		return _view;
	}

	protected void charger(){
	}
	protected void remplir(){
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
