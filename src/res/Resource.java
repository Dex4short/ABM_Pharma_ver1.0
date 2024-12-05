package res;

import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

public class Resource{

	public static URL get(String file_name) {
		return new Resource().getClass().getResource(file_name);
	}
	public static ImageIcon getAsImageIcon(String file_name) {
		return new ImageIcon(Resource.get(file_name));
	}
	public static InputStream getAsInputStream(String file_name) {
		return new Resource().getClass().getResourceAsStream(file_name);
	}
}
