package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.some_example_name.TowerDefenseGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new TowerDefenseGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Tower Defense Game"); // Ganti nama proyek
        configuration.useVsync(true);
        configuration.setForegroundFPS(60); // Set FPS ke 60
        configuration.setWindowedMode(1280, 720); // Resolusi layar
//        configuration.setWindowIcon("icon128.png", "icon64.png", "icon32.png", "icon16.png"); // Pastikan file ada

        return configuration;
    }
}
