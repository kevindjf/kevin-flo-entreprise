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
	int _valeurMin = 0;

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
	

	public MinMaxDialog(Context context, String titre, OnMinMaxListener listener, String min, String max, int valeurMin, int valeurMax) {
		this(context,titre,listener);

		_tmpMin = min;
		_tmpMax = max;
		_valeurMax = valeurMax;
		_valeurMin = valeurMin;
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
			_min.setProgress(Integer.parseInt(_tmpMin)-_valeurMin);
			_minValeur.setText(_tmpMin);
		}else
			_minValeur.setText(""+_valeurMin);
		if(_tmpMax != null){
			if(_tmpMax.equals(PLUS)){
				_max.setProgress(_valeurMax-_valeurMin);
				_maxValeur.setText(_tmpMax);
			}else{
				_max.setProgress(Integer.parseInt(_tmpMax)-_valeurMin);
				_maxValeur.setText(_tmpMax);
			}
		}else
			_maxValeur.setText(""+_valeurMin);
			
	}

	protected void ajouterListeners(){
		_min.setOnSeekBarChangeListener(this);
		_max.setOnSeekBarChangeListener(this);

		_ok.setOnClickListener(this);
		_cancel.setOnClickListener(this);
	}

	public void onClick(View v){
		int id = v.getId();
		if (id == R.id.ok) {
			int min, max;
			min = (_valeurMin+_min.getProgress());
			max = (_valeurMin+_max.getProgress());
			if(min==max && max==_valeurMin)
				this.dismiss();
			else{
				String sMax = ""+max;

				if(_maxValeur.getText().toString().equals(PLUS) || _maxValeur.getText().toString().equals(""+_valeurMax))
					sMax = "+";

				if(min>max)
					min=_valeurMin;

				_listener.onMinMaxSelected(_titre, ""+min, sMax);
				this.dismiss();
			}
		} else if (id == R.id.cancel) {
			this.dismiss();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar s) {}

	@Override
	public void onStopTrackingTouch(SeekBar s) {}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		if(seekBar.equals(_min)){
			_minValeur.setText(""+(_valeurMin+progress));			
		}

		else if(seekBar.equals(_max)){
			_maxValeur.setText(""+(_valeurMin+progress));

			if(_maxValeur.getText().toString().equals(""+(_valeurMin+_valeurMax))){
				_maxValeur.setText("+");
			}
		}
	}

}