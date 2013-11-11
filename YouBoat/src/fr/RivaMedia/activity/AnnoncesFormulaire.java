package fr.RivaMedia.activity;

import fr.RivaMedia.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends Activity implements View.OnClickListener{

	public static final String TYPE = "TYPE";
	int type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(getIntent().getExtras()!=null)
			type = getIntent().getExtras().getInt(TYPE);
		
		setContentView(R.layout.annonces_formulaire);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}


}
