package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class LocalDateConverterTest {
  class Stuff {
    LocalDate date;

    Stuff(LocalDate date) {
      this.date = date;
    }
  }

  private Stuff stuff = new Stuff(LocalDate.parse("2017-01-02"));

  @Test
  void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateConverter())
      .create();
    assertEquals("{\"date\":\"2017-01-02\"}", genson.serialize(stuff));
  }

  @Test
  void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateConverter(DateTimeFormatter.ofPattern("yyyy年MM月dd日")))
      .create();
    assertEquals("{\"date\":\"2017年01月02日\"}", genson.serialize(stuff));
  }
}
