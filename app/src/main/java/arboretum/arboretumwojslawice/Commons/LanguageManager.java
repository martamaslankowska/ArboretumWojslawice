package arboretum.arboretumwojslawice.Commons;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class LanguageManager {

    public static void setLocale(Context c) {
        setNewLocale(c, getLanguage(c));
    }

    public static void setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        return c.getResources().getConfiguration().locale.getLanguage();
    }

    private static void persistLanguage(Context c, String language) {
        c.getResources().getConfiguration().locale = new Locale(language);
    }


    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

}
