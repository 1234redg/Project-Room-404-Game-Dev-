package Main;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class MainMenu extends JPanel {

    private JFrame window;
    private Image background;
    private Image aboutImage;

    private JButton startButton;
    private JButton aboutButton;
    private JButton exitButton;

    private int startX = 118;
    private int startY = 450;
    private int aboutX = 118;
    private int aboutY = 560;
    private int exitX = 118;
    private int exitY = 690;
    private int buttonWidth = 260;
    private int buttonHeight = 75;

    public MainMenu(JFrame window) {
        this.window = window;

        try {
            background = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/MainMenu1.png"));
        } catch (Exception e) {
            System.out.println("Background image not found!");
        }

        try {
            aboutImage = ImageIO.read(getClass().getClassLoader().getResource("MurderRoomMaps/AboutMenu1.png"));
        } catch (Exception e) {
            System.out.println("About image not found!");
        }

        setLayout(null);

        // START BUTTON
        startButton = new FadeButton();
        startButton.setBounds(startX, startY, buttonWidth, buttonHeight);
        styleButton(startButton);
        startButton.addActionListener(e -> fadeToStartGame());
        add(startButton);

        // ABOUT BUTTON
        aboutButton = new FadeButton();
        aboutButton.setBounds(aboutX, aboutY, buttonWidth, buttonHeight);
        styleButton(aboutButton);
        aboutButton.addActionListener(e -> fadeToAbout());
        add(aboutButton);

        // EXIT BUTTON
        exitButton = new FadeButton();
        exitButton.setBounds(exitX, exitY, buttonWidth, buttonHeight);
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    // -----------------------------------------------------------
    private void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBorder(null);
        btn.setBackground(new Color(0, 0, 0, 0));
    }

    // -----------------------------------------------------------
    // FADE BUTTON CLASS
    private class FadeButton extends JButton {
        private float alpha = 0f;
        private float targetAlpha = 0f;

        private final int interval = 10;
        private final float speed = 0.008f;

        private Timer fadeTimer;

        public FadeButton() {
            super();
            setOpaque(false);

            fadeTimer = new Timer(interval, e -> animateFade());

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    targetAlpha = 0.4f;
                    fadeTimer.start();
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    targetAlpha = 0f;
                    fadeTimer.start();
                }
            });
        }

        private void animateFade() {
            if (alpha < targetAlpha)
                alpha = Math.min(alpha + speed, targetAlpha);
            else if (alpha > targetAlpha)
                alpha = Math.max(alpha - speed, targetAlpha);
            else
                fadeTimer.stop();

            repaint();
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.SrcOver.derive(alpha));
            g2.setColor(new Color(10, 10, 10, 150));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
        }
    }

    // -----------------------------------------------------------
    // FADE TRANSITION BLACK SCREEN
    private class FadeTransitionPanel extends JPanel {
        private float alpha = 0f;
        private Timer timer;
        private Runnable onFinish;

        public FadeTransitionPanel(Runnable onFinish) {
            this.onFinish = onFinish;
            setOpaque(false);

            timer = new Timer(15, e -> {
                alpha += 0.05f;
                if (alpha >= 1f) {
                    alpha = 1f;
                    timer.stop();
                    if (onFinish != null) onFinish.run();
                }
                repaint();
            });

            timer.start();
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.SrcOver.derive(alpha));
            g2.setColor(Color.black);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    // -----------------------------------------------------------
    // FIXED: START → STORY PANEL (not GamePanel)
    private void fadeToStartGame() {
        FadeTransitionPanel fade = new FadeTransitionPanel(null);
        window.setGlassPane(fade);
        fade.setVisible(true);

        new Thread(() -> {

            StoryPanel story = new StoryPanel(window);

            try { Thread.sleep(600); } catch (Exception ignored) {}

            SwingUtilities.invokeLater(() -> {
                window.setContentPane(story);
                window.revalidate();
                story.requestFocusInWindow();
                fade.setVisible(false);
            });

        }).start();
    }

    // -----------------------------------------------------------
    // ABOUT PAGE
    private void fadeToAbout() {
        FadeTransitionPanel fade = new FadeTransitionPanel(null);
        window.setGlassPane(fade);
        fade.setVisible(true);

        new Thread(() -> {
            try { Thread.sleep(600); } catch (Exception ignored) {}

            SwingUtilities.invokeLater(() -> {
                JPanel aboutPanel = new JPanel() {
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (aboutImage != null)
                            g.drawImage(aboutImage, 0, 0, getWidth(), getHeight(), this);
                    }
                };

                aboutPanel.setLayout(null);

                JButton backButton = new FadeButton();
                backButton.setBounds(180, 90, 55, 50);
                styleButton(backButton);

                // Make back button fully invisible
                backButton.setOpaque(false);
                backButton.setContentAreaFilled(false);
                backButton.setBorderPainted(false);
                backButton.setBackground(new Color(0, 0, 0, 0));

                // Disable hover fade effect
                backButton.addMouseListener(null);

                backButton.addActionListener(e -> {
                    window.setContentPane(MainMenu.this);
                    window.revalidate();
                

                });

                aboutPanel.add(backButton);
                window.setContentPane(aboutPanel);
                window.revalidate();
                fade.setVisible(false);
            });

        }).start();
    }

    // -----------------------------------------------------------
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null)
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
