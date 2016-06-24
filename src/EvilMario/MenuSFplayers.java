package EvilMario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MenuSFplayers {
    public static Rectangle umpButton = new Rectangle(325, 570, 300, 37);
    public static Rectangle doispButton = new Rectangle(645, 570, 300, 37);

    public static void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("Proxima Nova", Font.BOLD, 45);
        Font fnt1 = new Font("Proxima Nova", Font.BOLD, 30);
        Font fnt2 = new Font("Proxima Nova", Font.BOLD, 15);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        //g.drawString("Ultimate Star Wars Arcade", 350, 40);

        g.setFont(fnt1);
        g.drawString("1 PLAYER", umpButton.x + 49, umpButton.y + 29);
            g2d.draw(umpButton);
        g.drawString("2 PLAYERS", doispButton.x + 73, doispButton.y + 29);
            g2d.draw(doispButton);
        g.setFont(fnt2);
        g.drawString("ONLINE",645,620);

        g.setFont(fnt2);
        g.drawString("VERSION 4.5.2016",1130,20);
    }
}