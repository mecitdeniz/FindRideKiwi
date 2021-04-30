package find.ride.kiwi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LanguageSpinnerAdapter extends ArrayAdapter<LanguageItem> {

    public LanguageSpinnerAdapter(Context context, ArrayList<LanguageItem> languageList){
        super(context,0,languageList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    public View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.language_spinner_row,parent,false);
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.imageView_flag);

        LanguageItem currentItem = getItem(position);
        if(currentItem != null){
            imageViewFlag.setImageResource(currentItem.getImage());
        }
        return convertView;
    }

}
