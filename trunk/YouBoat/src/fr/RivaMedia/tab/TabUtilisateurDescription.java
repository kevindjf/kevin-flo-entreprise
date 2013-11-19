package fr.RivaMedia.tab;


import fr.RivaMedia.R;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.tab.core.Tab;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("ValidFragment") 
public class TabVendeurDescription extends Tab {

	Vendeur _vendeur;
	TextView _description;
	ViewGroup _services;
	
	public TabVendeurDescription(String titre, Vendeur vendeur){
		super(titre);
		this._vendeur = vendeur;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.vendeur_description, container, false);
		
		

		return v;
	}

}