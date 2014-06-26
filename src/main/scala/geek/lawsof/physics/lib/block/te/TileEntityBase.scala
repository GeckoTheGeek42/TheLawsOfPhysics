package geek.lawsof.physics.lib.block.te

import net.minecraft.tileentity.TileEntity
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.{Packet, NetworkManager}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import geek.lawsof.physics.lib.block.te.traits.ITickingTile
import geek.lawsof.physics.lib.block.nbt.traits.ISyncMap

/**
 * Created by anshuman on 28-05-2014.
 */
abstract class TileEntityBase extends TileEntity {

  def name: String

  override def canUpdate: Boolean = this.isInstanceOf[ITickingTile]

  //ISyncTile
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

  override def readFromNBT(nbt: NBTTagCompound): Unit = if (syncMap != null) syncMap.readAllNBT(nbt)

  override def writeToNBT(nbt: NBTTagCompound): Unit = if (syncMap != null) syncMap.writeAllNBT(nbt)

  def onBlockActivated(w: World, x: Int, y: Int, z: Int, p: EntityPlayer, s: Int, px: Float, py: Float, pz: Float): Boolean = false

  def onNeighbourBlockChanged(w: IBlockAccess, x: Int, y: Int, z: Int, tileX: Int, tileY: Int, tileZ: Int): Unit = {}

  def removedByPlayer(w: World, player: EntityPlayer, x: Int, y: Int, z: Int): Boolean = true

  def onBlockPlaced(w: World, x: Int, y: Int, z: Int, s: Int, hX: Float, hY: Float, hZ: Float, meta: Int): Int = meta

  def onBlockEventReceived(w: World, x: Int, y: Int, z: Int, evtID: Int, evtPar: Int): Boolean

  def updateGui = {}

}

object TileEntityBase {
  def hasTeAt(w: World, x: Int, y: Int, z: Int): Boolean = !((getTileAt(w, x, y, z) == null))

  def getTileAt(w: World, x: Int, y: Int, z: Int): TileEntity = w.getTileEntity(x, y, z)
}