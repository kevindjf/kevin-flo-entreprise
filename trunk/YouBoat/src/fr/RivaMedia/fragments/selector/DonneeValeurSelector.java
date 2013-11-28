package fr.RivaMedia.fragments.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.woozzu.android.adapter.IndexableListViewAdapter;
import com.woozzu.android.widget.IndexableListView;

import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.fragments.core.ItemSelectedListener;

@SuppressLint("ValidFragment")
public class DonneeValeurSelector extends FragmentNormal implements OnItemClickListener{

	View _view;
	IndexableListView _listView;

	List<String> _types;
	Map<String,String> _types_valeurs;

	ItemSelectedListener _listener;

	int _idRetour; //indicateur de retour

	public DonneeValeurSelector (ItemSelectedListener listener,  int idRetour, Map<String,String> types_valeurs){
		this._listener = listener;
		this._types_valeurs = types_valeurs;
		this._idRetour = idRetour;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_selector, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	public void charger() {
		_listView = (IndexableListView)_view.findViewById(android.R.id.list);
		_listView.setFastScrollEnabled(true);
	}

	public void remplir() {
		_types = new ArrayList<String>(_types_valeurs.keySet());
		Collections.sort(_types);

		if(_types.size()==0)
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		else{
			IndexableListViewAdapter adapter = new IndexableListViewAdapter(getActivity(), R.layout.element_liste_simple,_types);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}


	public void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}

	public static Map<String,String> creerDonneeValeur(String...elements){
		Map<String,String> donneesValeur = new HashMap<String,String>();
		for(int s=0;s<elements.length;s+=2){
			String donnee = elements[s];
			String valeur = elements[s+1];
			donneesValeur.put(donnee,valeur);
		}
		return donneesValeur;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		String item = _types.get(position);
		String valeur = _types_valeurs.get(item);
		_listener.itemSelected(DonneeValeurSelector.this,_idRetour,valeur,item);
		getFragmentManager().popBackStack();
	}



}
