package geek.lawsof.physics.lib.fluid

import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.{Side, SideOnly}
import geek.lawsof.physics.Reference
import geek.lawsof.physics.lib.CreativeTabBase
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.fluids.{BlockFluidClassic, Fluid}

class FluidBlockBase(fluid: Fluid, material: Material, ctab: CreativeTabBase) extends BlockFluidClassic(fluid, material) {
  this.setBlockName(fluid.getUnlocalizedName)
  this.setBlockTextureName(fluid.getName.substring(6))
  GameRegistry.registerBlock(this, fluid.getUnlocalizedName)
  setCreativeTab(ctab)

  def this(fluid: FluidBase) = this(fluid, fluid.material, fluid.ctab)

  override def getIcon(side: Int, meta: Int): IIcon = {
    if (side == 0 || side == 1) stillIcon else flowingIcon
  }

  @SideOnly(Side.CLIENT) override def registerBlockIcons(register: IIconRegister) {
    stillIcon = register.registerIcon(s"${Reference.MOD_ID}:fluid/${this.getUnlocalizedName.substring(11)}_still")
    flowingIcon = register.registerIcon(s"${Reference.MOD_ID}:fluid/${this.getUnlocalizedName.substring(11)}")
  }

  override def canDisplace(world: IBlockAccess, x: Int, y: Int, z: Int): Boolean = {
    if (world.getBlock(x, y, z).getMaterial.isLiquid) return false
    super.canDisplace(world, x, y, z)
  }

  override def displaceIfPossible(world: World, x: Int, y: Int, z: Int): Boolean = {
    if (world.getBlock(x, y, z).getMaterial.isLiquid) return false
    super.displaceIfPossible(world, x, y, z)
  }

  @SideOnly(Side.CLIENT) protected var stillIcon: IIcon = null
  @SideOnly(Side.CLIENT) protected var flowingIcon: IIcon = null
}
