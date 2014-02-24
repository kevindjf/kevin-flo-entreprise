package fr.RivaMedia.AnnoncesBateauGenerique.fragments;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;

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
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.adapter.AnnonceListAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnonceDateComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnoncePrixComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnoncePrixParLongueurComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.AnnoncesFormulaire.AnnoncesFormulaireDelegate;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentListe;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetAnnonce;

@SuppressLint("ValidFragment")
public class AnnoncesListe extends FragmentListe implements View.OnClickListener, AnnoncesFormulaireDelegate{

	TypeAnnonce typeAnnonce = null;
	
	LoadMoreListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();

	List<NameValuePair> _donneesFormulaire;
	String _type;

	int page = 1;
	int dernierNombre = 1;

	String idClient = null; 
	
	TextView nombreAnnonces = null;

	public AnnoncesListe(){}

	public AnnoncesListe(TypeAnnonce typeAnnonce){
		System.out.println(typeAnnonce);
		this.typeAnnonce = typeAnnonce;
	}
	
	public AnnoncesListe(String type){
		this._type = type ;
	}
	public AnnoncesListe(List<NameValuePair> donneesFormulaire, String type){
		this._donneesFormulaire = donneesFormulaire;
		this._type = type;
	}

	public void chargerAnnoncesTask(){
		task = new ChargerAnnoncesTask();
		task.execute();

		if(typeAnnonce != null || (_type != null && (_type.equals(Constantes.BATEAU_A_MOTEUR) || _type.equals(Constantes.VOILIER) || _type.equals(Constantes.PNEU)))){
			super.afficherTriLongueur(true);
		}
		else
			super.afficherTriLongueur(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(super.onCreateView(inflater, container, savedInstanceState, R.layout.liste_annonces)){
			afficherProgress(afficherProgress);

			chargerAnnoncesTask();

			ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)getFragmentView().findViewById(R.id.fond));

		}


		return getFragmentView();
	}

	public void charger(){
		if(_liste == null)
			_liste = (LoadMoreListView)findViewById(android.R.id.list);	
		
		if(nombreAnnonces == null)
			nombreAnnonces = (TextView)findViewById(R.id.nombre_annonces);
	}
	public void remplir(){

		if(_annonces == null || _annonces.size()==0){
			findViewById(R.id.vide).setVisibility(View.VISIBLE);
			nombreAnnonces.setVisibility(View.GONE);
		}
		
		else{
			nombreAnnonces.setVisibility(View.VISIBLE);
			if(_adapter == null){
				_adapter = new AnnonceListAdapter(getActivity(), _annonces,_type);
				_liste.setAdapter(_adapter);
			}else
				_adapter.notifyDataSetChanged();
			
			nombreAnnonces.setText(Donnees.nb_result+" "+getActivity().getResources().getString(R.string.annonces_trouvees));
		}
	}
	public void ajouterListeners(){
		if(dernierNombre>0){
			_liste.setOnLoadMoreListener(new OnLoadMoreListener() {
				public void onLoadMore() {
					page+=1;
					task = new ChargerAnnoncesTask();
					task.execute();
				}
			});
		}
		else{
			_liste.setOnLoadMoreListener(null);
		}
	}


	@Override
	public void onClick(View v) {
		super.onClick(v);		
		if(v.getId() == R.id.header_plus)
			ajouterFragment(new AnnoncesFormulaire(_type,this,typeAnnonce), true);

	}


	protected void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
	}



	@Override
	public void effacer() {
		// TODO Auto-generated method stub

	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}


	@Override
	public void afficherPrixCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherPrixDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherDateCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherDateDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherLongueurCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixParLongueurComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherLongueurDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixParLongueurComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}

	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			List<Annonce> annonces = NetAnnonce.rechercher(_type, typeAnnonce, Integer.valueOf(page), _donneesFormulaire, idClient);
			dernierNombre = annonces.size();
			
			if(page == 1)
				_annonces = annonces;
			else 
				_annonces.addAll(annonces);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					afficherProgress = false;
					afficherProgress(afficherProgress);

					_liste.onLoadMoreComplete();
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.e("AnnonceListe", "OnResume");
		//afficherProgress(afficherProgress);
		setTitre(getString(R.string.annonces));
		super.afficherPlus().setOnClickListener(this);
	}

	@Override
	public void rechercher(List<NameValuePair> donneesFormulaire, String type) {
		this._donneesFormulaire = donneesFormulaire;
		this._type = type;
		
		this._annonces.clear();
		remplir();
		
		page = 1;
		dernierNombre = 1;
		
		_liste = null;
		_adapter = null;
		
		chargerAnnoncesTask();
	}
}
