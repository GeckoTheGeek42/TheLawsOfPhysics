package qmech.lib.objects.meta;

import net.minecraft.item.ItemStack;

public enum EnumItemsBase {
	Generic;

	public ItemStack newItemStack(MetaItemBase base, int size) {
		return new ItemStack(base, size, ordinal());
	}

	public ItemStack newItemStack(MetaItemBase base) {
		return new ItemStack(base, 1, ordinal());
	}

	public boolean isA(ItemStack stack) {
		return (stack.getItem() instanceof MetaItemBase) && (stack.getItemDamage() == ordinal());
	}

	protected MetaItemBase.MetaItem createMetaItem(MetaItemBase base) {
		return new MetaItemBase.MetaItem(base.prefix + "_" + this.name());
	}

	protected boolean isEnabled() {
		return true;
	}

	public static void registerItems(MetaItemBase base) {
		for (EnumItemsBase m : values())
			if (m.isEnabled()) base.registerItem(m.ordinal(), m.createMetaItem(base));
	}
}