package fr.RivaMedia.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Ville;

public class RechercheVille extends BaseAdapter{

	private LayoutInflater inflater;
	public ListView liste;
	private List<Ville> villes;
	private List<Ville> villesRecherche = new ArrayList<Ville>();
	private List<SpannableStringBuilder> spans = new ArrayList<SpannableStringBuilder>();

	public RechercheVille(Activity activity, ViewGroup layout, List<Ville> villes){
		this.villes = villes;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View l = inflater.inflate(R.layout.liste_recherche, null);

		this.liste = (ListView)(l.findViewById(R.id.recherche_listview));
		
		layout.addView(l);

		chercher("");
	}

	public void reset(){
		villesRecherche.clear();
		spans.clear();
		this.liste.setAdapter(null);
	}

	public Ville retourner(int position){
		return this.villesRecherche.get(position);
	}

	
	@SuppressLint("DefaultLocale")
	public void chercher(String texte){		
		reset();

		Pattern p = Pattern.compile(texte.toLowerCase()
				.replace("é","e")
				.replace("ê","e")
				.replace("è","e")
				.replace("â","a")
				.replace("à","a")
				);

		for(Ville ville : villes){
			if(ville.getNom() != null){
				String nom = ville.getNom();
				String nomComparable = nom.toLowerCase()
						.replace("é","e")
						.replace("ê","e")
						.replace("è","e")
						.replace("â","a")
						.replace("à","a");
				
				final Matcher matcher = p.matcher(nomComparable);
				final SpannableStringBuilder spannable = new SpannableStringBuilder(nom);
				final ForegroundColorSpan span = new ForegroundColorSpan(Color.BLACK);
				if (matcher.find()) {
					spannable.setSpan(
							span, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
							);
					spans.add(spannable);
					villesRecherche.add(ville);
				}
			}
		}

		this.liste.setAdapter(this);
	}

	@Override
	public int getCount() {
		return villesRecherche.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View reuse, ViewGroup parent) {
		View cell = inflater.inflate(R.layout.liste_recherche_element, null);
		//Ville ville = villesRecherche.get(position);

		TextView tv = (TextView)cell.findViewById(R.id.recherche_element_texte);
		tv.setText(this.spans.get(position));

		return cell;
	}

}
