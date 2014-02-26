package fr.RivaMedia.ProjetBateauGenerique;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import fr.RivaMedia.AnnoncesBateauGenerique.R;

import android.app.Application;

@ReportsCrashes(formKey = "", // will not be used
mailTo = "greg@rivamedia.fr",
mode = ReportingInteractionMode.TOAST,
resToastText = R.string.crash_toast_text)
public class MyApplication extends Application {
	@Override
	public void onCreate() {
        super.onCreate();

        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
