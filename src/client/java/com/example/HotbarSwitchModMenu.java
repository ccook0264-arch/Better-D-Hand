package com.example.hotbarswitch;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.autoconfig.AutoConfig;

public class HotbarSwitchModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(HotbarSwitchConfig.class, parent).get();
    }
}
