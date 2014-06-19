package lawsof.physics.lib.objects.block

import net.minecraft.tileentity.TileEntity
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import lawsof.physics.lib.objects.block.nbt.ISyncMap
import net.minecraft.network.{Packet, NetworkManager}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import lawsof.physics.lib.objects.block.traits.IGuiTile

/**
 * Created by anshuman on 28-05-2014.
 */
abstract class TileEntityBase extends TileEntity{

  def syncMap: ISyncMap = null

  override def getDescriptionPacket: Packet = {
    var nbt = new NBTTagCompound()
    this writeToNBT nbt
    new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt)
  }

  override def onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity): Unit = {
    this readFromNBT pkt.func_148857_g()
    syncMap.markAllClean
    updateGui
  }

  override def readFromNBT(nbt : NBTTagCompound): Unit = if (syncMap != null) syncMap.readAllNBT(nbt)

  override def writeToNBT(nbt : NBTTagCompound): Unit = if (syncMap != null) syncMap.writeAllNBT(nbt)

  def onBlockActivated(p: EntityPlayer, s: Int, px: Float, py: Float, pz: Float): Boolean = false

  def onNeighbourBlockChanged(tileX: Int, tileY: Int, tileZ: Int): Unit = {}

  def onBlockBroken(player: EntityPlayer): Boolean = true

  def onBlockEventRecieved(evtID: Int, evtPar: Int): Boolean = false

  def updateGui = {}

}

object TileEntityBase {
  def hasTeAt(w: World, x: Int, y: Int, z: Int): Boolean = !((getTileAt(w, x, y, z) == null))

  def getTileAt(w: World, x: Int, y: Int, z: Int): TileEntity = w.getTileEntity(x, y, z)
}