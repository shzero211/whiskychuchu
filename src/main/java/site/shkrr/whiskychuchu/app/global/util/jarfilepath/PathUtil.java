package site.shkrr.whiskychuchu.app.global.util.jarfilepath;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import site.shkrr.whiskychuchu.WhiskychuchuApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PathUtil {
    public static String getResourcePath(String path) {
        try {
            return new ClassPathResource(path).getURL().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
