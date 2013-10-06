package spacecraft.mods.galacticraft.venus;

import java.io.File;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.world.IPlanet;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import spacecraft.mods.galacticraft.venus.blocks.VenusBlock;
import spacecraft.mods.galacticraft.venus.dimension.GCVenusWorldProvider;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="gcvenus", name="Galacticraft Venus", version="0.1.1A-P", dependencies = "required-after:Forge@[9.10.0.789,)")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
  public class GalacticraftVenus 
  {
	    public static final IPlanet VenusPlanet = new VenusPlanet();
	    
	    public static CreativeTabs GalacticraftVenusTab;
	    
	    public static final String FILE_PATH = "/spacecraft/mods/galatcicraft/venus/";
	    public static final String CLIENT_PATH = "client/";
	    public static final String LANGUAGE_PATH = "/assets/gcvenus/lang/";
	    public static final String CONFIG_FILE = "Spacecraft/Venus.conf";
	    private static final String[] LANGUAGES_SUPPORTED = new String[] {"en_US"};
	    
	    public static String TEXTURE_DOMAIN = "gcvenus";
	    public static String TEXTURE_PREFIX = GalacticraftVenus.TEXTURE_DOMAIN + ":";
	    public static String TEXTURE_SUFFIX;

	    @Instance("gcvenus")
        public static GalacticraftVenus instance;
		
		//Adding Creative Tab
		public static CreativeTabs tabVenus = new CreativeTabs("tabVenus") {
			public ItemStack getIconItemStack() {
				return new ItemStack(VenusGrass, 1, 0);
			}
		};
		
		//Adding Blocks
		//VenusGrass
		public final static Block VenusGrass = new VenusBlock(610, Material.ground)
		.setHardness(0.4F).setStepSound(Block.soundGrassFootstep)
		.setUnlocalizedName("galacticraftvenus:VenusGrass")
		.setTextureName("gcvenus:veunsgrass_top");
		
		//VenusDirt
		public final static Block VenusDirt = new VenusBlock(611, Material.ground)
		.setHardness(0.3F).setStepSound(Block.soundGrassFootstep)
		.setUnlocalizedName("galacticraftvenus:VenusDirt")
		.setTextureName("gcvenus:veunsdirt");
        
        @SidedProxy(clientSide="spacecraft.mods.galacticraft.venus.client.ClientProxy", serverSide="spacecraft.mods.galacticraft.venus.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) 
        {
            GalacticraftRegistry.registerTeleportType(GCVenusWorldProvider.class, new GCVenusTeleportType());
    		GalacticraftRegistry.registerCelestialBody(new VenusPlanet());
    		new GCVenusConfigManager(new File(event.getModConfigurationDirectory(), GalacticraftVenus.CONFIG_FILE));
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) 
        {
                proxy.registerRenderers();
 		        //Registering CreativeTab Name
                LanguageRegistry.instance().addStringLocalization("itemGroup.tabVenus", "en_US", "Venus");
                //Registering Blocks
                GameRegistry.registerBlock(VenusGrass, "galacticraftvenus:VenusGrass");
                GameRegistry.registerBlock(VenusDirt, "galacticraftvenus:VenusDirt");
                //Registering Names
                LanguageRegistry.addName(VenusGrass, "Venus Grass");
                LanguageRegistry.addName(VenusDirt, "Venus Dirt");
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) 
        {

        }
}