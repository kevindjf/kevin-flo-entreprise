package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class Credit extends FragmentNormal implements View.OnClickListener{
	View contact_pro_telephone;
	View contact_pro_email;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.credit,container, false);
		contact_pro_telephone = view.findViewById(R.id.credit_telephone);
		contact_pro_email = view.findViewById(R.id.credit_email);
		ajouterListeners();
		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.contact_pro_telephone :
			super.appeller(((TextView)(contact_pro_telephone.findViewById(R.id.text))).getText().toString());
			break;
			
		case R.id.contact_pro_email :
			super.envoyerEmail(getString(R.string.adresse_email_youboat));
			break;
		}
	}

	@Override
	public void charger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remplir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterListeners() {
		contact_pro_telephone.setOnClickListener(this);
		contact_pro_email.setOnClickListener(this);	
	}

}
