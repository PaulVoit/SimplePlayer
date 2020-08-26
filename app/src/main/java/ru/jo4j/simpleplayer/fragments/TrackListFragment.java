package ru.jo4j.simpleplayer.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.jo4j.simpleplayer.R;
import ru.jo4j.simpleplayer.model.IStore;
import ru.jo4j.simpleplayer.model.Track;
import ru.jo4j.simpleplayer.model.TrackStore;

public class TrackListFragment extends Fragment {
    private MediaPlayer media;
    private IStore store;
    private RecyclerView recycler;
    private TrackListAdapter adapter;
    private int resumePosition;
    public static final String TRACK = "track";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_items, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tracklist, container, false);
        updateUI(view);
        return view;
    }

    public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TrackHolder> {
        private List<Track> tracks;

        public TrackListAdapter(List<Track> tracks) {
            this.tracks = tracks;
        }

        public class TrackHolder extends RecyclerView.ViewHolder {
            public TrackHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        @NonNull
        @Override
        public TrackListAdapter.TrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_track, parent, false);
            return new TrackListAdapter.TrackHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final TrackListAdapter.TrackHolder holder, final int position) {
            final Button button = holder.itemView.findViewById(R.id.name);
            button.setText(tracks.get(position).getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    media = MediaPlayer.create(getActivity(), store.getTracks().get(position).getTrackId());
                    try {
                        if (!media.isPlaying()) {
                            media.start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return tracks.size();
        }
    }

    public void updateUI(View view) {
        store = new TrackStore();
        recycler = view.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new TrackListAdapter(store.getTracks());
        recycler.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stop_playing:
                if (media.isPlaying()) {
                    media.stop();
                }
                return true;
            case R.id.pause_playing:
                if (media.isPlaying()) {
                    media.pause();
                    resumePosition = media.getCurrentPosition();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static TrackListFragment of(int index) {
        TrackListFragment fragment = new TrackListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TrackListFragment.TRACK, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (media != null) media.release();
    }
}

