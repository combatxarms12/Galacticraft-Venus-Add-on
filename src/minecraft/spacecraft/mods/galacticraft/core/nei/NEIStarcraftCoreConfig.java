package spacecraft.mods.galacticraft.core.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import spacecraft.mods.galacticraft.core.items.SCCoreItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.items.GCCoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIStarcraftCoreConfig implements IConfigureNEI
{
    private static HashMap<ArrayList<PositionedStack>, PositionedStack> rocketBenchRecipes = new HashMap<ArrayList<PositionedStack>, PositionedStack>();

    @Override
    public void loadConfig()
    {
        this.registerRecipes();
        API.registerRecipeHandler(new RocketT3RecipeHandler());
        API.registerUsageHandler(new RocketT3RecipeHandler());
    }

    @Override
    public String getName()
    {
        return "Galacticraft Venus 2 NEI Plugin";
    }

    @Override
    public String getVersion()
    {
        return GalacticraftCore.LOCALMAJVERSION + "." + GalacticraftCore.LOCALMINVERSION + "." + GalacticraftCore.LOCALBUILDVERSION;
    }

    public void registerRocketBenchRecipe(ArrayList<PositionedStack> input, PositionedStack output)
    {
        NEIStarcraftCoreConfig.rocketBenchRecipes.put(input, output);
    }

    public static Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRocketBenchRecipes()
    {
        return NEIStarcraftCoreConfig.rocketBenchRecipes.entrySet();
    }

    public void registerRecipes()
    {
        final int changey = 15;

        ArrayList<PositionedStack> input1 = new ArrayList<PositionedStack>();

        input1.add(new PositionedStack(new ItemStack(GCCoreItems.partNoseCone), 45, -8 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 36, -6 + 0 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 36, -6 + 1 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 36, -6 + 2 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 36, -6 + 3 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 36, -6 + 4 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 54, -6 + 0 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 54, -6 + 1 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 54, -6 + 2 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 54, -6 + 3 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(SCCoreItems.venusItemBasic, 1, 3), 54, -6 + 4 * 18 + 16 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.rocketEngine), 45, 100 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.rocketEngine, 1, 1), 18, 64 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.rocketEngine, 1, 1), 72, 64 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.partFins), 18, 82 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.partFins), 18, 100 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.partFins), 72, 82 + changey));
        input1.add(new PositionedStack(new ItemStack(GCCoreItems.partFins), 72, 100 + changey));
        this.registerRocketBenchRecipe(input1, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 0), 139, 87 + changey));

        ArrayList<PositionedStack> input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 1), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 1), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 1), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 2), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 2), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 2), 139, 87 + changey));

        input2 = new ArrayList<PositionedStack>(input1);
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 0 * 26, -15 + changey));
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 1 * 26, -15 + changey));
        input2.add(new PositionedStack(new ItemStack(Block.chest), 90 + 2 * 26, -15 + changey));
        this.registerRocketBenchRecipe(input2, new PositionedStack(new ItemStack(SCCoreItems.spaceship, 1, 3), 139, 87 + changey));
    }
}
