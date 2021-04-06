import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JLabel implements MouseListener {
    final Icon faceIcon;
    final Icon backIcon;
    boolean faceUp = false;
    final int num;
    final int iconWidthHalf;
    final int iconHeightHalf;
    boolean mousePressedOnMe = false;
    private final cardController controller;

    public Card(cardController controller, Icon face, Icon back, int num) {
        super(back);
        this.faceIcon = face;
        this.backIcon = back;
        this.num = num;
        //for mouse control
        this.addMouseListener(this);
        this.iconHeightHalf = back.getIconHeight() / 2;
        this.iconWidthHalf = face.getIconWidth() / 2;
        this.controller = controller;
    }

    public int getNum() {
        return num;
    }

    private boolean overIcon(int x, int y) {
        int distX = Math.abs(x - (this.getWidth() / 2));
        int distY = Math.abs(y - (this.getHeight() / 2));
        return distX <= this.iconHeightHalf && distY <= this.iconWidthHalf;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (overIcon(mouseEvent.getX(), mouseEvent.getY()))
            this.turnUp();
    }

    public void turnUp() {
        if (this.faceUp) return;
        this.faceUp=true;
        this.faceUp=this.controller.turnUp(this);
        if (this.faceUp) this.setIcon(this.faceIcon);
    }

    public void turnDown() {
        if (!this.faceUp) return;
        this.setIcon(this.backIcon);
        this.faceUp = false;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (overIcon(mouseEvent.getX(), mouseEvent.getY()))
            this.mousePressedOnMe = true;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (this.mousePressedOnMe) {
            this.mousePressedOnMe = false;
            this.mouseClicked(event);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.mousePressedOnMe = false;
    }
}