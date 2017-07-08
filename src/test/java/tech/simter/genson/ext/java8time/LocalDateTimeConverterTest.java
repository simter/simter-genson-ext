package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author dragon 2017-03-27
 */
public class LocalDateTimeConverterTest {
  public class Stuff {
    LocalDateTime time;

    Stuff(LocalDateTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(LocalDateTime.parse("2017-01-02T13:10:00"));

  @Test
  public void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateTimeConverter())
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"2017-01-02T13:10:00\"}"));
  }

  @Test
  public void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateTimeConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"2017-01-02T13:10:00\"}"));
  }
}
