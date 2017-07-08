package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link LocalTime} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("HH:mm:ss")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ISO_LOCAL_TIME}.
 *
 * @author RJ
 */
public class LocalTimeConverter implements Converter<LocalTime> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
  private final DateTimeFormatter formatter;

  public LocalTimeConverter() {
    this.formatter = defaultFormatter;
  }

  public LocalTimeConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(LocalTime object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public LocalTime deserialize(ObjectReader reader, Context ctx) throws Exception {
    return LocalTime.parse(reader.valueAsString(), formatter);
  }
}