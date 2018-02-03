package twitter_viz;

class UserFactory {
    static User newUser(twitter4j.User rawUser) {
        return new User(
                rawUser.getName(),
                rawUser.getScreenName()
        );
    }
}
