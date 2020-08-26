package ru.jo4j.simpleplayer.model;

public class Track {
    private int trackId;
    private String name;

    public Track(int trackId, String name) {
        this.trackId = trackId;
        this.name = name;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;

        Track that = (Track) o;

        if (getTrackId() != that.getTrackId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getTrackId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
