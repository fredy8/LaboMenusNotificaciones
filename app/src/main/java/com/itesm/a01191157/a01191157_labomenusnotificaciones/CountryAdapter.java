package com.itesm.a01191157.a01191157_labomenusnotificaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alfredo_altamirano on 10/23/15.
 */
public class CountryAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private List<String> countries;

    public CountryAdapter(Context context, int resource, List<String> countries) {
        super(context, resource, countries);

        this.context = context;
        this.resource = resource;
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.renglon_layout, parent, false);

        String country = countries.get(position);
        ((TextView)row.findViewById(R.id.country)).setText(country);
        return row;
    }

}
