package ru.jo4j.simpleplayer.model;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ru.jo4j.simpleplayer.R;

public class TrackStore implements IStore {

    private List<Track> tracks = new ArrayList<>();
    private List<String> names = new ArrayList<>();

    public TrackStore() {
        getRawResources();
        initNames();
    }

    private void initNames() {
        for (Track track : tracks) {
            names.add(track.getName());
        }
    }

    private void getRawResources() {
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            int resourceID = 0;
            try {
                resourceID = fields[i].getInt(fields[i]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            tracks.add(new Track(resourceID, name));
        }
    }

    @Override
    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public int getIdByName(String name) {
        int id = 0;
        for (Track track :
                tracks) {
            if (name.equals(track.getName())) {
                id = track.getTrackId();
            }
        }
        return id;
    }

    @Override
    public List<String> getNames() {
        return names;
    }
}
