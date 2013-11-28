package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Actualite;
import fr.RivaMedia.net.NetActualite;

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
	private String changerJour(String jour){
		if(jour.equals("Mon"))
			return "Lundi";
		if(jour.equals("Tue"))
			return "Mardi";
		if(jour.equals("Wed"))
			return "Mercredi";
		if(jour.equals("Thu"))
			return "Jeudi";
		if(jour.equals("Fri"))
			return "Vendredi";
		if(jour.equals("Sat"))
			return "Samedi";
		if(jour.equals("Sun"))
			return "Dimanche";

		return "Err";		
	}

	private String changerMois(String mois){
		if(mois.equals("Jan"))
			return "Janvier";
		if(mois.equals("Feb"))
			return "Février";
		if(mois.equals("Mar"))
			return "Mars";
		if(mois.equals("Apr"))
			return "Avril";
		if(mois.equals("May"))
			return "Mai";
		if(mois.equals("Jun"))
			return "Juin";
		if(mois.equals("Jul"))
			return "Juillet";
		if(mois.equals("Aug"))
			return "Août";
		if(mois.equals("Sep"))
			return "Septembre";
		if(mois.equals("Oct"))
			return "Octobre";
		if(mois.equals("Nov"))
			return "Novembre";
		if(mois.equals("Dec"))
			return "Décembre";
		return "Err";
	}
	@SuppressWarnings("unused")
	private String changerDate(String dateEn){
		String dateFr ="";
		Log.e("Jour", dateEn.substring(0,3));
		dateFr+= changerJour(dateEn.substring(0,3))+dateEn.substring(3,7) + " "
				+ changerMois(dateEn.substring(8,11))+ dateEn.substring(11,17) + "à "+dateEn.substring(17,22);
		Log.e("DateFr",dateFr);
		return dateFr;
	}

	public String convertirDate(String date){

		date = date.replace("Jan", "Janvier")
				.replace("Feb", "Feb")
				.replace("Mars", "Avril")
				.replace("May", "Mai")
				.replace("Jun", "Juin")
				.replace("Jul", "Juillet")
				.replace("Aug", "Août")
				.replace("Sep", "Sep")
				.replace("Oct", "Octobre")
				.replace("Nov", "Novembre")
				.replace("Dec", "Décembre")

				.replace("Mon", "Lundi")
				.replace("Tue", "Mardi")
				.replace("Wed", "Mercredi")
				.replace("Thu", "Jeudi")
				.replace("Fri", "Vendredi")
				.replace("Sat", "Samedi")
				.replace("Sun", "Dimanche")

				.replace(",", "");

		String [] elements = date.split(" ");
		String jourNom = elements[0];
		String jour = elements[1];
		String mois = elements[2];
		String annee = elements[3];
		String heureComplete = elements[4];

		String[] hs = heureComplete.split(":");
		String h = hs[0];
		String m = hs[1];

		date = jourNom+" "+jour+" "+mois+" "+annee+" à "+h+"h"+m;

		return date;

	}

	public void remplir(){
		if(_news != null){
			if(_news.getImageAdress() != null)
				ImageLoaderCache.charger(_news.getImageAdress(), _image);

			if(_news.getTitle() != null)
				_titre.setText(_news.getTitle());

			if(_news.getPubDate() != null)
				_date.setText(convertirDate(_news.getPubDate()));
			//	_date.setText(changerDate(_news.getPubDate()));

			Log.e("Date",_news.getPubDate());
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

			return null;
		}

		protected void onPostExecute(){
		}
	}
}
