package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.Period;
import io.vertx.up.tool.mirror.Types;

import java.util.Calendar;
import java.util.Date;

/**
 * Date, Calendar
 */
public class DateSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(Date.class == paramType ||
                                        Calendar.class == paramType, getLogger(),
                                () -> {
                                    verifyInput(!Types.isDate(literal), paramType, literal);
                                    final Date reference = Period.parse(literal);
                                    if (Calendar.class == paramType) {
                                        // Specific date format
                                        final Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(reference);
                                        return calendar;
                                    }
                                    return reference;
                                }, Date::new),
                paramType, literal);
    }

    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof Date) {
                final Date date = (Date) input;
                reference = date.getTime();
            } else if (input instanceof Calendar) {
                final Calendar date = (Calendar) input;
                reference = date.getTime().getTime();
            }
            return reference;
        }, input);
    }
}
