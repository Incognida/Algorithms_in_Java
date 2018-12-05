import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BruteCollinearPoints {
    private Point[] brutePoints;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        brutePoints = points;
        lineSegments = new LineSegment[points.length / 4];
        int seg_idx = 0;
        Insertion.sort(points);
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                for (int k = j; k < points.length; k++) {
                    for (int l = k; l < points.length; l++) {
                        Comparator slopeComparator = points[i].slopeOrder();
                        if (slopeComparator.compare(points[j], points[k]) == slopeComparator.compare(points[k], points[l])) {
                            LineSegment lineSegment = new LineSegment(points[i], points[l]);
                            lineSegments[seg_idx++] = lineSegment;
                        }
                    }
                }
            }
        }
    }  // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegments.length;
    }        // the number of line segments

    public LineSegment[] segments() {
        for (int i = 0; i < lineSegments.length; i++) {
            StdOut.println(lineSegments[i].toString());
        }
    }                // the line segments
}
