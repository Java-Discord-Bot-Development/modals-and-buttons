import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {

        //This basically will work the same as the SlashCommandInteractionEvent

        if (event.getModalId().equals("sup-modal")) {

            //Get the name and message from the modal
            String name = event.getValue("sup-name").getAsString();
            String message = event.getValue("sup-message").getAsString();

            //Find the Member with that name if it exists
            Optional<Member> memberOptional = event.getGuild().getMembersByName(name, true).stream().findFirst();

            //Send the message to the member
            if (memberOptional.isPresent()) {
                //Reply to the user with the name and message
                event.reply("Sup, " + memberOptional.get().getAsMention() + "! " + message).queue();
            }else{
                //Reply to the user with the name and message
                event.reply("Sup, " + name + "! " + message).queue();
            }

        } else if (event.getModalId().equals("multiply-modal")) {

            //Get the two numbers from the modal and then parse them to int
            String num1 = event.getValue("operand1").getAsString();
            String num2 = event.getValue("operand2").getAsString();

            try {
                int num1Int = Integer.parseInt(num1);
                int num2Int = Integer.parseInt(num2);

                //Calculate the product
                int product = num1Int * num2Int;

                //Reply to the user with the product
                event.reply("The product is: " + product).setEphemeral(true).queue();

            } catch (NumberFormatException e) {
                event.reply("One of the numbers was not a number.").setEphemeral(true).queue();
            }
        }
    }
}