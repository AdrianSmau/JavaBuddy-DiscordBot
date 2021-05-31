import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class JavaBuddy {
    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("ODQxMzkyNDQ2MTE4MTY2NTg4.YJmFxw.aerOyLkyM0f1O6QgkZLMkbr5JRU",
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES).disableCache(EnumSet.of(
                CacheFlag.CLIENT_STATUS,
                CacheFlag.ACTIVITY,
                CacheFlag.EMOTE
        )).enableIntents(GatewayIntent.GUILD_PRESENCES)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableCache(CacheFlag.VOICE_STATE).addEventListeners(new Listener())
                .setActivity(Activity.watching("you learn Java!"))
                .build();
    }
}
