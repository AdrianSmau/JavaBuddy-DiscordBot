package Commands.Date;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Current Date and Time");
        info.addField("Date:", String.valueOf(java.time.LocalDate.now()), false);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        info.addField("Time:", formatter.format(date), false);
        info.setColor(Color.white);

        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "date";
    }

    @Override
    public String getHelp() {
        return "Getting the current date and time!";
    }
}
