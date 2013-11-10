package fr.RivaMedia.tab;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class AnnoncesTab extends Tab{

	public AnnoncesTab(String titre,Drawable icon) {
		super(titre,icon);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.annonces, container, false);
		
		return v;
	}
	
}
