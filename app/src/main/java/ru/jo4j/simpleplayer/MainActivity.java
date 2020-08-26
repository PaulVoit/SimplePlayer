package ru.jo4j.simpleplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import ru.jo4j.simpleplayer.fragments.TrackListFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        Fragment trackListFragment = loadFrg();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .replace(R.id.content, trackListFragment)
                    .commit();
        }
    }
    public Fragment loadFrg() {
        return TrackListFragment.of(getIntent().getIntExtra(TrackListFragment.TRACK, 0));
    }
}