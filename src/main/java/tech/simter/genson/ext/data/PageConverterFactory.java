package tech.simter.genson.ext.data;

import com.owlike.genson.Converter;
import com.owlike.genson.Factory;
import com.owlike.genson.Genson;
import tech.simter.data.Page;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Type;

/**
 * Register all Page instance to use the common PageConverter.
 *
 * @author RJ
 */
@Named
@Singleton
public class PageConverterFactory implements Factory<Converter<Page>> {
  private PageConverter pageConverter;

  @Override
  public Converter<Page> create(Type type, Genson genson) {
    if (pageConverter == null) pageConverter = new PageConverter();
    return pageConverter;
  }
}