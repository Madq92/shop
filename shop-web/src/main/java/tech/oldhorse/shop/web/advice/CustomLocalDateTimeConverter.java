package tech.oldhorse.shop.web.advice;

import org.apache.commons.lang3.math.NumberUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeConverter extends PropertyEditorSupport {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_SHORT = "yyyy-MM-dd";
    private static final DateTimeFormatter PATTERN_Formatter = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateTimeFormatter PATTERN_SHORT_Formatter = DateTimeFormatter.ofPattern(PATTERN_SHORT);
    private static final ZoneOffset ZONE_OFFSET;

    static {
        OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
        ZONE_OFFSET = odt.getOffset();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (NumberUtils.isDigits(text)) {
            if (text.length() == 13) {
                this.setValue(LocalDateTime.ofEpochSecond(NumberUtils.toLong(text) / 1000L, 0, ZONE_OFFSET));
            } else if (text.length() == 10) {
                this.setValue(LocalDateTime.ofEpochSecond(NumberUtils.toLong(text), 0, ZONE_OFFSET));
            }
        } else {
            if (text.length() == 10) {
                this.setValue(LocalDateTime.parse(text, PATTERN_SHORT_Formatter));
            } else if (text.length() == 19) {
                this.setValue(LocalDateTime.parse(text, PATTERN_Formatter));
            }
        }
        this.setValue(null);
    }
}
