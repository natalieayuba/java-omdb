/*
 * MovieDatabase.java
 * This class represents a MovieDatabase object.
 * Each MovieDatabase contains a collection of Movie objects, one for each movie in a given file.
 * @author Natalie Ayuba (n.ayuba@uea.ac.uk)
 */

package omdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class MovieDatabase {
    
    private final ArrayList<Movie> movieList;

    public MovieDatabase(){
        movieList = new ArrayList<>();
    }

    public MovieDatabase(ArrayList<Movie> movieList){
        this.movieList = movieList;
    }

    public void loadMovies(String file) {
        try {
            Scanner scan = new Scanner(new File(file));
            while(scan.hasNextLine()) {
                String[] tokens = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String title = tokens[0].replace("\"","");
                int year = Integer.parseInt(tokens[1]);
                String certString = tokens[2].replace("\"","");
                Movie.Certificate certificate = null;
                for (Movie.Certificate cert : Movie.Certificate.values()) {
                    if(cert.toString().equals(certString)) {
                        certificate = cert;
                        break;
                    }
                }
                String[] genreList = tokens[3].replace("\"","").split("/");
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(genreList));
                int duration = Integer.parseInt(tokens[4]);
                double rating = Double.parseDouble(tokens[5]);
                movieList.add(new Movie(title, year, certificate, genres, duration, rating));
            }
            Collections.sort(movieList);
        } catch (FileNotFoundException e) {
            System.out.println("Error. File \"" + file + "\" not found.");
        }
    }

    public Movie getMovie(int i) {
        Movie m = null;
        i -= 1;
        if(movieList.size() == 0) {
            System.out.println("Cannot retrieve movie. This Movie Database is empty.");
        } else if(i>=0 && i<= movieList.size()) {
            m = movieList.get(i);
        } else {
            System.out.println("Cannot retrieve movie.");
        }
        return m;
    }

    public MovieDatabase filter(String attribute, String value) {
        MovieDatabase filteredDB = new MovieDatabase();
        List<Movie> movies = null;
        switch (attribute.toLowerCase()) {
            case "year" -> movies = movieList.stream().filter(m -> m.getYear() == Integer.parseInt(value)).collect(Collectors.toList());
            case "genre" -> movies = movieList.stream().filter(m -> m.getGenres().contains(value)).collect(Collectors.toList());
            case "certificate" -> movies = movieList.stream().filter(m -> m.getCertificate().equals(Movie.Certificate.valueOf(value))).collect(Collectors.toList());
            default -> System.out.println("Unable to filter by " + attribute + ": " + value + ".");
        }
        filteredDB.movieList.addAll(movies);
        return filteredDB;
    }

    public MovieDatabase sort(String attribute) {
        return sort(attribute, "desc");
    }

    public MovieDatabase sort(String attribute, String order) {
        MovieDatabase sortedDB = this;
        switch(attribute.toLowerCase()) {
            case "title":
                if(order.equals("asc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getTitle));
                } else if(order.equals("desc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
                }
                break;
            case "year":
                if(order.equals("desc")){
                    movieList.sort(Collections.reverseOrder());
                }
                break;
            case "duration":
                if(order.equals("asc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getDuration));
                } else if(order.equals("desc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getDuration).reversed());
                }
                break;
            case "rating":
                if(order.equals("asc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getRating));
                } else if(order.equals("desc")) {
                    sortedDB.movieList.sort(Comparator.comparing(Movie::getRating).reversed());
                }
                break;
            case "titleLength":
                if(order.equals("asc")) {
                    sortedDB.movieList.sort(Comparator.comparingInt(m -> m.getTitle().length()));
                } else if(order.equals("desc")) {
                    sortedDB.movieList.sort((m1, m2) -> m2.getTitle().length() - m1.getTitle().length());
                }
                break;
            default:
                System.out.println("Unable to sort by " + attribute + ".");
        }
        return sortedDB;
    }

    public String toString() {
        StringBuilder movies = new StringBuilder();
        for(Movie m : movieList) {
            movies.append(m).append("\n");
        }
        return movies.toString();
    }

    //-------------------------------
    //  TEST HARNESS
    //-------------------------------

/*    public static void main(String[] args) throws FileNotFoundException {
        MovieDatabase movieDB = new MovieDatabase();
        movieDB.loadMovies("films.txt");
        System.out.println(movieDB); // display all films in chronological order
        System.out.println("Movies released in 2010: \n" + movieDB.filter("year", "2010"));
        System.out.println("Action movies: \n" + movieDB.filter("genre", "Action"));
        System.out.println("G-rated movies: \n" + movieDB.filter("certificate", "G"));
        System.out.println("2010 films sorted alphabetically: \n" + movieDB.filter("year", "2010").sort("title"));
        System.out.println("Action movies sorted by duration (desc): \n" + movieDB.filter("genre", "Action").sort("duration", "desc"));
    }*/
}

