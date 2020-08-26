package ru.jo4j.simpleplayer.model;

import java.util.List;

public interface IStore {
    List<Track> getTracks();

    void setTracks(List<Track> tracks);

    int getIdByName(String name);

    List<String> getNames();
}
