package ru.alive.env.visual;

import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.env.Environment;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author dart
 * @since 11/14/12 3:20 AM
 */
public class ControlPanel extends JPanel {

    private final Environment env;
    private final EnvironmentVisualisation environmentVisualisation;

    private LayoutManager layoutManager = new VerticalBagLayout();
    private Button recreateCreaturesButton = new Button("Recreate creatures");
    private ActionListener recreateCreaturesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            env.clearCreatures();
            env.addSomeRandomCreatures();
            environmentVisualisation.repaint();
        }
    };

    public ControlPanel(Environment environment, EnvironmentVisualisation environmentVisualisation) {
        env = environment;
        this.environmentVisualisation = environmentVisualisation;

        this.setLayout(layoutManager);
        setMinimumSize(new Dimension(100, 300));

        setBorder(BorderFactory.createLineBorder(Color.black));

        recreateCreaturesButton.addActionListener(recreateCreaturesListener);

        add(recreateCreaturesButton);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
