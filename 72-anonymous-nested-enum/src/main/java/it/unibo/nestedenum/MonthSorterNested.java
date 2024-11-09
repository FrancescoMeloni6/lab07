package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month{

        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }
        
        /** 
         * @param month 
         * @return the month starting or called like {@code month}
         * @throws IllegalArgumentException if there's no month called or starting like {@code month}
         * @throws IllegalArgumentException if there are multiple months starting with {@code month}
         */
        public static Month fromString(String month){
            Month out = null;
            int i = 0;
            for (final Month m : Month.values()) {
                if (m.toString().toLowerCase().startsWith(month.toLowerCase())) {
                    i++;
                    out = m;
                }
            }
            if (i == 0) {
                throw new IllegalArgumentException("There's no month called or starting like " + month);
            } else if(i > 1) {
                throw new IllegalArgumentException("There are " + i + " months starting with " + month);
            }
            return out;
        }


    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByOrder();
    }

    private static class SortByDays implements Comparator<String>{
        public int compare(final String s1, final String s2) {
            final Month m1 = Month.fromString(s1);
            final Month m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }
    }

    private static class SortByOrder implements Comparator<String>{
        public int compare(final String s1, final String s2) {
            final Month m1 = Month.fromString(s1);
            final Month m2 = Month.fromString(s2);
            return m1.compareTo(m2);
        }
    }
}
