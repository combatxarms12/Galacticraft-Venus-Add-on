package spacecraft.mods.galacticraft.Venus2.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.EnumSet;

import spacecraft.mods.galacticraft.Venus2.CommonProxyVenus2;
import spacecraft.mods.galacticraft.Venus2.GCVenus2ConfigManager;
import spacecraft.mods.galacticraft.Venus2.GCVenus2;
import spacecraft.mods.galacticraft.Venus2.blocks.GCVenus2Blocks;
import spacecraft.mods.galacticraft.Venus2.client.fx.GCVenus2EntityDropParticleFX;
import spacecraft.mods.galacticraft.Venus2.client.model.GCVenus2ModelSpaceshipTier3;
import spacecraft.mods.galacticraft.Venus2.client.render.block.GCVenus2BlockRendererTreasureChest;
import spacecraft.mods.galacticraft.Venus2.client.render.item.GCVenus2ItemRendererSpaceshipT3;
import spacecraft.mods.galacticraft.Venus2.client.render.tile.GCVenus2TileEntityTreasureChestRenderer;
import spacecraft.mods.galacticraft.Venus2.client.sounds.GCVenus2Sounds;
import spacecraft.mods.galacticraft.Venus2.dimension.GCVenus2WorldProvider;
import spacecraft.mods.galacticraft.Venus2.entities.GCVenus2EntityRocketT3;
import spacecraft.mods.galacticraft.Venus2.items.GCVenus2Items;
import spacecraft.mods.galacticraft.Venus2.tile.GCVenus2TileEntityTreasureChest;
import micdoodle8.mods.galacticraft.core.client.GCCoreCloudRenderer;
import micdoodle8.mods.galacticraft.core.client.render.entities.GCCoreRenderOxygenBubble;
import micdoodle8.mods.galacticraft.core.client.render.entities.GCCoreRenderSpaceship;
import micdoodle8.mods.galacticraft.core.client.render.item.GCCoreItemRendererKey;
import micdoodle8.mods.galacticraft.core.client.sounds.GCCoreSoundUpdaterSpaceship;
import micdoodle8.mods.galacticraft.core.util.PacketUtil;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyVenus2 extends CommonProxyVenus2
{
//    private static int vineRenderID;
    private static int eggRenderID;
    private static int treasureRenderID;
//    private static int machineRenderID;
//    private static int tintedGlassRenderID;

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new GCVenus2Sounds());
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
        NetworkRegistry.instance().registerChannel(new ClientPacketHandler(), GCVenus2.CHANNEL, Side.CLIENT);
//        ClientProxyMars.vineRenderID = RenderingRegistry.getNextAvailableRenderId();
//        RenderingRegistry.registerBlockHandler(new GCMarsBlockRendererVine(ClientProxyMars.vineRenderID));
        ClientProxyVenus2.eggRenderID = RenderingRegistry.getNextAvailableRenderId();
//        RenderingRegistry.registerBlockHandler(new GCMarsBlockRendererRock(ClientProxyMars.eggRenderID));
//        ClientProxyMars.treasureRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new GCVenus2BlockRendererTreasureChest(ClientProxyVenus2.treasureRenderID));
//        ClientProxyMars.machineRenderID = RenderingRegistry.getNextAvailableRenderId();
//        RenderingRegistry.registerBlockHandler(new GCMarsBlockRendererMachine(ClientProxyMars.machineRenderID));
//        ClientProxyMars.tintedGlassRenderID = RenderingRegistry.getNextAvailableRenderId();
//        RenderingRegistry.registerBlockHandler(new GCMarsBlockRendererTintedGlassPane(ClientProxyMars.tintedGlassRenderID));
    }

