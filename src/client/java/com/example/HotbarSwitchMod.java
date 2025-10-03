package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

@Environment(EnvType.CLIENT)
public class HotbarSwitchMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register Cloth Config
        HotbarSwitchConfig.register();

        // Intercept vanilla inventory key (E)
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.options != null) {
                if (client.options.inventoryKey.wasPressed()) {
                    handleInventoryPress(client);
                }
            }
        });
    }

    private void handleInventoryPress(MinecraftClient client) {
        if (client.player == null || client.getNetworkHandler() == null) return;

        // Slider is 1–9 → convert to 0–8 index
        int slotIndex = Math.max(1, Math.min(9, HotbarSwitchConfig.get().slot)) - 1;

        // Switch slot locally
        client.player.getInventory().setSelectedSlot(slotIndex);

        // Sync with server
        client.getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(slotIndex));

        // Open inventory
        client.setScreen(new InventoryScreen(client.player));
    }
}
