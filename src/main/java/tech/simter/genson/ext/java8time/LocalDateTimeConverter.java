package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link LocalDateTime} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("yyyy-MM-dd HH:mm:ss")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}.
 *
 * @author RJ
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  private final DateTimeFormatter formatter;

  public LocalDateTimeConverter() {
    this.formatter = defaultFormatter;
  }

  public LocalDateTimeConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(LocalDateTime object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public LocalDateTime deserialize(ObjectReader reader, Context ctx) throws Exception {
    String text = reader.valueAsString();
    return text == null || text.isEmpty() ? null : LocalDateTime.parse(text, formatter);
  }
}