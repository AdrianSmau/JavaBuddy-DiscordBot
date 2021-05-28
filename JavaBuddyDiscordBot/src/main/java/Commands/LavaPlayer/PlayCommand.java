package Commands.LavaPlayer;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage for the JavaBuddy Player is: `-play 'YouTube_Link'`").queue();
            return;
        }

        if (ctx.getArgs().size() != 1) {
            channel.sendMessage("Correct usage for the JavaBuddy Player is: `-play 'YouTube_Link'`").queue();
            return;
        } else {
            if (!isUrl(ctx.getArgs().get(0))) {
                channel.sendMessage("The argument you entered is not a valid YouTube Link!").queue();
                return;
            }
        }

        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to first get JavaBuddy to join you in the Voice Channel using the command `-join`").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a Voice Channel in order to play Audio!").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same Voice Channel as JavaBuddy in order to play Audio!").queue();
            return;
        }

        String link = ctx.getArgs().get(0);

        PlayerManager.getInstance().loadAndPlay(channel, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Makes JavaBuddy play a YouTube audio";
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
    }
}
