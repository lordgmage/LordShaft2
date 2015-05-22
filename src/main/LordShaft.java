package main;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import strategies.BankingShaft;
import strategies.ChopAndCut;
import strategies.Shafting;
import view.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

@ScriptManifest(author = "Lord",
        category = Category.FLETCHING,
        description = "Makes Arrow shaft",
        name = "LordShaft",
        servers = {"Ikov"},
        version = 2.0)

public class LordShaft extends Script implements MessageListener, Paintable {

    private static ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    public static boolean bank;
    public static boolean start;
    public static int shaftsmade;
    public static int treescut;
    private static long startTime = System.currentTimeMillis();
    private final Color color1 = new Color(255, 255, 255);
    private final Color color2 = new Color(0, 0, 0);
    private final Font font1 = new Font("Cooper Black", 0, 16);
    private final Image img1 = getImage("http://i.imgur.com/QkrKz34.png");


    public boolean onExecute() {
        new UI();
        while (!start) {
            Time.sleep(500);
        }

        strategies.add(new Shafting());
        if (!bank) {
            strategies.add(new ChopAndCut());
        } else {
            strategies.add(new BankingShaft());
        }
        provide(strategies);

        return true;

    }

    public static String runTime(long i) {

        DecimalFormat nf = new DecimalFormat("00");
        long millis = System.currentTimeMillis() - i;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;
        return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        if (messageEvent.getMessage().contains("You fletch the bow")) {
            shaftsmade += 15;
        }
        if (messageEvent.getMessage().contains("You get some Logs")) {
            treescut += 1;
        }
    }


    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.drawImage(img1, 0, 337, null);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString(" " + runTime(startTime), 238, 386);
        g.drawString(" " + shaftsmade, 203, 437);
        g.drawString(" " + treescut, 382, 437);
    }
}




