package Commands;

import me.duncte123.botcommons.commands.ICommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandContext implements ICommandContext {
    private final GuildMessageReceivedEvent event;
    private final List<String> args;
    private final Map<Integer, String> rssMap;

    public CommandContext(GuildMessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
        this.rssMap = new HashMap<>();
        this.rssMap.put(1, "https://thejavaprogrammer.com/feed");
        this.rssMap.put(2, "https://www.infoworld.com/category/java/index.rss");
        this.rssMap.put(3, "https://javacodegeeks.com/feed");
        this.rssMap.put(4, "https://www.theserverside.com/rss/ContentSyndication.xml");
        this.rssMap.put(5, "https://blog.frankel.ch/feed.xml");
        this.rssMap.put(6, "https://feeds.feedburner.com/Javarevisited");
        this.rssMap.put(7, "https://feeds.feedburner.com/Java67");
        this.rssMap.put(8, "https://thorben-janssen.com/feed/");
        this.rssMap.put(9, "https://blog.jooq.org/feed/");
        this.rssMap.put(10, "https://feed.infoq.com/java");
        this.rssMap.put(11, "https://aws.amazon.com/blogs/developer/category/programing-language/java/feed/");
        this.rssMap.put(12, "https://devblogs.microsoft.com/java/feed/");
        this.rssMap.put(13, "https://feeds.feedblitz.com/baeldung");
    }

    @Override
    public Guild getGuild() {
        return this.getEvent().getGuild();
    }

    @Override
    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }

    public List<String> getArgs() {
        return args;
    }

    public Map<Integer, String> getRssMap() {
        return this.rssMap;
    }
}
