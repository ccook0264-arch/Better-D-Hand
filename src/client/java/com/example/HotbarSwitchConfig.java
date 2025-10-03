package com.example;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "hotbarswitch")
public class HotbarSwitchConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 1, max = 9)
    @ConfigEntry.Gui.Tooltip
    public int slot = 4; // default to in-game slot 4

    public static HotbarSwitchConfig get() {
        return AutoConfig.getConfigHolder(HotbarSwitchConfig.class).getConfig();
    }

    public static void register() {
        AutoConfig.register(HotbarSwitchConfig.class, GsonConfigSerializer::new);
    }
}
