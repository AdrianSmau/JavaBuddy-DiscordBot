package Commands.Ping;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.JDA;

public class PingCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        jda.getRestPing().queue((ping) -> ctx.getChannel().sendMessageFormat("JavaBuddy Reset ping: %sms\nJavaBuddy WS ping: %sms", ping, jda.getGatewayPing()).queue());

    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Shows current ping of JavaBuddy to Discord";
    }
}
