package fr.istic.hbmlh.photoloc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;

import java.util.List;

public class PhotoLocAdapter extends RecyclerView.Adapter<PhotoLocAdapter.PhotoLocViewHolder> {

    private List<PhotoLoc> photos;

    public List<PhotoLoc> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoLoc> photos) {
        this.photos = photos;
    }

    public static class PhotoLocViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public PhotoLocViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.img_view);
        }

        public ImageView getImageView() {
            return imageView;
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

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_wrapper, parent, false);


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