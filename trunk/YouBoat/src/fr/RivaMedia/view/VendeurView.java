package fr.RivaMedia.view;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.VendeurDetail;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.view.core.YouBoatView;

public class VendeurView extends YouBoatView implements View.OnTouchListener{

	Vendeur _vendeur;
	int _position;

	ImageView _image;
	TextView _titre;
	TextView _adressePostale;
	TextView _telephone;

	public VendeurView(Vendeur vendeur, Context context, View view, int position) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._vendeur = vendeur;
		this._position = position;
		charger();
		remplir();
		ajouterListeners();
	}

	@Override
	public void charger() {
		_image = (ImageView)getView().findViewById(R.id.vendeur_element_liste_image);
		_titre = (TextView)getView().findViewById(R.id.vendeur_element_liste_titre);
		_adressePostale = (TextView)getView().findViewById(R.id.vendeur_element_liste_adresse_postale);
		_telephone = (TextView)getView().findViewById(R.id.vendeur_element_liste_telephone);
	}

	@Override
	public void remplir() {
		if(_vendeur != null){
			if(_vendeur.getLogo() != null)
				ImageLoaderCache.charger(_vendeur.getLogo(),_image);
			if(_vendeur.getNom() != null)
				_titre.setText(_vendeur.getNom());
			if(_vendeur.getVille() != null && _vendeur.getCodePostal()!=null)
				_adressePostale.setText(_vendeur.getCodePostal()+" "+_vendeur.getVille());
			
			if(_vendeur.getTel1() != null){
				
				String num = _vendeur.getTel1().replace("+33", "0");
				
				DecimalFormatSymbols dfs = new DecimalFormatSymbols();
				dfs.setGroupingSeparator(' ');  // sometimes '.' is used
				DecimalFormat decfmt = new DecimalFormat("0,0", dfs);  // enable grouping
				decfmt.setMinimumIntegerDigits(10);  // we always have 10 digits
				decfmt.setGroupingSize(2);  // necessary in order to group digits by 2 orders
				String tel = decfmt.format(Integer.parseInt(num));  // outputs "01 44 55 66 77"
				
				_telephone.setText(tel);
			}
		}

		afficherNormal();
	}

	@Override
	public void ajouterListeners() {
		this.getView().setOnClickListener(this);
		this.getView().setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		default:
			lancerVendeur();
		}
	}

	private void afficherNormal(){
		if(_position%2==0){
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_paire));
		}else{
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_impaire));
		}
	}
	private void afficherTouch(){
		getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_touch));
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			afficherTouch();
		}
		if(event.getAction() == MotionEvent.ACTION_CANCEL){
			afficherNormal();
		}
		return false;
	}

	public void lancerVendeur(){
		afficherNormal();
		String id = _vendeur.getNumero();

		FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new VendeurDetail(id));
		transaction.addToBackStack(null);
		transaction.commit();
	}

}
