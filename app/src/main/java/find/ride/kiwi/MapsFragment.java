package find.ride.kiwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import find.ride.kiwi.viewmodels.KiwiViewModel;

public class MapsFragment extends Fragment {


    private KiwiViewModel kiwiViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        kiwiViewModel = ViewModelProviders.of(this).get(KiwiViewModel.class);

        kiwiViewModel.init(getContext(),"data.json");

        Log.d("MAPS FRAGMENT KIWIS : ", kiwiViewModel.getKiwis().getValue().toString());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MapsFragment.this)
                        .navigate(R.id.action_MapsFragment_to_HomeFragment);
            }
        });
    }
}