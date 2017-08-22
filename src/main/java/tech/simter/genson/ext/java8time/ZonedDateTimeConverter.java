package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link ZonedDateTime} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("yyyy-MM-dd HH:mm:ss")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ISO_ZONED_DATE_TIME}.
 *
 * @author RJ
 */
public class ZonedDateTimeConverter implements Converter<ZonedDateTime> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
  private final DateTimeFormatter formatter;

  public ZonedDateTimeConverter() {
    this.formatter = defaultFormatter;
  }

  public ZonedDateTimeConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(ZonedDateTime object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public ZonedDateTime deserialize(ObjectReader reader, Context ctx) throws Exception {
    String text = reader.valueAsString();
    return text == null || text.isEmpty() ? null : ZonedDateTime.parse(text, formatter);
  }
}