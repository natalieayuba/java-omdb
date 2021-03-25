/*
 * OMDB.java
 * This class holds an Online Movie Database object, which contains the main method.
 * @author Natalie Ayuba (n.ayuba@uea.ac.uk)
 */

package omdb;

public class OMDB {
    public static void main(String[] args) {
        MovieDatabase movieDB = new MovieDatabase();
        String file = "films.txt";
        movieDB.loadMovies(file);
        System.out.println(movieDB);
        // Task #1 - Get the third longest "Film-Noir"
        System.out.println("Third longest \"Film-Noir\": "
                + movieDB.filter("genre", "Film-Noir").sort("duration").getMovie(3));
        // Task #2 - Get the eight most recent "UNRATED" film
        System.out.println("Eight most recent \"UNRATED\" film: "
                + movieDB.filter("certificate", "UNRATED").sort("year", "desc").getMovie(8));
        // Task #3 - Get the film with the longest title
        System.out.println("Film with the longest title: "
                + movieDB.sort("titleLength").getMovie(1));
    }
}
