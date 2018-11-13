package de.freenet.espresso_databinding

import android.app.Application
import de.freenet.espresso_databinding.ui.main.mainModule
import org.koin.android.ext.android.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule))
    }
}