//    @Override
//    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @Override
    public void registerRenderInformation()
    {
//        IModelCustom chamberModel = AdvancedModelLoader.loadModel("/assets/galacticraftvenus2/models/chamber.obj");
        IModelCustom cargoRocketModel = AdvancedModelLoader.loadModel("/assets/galacticraftvenus2/models/cargoRocket.obj");
        ClientRegistry.bindTileEntitySpecialRenderer(GCVenus2TileEntityTreasureChest.class, new GCVenus2TileEntityTreasureChestRenderer());
//        ClientRegistry.bindTileEntitySpecialRenderer(GCMarsTileEntityCryogenicChamber.class, new GCMarsTileEntityCryogenicChamberRenderer(chamberModel));
        RenderingRegistry.registerEntityRenderingHandler(GCVenus2EntityRocketT3.class, new GCCoreRenderSpaceship(new GCVenus2ModelSpaceshipTier3(), GCVenus2.TEXTURE_DOMAIN, "rocketT2"));
        RenderingRegistry.addNewArmourRendererPrefix("desh");
        MinecraftForgeClient.registerItemRenderer(GCVenus2Items.spaceship.itemID, new GCVenus2ItemRendererSpaceshipT3(cargoRocketModel));
        MinecraftForgeClient.registerItemRenderer(GCVenus2Items.key.itemID, new GCCoreItemRendererKey(new ResourceLocation(GCVenus2.TEXTURE_DOMAIN, "textures/model/treasure.png")));
//        MinecraftForgeClient.registerItemRenderer(GCMarsBlocks.machine.blockID, new GCMarsItemRendererMachine(chamberModel));
    }

//    @Override
//    public int getVineRenderID()
    {
//        return ClientProxyMars.vineRenderID;
    }

    @Override
    public int getEggRenderID()
    {
        return ClientProxyVenus2.eggRenderID;
    }

    @Override
    public int getTreasureRenderID()
    {
        return ClientProxyVenus2.treasureRenderID;
    }

//    @Override
//    public int getMachineRenderID()
    {
//        return ClientProxyMars.machineRenderID;
    }

//    @Override
//    public int getTintedGlassPaneRenderID()
    {
//        return ClientProxyMars.tintedGlassRenderID;
    }

    @Override
    public void spawnParticle(String var1, double var2, double var4, double var6)
    {
        final Minecraft var14 = FMLClientHandler.instance().getClient();

        if (var14 != null && var14.renderViewEntity != null && var14.effectRenderer != null)
        {
            final double var15 = var14.renderViewEntity.posX - var2;
            final double var17 = var14.renderViewEntity.posY - var4;
            final double var19 = var14.renderViewEntity.posZ - var6;
            Object var21 = null;
            final double var22 = 64.0D;

            if (var15 * var15 + var17 * var17 + var19 * var19 < var22 * var22)
            {
                if (var1.equals("sludgeDrip"))
                {
//                    var21 = new GCMarsEntityDropParticleFX(var14.theWorld, var2, var4, var6, GCMarsBlocks.bacterialSludge);
                }
            }
        }
    }

    public class ClientPacketHandler implements IPacketHandler
    {
        @Override
        public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p)
        {
            final DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
            final int packetType = PacketUtil.readPacketID(data);
            EntityPlayer player = (EntityPlayer) p;

            if (packetType == 0)
            {
                final Class<?>[] decodeAs = { Integer.class, Integer.class, Integer.class };
                final Object[] packetReadout = PacketUtil.readPacketData(data, decodeAs);

                int entityID = 0;
                Entity entity = null;
                
                switch ((Integer) packetReadout[1])
                {
                case 0:
                    entityID = (Integer) packetReadout[2];
                    entity = player.worldObj.getEntityByID(entityID);

//                    if (entity != null && entity instanceof GCMarsEntitySlimeling)
                    {
//                        FMLClientHandler.instance().getClient().displayGuiScreen(new GCMarsGuiSlimelingInventory(player, (GCMarsEntitySlimeling) entity));
                    }

                    player.openContainer.windowId = (Integer) packetReadout[0];
                    break;
                case 1:
                    entityID = (Integer) packetReadout[2];
                    entity = player.worldObj.getEntityByID(entityID);

//                    if (entity != null && entity instanceof GCMarsEntityCargoRocket)
                    {
//                        FMLClientHandler.instance().getClient().displayGuiScreen(new GCMarsGuiCargoRocket(player.inventory, (GCMarsEntityCargoRocket) entity));
                    }

                    player.openContainer.windowId = (Integer) packetReadout[0];
                    break;
                }
            }
        }
    }

