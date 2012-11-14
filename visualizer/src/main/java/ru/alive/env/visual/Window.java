package ru.alive.env.visual;

import org.springframework.stereotype.Service;
import sun.awt.HorizBagLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author pvyazankin
 * @since 08.11.12 12:38
 */
public class Window extends JFrame {

    public Window(EnvironmentVisualisation visualisation, ControlPanel controlPanel) throws HeadlessException {
        super("Universe visualisation");

        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(visualisation);
        add(controlPanel);
        setSize(500, visualisation.getHeight());
        setResizable(false);

        setVisible(true);


    }
}
