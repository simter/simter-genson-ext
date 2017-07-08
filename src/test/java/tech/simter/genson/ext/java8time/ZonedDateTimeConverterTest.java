package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author dragon 2017-03-27
 */
public class ZonedDateTimeConverterTest {
  public class Stuff {
    ZonedDateTime time;

    Stuff(ZonedDateTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(ZonedDateTime.parse("2017-01-02T13:10:00+08:00"));

  @Test
  public void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new ZonedDateTimeConverter())
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"2017-01-02T13:10:00+08:00\"}"));
  }

  @Test
  public void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new ZonedDateTimeConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")))
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"2017-01-02 13:10:00+0800\"}"));
  }
}
