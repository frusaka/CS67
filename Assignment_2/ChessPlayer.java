

public class ChessPlayer {
    private String name;
    private String title;
    private int elo;

    public ChessPlayer(String name, String title, int elo)
    {
        this.name = name;
        this.title = title;
        this.elo = elo;
    }

    public String toString()
    {
        if (this.title == null){
            return String.format("Untitled %s with %d elo", name, elo);
        }
        return String.format("%s %s with %d elo", title, name, elo);
    }
}