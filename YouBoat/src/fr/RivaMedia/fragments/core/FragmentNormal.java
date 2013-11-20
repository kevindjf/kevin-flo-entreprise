package fr.RivaMedia.fragments.core;

import fr.RivaMedia.activity.MainActivity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class FragmentNormal extends Fragment implements IFragment, OnClickListener{

	protected AsyncTask<Void, Void, Void> task;
	protected boolean visible = false;
	protected boolean afficherProgress = false;
	
	public void afficherProgress(boolean afficher){
		((MainActivity)getActivity()).afficherProgress(afficher);
	}
	
	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		afficherProgress(afficherProgress);
		try{
			if(task != null)
				this.task.cancel(true);
		}
		catch(Exception e){e.printStackTrace();}
		super.onPause();
	}



	@Override
	public void onResume() {
		((MainActivity)getActivity()).cacherEffacer();
		afficherProgress(afficherProgress);
		super.onResume();
		getView().setOnClickListener(this);
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
	
	public void envoyerEmail(String email){
		((MainActivity)getActivity()).envoyerEmail(email);
	}
	public void appeller(String phone){
		((MainActivity)getActivity()).appeller(phone);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
