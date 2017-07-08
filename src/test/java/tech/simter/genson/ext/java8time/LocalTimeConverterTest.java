package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author dragon 2017-03-27
 */
public class LocalTimeConverterTest {
  public class Stuff {
    LocalTime time;

    Stuff(LocalTime time) {
      this.time = time;
    }
  }

  private Stuff stuff = new Stuff(LocalTime.parse("13:10:05"));

  @Test
  public void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalTimeConverter())
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"13:10:05\"}"));
  }

  @Test
  public void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalTimeConverter(DateTimeFormatter.ofPattern("HH时mm分ss秒")))
      .create();
    assertThat(genson.serialize(stuff), is("{\"time\":\"13时10分05秒\"}"));
  }
}
