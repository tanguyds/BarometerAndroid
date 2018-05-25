package com.nalu.barometer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nalu.barometer.R;
import com.nalu.barometer.api.model.Barometer;

import java.util.List;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 16:00
 */
public class BarometersAdapter extends ArrayAdapter<Barometer> {

    public BarometersAdapter(Context context, List<Barometer> barometers) {
        super(context, -1, barometers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Barometer barometer = getItem(position);
        if (convertView == null){
            LayoutInflater inflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.barometer_item, parent, false);
        }

        if (barometer == null) return convertView;

        ImageView ivBarometerItem = convertView.findViewById(R.id.ivBaromterItem);
        byte[] decodedString = Base64.decode(barometer.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ivBarometerItem.setImageBitmap(decodedByte);

        TextView tvBarometerItemName = convertView.findViewById(R.id.tvBarometerItemName);

        tvBarometerItemName.setText(barometer.getName());

        return convertView;
    }
}
