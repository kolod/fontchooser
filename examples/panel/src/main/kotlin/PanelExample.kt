package io.github.kolod

import io.github.kolod.model.FontSelectionModel
import java.awt.BorderLayout
import java.lang.reflect.InvocationTargetException
import javax.swing.*
import javax.swing.event.ChangeEvent

class PanelExample : Runnable {
    private val selection = JLabel("Selected font will be displayed here")
    override fun run() {
        selection.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)
        val fontChooser = FontChooser()
        fontChooser.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)
        fontChooser.addChangeListener { event: ChangeEvent ->
            val model = event.source as FontSelectionModel
            selection.text = model.selectedFont.toString()
        }
        val frame = JFrame("Select Font")
        frame.setSize(600, 400)
        frame.add(fontChooser)
        frame.add(selection, BorderLayout.PAGE_END)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    companion object {
        @Throws(InvocationTargetException::class, InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleRunner.useLookAndFeel(UIManager.getSystemLookAndFeelClassName())
            ExampleRunner.invoke(PanelExample())
        }
    }
}
