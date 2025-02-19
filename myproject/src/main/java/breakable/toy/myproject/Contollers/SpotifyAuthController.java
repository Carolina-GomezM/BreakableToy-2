package breakable.toy.myproject.Contollers;

import org.springframework.web.bind.annotation.*;

import breakable.toy.myproject.Services.SpotifyAuthService;

import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
public class SpotifyAuthController {
    private final SpotifyAuthService authService;

    public SpotifyAuthController(SpotifyAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth/spotify")
    public ResponseEntity<Void> authenticate() {
        return ResponseEntity.status(302).location(URI.create(authService.getAuthUrl())).build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        authService.requestToken(code);
        return ResponseEntity.ok("Authentication successful!");
    }
}