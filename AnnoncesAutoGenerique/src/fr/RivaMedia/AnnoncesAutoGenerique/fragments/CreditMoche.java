package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

public class CreditMoche extends FragmentNormal implements View.OnClickListener{

	View _view;
	ImageView _logo;
	TextView _nomEntreprise;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.credit_moche,container, false);

		ImageLoaderCache.load(getActivity());
		
		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	@Override
	public void charger() {
		_logo = (ImageView)_view.findViewById(R.id.credits_logo);
		_nomEntreprise = (TextView)_view.findViewById(R.id.credits_nom_entreprise);
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
	}

	@Override
	public void remplir() {
		_nomEntreprise.setText(Donnees.client.getNom());
		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(), _logo);
	}

	@Override
	public void ajouterListeners() {
	}

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getActivity().getString(R.string.credit));
	}
	
	

}
