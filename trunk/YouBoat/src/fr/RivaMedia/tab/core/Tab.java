package fr.RivaMedia.tab.core;

import fr.RivaMedia.R;
import android.text.style.ImageSpan;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;

public abstract class Tab extends Fragment implements ITitreProvider{

	String titre;
	Drawable icon;

	public Tab(String titre, Drawable icon){
		this.titre = titre;
		this.icon = icon;
	}

	@Override
	public CharSequence getTitle() {

		SpannableStringBuilder sb = new SpannableStringBuilder("  " + titre); // space added before text for convenience

		icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight()); 
		ImageSpan span = new ImageSpan(icon, ImageSpan.ALIGN_BASELINE); 
		sb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 

		return sb;
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
