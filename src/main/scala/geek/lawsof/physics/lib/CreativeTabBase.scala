package geek.lawsof.physics.lib

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.{Item, ItemStack}

/**
 * Created by anshuman on 26-05-2014.
 */
class CreativeTabBase(val tabLabel: String) extends CreativeTabs(CreativeTabs.getNextID, tabLabel) {
  override def getTabIconItem: Item = null

  var icon: ItemStack = new ItemStack(Items.iron_ingot)

  def setTabIcon(ico: ItemStack) = icon = ico

  override def getIconItemStack: ItemStack = icon
}
