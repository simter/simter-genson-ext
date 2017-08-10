package tech.simter.genson.ext.data;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.data.Ts;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Convert all {@link Ts} instance to the specific json structure.
 *
 * @author RJ
 */
@Named
@Singleton
public class TsConverter implements Converter<Ts> {
  @Override
  public void serialize(Ts ts, ObjectWriter writer, Context ctx) throws Exception {
    Map<String, Object> all = new HashMap<>();
    all.put("ts", ts.getTs());
    if (!ts.isEmpty()) all.putAll(ts.map());

    ctx.genson.serialize(all, writer, ctx);
  }

  @Override
  public Ts deserialize(ObjectReader reader, Context ctx) throws Exception {
    throw new UnsupportedOperationException();
  }
}