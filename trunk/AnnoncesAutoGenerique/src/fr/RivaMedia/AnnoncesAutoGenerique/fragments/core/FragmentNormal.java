package fr.RivaMedia.AnnoncesAutoGenerique.fragments.core;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.CallDialog;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import fr.RivaMedia.AnnoncesAutoGenerique.activity.*;

public abstract class FragmentNormal extends Fragment implements IFragment, OnClickListener{

	protected AsyncTask<Void, Void, Void> task;
	protected boolean visible = false;
	protected boolean afficherProgress = false;


	public void afficherProgress(boolean afficher){
		this.afficherProgress = afficher;
		((MainActivityAnnoncesAuto)getActivity()).afficherProgress(afficher);
	}

	@Override
	public void onPause() {
		((MainActivityAnnoncesAuto)getActivity()).cacherEffacer();
		((MainActivityAnnoncesAuto)getActivity()).cacherTrier();
		((MainActivityAnnoncesAuto)getActivity()).cacherFavoris();
		((MainActivityAnnoncesAuto)getActivity()).cacherPlus();
		afficherProgress(afficherProgress);
		try{
			if(task != null)
				this.task.cancel(true);
		}
		catch(Exception e){e.printStackTrace();}
		super.onPause();
	}


	public void trackerEcran(String title){

		Tracker easyTracker = EasyTracker.getInstance(getActivity());

		// This screen name value will remain set on the tracker and sent with
		// hits until it is set to a new value or to null.
		easyTracker.set(Fields.SCREEN_NAME, title + getString(R.string.app_name));

		// Now this event hit will not include a screen name value.
		easyTracker.send(MapBuilder
				.createAppView()
				.build()
				);
	}

	@Override
	public void onResume() {
		((MainActivityAnnoncesAuto)getActivity()).cacherEffacer();
		((MainActivityAnnoncesAuto)getActivity()).cacherTrier();
		((MainActivityAnnoncesAuto)getActivity()).cacherFavoris();
		((MainActivityAnnoncesAuto)getActivity()).cacherPlus();

		afficherProgress(afficherProgress);
		super.onResume();
		getView().setOnClickListener(this);
	}

	@Override
	public void onViewStateRestored(){
		Log.e("FragmentNormal","Refresh");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser == true) { 
			Log.d("VISIBLE", "this fragment is now visible");
			visible = true;
		}

		else if (isVisibleToUser == false) {  
			Log.d("VISIBLE", "this fragment is now invisible");
			visible = false;
		}
	}

	public void ajouterFragment(Fragment fragment){
		ajouterFragment( fragment, true);
	}
	public void ajouterFragment(Fragment fragment, boolean back){
		((MainActivityAnnoncesAuto)getActivity()).ajouterFragment(fragment,back);
	}

	public void envoyerEmailVendeur(String email, Vendeur vendeur){
		((MainActivityAnnoncesAuto)getActivity()).envoyerEmailVendeur(email, vendeur);
	}
	public void envoyerEmailAnnonce(String email, Annonce annonce){
		((MainActivityAnnoncesAuto)getActivity()).envoyerEmailAnnonce(email, annonce);
	}
	public void envoyerEmailDirect(String email){
		((MainActivityAnnoncesAuto)getActivity()).envoyerEmailDirect(email);
	}
	public void appeller(String phone){
		new CallDialog(getActivity(), getString(R.string.telephoner), phone).show();
	}

	@Override
	public void onClick(View v) {

	}

	public View afficherFavoris(){
		return ((MainActivityAnnoncesAuto)getActivity()).afficherFavoris();
	}

	public void cacherFavoris(){
		((MainActivityAnnoncesAuto)getActivity()).cacherFavoris();
	}

	public void setTitre(String titre){
		((MainActivityAnnoncesAuto)getActivity()).setTitre(titre);
	}

	public void ajouterContactPro() {
		((MainActivityAnnoncesAuto)getActivity()).ajouterContactPro();
	}

	public static void afficherCouleurNormal(View...vs){
		for(View v : vs)
			afficherCouleurNormal(v);
	}
	public static void afficherCouleurNormal(View v){
		Log.e("Je color","normalement");
		v.setBackgroundColor(Donnees.parametres.getCouleurPrincipale());
	}
	public static void afficherCouleurTouch(View v){
		v.setBackgroundColor(Donnees.parametres.getCouleurSecondaire());
	}

	public static void afficherTexteCouleurTexte(View v){
			try{
				((TextView)v).setTextColor(Donnees.parametres.getCouleurTexte());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.text)).setTextColor(Donnees.parametres.getCouleurTexte());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.titre)).setTextColor(Donnees.parametres.getCouleurTexte());
			}catch(Exception e){
			}
	}
	public static void afficherTexteCouleurTexte(View...views){
		for(View v : views){
			afficherTexteCouleurTexte(v);
		}
	}
	public static void afficherTexteCouleurTitre(View v){
			try{
				((TextView)v).setTextColor(Donnees.parametres.getCouleurTitre());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.text)).setTextColor(Donnees.parametres.getCouleurTitre());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.titre)).setTextColor(Donnees.parametres.getCouleurTitre());
			}catch(Exception e){
			}
	}
	public static void afficherTexteCouleurTitre(View...views){
		for(View v : views){
			afficherTexteCouleurTitre(v);
		}
	}

	public static void selector(View v){
		selector(v,true);
	}
	public static void selector(View v, final boolean primaire){
		afficherTexteCouleurTitre(v);
		v.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("Je touche", "Je vais ou ?" + event.getAction());
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if(primaire)
						afficherCouleurTouch(v);
					else
						afficherCouleurNormal(v);
					Log.e("Je touche", "Down");
					break;

				case MotionEvent.ACTION_CANCEL:
					if(primaire)
						afficherCouleurNormal(v);
					else 
						afficherCouleurTouch(v);
					break;

				case MotionEvent.ACTION_UP:
					if(primaire)
						afficherCouleurNormal(v);
					else 
						afficherCouleurTouch(v);
					Log.e("Je touche", "Up");

					break;

				}

				return false;
			}
		});
	}




}
