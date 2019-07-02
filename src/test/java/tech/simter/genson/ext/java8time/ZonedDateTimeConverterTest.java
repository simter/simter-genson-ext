package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class ZonedDateTimeConverterTest {
  class Stuff {
    ZonedDateTime time;

    Stuff(ZonedDateTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(ZonedDateTime.parse("2017-01-02T13:10:00+08:00"));

  @Test
  void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new ZonedDateTimeConverter())
      .create();
    assertEquals("{\"time\":\"2017-01-02T13:10:00+08:00\"}", genson.serialize(stuff));
  }

  @Test
  void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new ZonedDateTimeConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")))
      .create();
    assertEquals("{\"time\":\"2017-01-02 13:10:00+0800\"}", genson.serialize(stuff));
  }
}
