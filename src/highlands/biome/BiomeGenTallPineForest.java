package highlands.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import highlands.worldgen.WorldGenHighlandsShrub;
import highlands.worldgen.WorldGenTreeFir;

public class BiomeGenTallPineForest extends BiomeGenBaseHighlands
{
	private static final Height biomeHeight = new Height(0.2F, 0.7F);
    private static final int trees = 4, grass = 2, flowers = 0;
	public BiomeGenTallPineForest(int par1)
    {
        super(par1, new BiomeDecoratorHighlands(trees, grass, flowers));
        this.setHeight(biomeHeight);
        this.temperature = 0.1F;
        this.rainfall = 0.8F;
        this.treeGenCache = new WorldGenTreeFir(15, 10, false, false);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (par1Random.nextInt(8) == 0 ?
                new WorldGenTaiga1() : par1Random.nextInt(3) == 0 ?
                new WorldGenHighlandsShrub(0, 0) :
                    par1Random.nextInt(4) == 0? new WorldGenTreeFir(15, 10, false, true) : this.treeGenCache);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        biomedec.decorateChunk(par1World, par2Random, this, par3, par4);
        biomedec.genOreHighlands(par1World, par2Random, par3, par4, 20, biomedec.coalGen, 0, 128);
    }
}
