package fr.RivaMedia.AnnoncesBateauGenerique.fragments;



import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Credit extends FragmentNormal implements View.OnClickListener{

	View _view;
	ImageView _logo;
	TextView _nomEntreprise;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.credit,container, false);

		ImageLoaderCache.load(getActivity());
		
		charger();
		remplir();
		ajouterListeners();
		chargerCouleurs();

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

	public void chargerCouleurs(){
		afficherTexteCouleurTexte(
				_view.findViewById(R.id.developpeurs_titre),
				_view.findViewById(R.id.developpeurs_texte),
				_view.findViewById(R.id.distributeur_titre),
				_view.findViewById(R.id.distributeur_texte),
				_view.findViewById(R.id.credits_nom_entreprise_titre),
				_view.findViewById(R.id.credits_nom_entreprise)
				);
	}
	
	@Override
	public void ajouterListeners() {
	}

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getActivity().getString(R.string.credit));
		try{
		//trackerEcran("Ecran Credits Android");
		}catch(Exception e){
			
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	

}

