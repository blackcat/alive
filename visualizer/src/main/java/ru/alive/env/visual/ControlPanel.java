package ru.alive.env.visual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alive.env.Creature;
import ru.alive.env.Environment;
import ru.alive.exception.VisualisaitonException;
import sun.awt.VerticalBagLayout;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dart
 * @since 11/14/12 3:20 AM
 */
@Service
public class ControlPanel extends JPanel {

    @Autowired
    private Environment env;
    @Autowired
    private EnvironmentVisualisation environmentVisualisation;

    @Value("${util.brainDumpPath}")
    private String brainDumpFilePath;

    private LayoutManager layoutManager = new VerticalBagLayout();
    //todo: add pause button

//    private Button recreateCreaturesButton = new Button("Recreate creatures");  //todo: repair
//    private ActionListener recreateCreaturesListener = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            env.clearCreatures();
//            env.addSomeRandomCreatures();
//            environmentVisualisation.repaint();
//        }
//    };

    private Button dumpBrainsButton = new Button("Dump brains");
    private ActionListener dumpBrainsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            File brainDumpFile = new File(brainDumpFilePath);
            File dirsToFile = brainDumpFile.getParentFile();
            dirsToFile.mkdirs();

            try {
                FileWriter fileWriter = new FileWriter(brainDumpFile);

                for (Creature creature : env.getCreaturesMap()) {
                    fileWriter
                            .append(creature.getName())
                            .append("\n")
                            .append(creature.getBrain().dump(creature))
                    ;
                }
                fileWriter.close();
            } catch (IOException e) {
                throw new VisualisaitonException("Brain dumping exception", e);
            }
        }
    };

    @PostConstruct
    public void init() {
        this.setLayout(layoutManager);
        setMinimumSize(new Dimension(100, 300));

        setBorder(BorderFactory.createLineBorder(Color.black));

//        recreateCreaturesButton.addActionListener(recreateCreaturesListener);
//
//        add(recreateCreaturesButton);
        add(dumpBrainsButton);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
