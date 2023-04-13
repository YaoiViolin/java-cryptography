import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Application {

    public static final String BASIC = "basic";
    public static final String SECURE = "secure";
    public static final String EXIT = "exit";

    public static void main(String[] args) throws IOException {
        Map<Boolean, String> forecasts = Map.of(
                true, "У вас сегодня будет удача в делах!",
                false, "Сегодня хороший день для саморазвития!"
        );
        String randomType = "";
        String name = "";

        while (!randomType.equals(EXIT)) {

            System.out.println("Введите имя и нажмите Enter");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();

            System.out.println("Введите тип прогноза и нажмите Enter илиexit, чтобы выйти");
            randomType = reader.readLine().toLowerCase(Locale.ROOT);

            System.out.print(name + "! ");
            switch (randomType) {
                case BASIC:
                    Random random = new Random(System.currentTimeMillis());
                    System.out.println(forecasts.get(random.nextBoolean()));
                    break;
                case SECURE:
                    SecureRandom secureRandom = new SecureRandom(name.getBytes(StandardCharsets.UTF_8));
                    System.out.println(forecasts.get(secureRandom.nextBoolean()));
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Wrong type");
            }
        }
    }
}
