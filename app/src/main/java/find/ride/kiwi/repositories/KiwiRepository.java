package find.ride.kiwi.repositories;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import find.ride.kiwi.models.Kiwi;

public class KiwiRepository {

    private static KiwiRepository instance;
    private ArrayList<Kiwi> kiwis = new ArrayList<>();

    public static KiwiRepository getInstance(){
        if (instance == null){
            instance = new KiwiRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Kiwi>> getKiwis(Context context,String fileName){
        setKiwis(context,fileName);
        MutableLiveData<List<Kiwi>> data = new MutableLiveData<>();
        data.setValue(kiwis);
        return data;
    }

    private void setKiwis(Context context,String fileName){
        try {
            JSONObject object = new JSONObject(readJSON(context,fileName));
            JSONArray features = object.getJSONArray("features");

            for (int i = 0; i<features.length();i++){
                JSONObject feature = features.getJSONObject(i);
                JSONObject geometry = feature.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                double latitude = coordinates.getDouble(0);
                double longitude = coordinates.getDouble(1);
                Kiwi kiwi = new Kiwi(latitude,longitude);
                kiwis.add(kiwi);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String readJSON(Context context, String fileName){
        String json = null;
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
