package de.iotacb.cu.core.mc.alt;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import info.client.civia.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.net.Proxy;

public class LoginUtil {

    private static final Minecraft MC = Minecraft.getMinecraft();

    public static final boolean login(final String email, final String password) {
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);

        authentication.setUsername(email);
        authentication.setPassword(password);

        try {
            authentication.logIn();
            MC.setSession(new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static final boolean loginNoGameProfile(final String email, final String password, final Proxy proxy) {
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);

        authentication.setUsername(email);
        authentication.setPassword(password);

        try {
            authentication.logIn();
            MC.setSession(new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang"));
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static final GameProfile login(final String email, final String password, final Proxy proxy) {
        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(proxy, "").createUserAuthentication(Agent.MINECRAFT);

        authentication.setUsername(email);
        authentication.setPassword(password);

        try {
            authentication.logIn();
            final GameProfile returnProfile = authentication.getSelectedProfile();
            Minecraft.getMinecraft().getSessionService().fillProfileProperties(returnProfile, false);
            return returnProfile;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
