import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.createDefault("OTQ2MTg0OTM3NTc1NDI4MTE2.YhbBZA.epdfTDPBdyMVG430giNAt4_h6E8")
                .setActivity(Activity.listening("to your grandma"))
                .addEventListeners(new SupCommand(), new ModalListener(), new ButtonListener())
                .build().awaitReady();

        //Modals - Popups that accept input from the user

        //Register the slash commands as a guild command
        Guild guild = jda.getGuildById("946147235375251498");

        if (guild != null){
            guild.upsertCommand("sup", "Sup wassup to someone").queue();
            guild.upsertCommand("multiply", "Multiply two numbers together").queue();
        }

    }
}