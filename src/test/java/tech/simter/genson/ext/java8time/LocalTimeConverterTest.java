package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class LocalTimeConverterTest {
  class Stuff {
    LocalTime time;

    Stuff(LocalTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(LocalTime.parse("13:10:05"));

  @Test
  void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalTimeConverter())
      .create();
    assertEquals("{\"time\":\"13:10:05\"}", genson.serialize(stuff));
  }

  @Test
  void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalTimeConverter(DateTimeFormatter.ofPattern("HH时mm分ss秒")))
      .create();
    assertEquals("{\"time\":\"13时10分05秒\"}", genson.serialize(stuff));
  }
}