package io.vertx.zero.tool;

import io.vertx.up.func.Fn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Period for datetime processing based on Java8
 */
public class Period {

    private static final List<DateTimeFormatter> DATETIMES = new ArrayList<DateTimeFormatter>() {
        {
            add(Iso.DATE_TIME);
            add(Iso.INSTANT);
            add(Iso.RFC1123_DATE_TIME);
            add(Iso.COMMON);
            add(Iso.READBALE);
        }
    };

    private static final List<DateTimeFormatter> DATES = new ArrayList<DateTimeFormatter>() {
        {
            add(Iso.DATE);
            add(Iso.BASIC_DATE);
            add(Iso.ORDINAL_DATE);
        }
    };

    private static final List<DateTimeFormatter> TIMES = new ArrayList<DateTimeFormatter>() {
        {
            add(Iso.TIME);
        }
    };

    /**
     * Convert to datetime
     *
     * @param literal
     * @return
     */
    public static LocalDateTime toFull(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> DATETIMES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalDateTime.parse(literal, formatter),
                                                literal))
                                .findAny(), literal);
        return hit.isPresent() ? LocalDateTime.parse(literal, hit.get()) : null;
    }

    /**
     * Convert to date
     *
     * @param literal
     * @return
     */
    public static LocalDate toDate(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> DATES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalDate.parse(literal, formatter),
                                                literal))
                                .findFirst(), literal);
        return hit.isPresent() ? LocalDate.parse(literal, hit.get()) : null;
    }

    /**
     * Convert to time
     *
     * @param literal
     * @return
     */
    public static LocalTime toTime(final String literal) {
        final Optional<DateTimeFormatter> hit =
                Fn.get(Optional.empty(),
                        () -> TIMES.stream()
                                .filter(formatter ->
                                        null != Fn.getJvm(
                                                null,
                                                () -> LocalTime.parse(literal, formatter),
                                                literal))
                                .findFirst(), literal);
        return hit.isPresent() ? LocalTime.parse(literal, hit.get()) : null;
    }

    /**
     * Check whether it's valid
     *
     * @param literal
     * @return
     */
    public static boolean isValid(final String literal) {
        final Date parsed = parse(literal);
        return null != parsed;
    }

    public static Date parse(final String literal) {
        return Fn.get(null, () -> {
            final int length = literal.length();
            final String pattern = Storage.PATTERNS_MAP.get(length);
            if (null != pattern) {
                // Time + Date, fast parsing first.
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
                Date converted = null;
                if (10 == pattern.length()) {
                    final LocalDate date = LocalDate.parse(literal, formatter);
                    converted = parse(date);
                } else if (15 > pattern.length()) {
                    final LocalTime time = LocalTime.parse(literal, formatter);
                    converted = parse(time);
                } else {
                    final LocalDateTime datetime = LocalDateTime.parse(literal, formatter);
                    converted = parse(datetime);
                }
                return converted;
            } else {
                return parseFull(literal);
            }
        }, literal);
    }

    /**
     * Not recommend directly for deep parsing
     *
     * @param literal
     * @return
     */
    public static Date parseFull(final String literal) {
        return Fn.get(null, () -> {
            // Datetime parsing
            final LocalDateTime datetime = toFull(literal);
            return Fn.nullFlow(datetime,
                    (ref) -> Date.from(ref.atZone(ZoneId.systemDefault()).toInstant()),
                    () -> {
                        // Date parsing
                        final LocalDate date = toDate(literal);
                        return Fn.nullFlow(date,
                                (ref) -> Date.from(ref.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                () -> {
                                    // Time parsing
                                    final LocalTime time = toTime(literal);
                                    return null == time ? null : parse(time);
                                });
                    });
        }, literal);
    }

    public static boolean equalDate(final Date left, final Date right) {
        // Compare year
        int leftVal = toItem(left, Calendar.YEAR);
        int rightVal = toItem(right, Calendar.YEAR);
        if (leftVal == rightVal) {
            // Compare month
            leftVal = toItem(left, Calendar.MONTH);
            rightVal = toItem(right, Calendar.MONTH);
            if (leftVal == rightVal) {
                // Compare day
                leftVal = toItem(left, Calendar.DAY_OF_MONTH);
                rightVal = toItem(right, Calendar.DAY_OF_MONTH);
                return leftVal == rightVal;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static int toMonth(final String literal) {
        final Date date = parse(literal);
        return toItem(date, Calendar.MONTH);
    }

    public static int toMonth(final Date date) {
        return toItem(date, Calendar.MONTH);
    }

    public static int toYear(final Date date) {
        return toItem(date, Calendar.YEAR);
    }

    public static int toYear(final String literal) {
        final Date date = parse(literal);
        return toItem(date, Calendar.YEAR);
    }

    private static int toItem(final Date date, final int flag) {
        final Calendar issue = Calendar.getInstance();
        issue.setTime(date);
        return issue.get(flag);
    }

    public static Date parse(final LocalTime time) {
        final LocalDate date = LocalDate.now();
        final LocalDateTime datetime = LocalDateTime.of(date, time);
        return Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parse(final LocalDateTime datetime) {
        return Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date parse(final LocalDate datetime) {
        return Date.from(datetime.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Period() {
    }
}
