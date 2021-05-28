import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        LOGGER.info("{} is ready", event.getJDA().getSelfUser());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);

        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String raw = event.getMessage().getContentRaw();
        String prefix = "-";
        if (raw.equalsIgnoreCase(prefix + "shutdown")) {
            LOGGER.info("shutdown");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        } else if (raw.equalsIgnoreCase(prefix + "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("JavaBuddy Information");
            info.setDescription("JavaBuddy is our first Discord Bot, created with the purpose of representing a project for our Advanced Programming class!");
            info.addField("Creator #1", "Smau Adrian-Constantin", false);
            info.addField("Creator #2", "Mosor Andrei", false);
            info.setColor(Color.white);

            event.getChannel().sendMessage(info.build()).queue();
        }

        if (raw.startsWith(prefix)) {
            manager.handle(event, prefix);
        }

    }
}
