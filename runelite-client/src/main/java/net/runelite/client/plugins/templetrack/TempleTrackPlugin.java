package net.runelite.client.plugins.templetrack;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Temple Tracking",
        description = "Highlights bog path!",
        tags = {"temple", "track", "swampletics", "bog", "highlight", "overlay"}
)

public class TempleTrackPlugin extends Plugin{
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private TempleTrackConfig config;

    @Inject
    private TempleTrackOverlay overlay;

    @Provides
    TempleTrackConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(TempleTrackConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
    }
}

