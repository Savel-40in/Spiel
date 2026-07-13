import java.awt.*;

public abstract class Button {
    protected int x, y, width, height;
    protected String text;

    public final void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width, height, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(text, x + 10, y + height / 2 + 5);
    }

    public final boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public abstract void onClick();

}
