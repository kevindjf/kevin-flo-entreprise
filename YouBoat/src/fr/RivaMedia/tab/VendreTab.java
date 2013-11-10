package fr.RivaMedia.tab;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class VendreTab extends Tab{

	public VendreTab(String titre,Drawable icone) {
		super(titre,icone);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.vendre, container, false);
		
		return v;
	}
	
}
