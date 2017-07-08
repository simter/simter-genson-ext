package tech.simter.genson.ext.java8time;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author dragon 2017-03-27
 */
public class LocalDateConverterTest {
  public class Stuff {
    LocalDate date;

    Stuff(LocalDate date) {
      this.date = date;
    }
  }

  private Stuff stuff = new Stuff(LocalDate.parse("2017-01-02"));

  @Test
  public void defaultFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateConverter())
      .create();
    assertThat(genson.serialize(stuff), is("{\"date\":\"2017-01-02\"}"));
  }

  @Test
  public void customFormatter() {
    Genson genson = new GensonBuilder()
      .withConverters(new LocalDateConverter(DateTimeFormatter.ofPattern("yyyy年MM月dd日")))
      .create();
    assertThat(genson.serialize(stuff), is("{\"date\":\"2017年01月02日\"}"));
  }
}
