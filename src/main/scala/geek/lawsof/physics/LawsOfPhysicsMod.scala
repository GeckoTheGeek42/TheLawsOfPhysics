package geek.lawsof.physics

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent
import cpw.mods.fml.common.event._
import cpw.mods.fml.common.{Mod, ModMetadata, SidedProxy}
import geek.lawsof.physics.Reference._
import geek.lawsof.physics.init._
import geek.lawsof.physics.lib.util.helpers.Log
import geek.lawsof.physics.network.proxy.ModProxy

import scala.collection.JavaConverters._

/**
 * Created by anshuman on 25-05-2014.
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = Reference.MOD_VERSION, modLanguage = MOD_LANGUAGE)
object LawsOfPhysicsMod {

  @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
  var proxy: ModProxy = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent) = {
    Log(s"This is $MOD_NAME:$MOD_MC_VERSION-$MOD_VERSION By $MOD_AUTHORS")

    Log.info("Theorizing the Laws Of Physics")

    //    modMeta()

    preInitEvt = event

    ModConfigs.preInit()

    ModMaterials.preInit()
    ModBlocks.preInit()
    ModItems.preInit()
    ModFluids.preInit()
    ModEquipment.preInit()

    Log.info("Laws Of Physics Theorizing Complete")
  }

  @EventHandler
  def init(event: FMLInitializationEvent) = {
    Log.info("Experimenting with the Laws Of Physics")

    initEvt = event

    ModBlocks.init()
    ModFluids.init()
    ModMaterials.init()

    ModRecipies.init()

    CTabs.init()

    Log.info("Laws Of Physics Experimentation Complete")
  }

  @EventHandler
  def sendInterModComms(event: FMLInterModComms) = {
    Log.info("Publishing Hypothesis of the Laws Of Physics")

    sendImcEvt = event

    Log.info("Hypothesis have been Published")
  }

  def IMC(event: IMCEvent) = {
    Log.info("Recieving Hypothesis about the Laws Of Physics")

    recImcEvt = event

    Log.info("Hypothesis Recieved")
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent) = {
    Log.info("Proving the Laws Of Physics")

    postInitEvt = event

    Log.info("Laws Of Physics Have Been Proved")
  }

  @EventHandler
  def serverStart(event: FMLServerStartingEvent) {
    Log.info(s"Implementing Laws Of Physics in World : ${event.getServer.getWorldName}")

    serverStartEvt = event

    ModCommands.serverLoad()

    Log.info(s"Laws Of Physics have been Implemented in World : ${event.getServer.getWorldName}")
  }

  def serverStop(event: FMLServerStoppingEvent) {
    Log.info("Deactivating Laws Of Physics in World")

    serverStopEvt = event

    Log.info("Laws Of Physics have been Deactivated")
  }

  def modMeta() = {
    var meta: ModMetadata = preInitEvt.getModMetadata
    meta.autogenerated = false
    meta.modId = MOD_ID
    meta.name = MOD_NAME
    meta.version = MOD_VERSION
    meta.description = MOD_DESCRIPTION
    meta.credits = MOD_CREDITS
    meta.authorList = MOD_AUTHORS.asJava
    meta.url = MOD_URL
  }

  var preInitEvt: FMLPreInitializationEvent = null
  var initEvt: FMLInitializationEvent = null
  var sendImcEvt: FMLInterModComms = null
  var recImcEvt: IMCEvent = null
  var postInitEvt: FMLPostInitializationEvent = null
  var serverStartEvt: FMLServerStartingEvent = null
  var serverStopEvt: FMLServerStoppingEvent = null

}


