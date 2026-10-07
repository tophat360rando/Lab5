import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Playlist {

    // Line separator for the current system
    private static final String LINE_SEPARATOR = System.lineSeparator();

    // Stores the songs
    private ArrayList<Song> songs;

    // Creates an empty playlist
    public Playlist() {
        songs = new ArrayList<Song>();
    }

    // Creates a playlist from a file
    public Playlist(String filename) throws IOException {
        this();
        addSongs(filename);
    }

    // Returns the number of songs
    public int getNumSongs() {
        return songs.size();
    }

    // Returns a song at the given index
    public Song getSong(int index) {
        if (index < 0 || index >= getNumSongs()) {
            return null;
        }

        return songs.get(index);
    }

    // Returns the songs as an array
    public Song[] getSongs() {
        return songs.toArray(new Song[0]);
    }

    // Adds a song to the end
    public boolean addSong(Song song) {
        return addSong(getNumSongs(), song);
    }

    // Adds a song at a specific index
    public boolean addSong(int index, Song song) {
        if (song == null || index < 0 || index > getNumSongs()) {
            return false;
        }

        songs.add(index, song);
        return true;
    }

    // Adds songs from another playlist
    public int addSongs(Playlist playlist) {
        if (playlist == null) {
            return 0;
        }

        Song[] otherSongs = playlist.getSongs();

        for (Song song : otherSongs) {
            songs.add(song);
        }

        return otherSongs.length;
    }

    // Adds songs from a file
    public int addSongs(String filename) throws IOException {
        int count = 0;

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;

        while ((line = br.readLine()) != null) {
            Song song = new Song(line);
            songs.add(song);
            count++;
        }

        br.close();

        return count;
    }

    // Removes the last song
    public Song removeSong() {
        return removeSong(getNumSongs() - 1);
    }

    // Removes a song at a specific index
    public Song removeSong(int index) {
        if (index < 0 || index >= getNumSongs()) {
            return null;
        }

        return songs.remove(index);
    }

    // Returns all songs as a String
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(LINE_SEPARATOR);

        for (Song song : songs) {
            joiner.add(song.toString());
        }

        return joiner.toString();
    }

    // Saves the playlist to a file
    public void saveSongs(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        bw.write(toString());

        bw.close();
    }

    // Returns the total playlist time
    public int[] getTotalTime() {
        int totalSeconds = 0;
        int totalMinutes = 0;
        int totalHours = 0;

        for (Song song : songs) {
            int[] time = song.getTime();

            totalSeconds += time[0];

            if (time.length > 1) {
                totalMinutes += time[1];
            }

            if (time.length > 2) {
                totalHours += time[2];
            }
        }

        // Convert seconds and minutes
        totalMinutes += totalSeconds / 60;
        totalSeconds = totalSeconds % 60;

        totalHours += totalMinutes / 60;
        totalMinutes = totalMinutes % 60;

        return new int[] {totalSeconds, totalMinutes, totalHours};
    }

}