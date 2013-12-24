package fr.RivaMedia.AnnoncesAutoGenerique.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.view.AnnonceView;

public class AnnonceListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Annonce> _annonces;
	private List<AnnonceView> _views = new ArrayList<AnnonceView>();

	private static LayoutInflater inflater=null;
	private boolean _swipable = false;

	public AnnonceListAdapter(Context context, List<Annonce> annonces, boolean swipable){
		this._context = context;
		this._annonces = annonces;
		this._swipable = swipable;

		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public AnnonceListAdapter(Context context, List<Annonce> annonces){
		this(context,annonces,false);
	}

	@Override
	public int getCount() {
		if(_annonces!=null)
			return _annonces.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return _annonces.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public AnnonceView getView(int position){
		return _views.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;

		if(_swipable){
			view = inflater.inflate(R.layout.annonce_element_liste_swipable, null);
		}
		else
			view = inflater.inflate(R.layout.annonce_element_liste, null);

		AnnonceView av = new AnnonceView(_annonces.get(position),_context,view,position,_swipable);
		_views.add(position,av);

		return view;
	}

}
