package shadows.apotheosis.deadly.loot.affix.impl.melee;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import shadows.apotheosis.Apotheosis;
import shadows.apotheosis.deadly.loot.EquipmentType;
import shadows.apotheosis.deadly.loot.affix.impl.AttributeAffix;
import shadows.apotheosis.deadly.loot.attributes.CustomAttributes;

/**
 * Adds cold damage, and slows hit enemies.
 */
public class ColdDamageAffix extends AttributeAffix {

	public static final DamageSource COLD = new DamageSource("apoth.frozen_solid").setMagicDamage().setDamageIsAbsolute();

	public ColdDamageAffix(int weight) {
		super(CustomAttributes.COLD_DAMAGE, 3, 7, Operation.ADDITION, weight);
	}

	@Override
	public boolean canApply(EquipmentType type) {
		return type == EquipmentType.SWORD;
	}

	@Override
	public void onEntityDamaged(LivingEntity user, Entity target, float level) {
		if (target instanceof LivingEntity) ((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * Math.max(3, (int) (level / 1.5F)), 1));
		target.attackEntityFrom(COLD, Apotheosis.localAtkStrength * level);
	}

	@Override
	public float getMin() {
		return 1;
	}

	@Override
	public float getMax() {
		return 10;
	}

}