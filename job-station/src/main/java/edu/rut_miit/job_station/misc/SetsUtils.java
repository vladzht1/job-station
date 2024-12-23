package edu.rut_miit.job_station.misc;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetsUtils {
    public static <T> Set<T> calculateIntersection(List<T> left, List<T> right) {
        return left.stream()
            .distinct()
            .filter(right::contains)
            .collect(Collectors.toSet());
    }
}
