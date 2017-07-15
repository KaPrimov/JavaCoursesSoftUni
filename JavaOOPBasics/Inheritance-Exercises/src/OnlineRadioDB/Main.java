package OnlineRadioDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(reader.readLine());
        int songAdded = 0;
        int secondsAdded = 0;
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(";");
            try{
                String artistName = input[0];
                Artist artist = new Artist(artistName);
                String songName = input[1];
                String[] time = input[2].split(":");
                int minutes = 0;
                int seconds = 0;
                try{
                    minutes = Integer.valueOf(time[0]);
                    seconds = Integer.valueOf(time[1]);
                } catch (NumberFormatException ex){
                    throw new InvalidSongLengthException();
                }
                Song song = new Song(songName, minutes, seconds);

                System.out.println("Song added.");
                songAdded++;
                secondsAdded += (minutes * 60) + seconds;

            } catch (InvalidSongException ex){
                System.out.println(ex.getMessage());
            }
        }

        System.out.println(String.format("Songs added: %d", songAdded));
        int hours = secondsAdded / 3600;
        secondsAdded %= 3600;
        int minutes = secondsAdded / 60;
        secondsAdded %= 60;
        int seconds = secondsAdded;
        System.out.println(String.format("Playlist length: %dh %dm %ds", hours, minutes, seconds));
    }
}