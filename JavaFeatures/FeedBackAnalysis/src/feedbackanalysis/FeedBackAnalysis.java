package feedbackanalysis;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FeedbackAnalysis {

    @FunctionalInterface
    interface FeedbackFilter {
        boolean filter(Feedback feedback);
    }

    @FunctionalInterface
    interface FeedbackProcessor {
        void process(Feedback feedback);
    }


    static class Feedback {
        private int feedbackId;
        private String customerName;
        private int rating;
        private String comments;

        public Feedback(int feedbackId, String customerName, int rating, String comments) {
            this.feedbackId = feedbackId;
            this.customerName = customerName;
            this.rating = rating;
            this.comments = comments;
        }

        public int getFeedbackId() { return feedbackId; }
        public String getCustomerName() { return customerName; }
        public int getRating() { return rating; }
        public String getComments() { return comments; }

        @Override
        public String toString() {
            return "Feedback{" +
                    "feedbackId=" + feedbackId +
                    ", customerName='" + customerName + '\'' +
                    ", rating=" + rating +
                    ", comments='" + comments + '\'' +
                    '}';
        }
    }

    private List<Feedback> feedbacks = new ArrayList<>();

    public FeedbackAnalysis() {

        feedbacks.add(new Feedback(1, "Alice", 5, "Excellent service!"));
        feedbacks.add(new Feedback(2, "Bob", 2, "Not satisfied with the product."));
        feedbacks.add(new Feedback(3, "Charlie", 4, "Very good, but could be improved."));
        feedbacks.add(new Feedback(4, "David", 1, "Terrible experience."));
        feedbacks.add(new Feedback(5, "Eve", 3, "It was okay."));
    }

    public void processFeedback(FeedbackFilter filter, FeedbackProcessor processor) {
        feedbacks.stream()
                .filter(feedback -> filter.filter(feedback))
                .forEach(feedback -> processor.process(feedback));
    }

    public void processAndAnalyzeFeedback(FeedbackFilter filter) {

        List<Feedback> filteredFeedbacks = feedbacks.stream()
                .filter(feedback -> filter.filter(feedback))
                .collect(Collectors.toList());

        System.out.println("Customer Names and Comments:");
        filteredFeedbacks.forEach(feedback -> 
            System.out.println(feedback.getCustomerName() + ": " + feedback.getComments())
        );

        long positiveCount = filteredFeedbacks.stream()
                .filter(feedback -> feedback.getRating() >= 4)
                .count();

        long negativeCount = filteredFeedbacks.stream()
                .filter(feedback -> feedback.getRating() <= 2)
                .count();

        System.out.println("Number of Positive Feedbacks: " + positiveCount);
        System.out.println("Number of Negative Feedbacks: " + negativeCount);
    }

    public static void main(String[] args) {
        FeedbackAnalysis analysis = new FeedbackAnalysis();

        FeedbackFilter highRatingFilter = feedback -> feedback.getRating() >= 4;
        FeedbackFilter lowRatingFilter = feedback -> feedback.getRating() <= 2;

        FeedbackProcessor printFeedback = feedback -> System.out.println(feedback);

        System.out.println("High Rating Feedbacks:");
        analysis.processFeedback(highRatingFilter, printFeedback);

        System.out.println("\nLow Rating Feedbacks:");
        analysis.processFeedback(lowRatingFilter, printFeedback);

        System.out.println("\nAnalyzing High Rating Feedbacks:");
        analysis.processAndAnalyzeFeedback(highRatingFilter);

        System.out.println("\nAnalyzing Low Rating Feedbacks:");
        analysis.processAndAnalyzeFeedback(lowRatingFilter);
    }
}


