package find.ride.kiwi.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import find.ride.kiwi.models.Kiwi;
import find.ride.kiwi.repositories.KiwiRepository;

public class KiwiViewModel extends ViewModel {

    private MutableLiveData<List<Kiwi>> kiwis;
    private KiwiRepository kiwiRepository;

    public void init(Context context, String fileName){
        if (kiwis != null){
            return;
        }
        kiwiRepository = KiwiRepository.getInstance();
        kiwis = kiwiRepository.getKiwis(context,fileName);
    }

    public LiveData<List<Kiwi>> getKiwis() {
        return kiwis;
    }

}
