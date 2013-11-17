package fr.RivaMedia.fragments.selector;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;

@SuppressLint("ValidFragment")
public class PaysSelector extends Fragment implements OnItemClickListener{

	View _view;
	ListView _listView;
	
	String[] _valeurs;
	
	ItemSelectedListener _listener;
	
	public PaysSelector (ItemSelectedListener listener){
		this._listener = listener;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.liste_recherche, container, false);
		
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}

	
	private void charger() {
		_listView = (ListView)_view.findViewById(R.id.liste);
	}

	private void remplir() {
		_valeurs = new String[]{
				"France",
				"Royaume-Uni",
				"Luxembourg",
				"Belgique",
				"Suède",
				"Allemagne",
				"Espagne",
				"Italie",
				"Pays-Bas",
				"Portugal",
				"Suisse",
				"Andorre",
				"Grèce",
				"Tunisie",
				"Turquie",
				"Etats-Unis",
				"Sénégal"
		};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,_valeurs);
		_listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	
	private void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		_listener.itemSelected(PaysSelector.this,_valeurs[position],_valeurs[position]);
		getFragmentManager().popBackStack();
	}
	
}
