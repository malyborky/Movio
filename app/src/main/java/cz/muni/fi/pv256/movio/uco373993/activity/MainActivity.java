package cz.muni.fi.pv256.movio.uco373993.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cz.muni.fi.pv256.movio.uco373993.R;
import cz.muni.fi.pv256.movio.uco373993.fragment.MovieDetailFragment;
import cz.muni.fi.pv256.movio.uco373993.fragment.MovieListFragment;
import cz.muni.fi.pv256.movio.uco373993.model.Movie;
import cz.muni.fi.pv256.movio.uco373993.sync.UpdaterSyncAdapter;

public class MainActivity extends AppCompatActivity implements MovieListFragment.Callback {

    private boolean mTabletMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdaterSyncAdapter.initializeSyncAdapter(this);

        if (findViewById(R.id.detail) != null) {
            mTabletMode = true;
        } else {
            mTabletMode = false;
        }
    }

    @Override
    public void onItemClick(Movie movie) {
        if (mTabletMode) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Movie", movie);
            movieDetailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail, movieDetailFragment).commit();
        } else {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("Movie", movie);
            startActivity(intent);
        }
    }


}
