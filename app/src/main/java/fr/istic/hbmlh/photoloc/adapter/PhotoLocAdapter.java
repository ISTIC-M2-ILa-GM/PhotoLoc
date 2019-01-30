package fr.istic.hbmlh.photoloc.adapter;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;

public class PhotoLocAdapter extends RecyclerView.Adapter<PhotoLocAdapter.PhotoLocViewHolder> {

    private  List<PhotoLoc> photos;

    public List<PhotoLoc> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoLoc> photos) {
        this.photos = photos;
    }

    public static class PhotoLocViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public PhotoLocViewHolder(ImageView v) {
            super(v);
            imageView = v;
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

        final ImageView v = new ImageView(parent.getContext());

        return new PhotoLocViewHolder(v);

    }

    @Override
    public void onBindViewHolder(
            @NonNull
                    PhotoLocViewHolder holder, int position) {
        final PhotoLoc photo = photos.get(position);

        Picasso.get().load(photo.getFilePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.photos.size();
    }



}
