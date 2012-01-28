package com.dumptruckman.actionmenu.plugin;

import com.dumptruckman.actionmenu.ActionMenu;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.Menus;
import com.dumptruckman.actionmenu2.api.event.ActionEvent;
import com.dumptruckman.actionmenu2.api.event.ActionListener;
import com.dumptruckman.actionmenu2.api.view.MenuView;
import com.dumptruckman.actionmenu2.impl.SimpleMenuItem;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ActionMenuPlugin extends JavaPlugin implements Listener {

    MenuHandle menuHandle = null;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {

    }

    @EventHandler
    public void signChange(SignChangeEvent event) {
        if (event.getLine(0).contains("menu")) {
            menuHandle = Menus.getMenuHandle((Sign) event.getBlock().getState());
            MenuItem test = new SimpleMenuItem();
            test.setText("test");
            test.getListeners().add(new TestActionListener());
            MenuItem poop = new SimpleMenuItem();
            poop.setText("poop");

            menuHandle.getMenu().getContents().add(test);
            menuHandle.getMenu().getContents().add(poop);
        }
    }

    private class TestActionListener implements ActionListener {
        public void onAction(ActionEvent event) {

        }
    }
}
