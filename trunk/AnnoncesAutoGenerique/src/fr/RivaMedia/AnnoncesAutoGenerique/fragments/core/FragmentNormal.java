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

import fr.RivaMedia.AnnoncesAutoGenerique.activity.*;

public abstract class FragmentNormal extends Fragment implements IFragment, OnClickListener{

	protected AsyncTask<Void, Void, Void> task;
	protected boolean visible = false;
	protected boolean afficherProgress = false;


	public void afficherProgress(boolean afficher){
		this.afficherProgress = afficher;
		((MainActivity)getActivity()).afficherProgress(afficher);
	}

	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		((MainActivity)getActivity()).cacherTrier();
		((MainActivity)getActivity()).cacherFavoris();
		((MainActivity)getActivity()).cacherPlus();
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
		easyTracker.set(Fields.SCREEN_NAME, title);

		// Now this event hit will not include a screen name value.
		easyTracker.send(MapBuilder
				.createAppView()
				.build()
				);
	}

	@Override
	public void onResume() {
		((MainActivity)getActivity()).cacherEffacer();
		((MainActivity)getActivity()).cacherTrier();
		((MainActivity)getActivity()).cacherFavoris();
		((MainActivity)getActivity()).cacherPlus();

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
		((MainActivity)getActivity()).ajouterFragment(fragment,back);
	}

	public void envoyerEmailVendeur(String email, Vendeur vendeur){
		((MainActivity)getActivity()).envoyerEmailVendeur(email, vendeur);
	}
	public void envoyerEmailAnnonce(String email, Annonce annonce){
		((MainActivity)getActivity()).envoyerEmailAnnonce(email, annonce);
	}
	public void envoyerEmailDirect(String email){
		((MainActivity)getActivity()).envoyerEmailDirect(email);
	}
	public void appeller(String phone){
		new CallDialog(getActivity(), getString(R.string.telephoner), phone).show();
	}

	@Override
	public void onClick(View v) {

	}

	public View afficherFavoris(){
		return ((MainActivity)getActivity()).afficherFavoris();
	}

	public void cacherFavoris(){
		((MainActivity)getActivity()).cacherFavoris();
	}

	public void setTitre(String titre){
		((MainActivity)getActivity()).setTitre(titre);
	}
	
	public void ajouterContactPro() {
		((MainActivity)getActivity()).ajouterContactPro();
	}

	public static void afficherCouleurNormal(View v){
		Log.e("Je color","normalement");
		v.setBackgroundColor(Donnees.parametres.getCouleurPrincipale());
	}
	public static void afficherCouleurTouch(View v){
		v.setBackgroundColor(Donnees.parametres.getCouleurSecondaire());
	}

	public static void selector(View v){
		selector(v,true);
	}
	public static void selector(View v, final boolean primaire){
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
