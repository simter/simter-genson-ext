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
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Add {@link java.time} support for Genson.
 *
 * @author RJ
 * @see GensonContextResolver
 */
@Named
@Singleton
public class Java8TimeContextualFactory implements ContextualFactory {
  private static Map<String, Converter<?>> converters = new HashMap<>();

  @Override
  public Converter create(BeanProperty property, Genson genson) {
    if (!Temporal.class.isAssignableFrom(property.getRawClass())) return null; // only deal with java.time

    String format = null, lang = null;
    Format cfg1 = property.getAnnotation(Format.class);                   // support simter Format annotation
    if (null != cfg1) {
      format = cfg1.value();
      lang = cfg1.lang();
    } else {
      JsonDateFormat cfg2 = property.getAnnotation(JsonDateFormat.class); // support genson JsonDateFormat annotation
      if (null != cfg2) {
        format = cfg2.value();
        lang = cfg2.lang();
      }
    }

    return getConverter(property.getRawClass(), format, lang);
  }

  private static Converter<?> getConverter(Class<?> type, String format, String lang) {
    String id = type.getName() + "_" + format + "_" + lang;
    // If exists just return it to avoid repeat instance
    if (converters.containsKey(id)) return converters.get(id);

    // instance and cache
    Converter<?> c;
    if (LocalDate.class.isAssignableFrom(type)) {
      converters.put(id, c = new LocalDateConverter(createFormatter(format, lang)));
    } else if (LocalDateTime.class.isAssignableFrom(type)) {
      converters.put(id, c = new LocalDateTimeConverter(createFormatter(format, lang)));
    } else if (LocalTime.class.isAssignableFrom(type)) {
      converters.put(id, c = new LocalTimeConverter(createFormatter(format, lang)));
    } else if (java.time.ZonedDateTime.class.isAssignableFrom(type)) {
      converters.put(id, c = new ZonedDateTimeConverter(createFormatter(format, lang)));
    } else if (java.time.OffsetDateTime.class.isAssignableFrom(type)) {
      converters.put(id, c = new OffsetDateTimeConverter(createFormatter(format, lang)));
    } else if (java.time.OffsetTime.class.isAssignableFrom(type)) {
      converters.put(id, c = new OffsetTimeConverter(createFormatter(format, lang)));
    } else if (java.time.YearMonth.class.isAssignableFrom(type)) {
      converters.put(id, c = new YearMonthConverter(createFormatter(format, lang)));
    } else {
      c = null;
    }
    return c;
  }

  private static DateTimeFormatter createFormatter(String format, String lang) {
    if (format == null || format.isEmpty()) return null;
    else {
      Locale locale = lang.isEmpty() ? null : new Locale(lang);
      if (locale == null) return DateTimeFormatter.ofPattern(format);
      else return DateTimeFormatter.ofPattern(format).withLocale(locale);
    }
  }
}