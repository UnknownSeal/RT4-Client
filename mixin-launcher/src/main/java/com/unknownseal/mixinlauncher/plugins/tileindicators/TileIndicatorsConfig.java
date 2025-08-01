package com.unknownseal.mixinlauncher.plugins.tileindicators;

import com.unknownseal.mixinlauncher.api.TileType;
import com.unknownseal.mixinlauncher.api.rendering.TileUtils;
import java.awt.Color;

public class TileIndicatorsConfig {

    private boolean showPlayerTile = true;
    private boolean showHoveredTile = true;
    private boolean showMarkedTiles = true;
    private boolean enableCtrlClick = true;

    private int playerTileColor = TileUtils.COLOR_RED;
    private int hoveredTileColor = TileUtils.COLOR_GREEN;
    private int markedTileColor = TileUtils.COLOR_BLUE;

    private boolean showTileLabels = false;
    private int tileOpacity = 255;

    public boolean isShowPlayerTile() {
        return showPlayerTile;
    }
    
    public void setShowPlayerTile(boolean showPlayerTile) {
        this.showPlayerTile = showPlayerTile;
    }
    
    public boolean isShowHoveredTile() {
        return showHoveredTile;
    }
    
    public void setShowHoveredTile(boolean showHoveredTile) {
        this.showHoveredTile = showHoveredTile;
    }
    
    public boolean isShowMarkedTiles() {
        return showMarkedTiles;
    }
    
    public void setShowMarkedTiles(boolean showMarkedTiles) {
        this.showMarkedTiles = showMarkedTiles;
    }
    
    public boolean isEnableCtrlClick() {
        return enableCtrlClick;
    }
    
    public void setEnableCtrlClick(boolean enableCtrlClick) {
        this.enableCtrlClick = enableCtrlClick;
    }

    public int getColorForType(TileType type) {
        switch (type) {
            case PLAYER_TILE:
                return playerTileColor;
            case HOVERED_TILE:
                return hoveredTileColor;
            case MANUALLY_MARKED:
                return markedTileColor;
            default:
                return type.getDefaultColor();
        }
    }
    
    public void setColorForType(TileType type, int color) {
        switch (type) {
            case PLAYER_TILE:
                this.playerTileColor = color;
                break;
            case HOVERED_TILE:
                this.hoveredTileColor = color;
                break;
            case MANUALLY_MARKED:
                this.markedTileColor = color;
                break;
        }
    }

    public Color getColorForTypeAsColor(TileType type) {
        return new Color(getColorForType(type));
    }
    
    public void setColorForType(TileType type, Color color) {
        setColorForType(type, color.getRGB() & 0xFFFFFF); // Remove alpha
    }

    public boolean isShowTileLabels() {
        return showTileLabels;
    }
    
    public void setShowTileLabels(boolean showTileLabels) {
        this.showTileLabels = showTileLabels;
    }
    
    public int getTileOpacity() {
        return tileOpacity;
    }
    
    public void setTileOpacity(int tileOpacity) {
        this.tileOpacity = Math.max(0, Math.min(255, tileOpacity));
    }
    
    public float getTileOpacityFloat() {
        return tileOpacity / 255.0f;
    }
}