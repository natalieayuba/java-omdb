/*
 * Movie.java
 * This class represents a Movie object.
 * Each Movie has a title, year, certificate, genre, duration (in mins) and an average rating.
 * @author Natalie Ayuba (n.ayuba@uea.ac.uk)
 */

package omdb;

import java.util.ArrayList;

public class Movie implements Comparable <Movie> {

    // Certificate types
    public enum Certificate {
        G("G"),
        PG("PG"),
        PG13("PG-13"),
        R("R"),
        APPROVED("APPROVED"),
        NOT_RATED("NOT RATED"),
        UNRATED("UNRATED"),
        PASSED("PASSED"),
        TV14("TV-14"),
        X("X"),
        M("M"),
        N_A("N/A");

        private final String certificate;

        Certificate(String certificate){
            this.certificate = certificate;
        }

        @Override
        public String toString() {
            return this.certificate;
        }
    }

    // Class properties
    private final String title;
    private final int year;
    private final Certificate certificate;
    private final ArrayList<String> genres;
    private final int duration;
    private double rating;

    // Default Constructor
    private Movie() {
        this.title = null;
        this.year = 0;
        this.certificate = null;
        this.genres = null;
        this.duration = 0;
        this.rating = 0.0;
    }

    // Main constructor
    public Movie(String title, int year, Certificate certificate, ArrayList<String> genres, int duration, double rating) {
        this.title = title;
        this.year = year;
        this.certificate = certificate;
        this.genres = genres;
        this.duration = duration;
        this.rating = rating;
    }

    // Accessor methods
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    //toString method
    @Override
    public String toString() {
        StringBuilder genreList = new StringBuilder();
        if (genres != null) {
            for(int i=0; i<genres.size(); i++) {
                genreList.append(genres.get(i));
                if(!genres.get(i).equals(genres.get(genres.size() - 1))) {
                    genreList.append("/");
                }
            }
        }
        return "\"" + title + "\","
                + year + ","
                + "\"" + certificate + "\","
                + "\"" + genreList + "\","
                + duration + ","
                + rating + "\n";
    }

    // compareTo method, sort films chronologically
    @Override
    public int compareTo (Movie otherMovie) {
        return this.year - otherMovie.year;
    }

    //-------------------------------
    //  TEST HARNESS
    //-------------------------------

    /*public static void main(String[] args) {
        System.out.println("TEST HARNESS");
        Certificate cert = Certificate.valueOf("PG13");
        System.out.println(cert);
        Movie m1 = new Movie();
        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Thriller");
        Movie m2 = new Movie("Big",1998,Certificate.PG,genres,120,0);
        System.out.println("Default movie:");
        System.out.println(m1);
        System.out.println("Example movie:");
        System.out.println(m2);
        System.out.println("Title: " + m2.getTitle());
        System.out.println("Year: " + m2.getYear());
        System.out.println("Certificate: " + m2.getCertificate());
        System.out.println("Genres: " + m2.getGenres());
        System.out.println("Duration: " + m2.getDuration());
        m2.setRating(5);
        System.out.println("Rating: " + m2.getRating());
    }*/
}

