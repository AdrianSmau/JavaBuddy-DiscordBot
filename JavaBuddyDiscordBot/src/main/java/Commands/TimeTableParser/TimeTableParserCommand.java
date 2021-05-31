package Commands.TimeTableParser;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;

public class TimeTableParserCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage for the JavaBuddy TimeTable is: `-timetable 'Day of the Week'`").queue();
            return;
        }
        String dayOfTheWeek = null;
        if (ctx.getArgs().size() != 1) {
            channel.sendMessage("Correct usage for the JavaBuddy TimeTable is: `-timetable 'Day of the Week'`").queue();
            return;
        } else {
            switch (ctx.getArgs().get(0).toLowerCase()) {
                case "monday" -> dayOfTheWeek = "luni";
                case "tuesday" -> dayOfTheWeek = "marti";
                case "wednesday" -> dayOfTheWeek = "miercuri";
                case "thursday" -> dayOfTheWeek = "joi";
                case "friday" -> dayOfTheWeek = "vineri";
                case "saturday" -> dayOfTheWeek = "sambata";
                case "sunday" -> dayOfTheWeek = "duminica";
                default -> channel.sendMessage("Day of the Week inserted not valid! Valid days are Monday through Sunday!").queue();
            }
        }
        if (dayOfTheWeek == null)
            return;

        String url = "https://profs.info.uaic.ro/~orar/participanti/orar_I2B5.html";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException ex) {
            System.err.println("[ERROR] IOException caught when trying to retrieve timetable!");
        }
        Elements elems = document.select("tbody").select("tr");
        System.out.println(elems.toString());
        String currentTitle = null;
        boolean empty = true;
        for (Element x : elems) {
            if (x.child(0).text().equals("De la"))
                continue;
            if (x.children().size() == 1) {
                currentTitle = x.text();
            } else {
                if (!currentTitle.substring(0, dayOfTheWeek.length()).toLowerCase().equals(dayOfTheWeek))
                    continue;
                empty = false;
                EmbedBuilder info = new EmbedBuilder();
                info.setTitle(currentTitle + " [" + x.child(0).text() + " - " + x.child(1).text() + "]");
                info.setColor(Color.white);
                info.setThumbnail("https://www.uaic.ro/wp-content/uploads/2013/12/steag_uaic.jpg");
                info.addField("Time Frame", x.child(0).text() + " - " + x.child(1).text(), true);
                info.addField("Class Name", x.child(2).text(), true);
                info.addField("Class Type", x.child(3).text(), true);
                info.addField("Professor(s)", x.child(4).text(), true);
                if (x.child(5).text().length() > 1)
                    info.addField("Classroom", x.child(5).text(), true);
                else
                    info.addField("Classroom", "Online", true);
                if (x.child(7).text().length() > 1)
                    info.addField("Optional Package Number", x.child(7).text(), true);
                ctx.getEvent().getChannel().sendMessage(info.build()).queue();
            }
        }
        if (empty)
            channel.sendMessage("You do not have any Classes in that day!").queue();
        else
            channel.sendMessage("[JavaBot] TimeTable for the specified day has been printed successfully!").queue();
    }

    @Override
    public String getName() {
        return "timetable";
    }

    @Override
    public String getHelp() {
        return "JavaBuddy provides methods for you to consult your TimeTable!";
    }
}
