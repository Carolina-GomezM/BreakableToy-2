package breakable.toy.myproject.Contollers;

import org.springframework.web.bind.annotation.*;

import breakable.toy.myproject.Services.SpotifyAuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
public class SpotifyAuthController {
    private final SpotifyAuthService authService;

    public SpotifyAuthController(SpotifyAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/spotify")
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok(authService.getAuthUrl());
    }

    @GetMapping("/callback")
    public ResponseEntity<Map<String, String>> callback(@RequestParam("code") String code) {

            authService.requestToken(code);
            HttpHeaders responseHeaders = new HttpHeaders();
            Map<String, String> responseBody = new HashMap<>();
            responseHeaders.add("Location", "http://localhost:5173/home");
            responseBody.put("message", "ok");
            return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.FOUND);

    }

    @GetMapping("/check-auth")
    public ResponseEntity<Boolean> checkAuth() {
        boolean isAuthenticated = authService.getAccessToken() != null; 
        return ResponseEntity.ok(isAuthenticated);
}

@GetMapping("/get-tokens")
public ResponseEntity<Map<String, String>> getTokens() {
    Map<String, String> tokens = new HashMap<>();
    tokens.put("accessToken", authService.getAccessToken());
    tokens.put("refreshToken", authService.getRefreshToken());
    return ResponseEntity.ok(tokens);
}

@GetMapping("/refresh-token")
public ResponseEntity<Map<String, String>> refreshToken() {
    authService.refreshToken();
    Map<String, String> response = new HashMap<>();
    response.put("accessToken", authService.getAccessToken());
    return ResponseEntity.ok(response);
}

    
}