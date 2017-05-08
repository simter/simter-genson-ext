package tech.simter.genson.ext.data;

import com.owlike.genson.Converter;
import com.owlike.genson.Factory;
import com.owlike.genson.Genson;
import tech.simter.data.Page;

import java.lang.reflect.Type;

/**
 * Register all Page instance to use the common PageConverter.
 *
 * @author RJ
 */
public class PageConverterFactory implements Factory<Converter<Page>> {
  @Override
  public Converter<Page> create(Type type, Genson genson) {
    return new PageConverter();
  }
}