package io.github.kolod

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.lang.reflect.InvocationTargetException
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.WindowConstants
import javax.swing.plaf.metal.MetalLookAndFeel

class ShowDialogExample : Runnable {
    override fun run() {
        val frame = JFrame()
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.setSize(600, 300)
        val textArea = JTextArea("This text will be formatted according to the selected font. " +
            "Double click to change font.")
        textArea.lineWrap = true
        textArea.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount == 2) {
                    FontDialog.showDialog(textArea)
                }
            }
        })
        val scrollPane = JScrollPane(textArea)
        frame.add(scrollPane)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    companion object {
        @Throws(InvocationTargetException::class, InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleRunner.useLookAndFeel(MetalLookAndFeel::class.java)
            ExampleRunner.invoke(ShowDialogExample())
        }
    }
}
