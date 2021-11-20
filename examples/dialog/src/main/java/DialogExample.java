import io.github.kolod.FontDialog;

import javax.swing.*;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

public class DialogExample implements Runnable {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.useLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        ExampleRunner.invoke(new DialogExample());
    }

    @Override
    public void run() {
        FontDialog dialog = new FontDialog((Frame) null, "Select Font", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (!dialog.isCancelSelected()) {
            System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
        }
    }

}
