package Commands.Help;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class HelpCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("JavaBuddy Help");
        info.setDescription("Feel free to experiment with the commands and expand your Java Knowledge!");
        info.addField("`-info`", "Some Information about JavaBuddy", false);
        info.addField("`-timetable 'day_of_the_week'`","JavaBuddy offers you a TimeTable of the group B5, 2nd year students at UAIC Iasi",false);
        info.addField("`-rssinfo`", "Offers some information about our RSS Fetcher", false);
        info.addField("`-fetch 'index_of_RSS_feed' 'number_of_displayed_news'`", "Gets the specified number of news to you, from the RSS News Outlet with the mentioned index", false);
        info.addField("`-join`", "Makes JavaBuddy join you in your current Voice Channel", false);
        info.addField("`-play 'youtube_link'`", "JavaBuddy plays the specified YouTube link (possible only if JavaBuddy is in the same Voice Channel as you)", false);
        info.addField("`-ping`", "Shows the current ping for JavaBuddy", false);
        info.addField("`-date`", "Displays the current date", false);
        info.addField("`-gif`","Searches for a GIF related to Java",false);
        info.addField("`-userinfo 'tag_user'`","Offers information about the specified username",false);
        info.addField("`-shutdown`", "Makes JavaBuddy shut down", false);
        info.setColor(Color.white);
        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Explains to the User what JavaBuddy can do!";
    }
}
