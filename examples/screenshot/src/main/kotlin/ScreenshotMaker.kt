package io.github.kolod

import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import javax.imageio.ImageIO
import javax.swing.CellRendererPane
import javax.swing.UIManager
import javax.swing.border.EmptyBorder


class ScreenshotMaker : Runnable {
    override fun run() {
        val size = Dimension(575, 275)
        for (lookAndFeelClassName in LOOK_AND_FEELS) {
            ExampleRunner.useLookAndFeel(lookAndFeelClassName)
            val fontChooser = FontChooser(Font("Helvetica", Font.PLAIN, 24))
            fontChooser.border = EmptyBorder(10, 10, 10, 10)
            fontChooser.size = size
            layoutComponent(fontChooser)
            val rendererPane = CellRendererPane()
            rendererPane.add(fontChooser)
            val image = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB)
            val graphics: Graphics = image.createGraphics()
            rendererPane.paintComponent(graphics, fontChooser, rendererPane, fontChooser.bounds)
            val pathname = createFileName(lookAndFeelClassName)
            try {
                ImageIO.write(image, "png", File(pathname))
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }

    private fun createFileName(lookAndFeelClassName: String): String {
        val lookAndFeelClassNameParts = lookAndFeelClassName.split("\\.").toTypedArray()
        val lookAndFeelName = lookAndFeelClassNameParts[lookAndFeelClassNameParts.size - 1].lowercase()
        return "fontchooser-$lookAndFeelName.png"
    }

    companion object {
        private val LOOK_AND_FEELS = arrayOf(
            UIManager.getCrossPlatformLookAndFeelClassName(),
            UIManager.getSystemLookAndFeelClassName()
        )

        @Throws(InvocationTargetException::class, InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            ExampleRunner.invoke(ScreenshotMaker())
        }

        fun layoutComponent(component: Component) {
            synchronized(component.treeLock) {
                component.doLayout()
                if (component is Container) {
                    for (child in component.components) {
                        layoutComponent(child)
                    }
                }
            }
        }
    }
}
