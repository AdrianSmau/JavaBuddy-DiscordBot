package Commands.Fetch;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class FetchHelperCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("RSS Fetcher Helper");
        info.setDescription("In order to have JavaBuddy deliver you your Java info, select one of the below mentioned feeds and mention its index when typing the command `-fetch`!");
        info.addField("Index #1", "[Freq. 4 posts / month] - The Java Programmer RSS Feed", false);
        info.addField("Index #2", "[Freq. 6 posts / month] - Java | News, how-tos, features, reviews, and videos RSS Feed", false);
        info.addField("Index #3", "[Freq. 6 posts / week] - Java Code Geeks RSS Feed", false);
        info.addField("Index #4", "[Freq. 30 posts / week] - TheServerSide.com RSS Feed", false);
        info.addField("Index #5", "[Freq. 4 posts / month] - A Java geek RSS Feed", false);
        info.addField("Index #6", "[Freq. 1 post / day] - Javarevisited RSS Feed", false);
        info.addField("Index #7", "[Freq. 2 posts / week] - Java67 RSS Feed", false);
        info.addField("Index #8", "[Freq. 12 posts / quarter] - Thoughts on Java | Java Language Writing Blog RSS Feed", false);
        info.addField("Index #9", "[Freq. 1 post / month] - Java, SQL and jOOQ. RSS Feed", false);
        info.addField("Index #10", "[Freq. 5 posts / week] - InfoQ - Java Community Content On InfoQ RSS Feed", false);
        info.addField("Index #11", "[Freq. 1 post / month] - Java AWS Developer Blog RSS Feed", false);
        info.addField("Index #12", "[Freq. 2 posts / month] - Java at Microsoft RSS Feed", false);
        info.addField("Index #13", "[Freq. 4 posts / week] - Baeldung » Java RSS Feed", false);
        info.addField("NOTICE", "The number of displayed news may be up to 20 news!", false);
        info.setFooter("These 13 RSS Feeds were chosen according to `https://blog.feedspot.com/java_rss_feeds/`!");
        info.setColor(Color.white);
        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "rssinfo";
    }

    @Override
    public String getHelp() {
        return "Helper command in order to get an understanding of the available RSS feeds!";
    }
}
