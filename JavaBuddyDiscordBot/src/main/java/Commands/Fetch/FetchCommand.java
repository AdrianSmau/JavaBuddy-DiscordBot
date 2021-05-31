package Commands.Fetch;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.entities.TextChannel;

public class FetchCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage for the JavaBuddy RSS Fetcher is: `-fetch 'Index_Of_Feed' 'Number_Of_News'`. For more details type in `-rssinfo`!").queue();
            return;
        }

        if (ctx.getArgs().size() != 2) {
            channel.sendMessage("Correct usage for the JavaBuddy RSS Fetcher is: `-fetch 'Index_Of_Feed' 'Number_Of_News'`. For more details type in `-rssinfo`!").queue();
            return;
        }

        int index = Integer.parseInt(ctx.getArgs().get(0));
        int displayNum = Integer.parseInt(ctx.getArgs().get(1));

        if (index <= 0 || index > 13) {
            channel.sendMessage("Not a valid index! For more details type in `-rssinfo`!").queue();
            return;
        }

        if (displayNum <= 0 || displayNum > 20) {
            channel.sendMessage("Not a valid number of news for display! For more details type in `-rssinfo`!").queue();
            return;
        }

        String myRssFeed = ctx.getRssMap().get(index);
        FeedReader fr = new FeedReader(ctx, myRssFeed, index, displayNum);
        fr.run();
    }

    @Override
    public String getName() {
        return "fetch";
    }

    @Override
    public String getHelp() {
        return "JavaBuddy allows you to get your daily dose of Java from different RSS feeds!";
    }
}