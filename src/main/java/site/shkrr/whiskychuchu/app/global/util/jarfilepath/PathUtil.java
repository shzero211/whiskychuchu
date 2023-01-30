package site.shkrr.whiskychuchu.app.global.util.jarfilepath;

import com.querydsl.core.util.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import site.shkrr.whiskychuchu.WhiskychuchuApplication;

import java.io.*;

public class PathUtil {
    static final ClassLoader loader = WhiskychuchuApplication.class.getClassLoader();
    public static String getResourcePath(String path) {
        return loader.getResource(path).getPath();
    }
}
