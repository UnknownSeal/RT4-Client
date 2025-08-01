package com.unknownseal.mixinlauncher.plugins.tileindicators;

import com.jagex.runetek4.PlayerList;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.scene.tile.Tile;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.events.DrawScene;
import com.unknownseal.mixinlauncher.events.MouseClicked;
import com.unknownseal.mixinlauncher.api.TileHighlighter;
import com.unknownseal.mixinlauncher.api.rendering.TileProjection;
import com.unknownseal.mixinlauncher.api.rendering.TileUtils;
import com.unknownseal.mixinlauncher.api.MarkedTile;
import com.unknownseal.mixinlauncher.api.TileType;
import com.unknownseal.mixinlauncher.api.coords.LocalPoint;
import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.plugin.Plugin;

import java.util.Set;
import java.util.HashSet;

public class TileIndicatorsPlugin implements Plugin {

    private final Set<MarkedTile> markedTiles = new HashSet<>();
    private final TileIndicatorsConfig config = new TileIndicatorsConfig();
    private MarkedTile currentPlayerTile;
    private MarkedTile currentHoveredTile;

    @Override
    public String getName() {
        return "Tile Indicators Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        bus.register(this);
    }

    @Override
    public void onStop() {

    }

    public TileIndicatorsConfig getConfig() {
        return config;
    }

    @Subscribe
    public void onDrawScene(DrawScene event) {
        updatePlayerTile();
        updateHoveredTile();

        renderTilesDirectly();
    }

    @Subscribe
    public void onMouseClicked(MouseClicked event) {
        if (!config.isEnableCtrlClick() || !event.isCtrlLeftClick()) {
            return;
        }

        if (currentHoveredTile != null) {
            toggleMarkedTile(currentHoveredTile.getTileX(), currentHoveredTile.getTileZ());
        }
    }

    private void updatePlayerTile() {
        if (PlayerList.self != null) {
            int playerTileX = PlayerList.self.xFine >> 7;
            int playerTileZ = PlayerList.self.zFine >> 7;
            
            currentPlayerTile = new MarkedTile(playerTileX, playerTileZ, TileType.PLAYER_TILE, config.getColorForType(TileType.PLAYER_TILE));
        }
    }
    
    private void updateHoveredTile() {
        if (PlayerList.self == null) {
            return;
        }
        
        int playerTileX = PlayerList.self.xFine >> 7;
        int playerTileZ = PlayerList.self.zFine >> 7;

        int[] hoveredCoords = findTileAtMouse(Mouse.lastMouseX, Mouse.lastMouseY);
        
        if (hoveredCoords != null && !(hoveredCoords[0] == playerTileX && hoveredCoords[1] == playerTileZ)) {
            currentHoveredTile = new MarkedTile(hoveredCoords[0], hoveredCoords[1], TileType.HOVERED_TILE, config.getColorForType(TileType.HOVERED_TILE));
        } else {
            currentHoveredTile = null;
        }
    }
    
    private void renderTilesDirectly() {
        int currentPlane = Player.plane;

        if (currentPlayerTile != null && config.isShowPlayerTile()) {
            TileHighlighter.highlightTile(currentPlane, currentPlayerTile.getTileX(), currentPlayerTile.getTileZ(), currentPlayerTile.getColor());
        }

        if (currentHoveredTile != null && config.isShowHoveredTile()) {
            TileHighlighter.highlightTile(currentPlane, currentHoveredTile.getTileX(), currentHoveredTile.getTileZ(), currentHoveredTile.getColor());
        }

        if (config.isShowMarkedTiles()) {
            for (MarkedTile tile : markedTiles) {
                TileHighlighter.highlightTile(currentPlane, tile.getTileX(), tile.getTileZ(), tile.getColor());
            }
        }
    }

    private int[] findTileAtMouse(int mouseX, int mouseY) {
        if (PlayerList.self == null) {
            return null;
        }
        
        int playerTileX = PlayerList.self.xFine >> 7;
        int playerTileZ = PlayerList.self.zFine >> 7;
        int currentPlane = Player.plane;
        
        int foundTileX = -1;
        int foundTileZ = -1;
        double minDistance = Double.MAX_VALUE;
        
        for (int tileX = playerTileX - 10; tileX <= playerTileX + 10; tileX++) {
            for (int tileZ = playerTileZ - 10; tileZ <= playerTileZ + 10; tileZ++) {
                if (tileX < 0 || tileZ < 0 || tileX >= 104 || tileZ >= 104) {
                    continue;
                }
                
                if (SceneGraph.tiles == null || SceneGraph.tiles[currentPlane] == null) {
                    continue;
                }
                
                Tile tile = SceneGraph.tiles[currentPlane][tileX][tileZ];
                if (tile == null) {
                    continue;
                }
                
                int[] screenCoords = TileProjection.projectTileToScreen(tileX, tileZ, currentPlane);
                if (screenCoords == null) {
                    continue;
                }
                
                double distance = Math.sqrt(
                    (mouseX - screenCoords[0]) * (mouseX - screenCoords[0]) + 
                    (mouseY - screenCoords[1]) * (mouseY - screenCoords[1])
                );
                
                if (distance < minDistance && distance < 64) {
                    minDistance = distance;
                    foundTileX = tileX;
                    foundTileZ = tileZ;
                }
            }
        }
        
        if (foundTileX != -1 && foundTileZ != -1) {
            return new int[]{foundTileX, foundTileZ};
        }
        
        return null;
    }

    private void toggleMarkedTile(int tileX, int tileZ) {
        LocalPoint location = new LocalPoint(tileX * 128 + 64, tileZ * 128 + 64);

        MarkedTile existingTile = null;
        for (MarkedTile tile : markedTiles) {
            if (tile.getLocation().equals(location)) {
                existingTile = tile;
                break;
            }
        }
        
        if (existingTile != null) {
            markedTiles.remove(existingTile);
        } else {
            MarkedTile newTile = new MarkedTile(tileX, tileZ, TileType.MANUALLY_MARKED, config.getColorForType(TileType.MANUALLY_MARKED));
            markedTiles.add(newTile);
        }
    }

    public void addMarkedTile(int tileX, int tileZ, TileType type) {
        MarkedTile tile = new MarkedTile(tileX, tileZ, type, config.getColorForType(type));
        markedTiles.add(tile);
    }
    
    public void addMarkedTile(int tileX, int tileZ, TileType type, int customColor) {
        MarkedTile tile = new MarkedTile(tileX, tileZ, type, customColor);
        markedTiles.add(tile);
    }
    
    public void removeMarkedTile(int tileX, int tileZ) {
        LocalPoint location = new LocalPoint(tileX * 128 + 64, tileZ * 128 + 64);
        markedTiles.removeIf(tile -> tile.getLocation().equals(location));
    }
    
    public void clearMarkedTiles() {
        markedTiles.clear();
    }
    
    public void setCustomColor(TileType type, int color) {
        config.setColorForType(type, color);
    }
}
