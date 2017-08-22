package tech.simter.genson.ext.java8time;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.annotation.Format;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * The {@link YearMonth} {@link Converter} of Genson.
 * <p>
 * Can use {@link Format @Format("yyyy-MM")} on filed or getter to config the formatter.
 * The default format is {@link DateTimeFormatter#ofPattern(java.lang.String) DateTimeFormatter.ofPattern("yyyy-MM")}.
 *
 * @author RJ
 */
public class YearMonthConverter implements Converter<YearMonth> {
  private final static DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
  private final DateTimeFormatter formatter;

  public YearMonthConverter() {
    this.formatter = defaultFormatter;
  }

  public YearMonthConverter(DateTimeFormatter formatter) {
    this.formatter = formatter == null ? defaultFormatter : formatter;
  }

  @Override
  public void serialize(YearMonth object, ObjectWriter writer, Context ctx) throws Exception {
    writer.writeString(object.format(formatter));
  }

  @Override
  public YearMonth deserialize(ObjectReader reader, Context ctx) throws Exception {
    String text = reader.valueAsString();
    return text == null || text.isEmpty() ? null : YearMonth.parse(text, formatter);
  }
}