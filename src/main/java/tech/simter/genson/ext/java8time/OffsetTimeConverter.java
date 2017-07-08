package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link OffsetTime} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("HH:mm:ss")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ISO_OFFSET_DATE_TIME}.
 *
 * @author RJ
 */
public class OffsetTimeConverter implements Converter<OffsetTime> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_OFFSET_TIME;
  private final DateTimeFormatter formatter;

  public OffsetTimeConverter() {
    this.formatter = defaultFormatter;
  }

  public OffsetTimeConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(OffsetTime object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public OffsetTime deserialize(ObjectReader reader, Context ctx) throws Exception {
    return OffsetTime.parse(reader.valueAsString(), formatter);
  }
}