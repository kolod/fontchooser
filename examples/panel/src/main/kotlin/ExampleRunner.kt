package io.github.kolod

import java.awt.EventQueue
import java.lang.reflect.InvocationTargetException
import java.util.*
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException


object ExampleRunner {
    @Throws(InterruptedException::class, InvocationTargetException::class)
    operator fun invoke(runnable: Runnable?) {
        enhanceFontRendering()
        useDifferentTranslation()
        EventQueue.invokeAndWait(runnable)
    }

    private fun useDifferentTranslation() {
        Locale.setDefault(Locale("en"))
    }

    private fun enhanceFontRendering() {
        System.setProperty("swing.aatext", "true")
        System.setProperty("awt.useSystemAAFontSettings", "lcd")
    }

    fun useLookAndFeel(lookAndFeelClass: Class<*>) {
        val lookAndFeelName = lookAndFeelClass.name
        useLookAndFeel(lookAndFeelName)
    }

    fun useLookAndFeel(lookAndFeelName: String) {
        try {
            UIManager.setLookAndFeel(lookAndFeelName)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Could not set look and feel to $lookAndFeelName", e)
        } catch (e: InstantiationException) {
            throw RuntimeException("Could not set look and feel to $lookAndFeelName", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Could not set look and feel to $lookAndFeelName", e)
        } catch (e: UnsupportedLookAndFeelException) {
            throw RuntimeException("Could not set look and feel to $lookAndFeelName", e)
        }
    }
}
