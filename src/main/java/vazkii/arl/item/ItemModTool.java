/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [06/02/2016, 20:07:08 (GMT)]
 */
package vazkii.arl.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vazkii.arl.interf.IVariantHolder;

public abstract class ItemModTool extends ItemTool implements IVariantHolder {

	private final String[] variants;
	private final String bareName;

	protected ItemModTool(String name, float attackDamage, float speed, ToolMaterial material, Set<Block> effectiveBlocks, String... variants) {
		super(attackDamage, speed, material, effectiveBlocks);
		setUnlocalizedName(name);
		if(variants.length > 1)
			setHasSubtypes(true);

		if(variants.length == 0)
			variants = new String[] { name };

		bareName = name;
		this.variants = variants;
		ItemMod.variantHolders.add(this);
	}

	@Override
	public Item setUnlocalizedName(String name) {
		super.setUnlocalizedName(name);
		GameRegistry.register(this, new ResourceLocation(getPrefix() + name));

		return this;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int dmg = par1ItemStack.getItemDamage();
		String[] variants = getVariants();

		String name;
		if(dmg >= variants.length)
			name = bareName;
		else name = variants[dmg];

		return "item." + getPrefix() + name;
	}

	@Override
	public String[] getVariants() {
		return variants;
	}

	@Override
	public ItemMeshDefinition getCustomMeshDefinition() {
		return null;
	}

}
