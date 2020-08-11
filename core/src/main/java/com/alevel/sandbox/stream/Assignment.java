package com.alevel.sandbox.stream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.util.stream.Collectors.*;

public final class Assignment {

    public static final class ParseDecimalNumberFromString {

        public static void main(String[] args) {
            List<String> strings = Arrays.asList("string 1 text", "2 string 3 text", "45");
            System.out.println(strings + ": " + parse(strings));
        }

        public static long parse(Collection<String> strings) {

            return strings.stream()
                    .flatMapToInt(String::codePoints)
                    .filter(Character::isDigit)
                    .map(codepoint -> Character.digit(codepoint, 10))
                    .asLongStream()
                    .reduce((result, next) -> result * 10 + next)
                    .orElseThrow(() -> new IllegalArgumentException("No digits found in " + strings));
        }

    }

    public static final class MostCommonNumber {

        public static void main(String[] args) {
            System.out.println(find(0, 1, 34, 1, 0, 4, 0, -324, 1000));
            System.out.println(find(0, 1, 34, 1, 0, 4, 34, 34, -324, 1000));
            System.out.println(find(-1));
            System.out.println(find(3, 3, 3, -3, -3, -3));
            System.out.println(find(-3, 3, 3, 3, -3, -3));
            System.out.println(find(2, 2, -3, 3, 3, 3, -3, -3, -3));
        }

        public static int find(int... numbers) {

            class MostCommon {

                int mc;

                final Map<Integer, Integer> occurrences = new HashMap<>();

                void next(int next) {
                    next(next, 1);
                }

                void next(int next, int times) {
                    if (isEmpty()) {
                        occurrences.put(next, times);
                        mc = next;
                        return;
                    }
                    int occurrencesOfNext = occurrences.merge(next, times, Integer::sum);
                    if (mc == next) return;
                    int occurrencesOfMax = occurrences.get(mc);
                    if (occurrencesOfNext > occurrencesOfMax) {
                        mc = next;
                    }
                }

                boolean isEmpty() {
                    return occurrences.isEmpty();
                }

                void merge(MostCommon other) {
                    other.occurrences.forEach(this::next);
                }

                int unwrap() {
                    if (isEmpty()) {
                        throw new IllegalArgumentException(
                                "Could not find the most commonly occurring element in an empty array");
                    }
                    return mc;
                }

            }

            return Arrays.stream(numbers)
                    .collect(MostCommon::new, MostCommon::next, MostCommon::merge)
                    .unwrap();
        }

    }

    public static final class GroupTimeByDates {

        public static void main(String[] args) {
            LocalDateTime now = LocalDateTime.now();
            List<LocalDateTime> timestamps = Arrays.asList(
                    now,
                    now.minusHours(1),
                    now.plusHours(1),
                    now.minusDays(1),
                    now.plusDays(1),
                    now.plusHours(25),
                    now.plusDays(2)
            );
            System.out.println(timestamps);

            SortedMap<LocalDate, SortedSet<LocalTime>> byDate = groupByDateAscending(timestamps);
            System.out.println(byDate);

            String newline = System.lineSeparator();
            String humanReadableOutput = byDate.entrySet().stream()
                    .map(String::valueOf)
                    .collect(joining(newline, "Human readable output:" + newline, newline));
            System.out.print(humanReadableOutput);
        }

        public static SortedMap<LocalDate, SortedSet<LocalTime>>
        groupByDateAscending(Collection<LocalDateTime> timestamps) {

            return timestamps.stream().collect(groupingBy(
                    LocalDateTime::toLocalDate,
                    TreeMap::new,
                    mapping(LocalDateTime::toLocalTime, toCollection(TreeSet::new))
            ));
        }
    }

}
