import java.util.Arrays;

public class Song {

    // Delimiter between song information
    private static final String INFO_DELIMITER = "; ";

    // Delimiter between time values
    private static final String TIME_DELIMITER = ":";

    private static final int SECONDS_PER_MINUTE = 60;

    private static final int MINUTES_PER_HOUR = 60;

    private String title;

    private String artist;

    private int[] time;

    // Creates a Song using the given information
    public Song(String title, String artist, int[] time) {

        this.title = title;

        this.artist = artist;

        this.time = Arrays.copyOf(time, time.length);
    }

    // Creates a Song from a formatted String
    public Song(String info) {

        String[] pieces = info.split(INFO_DELIMITER);

        title = pieces[0];

        artist = pieces[1];

        String[] timePieces = pieces[2].split(TIME_DELIMITER);

        time = new int[timePieces.length];

        for (int i = 0; i < timePieces.length; i++) {

            time[timePieces.length - 1 - i] =
                    Integer.parseInt(timePieces[i]);
        }
    }

    // Returns the song title
    public String getTitle() {

        return title;
    }

    // Returns the artist
    public String getArtist() {

        return artist;
    }

    // Returns a copy of the time array
    public int[] getTime() {

        return Arrays.copyOf(time, time.length);
    }

    // Returns the song information as a String
    @Override
    public String toString() {

        String timeString;

        if (time.length == 1) {

            // Only seconds
            timeString = Integer.toString(time[0]);

        } else if (time.length == 2) {

            // Minutes and seconds
            timeString = time[1] + ":"
                    + String.format("%02d", time[0]);

        } else {

            // Hours, minutes, and seconds
            timeString = time[2] + ":"
                    + String.format("%02d", time[1]) + ":"
                    + String.format("%02d", time[0]);
        }

        return title + INFO_DELIMITER + artist
                + INFO_DELIMITER + timeString;
    }

}
