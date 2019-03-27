package services;

import java.io.InputStream;
import java.net.URL;

public class ResourceManager {

    public static URL getResource(String filename) {
        return ResourceManager.class.getResource(filename);
    }
}
