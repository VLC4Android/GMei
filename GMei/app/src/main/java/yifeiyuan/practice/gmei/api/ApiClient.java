package yifeiyuan.practice.gmei.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by alanchen on 15/8/26.
 */
public class ApiClient {

    Gson sGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();

    private static GMeiService service ;
    public ApiClient(){
        OkHttpClient client = new OkHttpClient();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://gank.avosapps.com")
                .setConverter(new GsonConverter(sGson))
                .setClient(new OkClient(client))
                .build();

        service = adapter.create(GMeiService.class);
    }

    private static ApiClient ins = new ApiClient();

    public static GMeiService getGMeiService(){
        return service;
    }
}
