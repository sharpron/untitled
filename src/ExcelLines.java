import java.util.Iterator;
import java.util.Map;

/**
 * @author ron 2020/9/23
 */
public class ExcelLines implements Iterable<Map<String, String>> {

  private final String xlsxPath;

  private final int sheetIndex;

  private final int skip;

  public ExcelLines(String xlsxPath, int sheetIndex, int skip) {
    this.xlsxPath = xlsxPath;
    this.sheetIndex = sheetIndex;
    this.skip = skip;
  }

  @Override
  public Iterator<Map<String, String>> iterator() {
    return null;
  }
}
