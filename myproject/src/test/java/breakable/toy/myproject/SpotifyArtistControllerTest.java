package breakable.toy.myproject;

import breakable.toy.myproject.Contollers.SpotifyArtistController;
import breakable.toy.myproject.Services.SpotifyApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SpotifyArtistControllerTest {

    @Mock
    private SpotifyApiService spotifyApiService;

    @InjectMocks
    private SpotifyArtistController spotifyArtistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArtist() {
        String artistId = "123";
        Map<String, String> artist = new HashMap<>();
        artist.put("id", artistId);
        artist.put("name", "Artist Name");

        when(spotifyApiService.getArtist(artistId)).thenReturn(artist);
        ResponseEntity<Map> response = spotifyArtistController.getArtist(artistId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(artist, response.getBody());
        verify(spotifyApiService, times(1)).getArtist(artistId);
    }

    @Test
    void testGetTopArtists() {
        List<Map> topArtists = Collections.singletonList(new HashMap<>());
        when(spotifyApiService.getTopArtists()).thenReturn(topArtists);

        ResponseEntity<List<Map>> response = spotifyArtistController.getTopArtists();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(topArtists, response.getBody());
        verify(spotifyApiService, times(1)).getTopArtists();
    }

    @Test
    void testGetAlbum() {
        String albumId = "123";
        Map<String, String> album = new HashMap<>();
        album.put("id", albumId);
        album.put("name", "Album Name");

        when(spotifyApiService.getArtist(albumId)).thenReturn(album);
        ResponseEntity<Map> response = spotifyArtistController.getArtist(albumId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(album, response.getBody());
        verify(spotifyApiService, times(1)).getArtist(albumId);
    }

    @Test
    public void testSearch() {
        String searchQuery = "Queen";
        
        Map<String, Object> mockSearchResults = new HashMap<>();
        mockSearchResults.put("artists", List.of(
            Map.of("id", "1", "name", "Queen"),
            Map.of("id", "2", "name", "Queen Latifah")
        ));
        mockSearchResults.put("albums", List.of(
            Map.of("id", "album1", "name", "Greatest Hits")
        ));

        when(spotifyApiService.search(searchQuery)).thenReturn(mockSearchResults);
        ResponseEntity<Map> response = spotifyArtistController.search(searchQuery);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSearchResults, response.getBody());
        
        verify(spotifyApiService).search(searchQuery);
    }

    @Test
    void testGetArtistAlbum() {
        String albumId = "123";
        Map<String, String> album = new HashMap<>();
        album.put("id", albumId);
        album.put("name", "Album Name");

        when(spotifyApiService.getArtistAlbum(albumId)).thenReturn(album);
        ResponseEntity<Map> response = spotifyArtistController.artistAlbum(albumId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(album, response.getBody());
        verify(spotifyApiService, times(1)).getArtistAlbum(albumId);
    }

    @Test
    void testGetArtistTopTracks() {
        String albumId = "123";
        Map<String, String> album = new HashMap<>();
        album.put("id", albumId);
        album.put("name", "Album Name");

        when(spotifyApiService.getArtistAlbum(albumId)).thenReturn(album);
        ResponseEntity<Map> response = spotifyArtistController.artistAlbum(albumId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(album, response.getBody());
        verify(spotifyApiService, times(1)).getArtistAlbum(albumId);
    }

    @Test
    public void testArtistTopTracks() {
        String artistId = "123";
        
        Map<String, Object> mockTopTracks = new HashMap<>();
        mockTopTracks.put("tracks", List.of(
            Map.of(
                "id", "track1", 
                "name", "Bohemian Rhapsody", 
                "popularity", 90
            ),
            Map.of(
                "id", "track2", 
                "name", "Another One Bites the Dust", 
                "popularity", 85
            )
        ));

        when(spotifyApiService.getArtistTopTracks(artistId)).thenReturn(mockTopTracks);

        ResponseEntity<Map> response = spotifyArtistController.artistTopTracks(artistId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTopTracks, response.getBody());
        verify(spotifyApiService).getArtistTopTracks(artistId);
    }

    @Test
    void testGetPlaylist() {
        String playlistId = "123";
        Map<String, String> playlist = new HashMap<>();
        playlist.put("id", playlistId);
        playlist.put("name", "Album Name");

        when(spotifyApiService.getPlaylistDetail(playlistId)).thenReturn(playlist);
        ResponseEntity<Map> response = spotifyArtistController.playlist(playlistId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(playlist, response.getBody());
        verify(spotifyApiService, times(1)).getPlaylistDetail(playlistId);
    }


}