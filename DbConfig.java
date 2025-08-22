import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    public static Properties load(){
        Properties prop = new Properties();
        try(InputStream is = DbConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(is);
            return prop;
        }catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки config.properties", e);
        }
    }
}
