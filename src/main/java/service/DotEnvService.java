package service;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URISyntaxException;

public class DotEnvService {
    private static DotEnvService ourInstance = new DotEnvService();
    private Dotenv dotenv;

    private DotEnvService() {
        final String path;
        try {
            path = DotEnvService.class.getResource("/.env").toURI().getPath();
            System.out.println(path);
            dotenv = Dotenv.configure()
                    .directory(path.substring(0, path.length()-4))
                    .load();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Twitter is not initialized");
        }
    }

    public static String get(String environment) {
        return ourInstance.dotenv.get(environment);
    }
}
