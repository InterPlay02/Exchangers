package me.jacky1356400.exchangers.item;

import me.jacky1356400.exchangers.handler.ExchangerHandler;
import me.jacky1356400.exchangers.helper.StringHelper;
import me.jacky1356400.exchangers.util.Data;
import me.jacky1356400.exchangers.util.IHasModel;
import me.jacky1356400.exchangers.util.Tier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static me.jacky1356400.exchangers.helper.StringHelper.localize;

public class ItemExchanger extends ExchangerHandler implements IHasModel {

	private final Tier t;

	public ItemExchanger(String name, boolean noRepair, Tier tier, int maxDmg) {
		setRegistryName(Data.MODID, name);
		setUnlocalizedName(Data.MODID + "." + name);
		setCreativeTab(Data.TAB);
		if (noRepair)
			setNoRepair();
		setMaxStackSize(1);
		t = tier;
		if (maxDmg > 0)
			setMaxDamage(maxDmg);
		Data.ITEMS.add(this);
	}

	public ItemExchanger(String name, Tier tier, int maxDmg) {
		this(name, false, tier, maxDmg);
	}

	public boolean showDurabilityBar(ItemStack stack) {
		return stack.isItemDamaged();
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag bool) {
		super.addInformation(stack, world, tooltip, bool);
		if (!isPowered()){
			tooltip.add(StringHelper.formatNumber(stack.getMaxDamage() - stack.getItemDamage()) + " / " + StringHelper.formatNumber(stack.getMaxDamage()) + " " + localize("tooltip.durability"));
			tooltip.add(getTier().getFormattedText());
		}
	}

	public Tier getTier() {
		return t;
	}

	public boolean isPowered() {
		return false;
	}

}
