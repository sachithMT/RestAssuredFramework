package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlayListTests {
    @Story("Create a Playlist")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("1234")
    @Issue("1000")
    @Description("This POST method should create a Playlist with valid data")
    @Test(description = "Should be able to Create a Playlist")
    public void ShouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.POST(requestPlaylist);
        assertStatusCode(response.statusCode(), 201);
        assertPlaylistEquals(response.as(Playlist.class), requestPlaylist);
    }

    @Description("This GET method should retrieve already created Playlist")
    @Test(description = "Should be able to get Playlist")
    public void ShouldBeAbleToGetAPlaylist() {
        Playlist requestPlaylist = playlistBuilder("Play List 8f0Cisf4ZQ", "Play List y7ABU4pFW0s99yvL7Pbd", false);
        Response response = PlaylistApi.GET(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(), 200);
        assertPlaylistEquals(response.as(Playlist.class), requestPlaylist);

    }

    @Description("This PUT method should Update a Playlist with valid data")
    @Test(description = "Should be able to update Playlist")
    public void ShouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.PUT(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), 200);
    }

    @Story("Create a Playlist")
    @Description("This POST method should not create a Playlist without a Playlist name")
    @Test(description = "Should not be able to create a Playlist without Name")
    public void ShouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist().
                setDescription(generateDescription()).
                setPublic(false);
        Response response = PlaylistApi.POST(requestPlaylist);
        assertError(response.as(ErrorRoot.class), 400, "Missing required field: name");
    }

    @Story("Create a Playlist")
    @Description("This POST method should not create a Playlist with invalid Token")
    @Test(description = "Should not be able to create Playlist with invalid Token")
    public void ShouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.POST("1234", requestPlaylist);
        assertError(response.as(ErrorRoot.class), 401, "Invalid access token");
    }


    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        return new Playlist().
                setName(name).
                setDescription(description).
                setPublic(_public);
    }

    @Step
    public void assertPlaylistEquals(Playlist responsePlaylist, Playlist requestPlaylist) {

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public void assertError(ErrorRoot responseError, int statusCode, String expectedMessage) {
        assertThat(responseError.getError().getStatus(), equalTo(statusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
    }

}