//    public static boolean handleBacterialMovement(EntityPlayer player)
    {
//        return player.worldObj.isMaterialInBB(player.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), GCMarsBlocks.bacterialSludge);
    }

    public static boolean handleLavaMovement(EntityPlayer player)
    {
        return player.worldObj.isMaterialInBB(player.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava);
    }

    public static boolean handleWaterMovement(EntityPlayer player)
    {
        return player.worldObj.isMaterialInBB(player.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.water);
    }

//    public static boolean handleLiquidMovement(EntityPlayer player)
    {
//        return ClientProxyMars.handleBacterialMovement(player) || ClientProxyMars.handleLavaMovement(player) || ClientProxyMars.handleWaterMovement(player);
    }

//    @Override
//    public void opengSlimelingGui(GCMarsEntitySlimeling slimeling, int gui)
    {
//        switch (gui)
        {
//        case 0:
//            FMLClientHandler.instance().getClient().displayGuiScreen(new GCMarsGuiSlimeling(slimeling));
//            break;
//        case 1:
//            FMLClientHandler.instance().getClient().displayGuiScreen(new GCMarsGuiSlimelingFeed(slimeling));
//            break;
        }
    }

    public static class TickHandlerClient implements ITickHandler
    {
        @Override
        public void tickStart(EnumSet<TickType> type, Object... tickData)
        {
            final Minecraft minecraft = FMLClientHandler.instance().getClient();

            final WorldClient world = minecraft.theWorld;

            if (type.equals(EnumSet.of(TickType.CLIENT)))
            {
                if (world != null)
                {
                    if (world.provider instanceof GCVenus2WorldProvider)
                    {
                        if (world.provider.getSkyRenderer() == null)
                        {
                            world.provider.setSkyRenderer(new GCVenus2SkyProvider());
                        }

                        if (world.provider.getCloudRenderer() == null)
                        {
                            world.provider.setCloudRenderer(new GCCoreCloudRenderer());
                        }
                    }

                    for (int i = 0; i < world.loadedEntityList.size(); i++)
                    {
                        final Entity e = (Entity) world.loadedEntityList.get(i);

                        if (e != null)
                        {
                            if (e instanceof GCVenus2EntityRocketT3)
                            {
                                final GCVenus2EntityRocketT3 eship = (GCVenus2EntityRocketT3) e;

                                if (eship.rocketSoundUpdater == null)
                                {
                                    eship.rocketSoundUpdater = new GCCoreSoundUpdaterSpaceship(FMLClientHandler.instance().getClient().sndManager, eship, FMLClientHandler.instance().getClient().thePlayer);
                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void tickEnd(EnumSet<TickType> type, Object... tickData)
        {
        }

        @Override
        public String getLabel()
        {
            return "Galacticraft Mars Client";
        }

        @Override
        public EnumSet<TickType> ticks()
        {
            return EnumSet.of(TickType.CLIENT);
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);

//        if (ID == GCMarsConfigManager.idGuiMachine)
        {
//            if (tile instanceof GCMarsTileEntityTerraformer)
            {
//                return new GCMarsGuiTerraformer(player.inventory, (GCMarsTileEntityTerraformer) tile);
            }
//            else if (tile instanceof GCMarsTileEntityLaunchController)
            {
//                return new GCMarsGuiLaunchController(player.inventory, (GCMarsTileEntityLaunchController) tile);
            }
        }

        return null;
    }
}