package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;


public class PlaylistApi {

    @Step
    public static Response POST(Playlist requestPlaylist) {
        return RestResource.POST(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);
    }

    @Step
    public static Response POST(String token, Playlist requestPlaylist) {
        return RestResource.POST(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
    }

    @Step
    public static Response GET(String playlistId) {
        return RestResource.GET(PLAYLISTS + "/" + playlistId, getToken());
    }

    @Step
    public static Response PUT(String playlistId, Playlist requestPlaylist) {
        return RestResource.PUT(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }

}
