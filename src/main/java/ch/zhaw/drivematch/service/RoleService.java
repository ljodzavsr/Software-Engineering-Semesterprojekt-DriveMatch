package ch.zhaw.drivematch.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

// Die hasRole-Methode überprüft, ob ein authentifizierter Benutzer (repräsentiert durch ein JWT) 
// eine bestimmte Rolle hat, indem sie die Rollen aus dem JWT liest und mit der angegebenen Rolle vergleicht.

@Service
public class RoleService {

    public boolean hasRole(String role, @AuthenticationPrincipal Jwt jwt) {
        List<String> userRoles = jwt.getClaimAsStringList("user_roles");
        if (userRoles.contains(role)) {
            return true;
        }
        return false;
    }
}
