package fr.RivaMedia.AnnoncesAutoGenerique.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;

public class CallDialog extends AlertDialog implements View.OnClickListener {
	Context _context;
	String _titre;
	String _numeroTel;
	TextView _titreView;


	TextView _numValeur;

	View _ok;
	View _cancel;
	


	public CallDialog(Context context, String titre,String numeroTel) {
		super(context);
		_context = context;
		_titre = titre;
		_numeroTel = numeroTel;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_appeller_holo);
		ajouterVues();
		ajouterListeners();

		super.setCancelable(true);
	}

	protected void ajouterVues(){
		_titreView = (TextView)findViewById(R.id.titre);
		_numValeur = (TextView)findViewById(R.id.num_valeur);
		_numValeur.setText(_numeroTel);
		_ok = findViewById(R.id.ok);
		_cancel = findViewById(R.id.cancel);

		_titreView.setText(_titre);

	}

	protected void ajouterListeners(){
		_ok.setOnClickListener(this);
		_cancel.setOnClickListener(this);
	}

	public void onClick(View v){
		switch(v.getId()){
		case R.id.ok:
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:"+_numeroTel));
			_context.startActivity(callIntent);
			this.dismiss();
			break;

		case R.id.cancel:
			this.dismiss();
			break;
		}
	}
}