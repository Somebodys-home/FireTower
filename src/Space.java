public class Space {
    private String spaceEmoji;
    private int x;
    private int y;

    public Space(String spaceEmoji, int x, int y) {
        this.spaceEmoji = spaceEmoji;
        this.x = x;
        this.y = y;
    }

    public String getSpaceEmoji() {
        return spaceEmoji;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //POSSIBLE PRECONDITION?? -Isfar

    public void setSpaceEmoji(String newEmoji) {
        if (newEmoji != null && !newEmoji.isEmpty()) {
            spaceEmoji = newEmoji;
        } else {
            System.out.println("Error: Invalid emoji");
        }
    }


}
