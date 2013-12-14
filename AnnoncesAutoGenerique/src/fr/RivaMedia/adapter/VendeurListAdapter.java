package fr.RivaMedia.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.view.VendeurView;

public class VendeurListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Vendeur> _vendeurs;
	private List<VendeurView> _views = new ArrayList<VendeurView>();

	private static LayoutInflater inflater=null;
	

	public VendeurListAdapter(Context context, List<Vendeur> vendeurs){
		this._context = context;
		this._vendeurs = vendeurs;

		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(_vendeurs!=null)
			return _vendeurs.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return _vendeurs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;

		view = inflater.inflate(R.layout.vendeur_element_liste, null);

		VendeurView av = new VendeurView(_vendeurs.get(position),_context,view,position);
		_views.add(position,av);

		return view;
	}

}
