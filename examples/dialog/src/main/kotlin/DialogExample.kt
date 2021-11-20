package io.github.kolod

import java.awt.Frame
import java.lang.reflect.InvocationTargetException
import javax.swing.UIManager
import javax.swing.WindowConstants

class DialogExample : Runnable {

    override fun run() {
        val dialog = FontDialog(null as Frame?, "Select Font", true)
        dialog.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        dialog.setLocationRelativeTo(null)
        dialog.isVisible = true
        if (!dialog.isCancelSelected) {
            System.out.printf("Selected font is: %s%n", dialog.selectedFont)
        }
    }

    companion object {
        @Throws(InvocationTargetException::class, InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleRunner.useLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            ExampleRunner.invoke(DialogExample())
        }
    }
}
