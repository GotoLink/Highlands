package highlands.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import highlands.HighlandsMain;
import highlands.worldgen.WorldGenHighlandsBigTree;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenSmallPlants;

public class BiomeGenFlyingMountains extends BiomeGenBaseHighlands
{
	private BiomeDecoratorHighlands biomedec;
	private static final Height biomeHeight = new Height(-1.5F, 7.0F);
	
	public BiomeGenFlyingMountains(int par1)
    {
        super(par1);
	    int trees = 20;
	    int grass = 10;
	    int flowers = 0;
	    int plants = 4;
	    this.biomedec = new BiomeDecoratorHighlands(this, trees, grass, flowers, plants);
        this.setHeight(biomeHeight);
        this.temperature = 0.7F;
        this.rainfall = 1.2F;
        this.spawnableCreatureList.clear();
    }
	
	public WorldGenerator getRandomWorldGenForHighlandsPlants(Random rand){
		return (WorldGenerator)new WorldGenSmallPlants(HighlandsBlocks.blueFlower);
	}

	/**
    * Gets a WorldGen appropriate for this biome.
     */
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
    	return (WorldGenerator)new WorldGenHighlandsShrub(0, 0);
    }
	
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.tallgrass, 2);
    }
    
    public void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int par3, int par4)
    {
        biomedec.decorate(par1World, par2Random, biome, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 1, biomedec.diamondGen, 0, 16);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.HLwater, 0, 128);
        
        //random water sources on top of the mountains
        for(int i = 0; i < 16; i++){
    		for(int j = 0; j < 16; j++){
    			if(par1World.getBiomeGenForCoords(par3+i, par4+j) == HighlandsBiomes.flyingMountains){
	    			int topY = 128;
	    			Block var11;
	    	        for (boolean var6 = false; ((var11 = par1World.getBlock(par3+i, topY, par4+j)) == Blocks.air || var11 == Blocks.leaves) && topY > 0; --topY)
	    	        {
	    	            ;
	    	        }
	    	        if(topY > 65){
		    			if(par1World.getBlock(par3+i, topY, par4+j) == Blocks.air)topY--;
		    			int a = par2Random.nextInt(10);
		    			if(a == 9 && par2Random.nextInt(10) == 0){
		    				par1World.setBlock(par3+i, topY, par4+j, Blocks.water, 0, 3);
		    				par1World.setBlock(par3+i, topY+1, par4+j, Blocks.air, 0, 3);
		    			}
	    	        }
	    		}
    		}
    	}
    }
    
    public int getBiomeFoliageColor(){
    	return 0x00BA78;
    }
    
    @SideOnly(Side.CLIENT)
    
    public int getBiomeGrassColor()
    {
        return 0x00BA78;
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
    	if(HighlandsMain.skyColorFlag)return 0x6868FF;
    	else return super.getSkyColorByTemp(par1);
    }
}
