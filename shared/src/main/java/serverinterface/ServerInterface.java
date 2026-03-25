package serverinterface;

import model.AuthData;

import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class ServerInterface {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String serverUrl;

    public ServerInterface(String url) {
        serverUrl = url;
    }

    public String createUser(String userData) throws Exception{
        var request = buildRequest("POST", "user",userData,"");
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String loginUser(String userData) throws Exception{
        var request = buildRequest("POST", "session",userData,"");
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String logoutUser(AuthData authData) throws Exception{
        var request = buildRequest("DELETE", "session",null, authData.getAuthToken());
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String getGames(AuthData authData) throws Exception{
        var request = buildRequest("Get", "game",null, authData.getAuthToken());
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String createGame(AuthData authData, String gameRequest)throws Exception{
        var request = buildRequest("POST", "game",gameRequest, authData.getAuthToken());
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String joinGame(AuthData authData, String gameReq)throws Exception{
        var request = buildRequest("PUT", "game",gameReq, authData.getAuthToken());
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    private HttpRequest buildRequest(String method, String path, String body,String authToken) {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl + path))
                .header("authorization", authToken)
                .method(method, makeRequestBody(body));

        if (body != null) {
            request.setHeader("Content-Type", "application/json");
        }
        return request.build();
    }

    private BodyPublisher makeRequestBody(String request) {
        if (request != null) {
            return BodyPublishers.ofString(request);
        } else {
            return BodyPublishers.noBody();
        }
    }
}
