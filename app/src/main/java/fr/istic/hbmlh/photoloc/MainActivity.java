package fr.istic.hbmlh.photoloc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity principal de l'application
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_map)
    Button btnMap;

    @BindView(R.id.btn_photo)
    Button btnPhoto;

    @BindView(R.id.rclr_view_photo)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // necessaire pour le binding de ButterKnife
        ButterKnife.bind(this);

    }
}
