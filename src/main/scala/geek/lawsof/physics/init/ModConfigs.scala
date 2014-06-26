package geek.lawsof.physics.init

import net.minecraftforge.common.config.Configuration
import geek.lawsof.physics.lib.info.{GenStats}
import java.io.File
import geek.lawsof.physics.{Reference, LawsOfPhysicsMod}
import geek.lawsof.physics.lib.util.Log
import net.minecraftforge.common.MinecraftForge
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent

object ModConfigs {

  var oreGenStats: Configuration = null

  def preInit() {
    Log.info("Creating Config Files")

    oreGenStats = create("OreGenStats", "Ore Generations Statisitics")

    MinecraftForge.EVENT_BUS.register(this)
  }

  def getAllFiles = List(oreGenStats)

  def onConfigChanged(evt: OnConfigChangedEvent) = {
    Log.info("Syncing Configuration")

    syncCFG
  }

  def syncCFG = {}

  def create(name: String, comment: String): Configuration = {
    var cfg: Configuration = new Configuration(new File(LawsOfPhysicsMod.preInitEvt.getModConfigurationDirectory, s"${Reference.MOD_ID}/$name.cfg"))
    cfg.addCustomCategoryComment(name, comment)
    return cfg
  }

  def createCategory(cfg: Configuration, name: String, comment: String) {
    cfg.addCustomCategoryComment(name, comment)
  }

  def getOreGenStats(oreName: String, maxFreq: Int, minHeight: Int, maxHeight: Int, maxVein: Int): GenStats = {
    oreGenStats.load
    createCategory(oreGenStats, s"OreGenStats.$oreName", s"Ore Generation Statistics For $oreName")
    val maxVeinSize = oreGenStats.get(s"OreGenStats.$oreName", "maxVeinSize", maxVein).getInt(maxVein)
    val maxVeinSpawn = oreGenStats.get(s"OreGenStats.$oreName", "maxVeinFreq", maxFreq).getInt(maxFreq)
    val maxY = oreGenStats.get(s"OreGenStats.$oreName", "maxHeight", maxHeight).getInt(maxHeight)
    val minY = oreGenStats.get(s"OreGenStats.$oreName", "minHeight", minHeight).getInt(minHeight)
    oreGenStats.save
    return new GenStats(minY, maxY, maxVeinSize, maxVeinSpawn)
  }
}
