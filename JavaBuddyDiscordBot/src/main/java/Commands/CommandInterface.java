package Commands;

public interface CommandInterface {
    void handle(CommandContext ctx);
    String getName();
    String getHelp();
}
