package qmech.lib.tileentity.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import qmech.lib.tileentity.TileEntityBase;

/**
 * Created by anshuman on 17-05-2014.
 */
public abstract class GuiHandlerBase {

    public abstract GuiContainerBase getServer(InventoryPlayer ip, TileEntityBase te);
    public abstract GuiBase getClient(InventoryPlayer ip, TileEntityBase te);

}