package fr.istic.hbmlh.photoloc;

import static fr.istic.hbmlh.photoloc.service.impl.PhotoServiceImpl.REQUEST_TAKE_PHOTO;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.istic.hbmlh.photoloc.adapter.PhotoLocAdapter;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.repository.impl.RepositoriesImpl;
import fr.istic.hbmlh.photoloc.service.PhotoService;
import fr.istic.hbmlh.photoloc.service.impl.PhotoServiceImpl;

/**
 * Activity principal de l'application
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_map)
    protected Button btnMap;

    @BindView(R.id.btn_photo)
    protected Button btnPhoto;

    @BindView(R.id.rclr_view_photo)
    protected RecyclerView recyclerView;

    private PhotoService photoService;

    private PhotoLocRepository photoLocRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // necessaire pour le binding de ButterKnife
        ButterKnife.bind(this);

        photoLocRepository = new RepositoriesImpl(this).getPhotoLocRepository();
        photoService = new PhotoServiceImpl(photoLocRepository);
        btnPhoto.setOnClickListener((view) -> photoService.takePicture(this));

        final List<PhotoLoc> photos = this.photoLocRepository.findAll();

        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.recyclerView.setAdapter(new PhotoLocAdapter(photos));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            photoService.saveLastPhoto();
        }
    }
}
