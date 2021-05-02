package find.ride.kiwi.Locale;

import java.util.ArrayList;
import java.util.Locale;
import android.os.Build;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import find.ride.kiwi.Constants;
import find.ride.kiwi.models.LanguageItem;
import find.ride.kiwi.R;

public class LocaleHelper {

    public static Context setLocale(Context context, String language){
        saveLanguagePreference(context,language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

        return context;
    }


    private static void saveLanguagePreference(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SELECTED_LANGUAGE, language);
        editor.apply();
    }

    public static String  saveLanguagePreference(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String language = preferences.getString(Constants.SELECTED_LANGUAGE,"en");
        return language;
    }

    public static ArrayList<LanguageItem> initLanguageList(){
        ArrayList<LanguageItem> languageList = new ArrayList<>();
        languageList.add(new LanguageItem("English", R.drawable.uk,"en"));
        languageList.add(new LanguageItem("Turkish",R.drawable.turkey,"tr"));
        languageList.add(new LanguageItem("Ukrainian",R.drawable.ukraine,"uk"));
        return languageList;
    }


}

