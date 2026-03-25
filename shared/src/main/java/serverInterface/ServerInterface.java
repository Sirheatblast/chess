package serverInterface;

import model.AuthData;

import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class ServerInterface {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String serverUrl ="http://localhost:8080/";

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

    public void logoutUser(AuthData authData) throws Exception{
        var request = buildRequest("DELETE", "session",null, authData.getAuthToken());
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    }

    public String getGames(String authData){

        return null;
    }

    public void createGame(String authData,String gameName){

    }

    public String joinGame(String authData, int gameId, String dColor, String username){
        return null;
    }

    public String observeGame(String authData,int gameId){
        return null;
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
