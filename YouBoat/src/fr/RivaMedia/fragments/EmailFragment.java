package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetEmail;

@SuppressLint("ValidFragment")
public class EmailFragment extends FragmentNormal implements ItemSelectedListener, View.OnClickListener{

	public static int EMAIL_ANNNONCE = 1;
	public static int EMAIL_CLIENT = 2;

	public static int DEPARTEMENT = 0;

	int type = 0;

	View _view;

	String emailDestinataire;
	String message;

	String id;
	Object objet;

	String departementId;

	EditText _adresseEmail;
	EditText _nom;
	EditText _telephone;
	EditText _message;

	View _departement;

	View _envoyer;

	ImageView _image;
	TextView _texte;

	public EmailFragment(int type, Object objet){
		this.type = type;
		this.objet = objet;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.email, container, false);

		afficherProgress(true);

		charger();
		remplir();
		ajouterListeners();

		ImageLoaderCache.load(getActivity());

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}

	@Override
	public void charger() {
		_adresseEmail = (EditText)_view.findViewById(R.id.email_adresse_email);
		_nom = (EditText)_view.findViewById(R.id.email_nom);
		_telephone = (EditText)_view.findViewById(R.id.email_telephone);
		_departement = _view.findViewById(R.id.email_departement);
		_message = (EditText)_view.findViewById(R.id.email_message);

		_envoyer = _view.findViewById(R.id.email_bouton_envoyer);

		_image = (ImageView)_view.findViewById(R.id.email_image);
		_texte = (TextView)_view.findViewById(R.id.email_texte);
	}

	@Override
	public void remplir() {
		if(type == EMAIL_ANNNONCE){
			if(objet instanceof Annonce){
				Annonce annonce = (Annonce)objet;
				if(annonce.getTitle() != null)
					_texte.setText(annonce.getTitle());
				if(annonce.getPhotos() != null && annonce.getPhotos().size()>0){
					String urlImage = annonce.getPhotos().get(0).getUrl();
					ImageLoaderCache.charger(urlImage, _image);
				}
			}

		}else if(type == EMAIL_CLIENT){
			if(objet instanceof Vendeur){
				Vendeur vendeur = (Vendeur)objet;
				if(vendeur.getNom() != null)
					_texte.setText(vendeur.getNom());
				if(vendeur.getLogo() != null){
					ImageLoaderCache.charger(vendeur.getLogo(), _image);
				}
			}
		}
	}

	@Override
	public void ajouterListeners() {
		_departement.setOnClickListener(this);
		_envoyer.setOnClickListener(this);
	}

	public void demanderDepartement(){
		List<Departement> departements = Donnees.departements;
		if(departements != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Departement departement : departements){
				donneesValeurs.put(departement.getNom(), departement.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,DEPARTEMENT,donneesValeurs));
		}
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {
		if(idRetour == DEPARTEMENT){
			departementId = item;
			((TextView)_departement.findViewById(R.id.text)).setText(value);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch(v.getId()){
		case R.id.email_departement:
			demanderDepartement();
			break;
		case R.id.email_bouton_envoyer:
			envoyerEmail();
			break;
		}
	}

	public void envoyerEmail(){

		if(_adresseEmail.getText().length() == 0)
			Toast.makeText(getActivity(), R.string.veuillez_preciser_votre_adresse_email, Toast.LENGTH_LONG).show();
		else if(_nom.getText().length() == 0)
			Toast.makeText(getActivity(), R.string.veuillez_indiquer_votre_nom, Toast.LENGTH_LONG).show();
		else if(_message.getText().length() == 0)
			Toast.makeText(getActivity(), R.string.veuillez_entrer_un_message, Toast.LENGTH_LONG).show();

		else if(task == null){
			task = new EnvoyerEmailTask();
			task.execute();
		}
	}

	public void emailEnvoye(){
		Toast.makeText(getActivity(), R.string.email_envoye,Toast.LENGTH_SHORT).show();
		getFragmentManager().popBackStack();
	}

	/* --------------------------------------------------------------------------- */

	class EnvoyerEmailTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			String email = _adresseEmail.getText().toString();
			String message = _message.getText().toString();
			String nom = _nom.getText().toString();
			String telephone = _telephone.getText().toString();

			if(type == EMAIL_ANNNONCE){
				Annonce annonce = (Annonce)objet;
				if(annonce != null)
					NetEmail.envoyerEmailAnnonce(email, message, annonce.getNumero(), annonce.getType(),nom,telephone,departementId);
			}else if(type == EMAIL_CLIENT){
				Vendeur vendeur = (Vendeur)objet;
				if(vendeur != null)
					NetEmail.envoyerEmailClientDirect(email, message, nom, vendeur.getNumero(),telephone,departementId);
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}	

}
