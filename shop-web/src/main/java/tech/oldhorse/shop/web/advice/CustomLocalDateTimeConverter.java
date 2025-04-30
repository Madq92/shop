package tech.oldhorse.shop.web.advice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.math.NumberUtils;

public class CustomLocalDateTimeConverter extends PropertyEditorSupport {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final ZoneOffset ZONE_OFFSET;

    static {
        OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
        ZONE_OFFSET = odt.getOffset();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 如果传进来的参数是数字且为 13 位，匹配我们传进来的时间戳
        if (NumberUtils.isDigits(text) && text.length() == 13) {
            this.setValue(LocalDateTime.ofEpochSecond(NumberUtils.toLong(text) / 1000L, 0, ZONE_OFFSET));
        } else if (NumberUtils.isDigits(text) && text.length() == 10) {
            this.setValue(LocalDateTime.ofEpochSecond(NumberUtils.toLong(text), 0, ZONE_OFFSET));
        } else {
            try {
                this.setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(PATTERN)));
            } catch (Exception var2) {
                this.setValue(null);
            }
        }
    }
}
