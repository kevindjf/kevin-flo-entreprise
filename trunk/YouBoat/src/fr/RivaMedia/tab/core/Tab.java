package fr.RivaMedia.tab.core;

import fr.RivaMedia.R;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class Tab extends Fragment implements ITitreProvider{

	String titre;

	public Tab(String titre){
		this.titre = titre;
	}

	public CharSequence getTitle() {
		return titre;
	}

	public void afficherVide(View parent, boolean afficher) {
		if(parent != null){
			View view = parent.findViewById(R.id.vide);
			if(view != null){
				if(afficher)
					view.setVisibility(View.VISIBLE);
				else
					view.setVisibility(View.GONE);
			}
		}
	}

}
