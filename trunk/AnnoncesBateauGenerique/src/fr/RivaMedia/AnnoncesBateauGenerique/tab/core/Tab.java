package fr.RivaMedia.AnnoncesBateauGenerique.tab.core;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import fr.RivaMedia.AnnoncesBateauGenerique.R;

public abstract class Tab extends Fragment implements ITitreProvider{

	String titre;
	Drawable logo = null;

	public Tab(String titre, Drawable logo){
		this.titre = titre;
		this.logo = logo;
	}

	public CharSequence getTitle() {
		Log.e("SPAN","span");
		SpannableStringBuilder ssb = new SpannableStringBuilder("S "+titre);
		logo.setBounds(0, 0, logo.getIntrinsicWidth(), logo.getIntrinsicHeight()); 
		ImageSpan ims = new ImageSpan( logo , ImageSpan.ALIGN_BASELINE);
		ssb.setSpan(ims, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		return ssb;
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
