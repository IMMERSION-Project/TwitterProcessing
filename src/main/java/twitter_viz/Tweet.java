package twitter_viz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
class Tweet {
    private final User author;
    private final String content;
    private final Date createdAt;
}
