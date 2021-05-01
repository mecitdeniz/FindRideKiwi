package find.ride.kiwi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.AdapterView;
import android.view.LayoutInflater;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import find.ride.kiwi.Locale.LocaleHelper;

public class HomeFragment extends Fragment {

    private ArrayList<LanguageItem> languageList;
    private LanguageSpinnerAdapter languageSpinnerAdapter;
    private TextView textView;
    private Button btn;
    private String savedLanguage;
    Context context;
    Resources resources;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //Get Saved Language
        savedLanguage = LocaleHelper.saveLanguagePreference(getContext());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        languageList = LocaleHelper.initLanguageList();

        textView = view.findViewById(R.id.textview_first);
        btn = view.findViewById(R.id.button_first);

        Spinner spinnerLanguages = view.findViewById(R.id.spinner_language);

        languageSpinnerAdapter = new LanguageSpinnerAdapter(getContext(),languageList);
        spinnerLanguages.setAdapter(languageSpinnerAdapter);

        for(int position = 0; position<languageList.size();position++){
            if (languageList.get(position).getLangCode() == savedLanguage){
                spinnerLanguages.setSelection(position);
            }
        }

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LanguageItem selectedItem = (LanguageItem) adapterView.getItemAtPosition(i);
                context = LocaleHelper.setLocale(getContext(),selectedItem.getLangCode());
                resources = context.getResources();
                textView.setText(resources.getText(R.string.where));
                btn.setText(resources.getText(R.string.yes));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_HomeFragment_to_MapsFragment);
            }
        });
    }


}