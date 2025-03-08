package breakable.toy.myproject.Services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SpotifyApiService {

    private final SpotifyAuthService authService;

    public SpotifyApiService(SpotifyAuthService authService) {
        this.authService = authService;
    }

    public Map getArtist(String id) {
        String url = "https://api.spotify.com/v1/artists/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody(); 
    }

    public List<Map> getTopArtists() {
        String url = "https://api.spotify.com/v1/me/top/artists?limit=10";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        List<Map> artists = (List<Map>) response.getBody().get("items");

        return artists;  
    }

    public Map getAlbumDetails(String id) {
        String url = "https://api.spotify.com/v1/albums/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }


    public Map search(String query) {
        String url = "https://api.spotify.com/v1/search?q=" + query + "&type=artist,album,track,playlist&limit=3";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }

    public Map getArtistAlbum(String id) {
        String url = "https://api.spotify.com/v1/artists/"+id+"/albums?limit=5";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }

    public Map getArtistTopTracks(String id) {
        String url = "https://api.spotify.com/v1/artists/"+id+"/top-tracks";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }

    public Map getRelatedArtists(String id) {
        String url = "https://api.spotify.com/v1/artists/"+id+"/related-artists";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }
    public Map getPlaylistDetail(String id) {
        String url = "https://api.spotify.com/v1/playlists/"+id+"";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authService.getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            authService.refreshToken();
            headers.set("Authorization", "Bearer " + authService.getAccessToken());
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        }

        return response.getBody();  
    }
}