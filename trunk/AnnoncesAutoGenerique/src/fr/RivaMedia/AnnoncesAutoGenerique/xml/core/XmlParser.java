package fr.RivaMedia.AnnoncesAutoGenerique.xml.core;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.util.Log;

public abstract class XmlParser {

	protected XmlPullParserFactory _factory = null;  //Recupere le contenu d'un fichier XML

	protected XmlPullParser _xpp = null;             //parcours le contenu d'un factory XML

	public XmlParser(String xml) {
		try {
			_factory = XmlPullParserFactory.newInstance();
			_factory.setNamespaceAware(true);
			_xpp = _factory.newPullParser();
			_xpp.setInput(new StringReader(xml.replaceAll("&", "&amp;")));
		} catch (Exception e) {
			Log.e("XML", "Erreur creation parser XML");
			e.printStackTrace();
		}
	}

	public int XMLgetEventType(){
		int eventType = -1;
		try {
			eventType = getXpp().getEventType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventType;
	}

	public int XMLgetSuivant(){
		int eventType = -1;
		try {
			eventType = getXpp().next();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return eventType;
	}

	public XmlParser(XmlPullParser xpp) {
		this._xpp = xpp;
	}

	public XmlPullParser getXpp() {
		return _xpp;
	}

	protected Integer getInteger() {
		Integer i = null;
		String s = getString();
		if(s != null && s.length()>0)
			i = Integer.valueOf(s);
		return i;
	}

	protected Double getDouble() {
		Double d = null;
		String s = getString();
		if(s != null && s.length()>0)
			d = Double.valueOf(s);
		return d;
	}

	protected String getString() {
		String s = "";
		try {
			if(getXpp().getEventType() == getXpp().START_TAG)
				getXpp().next(); //passe au contenu
			if(getXpp().getEventType() == getXpp().TEXT){
				s = getXpp().getText();
				if(s != null)
					s = s.trim().replaceAll("&amp;","&");
				getXpp().next(); //passe le tag de fermeture
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return s;
	}

	@SuppressLint("SimpleDateFormat")
	protected Date getDate(){
		Date d=null;

		String s = getString();

		if(s != null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				d = formatter.parse(s);
			} catch (ParseException e) {

				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					d = formatter2.parse(s);
				} catch (ParseException e2) {
					e.printStackTrace();
					e2.printStackTrace();
				}
			}
		}

		return d;
	}

	@SuppressLint("SimpleDateFormat")
	protected Date getDateTime() {
		Date d=null;

		String s = getString();
		if(s != null && s.length()>0){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				d = formatter.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return d;
	}

	protected Boolean getBoolean() {
		Boolean b = null;
		String s = getString();
		if(s != null && s.length()>0)
			b = Boolean.valueOf("1".equals(s));

		return b;
	}

}
