package fr.RivaMedia.AnnoncesAutoGenerique.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;

public class MinMaxDialog extends AlertDialog implements View.OnClickListener, OnSeekBarChangeListener
{

	Context _context;
	String _titre;
	OnMinMaxListener _listener;

	TextView _titreView;

	SeekBar _min;
	SeekBar _max;

	TextView _minValeur;
	TextView _maxValeur;

	View _ok;
	View _cancel;
	String _tmpMin = null;
	String _tmpMax = null;
	int _valeurMax;

	public static final String PLUS = "+";

	public MinMaxDialog(Context context, String titre, OnMinMaxListener listener) {
		super(context);
		_context = context;
		_titre = titre;
		_listener = listener;
	}

	public MinMaxDialog(Context context, String titre, OnMinMaxListener listener, String min, String max, int valeurMax) {
		this(context,titre,listener);

		_tmpMin = min;
		_tmpMax = max;
		_valeurMax = valeurMax;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_min_max);
		ajouterVues();
		ajouterListeners();

		super.setCancelable(true);
	}

	protected void ajouterVues(){
		_titreView = (TextView)findViewById(R.id.titre);

		_min = (SeekBar)findViewById(R.id.min);
		_max = (SeekBar)findViewById(R.id.max);

		_minValeur = (TextView)findViewById(R.id.min_valeur);
		_maxValeur = (TextView)findViewById(R.id.max_valeur);

		_ok = findViewById(R.id.ok);
		_cancel = findViewById(R.id.cancel);
		
		_titreView.setText(_titre);

		_min.setMax(_valeurMax);
		_max.setMax(_valeurMax);
		if(_tmpMin != null){
			_min.setProgress(Integer.parseInt(_tmpMin));
			_minValeur.setText(_tmpMin);
		}
		if(_tmpMax != null){
			if(_tmpMax.equals(PLUS)){
				_max.setProgress(_valeurMax);
				_maxValeur.setText(_tmpMax);
			}else{
				_max.setProgress(Integer.parseInt(_tmpMax));
				_maxValeur.setText(_tmpMax);
			}
		}
	}

	protected void ajouterListeners(){
		_min.setOnSeekBarChangeListener(this);
		_max.setOnSeekBarChangeListener(this);

		_ok.setOnClickListener(this);
		_cancel.setOnClickListener(this);
	}

	public void onClick(View v){
		switch(v.getId()){
		case R.id.ok:
			int min, max;

			min = _min.getProgress();
			max = _max.getProgress();

			if(min==max && max==0)
				this.dismiss();
			else{
				String sMax = ""+max;

				if(_maxValeur.getText().toString().equals(PLUS) || _maxValeur.getText().toString().equals(""+_valeurMax))
					sMax = "+";

				if(min>max)
					min=0;

				_listener.onMinMaxSelected(_titre, ""+min, sMax);
				this.dismiss();
			}
			break;
			
		case R.id.cancel:
			this.dismiss();
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar s) {}

	@Override
	public void onStopTrackingTouch(SeekBar s) {}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		if(seekBar.equals(_min)){
			_minValeur.setText(""+progress);			
		}

		else if(seekBar.equals(_max)){
			_maxValeur.setText(""+progress);

			if(_maxValeur.getText().toString().equals(""+_valeurMax)){
				_maxValeur.setText("+");
			}
		}
	}

}