package twitter_viz;

import twitter4j.Status;

class TweetFactory {
    static Tweet newTweet(Status rawTweet) {
        return new Tweet(
                UserFactory.newUser(rawTweet.getUser()),
                rawTweet.getText(),
                rawTweet.getCreatedAt()
        );
    }
}
