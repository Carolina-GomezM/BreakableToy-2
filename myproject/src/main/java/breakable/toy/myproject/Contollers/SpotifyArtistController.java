package breakable.toy.myproject.Contollers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import breakable.toy.myproject.Services.SpotifyApiService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/artists")
public class SpotifyArtistController {

    private final SpotifyApiService spotifyApiService;

    public SpotifyArtistController(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getArtist(@PathVariable String id) {
        Map artist = spotifyApiService.getArtist(id);
        return ResponseEntity.ok(artist); 
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map>> getTopArtists() {
        List<Map> topArtists = spotifyApiService.getTopArtists();
        return ResponseEntity.ok(topArtists);  
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<Map> getAlbum(@PathVariable String id) {
        Map albumDetails = spotifyApiService.getAlbumDetails(id);
        return ResponseEntity.ok(albumDetails);
    }

    @GetMapping("/search")
    public ResponseEntity<Map> search(@RequestParam String query) {
        Map searchResults = spotifyApiService.search(query);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/artist/album/{id}")
    public ResponseEntity<Map> artistAlbum(@PathVariable String id) {
        Map albumDetails = spotifyApiService.getArtistAlbum(id);
        return ResponseEntity.ok(albumDetails);
    }

    @GetMapping("/artist/top-tracks/{id}")
    public ResponseEntity<Map> artistTopTracks(@PathVariable String id) {
        Map albumDetails = spotifyApiService.getArtistTopTracks(id);
        return ResponseEntity.ok(albumDetails);
    }

    @GetMapping("/related/artists/{id}")
    public ResponseEntity<Map> relatedArtists(@PathVariable String id) {
        Map albumDetails = spotifyApiService.getRelatedArtists(id);
        return ResponseEntity.ok(albumDetails);
    }
}