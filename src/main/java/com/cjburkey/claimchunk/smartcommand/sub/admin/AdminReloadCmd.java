package com.cjburkey.claimchunk.smartcommand.sub.admin;

import com.cjburkey.claimchunk.ClaimChunk;
import com.cjburkey.claimchunk.Utils;
import com.cjburkey.claimchunk.smartcommand.CCSubCommand;

import de.goldmensch.commanddispatcher.Executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AdminReloadCmd extends CCSubCommand {

    public AdminReloadCmd(ClaimChunk claimChunk) {
        super(claimChunk, Executor.CONSOLE_PLAYER);
    }

    @Override
    public @NotNull Optional<String> getDescription() {
        return Optional.ofNullable(claimChunk.getMessages().cmdReload);
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return Utils.hasAdmin(sender);
    }

    @Override
    public @NotNull String getPermissionMessage() {
        return claimChunk.getMessages().reloadNoPerm;
    }

    @Override
    public CCArg[] getPermittedArguments() {
        return new CCArg[0];
    }

    @Override
    public int getRequiredArguments() {
        return 0;
    }

    @SuppressWarnings("CommentedOutCode")
    @Override
    public boolean onCall(@NotNull String cmdUsed, @NotNull CommandSender executor, String[] args) {
        var pluginManager = claimChunk.getServer().getPluginManager();
        pluginManager.disablePlugin(claimChunk);
        // Simulate a restart
        claimChunk.onLoad();
        pluginManager.enablePlugin(claimChunk);
        if (executor instanceof Player player) {
            messagePly(player, claimChunk.getMessages().reloadComplete);
        } else {
            messageChat(executor, claimChunk.getMessages().reloadComplete);
        }
        return true;
    }
}