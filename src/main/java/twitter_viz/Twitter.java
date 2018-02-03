package twitter_viz;

import processing.core.PApplet;
import service.DotEnvService;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;

public class Twitter extends PApplet {
    private twitter4j.Twitter twitter;

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
        cb.setOAuthConsumerKey(DotEnvService.get("consumer.key"));
        cb.setOAuthConsumerSecret(DotEnvService.get("consumer.secret"));
        cb.setOAuthAccessToken(DotEnvService.get("access.token.key"));
        cb.setOAuthAccessTokenSecret(DotEnvService.get("access.token.secret"));
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
            final Query request = new Query("#LOSCPSG");
            final QueryResult result = twitter.search(request);

            final List<Status> tweets = result.getTweets();
            final int[] index = {0};
            final int[] data = tweets.stream().mapToInt(tweet -> tweet.getText().length()).toArray();
            tweets.forEach(status -> {
                final Tweet tweet = TweetFactory.newTweet(status);
                System.out.println(tweet);
//                System.out.println(status.getPlace());
                System.out.println("fav: " + status.getFavoriteCount() + ", ret: " + status.getRetweetCount());

                fill(255);
                textSize(10);
//                text(message, 10, 15 + 30 * index[0]++);
                rect(index[0] * 20, 0, 20, data[index[0]]);
                index[0]++;
            });
        } catch (TwitterException e) {
            println("Couldn't connect: " + e);
        }
    }

    public static void main(String[] passedArgs) {
        PApplet.main("twitter_viz.Twitter");
    }
}
