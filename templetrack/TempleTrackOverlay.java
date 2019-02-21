package net.runelite.client.plugins.templetrack;

import net.runelite.api.*;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class TempleTrackOverlay extends Overlay {
    private static final int MAX_DISTANCE = 2500;
    private final Client client;
    private final TempleTrackPlugin plugin;
    private final TempleTrackConfig config;

    @Inject
    private TempleTrackOverlay(Client client, TempleTrackPlugin plugin, TempleTrackConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        renderTileObjects(graphics);
        return null;
    }

    private void renderGroundObject(Graphics2D graphics, Tile tile, Player player)
    {
        GroundObject groundObject = tile.getGroundObject();
        if (groundObject != null) {
            if (player.getLocalLocation().distanceTo(groundObject.getLocalLocation()) <= MAX_DISTANCE && groundObject.getId() == 13838) {
                OverlayUtil.renderTileOverlay(graphics, groundObject, "", Color.green);
            }
        }
    }

    private void renderTileObjects(Graphics2D graphics)
    {
        Scene scene = client.getScene();
        Tile[][][] tiles = scene.getTiles();

        int z = client.getPlane();

        for (int x = 0; x < Constants.SCENE_SIZE; ++x)
        {
            for (int y = 0; y < Constants.SCENE_SIZE; ++y)
            {
                Tile tile = tiles[z][x][y];

                if (tile == null)
                {
                    continue;
                }

                Player player = client.getLocalPlayer();
                if (player == null)
                {
                    continue;
                }
                renderGroundObject(graphics, tile, player);
            }
        }
    }

}


