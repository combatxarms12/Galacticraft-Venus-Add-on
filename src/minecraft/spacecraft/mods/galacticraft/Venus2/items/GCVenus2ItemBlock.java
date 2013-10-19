package spacecraft.mods.galacticraft.Venus2.items;

import spacecraft.mods.galacticraft.Venus2.GCVenus2;
import micdoodle8.mods.galacticraft.core.client.ClientProxyCore;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GCVenus2ItemBlock extends ItemBlock
{
    public GCVenus2ItemBlock(int i)
    {
        super(i);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        String name = "";

        switch (itemstack.getItemDamage())
        {
        case 0:
        {
            name = "copperOreVenus";
            break;
        }
        case 1:
        {
            name = "tinOreVenus";
            break;
        }
        case 2:
        {
            name = "ironOreVenus";
            break;
        }
        case 3:
        {
            name = "coalOreVenus";
            break;
        }
        case 4:
        {
            name = "meteorOreVenus";
            break;
        }
        case 5:
        {
            name = "gemVenus";
            break;
        }
        case 6:
        {
            name = "sulferVenus";
            break;
        }
		case 7:
        {
            name = "basaltVenus";
            break;
        }
        case 8:
        {
            name = "cobblestoneVenus";
            break;
        }
        case 9:
        {
            name = "grassVenus";
            break;
        }
        case 10:
        {
            name = "dirtVenus";
            break;
        }
        case 11:
        {
            name = "dungeonVenusBrick";
            break;
        }
        case 12:
        {
            name = "gemBlockVenus";
            break;
        }
        case 13:
        {
            name = "sulferBlockVenus";
            break;
        }
        case 14:
        {
            name = "gemBlockVenus";
            break;
        }
        case 15:
        {
            name = "meteorBlockVenus";
            break;
        }
        case 16:
        {
            name = "stoneVenus";
            break;
        }
        default:
            name = "null";
        }

        return Block.blocksList[this.getBlockID()].getUnlocalizedName() + "." + name;
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return GCVenus2.galacticraftVenus2Tab;
    }

    @Override
    public String getUnlocalizedName()
    {
        return Block.blocksList[this.getBlockID()].getUnlocalizedName() + ".0";
    }
}
