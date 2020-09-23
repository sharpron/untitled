import java.util.Iterator;
import java.util.Map;

/**
 * @author ron 2020/9/23
 */
public class Poster implements Iterable<Boolean> {

  private final Iterable<Map<String, Object>> origin;

  public Poster(Iterable<Map<String, Object>> origin) {
    this.origin = origin;
  }

  @Override
  public Iterator<Boolean> iterator() {
    Iterator<Map<String, Object>> iterator = origin.iterator();
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public Boolean next() {
        return Boolean.FALSE;
      }
    };
  }
}
