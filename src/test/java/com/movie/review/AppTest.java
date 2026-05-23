package com.movie.review;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testAddReview() {

        String result = App.addReview(
                "Interstellar",
                "Kushal",
                "Amazing Sci-Fi Movie"
        );

        String expected =
                "Movie: Interstellar\n"
                + "Reviewer: Kushal\n"
                + "Review: Amazing Sci-Fi Movie";

        assertEquals(expected, result);
    }
}
