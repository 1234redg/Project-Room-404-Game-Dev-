package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;

public class StoryPanel extends JPanel implements ActionListener, KeyListener {

    private JFrame window;

    private Image[] images;
    private Queue<String> storyQueue;        
    private String currentText = "";         
    private int textPos = 0;                 

    private boolean textFinished = false;    
    private boolean lastScreen = false;      

    private Timer typingTimer;               
    private int index = 0;                   
    private String currentStory;             

    public StoryPanel(JFrame window) {
        this.window = window;

        // LOAD IMAGES
        images = new Image[5];
        try {
            images[0] = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/story1.png"));
            images[1] = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/story2.png"));
            images[2] = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/story4.png"));
            images[3] = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/story3.png"));
            images[4] = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/story5.png"));
        } catch (IOException e) {
            System.out.println("Story images not found!");
        }

        // INITIALIZE QUEUE
        storyQueue = new LinkedList<>();
        storyQueue.add("It was a stormy night at Hillcrest Apartment. The wind howled outside, rattling the windows. Thunder echoed through the empty halls, and rain pounded against the roof.");
        storyQueue.add("Inside the apartment that night lived different people with different professions and occupations. It was just a typical rainy night—until something unexpected happened…");
        storyQueue.add("On the night of the 24th of November, the lifeless body of the maintenance worker was discovered in Room 404. Panic spread throughout the building, but it was already too late—the doors had been locked. Only five people were in the dormitory that night: You, as the investigator, John, Mark, Joy, and Mae.");
        storyQueue.add("Each claims to be innocent… but one of them is lying. Your job is clear: explore the apartment, gather clues, and use logic to uncover who the killer is. Be careful—one wrong move, and you may become the next victim...");
        storyQueue.add("Press Enter to Continue.");

        currentStory = storyQueue.poll();

        this.setFocusable(true);
        this.addKeyListener(this);

        typingTimer = new Timer(45, this);
        typingTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!textFinished) {
            if (textPos < currentStory.length()) {
                currentText += currentStory.charAt(textPos);
                textPos++;
                repaint();
            } else {
                textFinished = true;
                if (index == images.length - 1) {
                    lastScreen = true;
                }
                repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(images[index], 0, 0, getWidth(), getHeight(), null);

        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRoundRect(40, getHeight() - 170, getWidth() - 80, 120, 20, 20);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Serif", Font.PLAIN, 22));
        drawWrappedText(g2, currentText, 60, getHeight() - 140, getWidth() - 120);

        if (index < 4) {
            String skipText = "Press ENTER to skip.";
            g2.setFont(new Font("Serif", Font.PLAIN, 18));
            FontMetrics fm = g2.getFontMetrics();
            int skipX = getWidth() - fm.stringWidth(skipText) - 60;
            int skipY = getHeight() - 60;
            g2.drawString(skipText, skipX, skipY);
        }
    }

    private int drawWrappedText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        String line = "";
        int lineHeight = fm.getHeight();

        for (String word : words) {
            if (fm.stringWidth(line + word) < maxWidth) {
                line += word + " ";
            } else {
                g2.drawString(line, x, y);
                y += lineHeight;
                line = word + " ";
            }
        }
        g2.drawString(line, x, y);
        return y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // If on last screen and text is finished, transition to game
            if (lastScreen && textFinished) {
                startGame();
                return;
            }

            // Otherwise, handle story progression
            if (!textFinished) {
                currentText = currentStory;
                textFinished = true;
                repaint();
            } else {
                nextStory();
            }
        }
    }

    private void nextStory() {
        index++;

        if (index >= images.length) {
            index = images.length - 1;
            lastScreen = true;
            return;
        }

        if (index == images.length - 1) {
            lastScreen = true;
        }

        String next = storyQueue.poll();
        if (next != null) currentStory = next;

        currentText = "";
        textPos = 0;
        textFinished = false;

        repaint();
    }

    private void startGame() {
        GamePanel gp = new GamePanel();

        // IMPORTANT FIX — without this, map + objects NEVER load
        gp.SetUpGame();

        window.setContentPane(gp);
        window.revalidate();
        gp.requestFocusInWindow();
        gp.startGameThread();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
