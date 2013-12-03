package fr.RivaMedia.utils;

import android.util.Log;

public class DateFrancais {

	private static String changerJour(String jour){
		if(jour.equals("Mon"))
			return "Lundi";
		if(jour.equals("Tue"))
			return "Mardi";
		if(jour.equals("Wed"))
			return "Mercredi";
		if(jour.equals("Thu"))
			return "Jeudi";
		if(jour.equals("Fri"))
			return "Vendredi";
		if(jour.equals("Sat"))
			return "Samedi";
		if(jour.equals("Sun"))
			return "Dimanche";

		return "Err";		
	}

	private static String changerMois(String mois){
		if(mois.equals("Jan"))
			return "Janvier";
		if(mois.equals("Feb"))
			return "Février";
		if(mois.equals("Mar"))
			return "Mars";
		if(mois.equals("Apr"))
			return "Avril";
		if(mois.equals("May"))
			return "Mai";
		if(mois.equals("Jun"))
			return "Juin";
		if(mois.equals("Jul"))
			return "Juillet";
		if(mois.equals("Aug"))
			return "Août";
		if(mois.equals("Sep"))
			return "Septembre";
		if(mois.equals("Oct"))
			return "Octobre";
		if(mois.equals("Nov"))
			return "Novembre";
		if(mois.equals("Dec"))
			return "Décembre";
		return "Err";
	}
	@SuppressWarnings("unused")
	private static String changerDate(String dateEn){
		String dateFr ="";
		Log.e("Jour", dateEn.substring(0,3));
		dateFr+= changerJour(dateEn.substring(0,3))+dateEn.substring(3,7) + " "
				+ changerMois(dateEn.substring(8,11))+ dateEn.substring(11,17) + "à "+dateEn.substring(17,22);
		Log.e("DateFr",dateFr);
		return dateFr;
	}

	public static String dateEnToFr(String date){
		
		return date.substring(8)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
	}
	public static String convertirDate(String date){

		date = date.replace("Jan", "Janvier")
				.replace("Feb", "Feb")
				.replace("Mars", "Avril")
				.replace("May", "Mai")
				.replace("Jun", "Juin")
				.replace("Jul", "Juillet")
				.replace("Aug", "Août")
				.replace("Sep", "Sep")
				.replace("Oct", "Octobre")
				.replace("Nov", "Novembre")
				.replace("Dec", "Décembre")

				.replace("Mon", "Lundi")
				.replace("Tue", "Mardi")
				.replace("Wed", "Mercredi")
				.replace("Thu", "Jeudi")
				.replace("Fri", "Vendredi")
				.replace("Sat", "Samedi")
				.replace("Sun", "Dimanche")

				.replace(",", "");

		String [] elements = date.split(" ");
		String jourNom = elements[0];
		String jour = elements[1];
		String mois = elements[2];
		String annee = elements[3];
		String heureComplete = elements[4];

		String[] hs = heureComplete.split(":");
		String h = hs[0];
		String m = hs[1];

		date = jourNom+" "+jour+" "+mois+" "+annee+" à "+h+"h"+m;

		return date;

	}
	
}
