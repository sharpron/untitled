import java.io.File;

/**
 * @author ron 2020/9/23
 */
public class App {

  public static void main(String[] args) {
    Poster results = new Poster(
        new AddFile(
            new TransformCode(
                new ExcelLines("test.xlsx", 0, 2),
                new ExcelLines("test.xlsx", 2, 0)
            ),
            new File("archive.zip")
        )
    );

    for (Boolean result : results) {
      if (result == Boolean.TRUE) {
        System.out.println("成功");
      }
    }
  }
}
