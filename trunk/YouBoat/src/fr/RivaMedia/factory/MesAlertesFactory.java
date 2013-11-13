package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.MesAlertes;

public class MesAlertesFactory {

	public static MesAlertes getMesAlertes(){
		
		MesAlertes mesAlertes = new MesAlertes();
		
		mesAlertes.setNumero("1");
		mesAlertes.setIdCategorie("1");
		mesAlertes.setTailleMin(100.0);
		mesAlertes.setTailleMax(1000.0);
		mesAlertes.setBudgetMin(0.0);
		mesAlertes.setBudgetMax(10000.0);
		mesAlertes.setDate("10/01/2010");
		mesAlertes.setDateFormattee("10 janvier 2010");
		
		return mesAlertes;
	
	}
	
	public static List<MesAlertes> getListeMesAlertes(){
		List<MesAlertes> mesAlertes = new ArrayList<MesAlertes>();
		for(int i=0;i<10;++i)
			mesAlertes.add(getMesAlertes());
		return mesAlertes;
	}
	
}
