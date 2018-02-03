package examples;

import processing.core.PApplet;
import processing.data.JSONObject;

public class NYTimes extends PApplet {
    @Override
    public void settings() {
        size(200, 200);
    }

    @Override
    public void setup() {
        // The URL for the JSON data (replace "imperial" with "metric" for celsius)
        String apiKey = "your key here";
        String url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
        String query = "?q=processing&sort=newest";

        // Load the XML document
        JSONObject json = loadJSONObject(url+query+"&api-key="+apiKey);
        String headline = json.getJSONObject("response").getJSONArray("docs").getJSONObject(0).getJSONObject("headline").getString("main");
        background(255);
        fill(0);
        text(headline,10,10,180,190);
    }
}