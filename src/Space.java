public class Space {
    String spaceEmoji;
    public Space(String spaceEmoji) {
        this.spaceEmoji = spaceEmoji;
    }

    public String getSpaceEmoji() {
        return spaceEmoji;
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
