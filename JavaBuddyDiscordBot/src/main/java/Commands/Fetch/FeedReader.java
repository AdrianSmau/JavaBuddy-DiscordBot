package Commands.Fetch;

import Commands.CommandContext;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
import com.sun.syndication.io.FeedException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedReader implements Runnable {
    private final CommandContext ctx;
    private final String rssFeed;
    private final Integer indexOfFeed;
    private final Integer displayNum;

    public FeedReader(CommandContext ctx, String rssFeed, Integer indexOfFeed, Integer displayNum) {
        this.ctx = ctx;
        this.rssFeed = rssFeed;
        this.indexOfFeed = indexOfFeed;
        this.displayNum = displayNum;
    }

    public void run() {
        FeedFetcher fetcher = new HttpClientFeedFetcher();
        try {
            String feedName = null;
            switch (this.indexOfFeed) {
                case 1 -> feedName = "The Java Programmer RSS Feed";
                case 2 -> feedName = "Java | News, how-tos, features, reviews, and videos RSS Feed";
                case 3 -> feedName = "Java Code Geeks RSS Feed";
                case 4 -> feedName = "TheServerSide.com RSS Feed";
                case 5 -> feedName = "A Java geek RSS Feed";
                case 6 -> feedName = "Javarevisited RSS Feed";
                case 7 -> feedName = "Java67 RSS Feed";
                case 8 -> feedName = "Thoughts on Java | Java Language Writing Blog RSS Feed";
                case 9 -> feedName = "Java, SQL and jOOQ. RSS Feed";
                case 10 -> feedName = "InfoQ - Java Community Content On InfoQ RSS Feed";
                case 11 -> feedName = "Java AWS Developer Blog RSS Feed";
                case 12 -> feedName = "Java at Microsoft RSS Feed";
                case 13 -> feedName = "Baeldung » Java RSS Feed";
            }
            URL rssFeedURL;
            try {
                rssFeedURL = new URL(this.rssFeed);
            } catch (MalformedURLException ex) {
                System.err.println("[ERROR] Malformed URL Exception!");
                return;
            }
            SyndFeed feed = fetcher.retrieveFeed(rssFeedURL);
            int index = 0;
            for (Object x : feed.getEntries()) {
                index++;
                if (index > this.displayNum)
                    break;
                SyndEntry entry = (SyndEntry) x;
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle(feedName + " - News #" + index);
                info.addField("Title", entry.getTitle(), false);
                info.addField("Link", entry.getLink(), false);
                info.addField("Author", entry.getAuthor(), false);
                info.addField("Date of Publication", entry.getPublishedDate().toString(), false);
                info.setColor(Color.white);
                this.ctx.getEvent().getChannel().sendMessage(info.build()).queue();
            }
        } catch (IOException | FeedException | FetcherException e) {
            Logger.getLogger(FeedReader.class.getName()).log(Level.SEVERE, null, e);
        }
        final TextChannel channel = ctx.getChannel();
        channel.sendMessage("[JavaBot] Required RSS Feed printed successfully!").queue();
    }
}
