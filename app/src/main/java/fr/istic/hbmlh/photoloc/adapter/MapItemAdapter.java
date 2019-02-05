package fr.istic.hbmlh.photoloc.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class MapItemAdapter implements GoogleMap.InfoWindowAdapter {

    private ImageView imageView;

    public MapItemAdapter(Context context) {
        imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(lp);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        Picasso.get().load("file://" + marker.getTitle())
                .resize(300, 300).centerCrop()
                .into(imageView);
        return imageView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
