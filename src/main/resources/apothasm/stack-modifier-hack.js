function initializeCoreMod() {
    return {
        'apothstackmodifier': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.entity.SharedMonsterAttributes',
                'methodName': 'func_111259_a',
                'methodDesc': '(Lnet/minecraft/nbt/CompoundNBT;)Lnet/minecraft/entity/ai/attributes/AttributeModifier;'
            },
            'transformer': function(method) {
                print('[ApotheosisCore]: Patching SharedMonsterAttributes#readAttributeModifier');

                var owner = "shadows/apotheosis/deadly/asm/DeadlyHooks";
                var name = "getRealUUID";
                var desc = "(Ljava/util/UUID;)Ljava/util/UUID;";
                var instr = method.instructions;

                var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var Opcodes = Java.type('org.objectweb.asm.Opcodes');
                var AbstractInsnNode = Java.type('org.objectweb.asm.tree.AbstractInsnNode');

				var n = null;
				var i;
				for (i = 0; i < instr.size(); i++) {
					n = instr.get(i);
					if (n.getOpcode() == Opcodes.ASTORE) {
						break;
					}
				}

				instr.insertBefore(n, ASMAPI.buildMethodCall(
                    owner,
                    name,
                    desc,
                    ASMAPI.MethodType.STATIC));

                return method;
            }
        }
    }
}