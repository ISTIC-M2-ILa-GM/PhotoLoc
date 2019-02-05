package fr.istic.hbmlh.photoloc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.repository.impl.RepositoriesImpl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PictureViewModel extends AndroidViewModel {


    private final ExecutorService executorService;
    private final PhotoLocRepository photoRepository;

    public PictureViewModel(@NonNull Application application) {
        super(application);
        photoRepository = new RepositoriesImpl(application.getApplicationContext()).getPhotoLocRepository();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<PhotoLoc>> findAll() {
        return this.photoRepository.findAll();
    }


}
