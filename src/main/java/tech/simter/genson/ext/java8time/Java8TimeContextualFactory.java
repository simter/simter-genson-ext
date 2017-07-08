package tech.simter.genson.ext.java8time;

import com.owlike.genson.Converter;
import com.owlike.genson.Genson;
import com.owlike.genson.annotation.JsonDateFormat;
import com.owlike.genson.convert.ContextualFactory;
import com.owlike.genson.reflect.BeanProperty;
import tech.simter.annotation.Format;

import javax.inject.Named;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Add {@link java.time} support for Genson.
 *
 * @author RJ
 * @see GensonContextResolver
 */
@Named
@Singleton
public class Java8TimeContextualFactory implements ContextualFactory {
  @Override
  public Converter create(BeanProperty property, Genson genson) {
    String format = null, lang = null;
    Format cfg1 = property.getAnnotation(Format.class); // support simter Format annotation
    if (null != cfg1) {
      format = cfg1.value();
      lang = cfg1.lang();
    } else {
      // support genson JsonDateFormat annotation
      JsonDateFormat cfg2 = property.getAnnotation(JsonDateFormat.class);
      if (null != cfg2) {
        format = cfg2.value();
        lang = cfg2.lang();
      }
    }
    if (LocalDate.class.isAssignableFrom(property.getRawClass())) {
      return new LocalDateConverter(createFormatter(format, lang));
    } else if (LocalDateTime.class.isAssignableFrom(property.getRawClass())) {
      return new LocalDateTimeConverter(createFormatter(format, lang));
    } else if (LocalTime.class.isAssignableFrom(property.getRawClass())) {
      return new LocalTimeConverter(createFormatter(format, lang));
    } else if (java.time.ZonedDateTime.class.isAssignableFrom(property.getRawClass())) {
      return new ZonedDateTimeConverter(createFormatter(format, lang));
    } else if (java.time.OffsetDateTime.class.isAssignableFrom(property.getRawClass())) {
      return new OffsetDateTimeConverter(createFormatter(format, lang));
    } else if (java.time.OffsetTime.class.isAssignableFrom(property.getRawClass())) {
      return new OffsetTimeConverter(createFormatter(format, lang));
    } else if (java.time.YearMonth.class.isAssignableFrom(property.getRawClass())) {
      return new YearMonthConverter(createFormatter(format, lang));
    }
    return null;
  }

  private DateTimeFormatter createFormatter(String format, String lang) {
    if (format == null || format.isEmpty()) return null;
    else {
      Locale locale = lang.isEmpty() ? null : new Locale(lang);
      if (locale == null) return DateTimeFormatter.ofPattern(format);
      else return DateTimeFormatter.ofPattern(format).withLocale(locale);
    }
  }
}