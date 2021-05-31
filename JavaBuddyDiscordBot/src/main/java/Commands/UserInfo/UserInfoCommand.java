package Commands.UserInfo;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;


import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserInfoCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage("Correct usage for the JavaBuddy UserInfo is: `-userinfo 'tag_user'`").queue();
            return;
        }
        if (ctx.getArgs().size() != 1) {
            ctx.getChannel().sendMessage("Correct usage for the JavaBuddy UserInfo is: `-userinfo 'tag_user'`").queue();
            return;
        }
        Member username;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            username = ctx.getEvent().getMessage().getMentionedMembers().get(0);
            EmbedBuilder info = new EmbedBuilder();
            info.setAuthor(username.getUser().getName(), "https://www.google.com", username.getUser().getAvatarUrl());
            info.setColor(Color.WHITE);
            info.setDescription(username.getUser().getName() + " joined on " + username.getTimeJoined().format(dtf));
            info.addField("Nickname", username.getNickname() == null ? "No Nickname" : username.getNickname(), false);
            info.addField("Roles", getRolesAsString(username.getRoles()), false);
            info.addField("Is the User currently in a voice channel?", username.getVoiceState().inVoiceChannel() ? "YES, in '" + username.getVoiceState().getChannel().getName() + "' Voice Channel" : "NO", false);
            if (username.getVoiceState().inVoiceChannel()) {
                info.addField("Is the User muted?", username.getVoiceState().isMuted() ? "YES" : "NO", false);
                info.addField("Is the User deafened?", username.getVoiceState().isDeafened() ? "YES" : "NO", false);
            }
            ctx.getEvent().getChannel().sendMessage(info.build()).queue();
        } catch (IndexOutOfBoundsException ex) {
            ctx.getEvent().getChannel().sendMessage("You need to provide the name as a mention").queue();
        }

    }

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getHelp() {
        return "Gets information about a specific user";
    }

    private String getRolesAsString(List<Role> rolesList) {
        String roles = "";
        if (!rolesList.isEmpty()) {
            for (int i = 0; i < rolesList.size(); i++) {
                Role rol = rolesList.get(i);
                roles = roles + " " + rol.getName();
            }
        } else {
            roles = "No roles";
        }


        return roles;
    }
}
