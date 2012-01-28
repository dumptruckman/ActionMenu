package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.view.MenuView;
import com.dumptruckman.actionmenu2.api.view.SignView;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;

public class SimpleSignView implements MenuView {
    
    private Block block;
    private Menu menu = null;

    public SimpleSignView(Sign sign) {
        this.block = sign.getBlock();
    }

    public Sign getSign() {
        BlockState state = this.getBlock().getState();
        if (state instanceof Sign) {
            return (Sign)state;
        }
        return null;
    }

    public Block getBlock() {
        return this.block;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public void show(CommandSender sender) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSender(CommandSender sender) {
        this.getMenu().setSender(sender);
    }

    @Override
    public CommandSender getSender() {
        return null;
    }
}
