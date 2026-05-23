package com.movie.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Logger logger =
            LoggerFactory.getLogger(App.class);

    // Store all reviews
    static List<String> reviews = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Movie Review System =====");

        System.out.println("Enter Movie Name:");
        String movieName = scanner.nextLine();

        System.out.println("Enter Reviewer Name:");
        String reviewer = scanner.nextLine();

        System.out.println("Enter Your Review:");
        String review = scanner.nextLine();

        if (StringUtils.isNotBlank(movieName)
                && StringUtils.isNotBlank(reviewer)
                && StringUtils.isNotBlank(review)) {

            String reviewData =
                    addReview(movieName, reviewer, review);

            // Store review
            reviews.add(reviewData);

            logger.info(reviewData);

            System.out.println("\n===== Review Added Successfully =====");

            // Display all reviews
            showReviews();

        } else {

            logger.error("Input fields cannot be empty!");

            System.out.println("Invalid Input!");
        }

        scanner.close();
    }

    // Add review
    public static String addReview(String movie,
                                   String reviewer,
                                   String review) {

        return "Movie: " + movie
                + "\nReviewer: " + reviewer
                + "\nReview: " + review;
    }

    // Display reviews
    public static void showReviews() {

        System.out.println("\n===== User Reviews =====");

        for (String r : reviews) {

            System.out.println("---------------------");
            System.out.println(r);
        }
    }
}
