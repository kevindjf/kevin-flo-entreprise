package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.News;
import fr.RivaMedia.net.NetNews;

/**
 * TODO: Afficher dans des onglets (viewpager)
 */

@SuppressLint("ValidFragment")
public class ActualiteDetail extends FragmentNormal{

	View _view;

	String _id;
	News _news;

	ImageView _image;
	TextView _titre;
	TextView _date;
	TextView _texte;

	public ActualiteDetail(String id){
		this._id = id;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.actualite_details, container, false);

		afficherProgress(true);

		ImageLoaderCache.load(getActivity());

		task = new ChargerNewsTask();
		task.execute();

		return _view;
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


	}
	public void remplir(){
		if(_news != null){
			if(_news.getImageAdress() != null)
				ImageLoaderCache.charger(_news.getImageAdress(), _image);

			if(_news.getTitle() != null)
				_titre.setText(_news.getTitle());

			if(_news.getPubDate() != null)
				_date.setText(_news.getPubDate());
			//TODO remplacer par dateFormatee

			if(_news.getDescription() != null)
				_texte.setText(_news.getDescription());
		}
	}
	public void ajouterListeners(){
	}


	/* --------------------------------------------------------------------------- */

	class ChargerNewsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_news = NetNews.getNews(_id);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerNews();
					afficherProgress(false);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}
}
