package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetActualite;
	
/**
 * TODO: Afficher dans des onglets (viewpager)
 */

@SuppressLint("ValidFragment")
public class ActualiteDetail extends FragmentNormal{

	View _view;

	String _id;
	Actualite _actualite;

	ImageView _image;
	TextView _titre;
	TextView _date;
	TextView _texte;

	boolean afficherProgress = true;
	
	View _actualiteDetailTitreLayout;

	public ActualiteDetail(String id){
		this._id = id;
	}
	
	public void chargerCouleurs(){
		//ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		_view.findViewById(R.id.fond).setVisibility(View.GONE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.actualite_details, container, false);
		_view.setVisibility(View.GONE);
		
		ImageLoaderCache.load(getActivity());

		task = new ChargerNewsTask();
		task.execute();
		
		chargerCouleurs();

		return _view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		setTitre(getResources().getString(R.string.actualites));
	}


	protected void chargerNews(){
		charger();
		remplir();
		ajouterListeners();
	}

	public void charger(){

		_image = (ImageView)_view.findViewById(R.id.actualite_detail_image);	
		_titre = (TextView)_view.findViewById(R.id.actualite_detail_titre);
		_date  = (TextView)_view.findViewById(R.id.actualite_detail_date);
		_texte = (TextView)_view.findViewById(R.id.actualite_detail_texte);
		
		_actualiteDetailTitreLayout = _view.findViewById(R.id.actualite_detail_titre_layout);
	}

	public void remplir(){
		if(_actualite != null){
			if(_actualite.getPhoto() != null){
				String url = _actualite.getPhoto();
				Log.e("ACTU_PHOTO",_actualite.getPhoto());
				ImageLoaderCache.charger(url, _image);
			}

			if(_actualite.getTitre() != null)
				_titre.setText(_actualite.getTitre());

			if(_actualite.getDate() != null)
				_date.setText(_actualite.getDate());

			if(_actualite.getTexte() != null)
				_texte.setText(_actualite.getTexte());
		}
		
		afficherCouleurNormal(_actualiteDetailTitreLayout);
	}
	public void ajouterListeners(){
	}


	/* --------------------------------------------------------------------------- */

	class ChargerNewsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			try{
			_actualite = NetActualite.getActualite(_id);

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
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}
}
