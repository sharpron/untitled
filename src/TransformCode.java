import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ron 2020/9/23
 */
public class TransformCode implements Iterable<Map<String, String>> {

  private final Iterable<Map<String, String>> origin;

  private final Iterable<Map<String, String>> dict;

  public TransformCode(Iterable<Map<String, String>> origin,
      Iterable<Map<String, String>> dict) {
    this.origin = origin;
    this.dict = dict;
  }

  @Override
  public Iterator<Map<String, String>> iterator() {
    Iterator<Map<String, String>> iterator = origin.iterator();
    Map<String, String> dictName = StreamSupport.stream(dict.spliterator(), false)
        .collect(
            Collectors.toMap(
                e -> e.get("projectName"),
                e -> e.get("projectCode")
            )
        );
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public Map<String, String> next() {
        Map<String, String> next = iterator.next();

        String projectCode = dictName.get(next.get("projectName"));
        dictName.put("projectCode", projectCode);
        return next;
      }
    };
  }
}
