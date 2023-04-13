import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws IOException {
        Map<Boolean, String> forecastMap = Map.of(
                true, "У вас сегодня будет удача в делах!",
                false, "Сегодня хороший день для саморазвития!"
        );
        String name = "";
        Random random = new Random(System.currentTimeMillis());

        while (!name.equals("exit")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();
            String forecast = reader.readLine();
            System.out.print(name + "! ");
            switch (forecast) {
                case "Basic":
                    System.out.println(forecastMap.get(random.nextBoolean()));
                    break;
                case "Secure":
                    SecureRandom secureRandom = new SecureRandom(name.getBytes(StandardCharsets.UTF_8));
                    System.out.println(forecastMap.get(secureRandom.nextBoolean()));
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }
}
