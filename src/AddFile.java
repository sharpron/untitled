import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author ron 2020/9/23
 */
public class AddFile implements Iterable<Map<String, Object>> {

  private final Iterable<Map<String, String>> origin;

  private final File zipFile;

  public AddFile(Iterable<Map<String, String>> origin, File zipFile) {
    this.origin = origin;
    this.zipFile = zipFile;
  }

  @Override
  public Iterator<Map<String, Object>> iterator() {
    final FileSystem fileSystem;
    try {
      fileSystem = FileSystems.newFileSystem(
          zipFile.toPath(), null
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Iterator<Map<String, String>> iterator = origin.iterator();

    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      @SuppressWarnings({"rawtypes", "unchecked"})
      public Map<String, Object> next() {
        Map next = iterator.next();
        String fileName = next.get("file").toString();
        Path path = fileSystem.getPath(fileName);

        next.put("fileSupplier", (Supplier<byte[]>) () -> {
          ByteArrayOutputStream stream = new ByteArrayOutputStream();
          try {
            Files.copy(path, stream);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          return stream.toByteArray();
        });

        return null;
      }
    };
  }
}
