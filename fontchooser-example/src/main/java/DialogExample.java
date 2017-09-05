import io.github.dheid.fontchooser.FontChooserDialog;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;


public class DialogExample {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        // better font rendering
        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");

        // FontChooserDialog and FontChooser provide different translations
        Locale.setDefault(new Locale("en"));

        EventQueue.invokeAndWait(() -> {
            FontChooserDialog dialog = new FontChooserDialog((Frame)null, "Font Dialog Example", true);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (!dialog.isCancelSelected()) {
                System.out.println("Selected font is: " + dialog.getSelectedFont());
            }
        });

    }

}