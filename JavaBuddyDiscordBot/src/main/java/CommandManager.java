import Commands.CommandContext;
import Commands.CommandInterface;
import Commands.Date.DateCommand;
import Commands.Fetch.FetchCommand;
import Commands.Fetch.FetchHelperCommand;
import Commands.Gif.GifCommand;
import Commands.Help.HelpCommand;
import Commands.LavaPlayer.JoinCommand;
import Commands.LavaPlayer.PlayCommand;
import Commands.Ping.PingCommand;
import Commands.TimeTableParser.TimeTableParserCommand;
import Commands.UserInfo.UserInfoCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<CommandInterface> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new TimeTableParserCommand());
        addCommand(new PingCommand());
        addCommand(new JoinCommand());
        addCommand(new PlayCommand());
        addCommand(new DateCommand());
        addCommand(new FetchCommand());
        addCommand(new FetchHelperCommand());
        addCommand(new HelpCommand());
        addCommand(new GifCommand());
        addCommand(new UserInfoCommand());
    }

    private void addCommand(CommandInterface cmd) {
        boolean commandName = this.commands.stream().anyMatch(it -> it.getName().equalsIgnoreCase(cmd.getName()));
        if (commandName)
            throw new IllegalArgumentException("[ERROR] Command is already in the List!");
        commands.add(cmd);
    }

    public List<CommandInterface> getCommands() {
        return commands;
    }

    @Nullable
    public CommandInterface getCommand(String search) {
        for (CommandInterface cmd : this.commands) {
            if (cmd.getName().equals(search.toLowerCase())) {
                return cmd;
            }
        }
        return null;
    }

    void handle(GuildMessageReceivedEvent event, String prefix) {
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");
        String invoke = split[0].toLowerCase();
        CommandInterface cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }
}
