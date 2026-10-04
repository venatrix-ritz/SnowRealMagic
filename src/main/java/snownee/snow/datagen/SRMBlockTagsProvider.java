package snownee.snow.datagen;

import static snownee.snow.CoreModule.FENCE;
import static snownee.snow.CoreModule.FENCE2;
import static snownee.snow.CoreModule.FENCE_GATE;
import static snownee.snow.CoreModule.SLAB;
import static snownee.snow.CoreModule.SNOWY_DOUBLE_PLANT_LOWER;
import static snownee.snow.CoreModule.SNOWY_DOUBLE_PLANT_UPPER;
import static snownee.snow.CoreModule.SNOWY_PLANT;
import static snownee.snow.CoreModule.SNOWY_SETTING;
import static snownee.snow.CoreModule.SNOW_BLOCK;
import static snownee.snow.CoreModule.SNOW_EXTRA_COLLISION_BLOCK;
import static snownee.snow.CoreModule.STAIRS;
import static snownee.snow.CoreModule.WALL;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import snownee.kiwi.AbstractModule;
import snownee.kiwi.KiwiGO;
import snownee.kiwi.datagen.KiwiBlockTagsProvider;
import snownee.snow.CoreModule;
import snownee.snow.block.ExtraCollisionSnowLayerBlock;

public class SRMBlockTagsProvider extends KiwiBlockTagsProvider {
	TagKey<Block> GRASS = AbstractModule.blockTag("c", "grass");
	TagKey<Block> MUSHROOMS = AbstractModule.blockTag("c", "mushrooms");

	public SRMBlockTagsProvider(
			FabricPackOutput output,
			CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		for (Block block : Stream.of(
				SNOW_EXTRA_COLLISION_BLOCK,
				SNOWY_DOUBLE_PLANT_LOWER,
				SNOWY_DOUBLE_PLANT_UPPER,
				SNOW_BLOCK,
				SNOWY_PLANT).map(KiwiGO::get).toList()) {
			tag(CoreModule.SNOW_TAG).add(block.builtInRegistryHolder().key());
			if (!(block instanceof ExtraCollisionSnowLayerBlock)) {
				tag(BlockTags.GOATS_SPAWNABLE_ON).add(block.builtInRegistryHolder().key());
				tag(BlockTags.MOOSHROOMS_SPAWNABLE_ON).add(block.builtInRegistryHolder().key());
				tag(BlockTags.RABBITS_SPAWNABLE_ON).add(block.builtInRegistryHolder().key());
				tag(BlockTags.FOXES_SPAWNABLE_ON).add(block.builtInRegistryHolder().key());
				tag(BlockTags.WOLVES_SPAWNABLE_ON).add(block.builtInRegistryHolder().key());
			}
		}

		tag(BlockTags.SNOW).addTag(CoreModule.SNOW_TAG);
		tag(BlockTags.MINEABLE_WITH_SHOVEL).addTag(CoreModule.SNOW_TAG);
		tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(CoreModule.SNOW_TAG);

		tag(BlockTags.STAIRS).add(STAIRS.get().builtInRegistryHolder().key());
		tag(BlockTags.SLABS).add(SLAB.get().builtInRegistryHolder().key());
		tag(BlockTags.FENCE_GATES).add(FENCE_GATE.get().builtInRegistryHolder().key());
		tag(BlockTags.FENCES).add(FENCE2.get().builtInRegistryHolder().key());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(FENCE2.get().builtInRegistryHolder().key());
		tag(BlockTags.WOODEN_FENCES).add(FENCE.get().builtInRegistryHolder().key());
		tag(BlockTags.WALLS).add(WALL.get().builtInRegistryHolder().key());

		tag(BlockTags.SUPPORT_OVERRIDE_SNOW_LAYER).forceAddTag(BlockTags.LEAVES);
		tag(SNOWY_SETTING).forceAddTag(BlockTags.SNOW);
		tag(SNOWY_SETTING).add(FENCE.get().builtInRegistryHolder().key(), FENCE2.get().builtInRegistryHolder().key(), FENCE_GATE.get().builtInRegistryHolder().key(), WALL.get().builtInRegistryHolder().key());

		tag(CoreModule.CANNOT_ACCUMULATE_ON).add(Blocks.HAY_BLOCK.builtInRegistryHolder().key()).forceAddTag(BlockTags.SLABS);

		tag(GRASS).add(Blocks.SHORT_GRASS.builtInRegistryHolder().key(), Blocks.FERN.builtInRegistryHolder().key(), Blocks.TALL_GRASS.builtInRegistryHolder().key(), Blocks.LARGE_FERN.builtInRegistryHolder().key());
		tag(MUSHROOMS).add(Blocks.RED_MUSHROOM.builtInRegistryHolder().key(), Blocks.BROWN_MUSHROOM.builtInRegistryHolder().key());
	}
}
