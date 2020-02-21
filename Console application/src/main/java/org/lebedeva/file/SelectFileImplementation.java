package org.lebedeva.file;

import javax.swing.*;
import java.io.File;

public class SelectFileImplementation implements SelectFile {

    /**
     * Method to select a file to read in the dialog window
     *
     * @return selected File
     */
    @Override
    public File getFile() {
        System.out.println("Select the file to read in the dialog window:");

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (chooser.getSelectedFile().isFile()) {
                System.out.println("You selected file: " + chooser.getSelectedFile().getName());
                return chooser.getSelectedFile();
            }
        }
        return new File("");
    }
}
