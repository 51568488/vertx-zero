package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.Period;
import io.vertx.up.tool.mirror.Types;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Java8DataTimeSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof LocalDate) {
                final LocalDate date = (LocalDate) input;
                reference = date.toString();
            } else if (input instanceof LocalDateTime) {
                final LocalDateTime dateTime = (LocalDateTime) input;
                reference = dateTime.toString();
            } else if (input instanceof LocalTime) {
                final LocalTime time = (LocalTime) input;
                reference = time.toString();
            }
            return reference;
        }, input);
    }

    @Override
    public Object from(final Class<?> paramType, final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(Date.class == paramType ||
                                        Calendar.class == paramType, getLogger(),
                                () -> {
                                    verifyInput(!Types.isDate(literal), paramType, literal);
                                    final Date reference = Period.parse(literal);
                                    if (LocalTime.class == paramType) {
                                        return Period.toTime(literal);
                                    } else if (LocalDate.class == paramType) {
                                        return Period.toDate(literal);
                                    } else if (LocalDateTime.class == paramType) {
                                        return Period.toFull(literal);
                                    }
                                    return reference;
                                }, Date::new),
                paramType, literal);
    }
}
