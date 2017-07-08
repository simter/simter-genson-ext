package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@link LocalDate} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("yyyy-MM-dd")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ISO_LOCAL_DATE}.
 *
 * @author RJ
 */
public class LocalDateConverter implements Converter<LocalDate> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
  private final DateTimeFormatter formatter;

  public LocalDateConverter() {
    this.formatter = defaultFormatter;
  }

  public LocalDateConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(LocalDate object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public LocalDate deserialize(ObjectReader reader, Context ctx) throws Exception {
    return LocalDate.parse(reader.valueAsString(), formatter);
  }
}