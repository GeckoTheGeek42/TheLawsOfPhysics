package geek.lawsof.physics

import cpw.mods.fml.client.registry.RenderingRegistry

/**
 * Created by anshuman on 19-06-2014.
 */
object Reference {
  final val MOD_ID = "lawsofphysics"
  final val MOD_NAME = "The Laws Of Physics"
  final val MOD_VERSION = "1.7.2-0.1.0"
  final val MOD_AUTHORS: List[String] = List("GeckoTheGeek42")
  final val MOD_URL: String = ""
  final val MOD_DESCRIPTION: String = "A Realistic and Complex Technology Mod Based on Real Science Concepts"
  final val MOD_CREDITS: String = "Forge making this so damn easy "
  final val MOD_LANGUAGE = "scala"

  final val CLIENT_PROXY_CLASS = "geek.lawsof.physics.network.ClientProxy"
  final val SERVER_PROXY_CLASS = "geek.lawsof.physics.network.CommonProxy"

  final val RENDER_ID: Int = RenderingRegistry.getNextAvailableRenderId

  final val TEXTURES_BLOCKS = "textures/blocks"
  final val TEXTURES_ITEMS = "textures/items"
  final val TEXTURES_MODELS = "textures/models"
  final val TEXTURES_GUI = "textures/gui"
  final val TEXTURES_EQUIP = "textures/equip"
}
