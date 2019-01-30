package fr.istic.hbmlh.photoloc.adapter;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;

public class PhotoLocAdapter extends RecyclerView.Adapter<PhotoLocAdapter.PhotoLocViewHolder> {

    private final List<PhotoLoc> photos;

    public static class PhotoLocViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public PhotoLocViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public PhotoLocAdapter(final List<PhotoLoc> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoLocViewHolder onCreateViewHolder(
            @NonNull
                    ViewGroup parent, int viewType) {

        // TODO changer pour le chargement d'une image
        final TextView v = new TextView(parent.getContext());

        return new PhotoLocViewHolder(v);

    }

    @Override
    public void onBindViewHolder(
            @NonNull
                    PhotoLocViewHolder holder, int position) {
        // TODO changer pour le chargement d'une image
        holder.mTextView.setText(photos.get(position).getFilePath());

    }

    @Override
    public int getItemCount() {
        return this.photos.size();
    }

}
