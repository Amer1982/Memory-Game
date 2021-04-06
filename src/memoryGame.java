import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class memoryGame implements ActionListener {
    private final JFrame mainFrame;
    private final Container mainContentPane;
    private final ImageIcon[] cardIcon;

    public memoryGame() {
        //main window
        this.mainFrame = new JFrame("Memory Game by Amer");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(650,650);
        this.mainFrame.setLocation(400,25);
        this.mainContentPane = this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

        // Menu line
        JMenuBar menuBar = new JMenuBar();
        this.mainFrame.setJMenuBar(menuBar);
        //This is for game menu
        JMenu gameMenu = new JMenu("Game Menu");
        menuBar.add(gameMenu);
        //This is submenu
        newMenuItem("New Game", gameMenu, this);
        newMenuItem("Exit", gameMenu, this);
        //Other menus
        JMenu helpMenu = new JMenu("Help");
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        //to load cards
        this.cardIcon = loadCardIcon();
    }
    private ImageIcon[] loadCardIcon() {
        ImageIcon[] icon = new ImageIcon[9];
        for (int i = 0; i < 9; i++) {
            String fileName = "src/Prva igra/Card" + i + ".jpg";
            icon[i] = new ImageIcon(fileName);
        }
        return icon;
    }
    //panel for the cards
    public JScrollPane makeCards() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,2,2));
        // Had to add ScrollPane because cards weren't showing correctly
        JScrollPane scrollPane=new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //For the back of the card I choose card no 9 with index No 8
        ImageIcon backIcon = this.cardIcon[8];
        cardController controller = new cardController();

        int[] cardsToAdd = new int[16];//8 cards*2
        for (int i = 0; i < 8; i++) {
            cardsToAdd[2 * i] = i;
            cardsToAdd[2 * i + 1] = i;
        }

        //to have random cards
        randomizeCardArray(cardsToAdd);

        for (int num : cardsToAdd) {
            Card newCard = new Card(controller, this.cardIcon[num], backIcon, num);
            panel.add(newCard);
        }
        return scrollPane;
    }

    private void randomizeCardArray(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int x = random.nextInt(a.length);
            //To mix cards
            int y = a[x];
            a[x] = a[i];
            a[i] = y;

        }
    }

    private void newMenuItem(String new_game, JMenu gameMenu, ActionListener listener) {
        JMenuItem newItem = new JMenuItem(new_game);
        newItem.setActionCommand(new_game);
        newItem.addActionListener(listener);
        gameMenu.add(newItem);
    }

    public void newGame() {
        this.mainContentPane.removeAll();
        this.mainContentPane.add(makeCards());
        this.mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("New Game"))
            newGame();

        if (actionEvent.getActionCommand().equals("Exit"))
            System.exit(0);

        if (actionEvent.getActionCommand().equals("Help"))
            System.out.println("Skontat cu poslije");

        if (actionEvent.getActionCommand().equals("About"))
            System.out.println("Skontat cu poslije");
    }
}