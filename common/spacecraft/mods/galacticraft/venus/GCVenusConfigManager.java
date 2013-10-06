package spacecraft.mods.galacticraft.venus;

import java.io.File;
import micdoodle8.mods.galacticraft.core.GCLog;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;


public class GCVenusConfigManager 
{
	public static boolean loaded;
	
	static Configuration configuration;
	
	// Blocks
	public static int VenusGrassID;
	public static int VenusDirtID;
	public static int VenusStoneID;
	public static int VenusCobblestoneID;
	
	// DIMENSIONS
	public static int dimensionIDVenus;
	
	// Rocket
	public static int TierLevel;
	
	public GCVenusConfigManager(File file)
	{
		if (!GCVenusConfigManager.loaded)
		{
			configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}
	
	private void setDefaultValues()
    {
		try
		{
	        configuration.load();
	        
	        GCVenusConfigManager.dimensionIDVenus = GCVenusConfigManager.configuration.get("Dimensions", "Venus Dimension ID", -45).getInt(-45);
	        
	        GCVenusConfigManager.TierLevel = GCVenusConfigManager.configuration.get("Rocket", "Tier Level", 2).getInt(2);
	        
	        GCVenusConfigManager.VenusGrassID = GCVenusConfigManager.configuration.get("Blocks", "Venus Grass ID", 504).getInt(504);
	        
	        GCVenusConfigManager.VenusDirtID= GCVenusConfigManager.configuration.get("Blocks", "Venus Dirt ID", 505).getInt(505);
			
			GCVenusConfigManager.VenusStoneID= GCVenusConfigManager.configuration.get("Blocks", "Venus Stone ID", 506).getInt(506);
			
			GCVenusConfigManager.VenusCobblestoneID= GCVenusConfigManager.configuration.get("Blocks", "Venus Cobblestone ID", 507).getInt(507);

		}
		catch (final Exception e)
		{
			 GCLog.severe("Problem loading Venus config (\"venus.conf\")");
		}
		finally 
		{
            if (GCVenusConfigManager.configuration.hasChanged())
            {
                GCVenusConfigManager.configuration.save();
            }

            GCVenusConfigManager.loaded = true;
		}
    }
}
