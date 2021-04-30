package find.ride.kiwi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<LanguageItem> languageList;
    private LanguageSpinnerAdapter languageSpinnerAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLanguageList();

        Spinner spinnerLanguages = view.findViewById(R.id.spinner_language);
        languageSpinnerAdapter = new LanguageSpinnerAdapter(getContext(),languageList);
        spinnerLanguages.setAdapter(languageSpinnerAdapter);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_HomeFragment_to_MapsFragment);
            }
        });
    }

    private void initLanguageList(){
        languageList = new ArrayList<>();
        languageList.add(new LanguageItem("English",R.drawable.uk));
        languageList.add(new LanguageItem("Turkish",R.drawable.turkey));
        languageList.add(new LanguageItem("Ukrainski",R.drawable.ukraine));
    }
}