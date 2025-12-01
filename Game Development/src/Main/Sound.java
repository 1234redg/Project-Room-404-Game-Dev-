package Main;

public class Sound {
    public String filePath;

    public Sound(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        AudioPlayer.getInstance().playMusic(filePath);
    }
}
