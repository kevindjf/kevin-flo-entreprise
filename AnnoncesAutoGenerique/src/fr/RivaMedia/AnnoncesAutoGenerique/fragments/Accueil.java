package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;

public class Accueil extends FragmentNormal implements View.OnClickListener, OnTouchListener{

	View _view;
	ImageView  _logo;
	ImageView  _imageEntreprise;
	TextView _text_entreprise;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.accueil,container, false);

		charger();	
		remplir();
		ajouterListeners();

		return _view;
	}	

	@Override
	public void charger() {
		_logo = (ImageView)_view.findViewById(R.id.logo_entreprise);
		_imageEntreprise = (ImageView) _view.findViewById(R.id.image_entreprise);
		_text_entreprise = (TextView) _view.findViewById(R.id.text_entreprise);
	}

	@Override
	public void remplir() {

		_logo.setImageResource(R.drawable.logo_entreprise);
		_imageEntreprise.setImageResource(R.drawable.image_entreprise);
		_text_entreprise.setText("Itaque tum Scaevola cum in eam ipsam mentionem incidisset, exposuit nobis sermonem Laeli de amicitia habitum ab illo secum et cum altero genero, C. Fannio Marci filio, paucis diebus post mortem Africani. Eius disputationis sententias memoriae mandavi, quas hoc libro exposui arbitratu meo; quasi enim ipsos induxi loquentes, ne &apos;inquam&apos; et &apos;inquit&apos; saepius interponeretur, atque ut tamquam a praesentibus coram haberi sermo videretur.Dumque ibi diu moratur commeatus opperiens, quorum translationem ex Aquitania verni imbres solito crebriores prohibebant auctique torrentes, Herculanus advenit protector domesticus, Hermogenis ex magistro equitum filius, apud Constantinopolim, ut supra rettulimus, populari quondam turbela discerpti. quo verissime referente quae Gallus egerat, damnis super praeteritis maerens et futurorum timore suspensus angorem animi quam diu potuit emendabat.");
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void ajouterListeners() {
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

}
