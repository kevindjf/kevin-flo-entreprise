package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

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
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Client;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetEmail;

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

		afficherProgress(false);

		charger();
		remplir();
		changerCouleur();
		ajouterListeners();

		ImageLoaderCache.load(getActivity());

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		setTitre("Email");
		try{
		trackerEcran("Ecran Email Android");
		}catch(Exception e){
			
		}
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
				if(annonce.getFinition() != null)
					_texte.setText(annonce.getFinition());
				if(annonce.getPhotos() != null && annonce.getPhotos().size()>0){
					String urlImage = annonce.getPhotos().get(0);
					ImageLoaderCache.charger(urlImage, _image);
				}
				
				if(annonce.getPrix() != null && annonce.getFinition() != null){
					String contenu = getString(R.string.email_contenu);
					contenu = contenu.replace("$nom$",annonce.getFinition());
					contenu = contenu.replace("$prix$",annonce.getPrix());
					_message.setText(contenu);
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

		int id = v.getId();
		if (id == R.id.email_departement) {
			demanderDepartement();
		} else if (id == R.id.email_bouton_envoyer) {
			envoyerEmail();
		}
	}

	public void demanderDepartement(){
		/*
		List<Departement> departements = Donnees.departements;
		if(departements != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Departement departement : departements){
				donneesValeurs.put(departement.getNom(), departement.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,DEPARTEMENT,donneesValeurs));
		}
		*/
	}

	
	public void changerCouleur(){
		afficherCouleurNormal(_texte);
		afficherCouleurNormal(_image);
		afficherCouleurTouch(_envoyer);
		selector(_envoyer,false);
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
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getActivity(), R.string.email_envoye,Toast.LENGTH_SHORT).show();
				getFragmentManager().popBackStack();
			}
		});
	}

	/* --------------------------------------------------------------------------- */

	class EnvoyerEmailTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			
			try{

			String email = _adresseEmail.getText().toString();
			String message = _message.getText().toString();
			String nom = _nom.getText().toString();
			String telephone = _telephone.getText().toString();

			if(type == EMAIL_ANNNONCE){
				Annonce annonce = (Annonce)objet;
				if(annonce != null)
					NetEmail.envoyerEmailAnnonce(nom, telephone, email, telephone, message, annonce.getId());
			}else if(type == EMAIL_CLIENT){
				Client client = (Client)objet;
				if(client != null)
					NetEmail.envoyerEmailClient(nom, telephone, email, nom, message, client.getId());
			}
			emailEnvoye();
			
			}catch(Exception e){
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}	

}
