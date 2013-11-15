package fr.RivaMedia.fragments.selector;

import java.util.List;

import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class MarqueSelector extends Fragment implements OnItemClickListener{

	View _view;
	ListView _listView;

	String _type;
	List<Marque> _marques;
	String[] _elements;

	ItemSelectedListener _listener;

	public MarqueSelector (String type,ItemSelectedListener listener){
		this._type = type;
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
		_marques = Donnees.getMarques(_type);

		if(_marques != null){
			_elements = new String[_marques.size()];
			int i=0;
			for(Marque m : _marques){
				_elements[i] = m.getLibelle();
				i++;
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,_elements);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}


	private void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		_listener.itemSelected(this,_marques.get(position).getId(), _elements[position]);
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.detach(this);
		transaction.commit();
	}

}
