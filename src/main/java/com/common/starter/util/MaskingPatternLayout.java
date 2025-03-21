package com.common.starter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * The MaskingPatternLayout class is a subclass of the PatternLayout class that masks sensitive information in log messages.
 * It allows users to add multiple mask patterns using regular expressions and masks the matching parts of log messages.
 */
public class MaskingPatternLayout extends PatternLayout {

    /**
     * List of mask patterns used for masking sensitive information in log messages.
     * The mask patterns are regular expressions that define the sensitive information to be masked.
     *
     * @see MaskingPatternLayout#addMaskPattern(String)
     * @see MaskingPatternLayout#doLayout(ILoggingEvent)
     * @see MaskingPatternLayout#maskMessage(String)
     */
    private final List<String> maskPatterns = new ArrayList<>();

    /**
     * Pattern representing a regular expression used for masking sensitive information in log messages.
     * The pattern is applied to log messages to identify and mask sensitive information.
     *
     * @see MaskingPatternLayout#addMaskPattern(String)
     * @see MaskingPatternLayout#doLayout(ILoggingEvent)
     * @see MaskingPatternLayout#maskMessage(String)
     */
    private Pattern multilinePattern;

    /**
     * Adds a mask pattern to the list of patterns used for masking sensitive information in log messages.
     *
     * @param maskPattern The regular expression pattern to be added as a mask.
     */
    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);

        multilinePattern = Pattern.compile(String.join("|", maskPatterns), Pattern.MULTILINE);
    }

    /**
     * Masks sensitive information in log messages by applying multiple mask patterns using regular expressions.
     *
     * @param event The logging event.
     * @return The formatted log message with sensitive information masked.
     */
    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    /**
     * Masks sensitive information in a message by replacing matching parts with asterisks.
     *
     * @param message The message to be masked.
     * @return The masked message.
     */
    private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }

        StringBuilder sb = new StringBuilder(message);

        Matcher matcher = multilinePattern.matcher(sb);

        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group)).forEach(i -> sb.setCharAt(i, '*'));
                }
            });
        }

        return sb.toString();
    }

}
