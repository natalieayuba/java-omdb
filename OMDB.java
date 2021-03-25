/*
 * OMDB.java
 * This class holds an Online Movie Database object, which contains the main method.
 * @author Natalie Ayuba (n.ayuba@uea.ac.uk)
 */

public class OMDB {
    public static void main(String[] args) {
        MovieDatabase movieDB = new MovieDatabase();
        String file = "films.txt";
        movieDB.loadMovies(file);
        System.out.println(movieDB);
        System.out.println("Third longest \"Film-Noir\": "
                + movieDB.filter("genre", "Film-Noir").sort("duration").getMovie(3));
        System.out.println("Eight most recent \"UNRATED\" film: "
                + movieDB.filter("certificate", "UNRATED").sort("year", "desc").getMovie(8));
        System.out.println("Film with the longest title: "
                + movieDB.sort("titleLength").getMovie(1));
    }
}
