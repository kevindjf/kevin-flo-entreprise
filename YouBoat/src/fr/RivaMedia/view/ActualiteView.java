package fr.RivaMedia.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.ActualiteDetail;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.News;
import fr.RivaMedia.view.core.YouBoatView;

public class ActualiteView extends YouBoatView implements View.OnTouchListener{

	Object _element;
	int _position;

	ImageView _image;
	TextView _titre;
	TextView _sousTitre;

	public ActualiteView(Object element, Context context, View view, int position) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._element = element;
		this._position = position;
		charger();
		remplir();
		ajouterListeners();
	}

	@Override
	public void charger() {
		_image = (ImageView)getView().findViewById(R.id.actualite_element_liste_image);
		_titre = (TextView)getView().findViewById(R.id.actualite_element_liste_titre);
		_sousTitre = (TextView)getView().findViewById(R.id.actualite_element_liste_sous_titre);
	}

	@Override
	public void remplir() {
		if(_element instanceof News){
			News news = (News)_element;

			ImageLoaderCache.charger(news.getImageAdress(),_image);
			_titre.setText(news.getTitle());
			_sousTitre.setText(news.getDescription());
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
			lancerActualiteDetail(_element);
		}
	}

	private void afficherNormal(){
		if(_position%2==0){
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.blanc));
		}else{
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.bleu_claire));
		}
	}
	private void afficherTouch(){
		getView().setBackgroundColor(getContext().getResources().getColor(R.color.blue));
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

	public void lancerActualiteDetail(Object element){
		if(_element instanceof News){
			News news = (News)_element;

			FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new ActualiteDetail(news.getLink()));
			transaction.addToBackStack(null);
			transaction.commit();
			
		}
	}

}