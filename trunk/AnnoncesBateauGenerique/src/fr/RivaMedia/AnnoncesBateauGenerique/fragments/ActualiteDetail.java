package fr.RivaMedia.AnnoncesBateauGenerique.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetActualite;
import fr.RivaMedia.AnnoncesBateauGenerique.utils.DateFrancais;

/**
 * TODO: Afficher dans des onglets (viewpager)
 */

@SuppressLint("ValidFragment")
public class ActualiteDetail extends FragmentNormal{

	View _view;

	String _id;
	Actualite _news;

	ImageView _image;
	TextView _titre;
	TextView _date;
	TextView _texte;

	boolean afficherProgress = true;

	public ActualiteDetail(String id){
		this._id = id;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.actualite_details, container, false);
		_view.setVisibility(View.GONE);

		ImageLoaderCache.load(getActivity());

		task = new ChargerNewsTask();
		task.execute();

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		setTitre(getString(R.string.actualite_detail));
	}


	protected void chargerNews(){
		charger();
		remplir();
		changerCouleurs();
		ajouterListeners();
	}

	public void charger(){

		_image = (ImageView)_view.findViewById(R.id.actualite_detail_image);	
		_titre = (TextView)_view.findViewById(R.id.actualite_detail_titre);
		_date  = (TextView)_view.findViewById(R.id.actualite_detail_date);
		_texte = (TextView)_view.findViewById(R.id.actualite_detail_texte);


	}
	
	protected void changerCouleurs(){
		afficherCouleurNormal(_view.findViewById(R.id.actualite_titre_layout));
		afficherTexteCouleurTexte(_view.findViewById(R.id.actualite_detail_titre));
	}

	public void remplir(){
		if(_news != null){
			if(_news.getImageAdress() != null)
				ImageLoaderCache.charger(_news.getImageAdress(), _image, _view.findViewById(android.R.id.progress));
			else{
				_image.setVisibility(View.GONE);
			}

			if(_news.getTitle() != null)
				_titre.setText(_news.getTitle());

			if(_news.getPubDate() != null)
				_date.setText(DateFrancais.dateEnToFr(_news.getPubDate()));
			//	_date.setText(changerDate(_news.getPubDate()));
			else
				_date.setVisibility(View.GONE);
			
			if(_news.getDescription() != null)
				_texte.setText(_news.getDescription());
		}
	}
	public void ajouterListeners(){
	}


	/* --------------------------------------------------------------------------- */

	class ChargerNewsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			try{
				_news = NetActualite.getActualite(_id);

				getActivity().runOnUiThread(new Runnable(){

					@Override
					public void run() {
						chargerNews();
						afficherProgress = false;
						afficherProgress(afficherProgress);
						_view.setVisibility(View.VISIBLE);
					}

				});
			}catch(Exception e){
				retirerFragment();
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}
}
