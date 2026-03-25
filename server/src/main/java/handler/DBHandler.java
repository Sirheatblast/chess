package handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import service.DBService;
import model.result.DBResult;

public class DBHandler {
    public void clearDB(Context cxt) {
        Gson gson = new Gson();
        DBResult result;
        try{
            DBService dbService = new DBService();
            dbService.clearDB();
            result = new DBResult(200,"");
        }
        catch (Exception e){
            result = new DBResult(500,e.getMessage());
        }

        String resultJson = gson.toJson(result);
        cxt.result(resultJson);
        cxt.status(result.getStatus());
    }

}
