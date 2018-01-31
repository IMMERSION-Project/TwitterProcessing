import io.github.cdimascio.dotenv.Dotenv;
import processing.core.PApplet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.net.URISyntaxException;
import java.util.*;

public class Twitter extends PApplet {
    private twitter4j.Twitter twitter;
    private Dotenv dotenv;

    public Twitter() {
        final String path;
        try {
            path = Twitter.class.getResource(".env").toURI().getPath();
            System.out.println(path);
            dotenv = Dotenv.configure()
                    .directory(path.substring(0, path.length()-4))
                    .load();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Twitter is not initalized");
        }
    }

    @Override
    public void settings() {
        size(550, 550);
    }

    @Override
    public void setup() {
        //Taille de la fenêtre de l'applet
        smooth();

        //Authentification
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(dotenv.get("consumer.key"));
        cb.setOAuthConsumerSecret(dotenv.get("consumer.secret"));
        cb.setOAuthAccessToken(dotenv.get("access.token.key"));
        cb.setOAuthAccessTokenSecret(dotenv.get("access.token.secret"));
        //Utilisation des informations d'authentification pour se connecter à l'API twitter
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        twitter = twitterFactory.getInstance();
    }

    @Override
    public void draw() {
        // on règle le rafraichissement de l'affichage de la page à
        // toutes les 5 secondes pour éviter des demandes trop fréquentes
        frameRate(1 / 5);
        //on renouvelle l'arrière plan noir pour éviter la superposition des messages
        background(0);

        //Essai
        try {
            final Query request = new Query("#prestige");
            final QueryResult result = twitter.search(request);

            final List<Status> tweets = result.getTweets();
            final int[] index = {0};
            final int[] data = tweets.stream().mapToInt(tweet -> tweet.getText().length()).toArray();
            tweets.forEach(tweet -> {
                final User user = tweet.getUser();

                final String userName = user.getName();
                final String message = tweet.getText();
                final String pseudo = user.getScreenName();

                final Date createdAt = tweet.getCreatedAt();

                fill(255);
                textSize(10);
//                text(message, 10, 15 + 30 * index[0]++);
                rect(index[0]*20, 0, 20, data[index[0]]);
                index[0]++;
            });
        } catch (TwitterException e) {
            println("Couldn't connect: " + e);
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main("Twitter");
    }
}
