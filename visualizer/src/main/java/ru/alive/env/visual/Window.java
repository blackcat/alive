package ru.alive.env.visual;

import javax.swing.*;
import java.awt.*;

/**
 * @author pvyazankin
 * @since 08.11.12 12:38
 */
public class Window extends JFrame {

    public Window(EnvironmentVisualisation visualisation) throws HeadlessException {
        super("Universe visualisation");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(visualisation);
        setSize(visualisation.getSize());
        setResizable(false);

        setVisible(true);


    }
}
