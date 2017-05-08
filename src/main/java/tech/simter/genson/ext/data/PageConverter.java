package tech.simter.genson.ext.data;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import tech.simter.data.Page;

/**
 * Convert all {@link Page} instance to the specific json structure.
 * Such as number to pageNo, capacity to pageSize, totalCount to count and rows keep rows, no more other keys.
 *
 * @author RJ
 */
public class PageConverter implements Converter<Page> {
  @Override
  public void serialize(Page page, ObjectWriter writer, Context ctx) throws Exception {
    writer.beginObject();
    writer.writeNumber("pageNo", page.getNumber())
      .writeNumber("pageSize", page.getCapacity())
      .writeNumber("count", page.getTotalCount())
      .writeName("rows");

    ctx.genson.serialize(page.getRows(), writer, ctx);

    writer.endObject();
  }

  @Override
  public Page deserialize(ObjectReader reader, Context ctx) throws Exception {
    throw new UnsupportedOperationException();
  }
}