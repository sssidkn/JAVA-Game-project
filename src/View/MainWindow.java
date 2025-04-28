package View;

import Model.Game;
import Contorller.MoveListener;
import Contorller.StartListener;
import Contorller.WindowResizedListener;
import Observer.WindowObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private final Game game;
    private Image bg;
    private JButton startButton;
    private JLabel gameName;
    private BackgroundPanel backgroundPanel;
    private ArrayList<WindowObserver> observers;
    private Font customFont;

    public MainWindow() throws IOException, FontFormatException {
        super("Game");
        customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/HellasDustCyrillic.ttf"));
        customFont = customFont.deriveFont(Font.BOLD, 60);

        observers = new ArrayList<>();

        game = new Game();
        game.addWindow(this);

        observers.addAll(game.getObjects());

        setUI();
        showMainMenu();

        setFocusable(true);
        addKeyListener(new MoveListener(Game.getHero()));
        addComponentListener(new WindowResizedListener(this));

        setVisible(true);
    }

    private void setUI() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowDimension = new Dimension(screenDimension.width / 2, screenDimension.height / 2);
        setMinimumSize(windowDimension);
        setLocation(screenDimension.width / 4, screenDimension.height / 4);

        startButton = new JButton("Start");
        startButton.setFont(customFont);
        startButton.setFocusable(false);
        buttonSettings(startButton);

        gameName = new JLabel("Game");
        gameName.setFont(customFont.deriveFont(Font.BOLD, 80));
        gameName.setForeground(Color.WHITE);

        startButton.addActionListener(new StartListener(this));
        setLayout(new GridBagLayout());
        try {
            add(Canvas.getInstance(), new GridBagConstraints(
                    0, 0, 1, 1, 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        backgroundPanel = new BackgroundPanel("src/res/Background.png");
    }

    private void buttonSettings(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBorder(null);
        btn.setForeground(Color.WHITE);
    }

    public void showMessageMenu(String message) throws IOException {
        remove(Canvas.getInstance());
        validate();
        gameName.setText(message);
        showMainMenu();
        remove(startButton);
    }

    public void showMainMenu() {
        setContentPane(backgroundPanel);
        setLayout(new GridBagLayout());

        add(gameName, new GridBagConstraints(
                0, 0, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(5, 5, 5, 5), 0, 0
        ));
        add(startButton, new GridBagConstraints(
                0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(5, 5, 5, 5), 0, 0
        ));
    }

    public void closeMainMenu() {
        remove(gameName);
        remove(startButton);
        remove(backgroundPanel);
    }

    private class BackgroundPanel extends JPanel {
        public BackgroundPanel(String path) throws IOException {
            bg = ImageIO.read(new File(path));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }

    public void addObserver(WindowObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WindowObserver observer) {
        observers.remove(observer);
    }

    public ArrayList<WindowObserver> getObservers() {
        return observers;
    }

    public Game getGame() {
        return game;
    }
}
