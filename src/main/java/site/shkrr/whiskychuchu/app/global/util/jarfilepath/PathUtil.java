package site.shkrr.whiskychuchu.app.global.util.jarfilepath;

import org.springframework.util.ResourceUtils;
import site.shkrr.whiskychuchu.WhiskychuchuApplication;

import java.io.FileNotFoundException;

public class PathUtil {
    public static String getResourcePath(String path) {
        try {
            return ResourceUtils.getURL("classpath:"+path).getPath().toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getJarResourcePath(String path) {
        try {
            return ResourceUtils.getURL("classpath:"+path).toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
