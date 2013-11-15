package fr.RivaMedia.fragments.selector;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class TypeSelector extends Fragment implements OnItemClickListener{

	View _view;
	ListView _listView;
	
	String[] _types_bateaux;
	String[] _valeurs;
	
	ItemSelectedListener _listener;
	
	public TypeSelector (ItemSelectedListener listener){
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
		_types_bateaux = new String[]{
				getActivity().getResources().getString(R.string.bateau_a_moteur),
				getActivity().getResources().getString(R.string.voiliers),
				getActivity().getResources().getString(R.string.pneumatiques_semi_rigide)
		};
		_valeurs = new String[]{
				Constantes.BATEAU_A_MOTEUR,
				Constantes.VOILIER,
				Constantes.PNEU
		};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,_types_bateaux);
		_listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	
	private void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		_listener.itemSelected(TypeSelector.this,_valeurs[position],_types_bateaux[position]);
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.detach(this);
		transaction.commit();
	}
	
}
