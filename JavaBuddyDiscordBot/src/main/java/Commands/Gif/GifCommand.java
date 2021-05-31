package Commands.Gif;

import Commands.CommandContext;
import Commands.CommandInterface;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Random;


public class GifCommand implements CommandInterface {

    @Override
    public void handle(CommandContext ctx) {
        Giphy giphy = new Giphy("01KooeUm8hpPPvut71eHZmisiJBuLQY3");

        SearchFeed feed = null;
        Random rand = new Random();
        int offset = rand.nextInt(15);
        try {
            feed = giphy.search("java programming", 1, offset);
        } catch (GiphyException e) {
            e.printStackTrace();
        }
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Random GIF about Java");
        assert feed != null;
        info.setImage(feed.getDataList().get(0).getImages().getOriginal().getUrl());
        info.setColor(Color.white);

        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "gif";
    }

    @Override
    public String getHelp() {
        return "Gets a GIF about Java";
    }
}
