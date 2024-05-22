package org.seng2050.lab08;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoviesController {
    
    @Autowired
    private DataSource dataSource;

    record Movie(
        String title,
        int year,
        String url
    ) {}

    @GetMapping("/movies")
    public ModelAndView showMovies() throws Exception {

        Connection connection = this.dataSource.getConnection();

        Statement statement = connection.createStatement();

        ResultSet results = statement.executeQuery("SELECT * FROM [movies]");

        List<Movie> movies = new LinkedList<>();

        while (results.next()) {
            String title = results.getString("title");
            int year = results.getInt("year");
            String url = results.getString("url");

            movies.add(new Movie(title, year, url));
        }
    
        var mav = new ModelAndView("movies");
        mav.addObject("movies", movies);
        return mav;
    }
}
