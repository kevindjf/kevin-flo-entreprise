package fr.RivaMedia.fragments.selector;

import java.util.ArrayList;
import java.util.List;

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
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;

@SuppressLint("ValidFragment")
public class MarqueSelector extends Fragment implements OnItemClickListener{

	View _view;
	ListView _listView;

	String _type;
	List<Marque> _marques;

	ItemSelectedListener _listener;
	int _reponseId;

	public MarqueSelector (ItemSelectedListener listener, int reponseId, String type){
		this._type = type;
		this._listener = listener;
		this._reponseId = reponseId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_selector, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	private void charger() {
		_listView = (ListView)_view.findViewById(R.id.liste);
	}

	private void remplir() {
		_marques = Donnees.getMarques(_type);
		
		if(_marques != null){
			List<String> libelles =  new ArrayList<String>();
			
			for(Marque m : _marques){
				libelles.add(m.getLibelle());
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,libelles);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}


	private void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new ModeleSelector(_listener, _reponseId, _type, _marques.get(position)));
		transaction.addToBackStack(null);
		transaction.commit();
	}

}
