package com.common.starter.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static com.common.starter.util.DateTimeConstants.DATE_TIME_FORMAT;
import static com.common.starter.util.DateTimeConstants.DATE_TIME_WITHOUT_SECONDS_FORMAT;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilities class for date conversion.
 */
@Slf4j
public final class DateUtils {

    private DateUtils() {
    }

    /**
     * Converts an XMLGregorianCalendar object to a LocalDate object.
     *
     * @param calendar The XMLGregorianCalendar to be converted.
     * @return The converted LocalDate object.
     */
    public static LocalDate convertToLocalDate(final XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }

        return LocalDate.of(calendar.getYear(), calendar.getMonth(), calendar.getDay());
    }

    /**
     * Converts an LocalDate object to a XMLGregorianCalendar object.
     *
     * @param localDate The LocalDate to be converted.
     * @return The converted XMLGregorianCalendar object.
     */
    @SneakyThrows
    public static XMLGregorianCalendar convertToXMLGregorian(final LocalDate localDate) {
        try {
            if (localDate == null) {
                return null;
            }
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        }
        catch (DatatypeConfigurationException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Converts from string to LocalDateTime.
     *
     * @param timestamp The date string to be converted.
     * @return The converted LocalDateTime object
     */
    public static LocalDateTime convertToLocalDateTime(final String timestamp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        try {
            return LocalDateTime.parse(timestamp, dateTimeFormatter);
        }
        catch (DateTimeParseException exc1) {
            try {
                dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_WITHOUT_SECONDS_FORMAT);
                return LocalDateTime.parse(timestamp, dateTimeFormatter);
            }
            catch (DateTimeParseException exc2) {
                log.error("Error parsing timestamp: ", exc2);
                throw exc2;
            }
        }
    }

}
