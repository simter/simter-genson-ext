package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class LocalDateTimeConverterTest {
  class Stuff {
    LocalDateTime time;

    Stuff(LocalDateTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(LocalDateTime.parse("2017-01-02T13:10:00"));

  @Test
  void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateTimeConverter())
      .create();
    assertEquals("{\"time\":\"2017-01-02T13:10:00\"}", genson.serialize(stuff));
  }

  @Test
  void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateTimeConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
      .create();
    assertEquals("{\"time\":\"2017-01-02T13:10:00\"}", genson.serialize(stuff));
  }
}