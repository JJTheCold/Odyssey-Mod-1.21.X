package net.thebastion.odyssey.util;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.thebastion.odyssey.Odyssey;


public class DataAttachmentRegistry {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Odyssey.MODID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<IArmorCapeProvider.CapeData>> CAPE_DATA = ATTACHMENT_TYPES.register("cape_data",
            () -> AttachmentType.builder((holder) -> new IArmorCapeProvider.CapeData()).build());
}