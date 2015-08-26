package yifeiyuan.practice.gmei.api;

import retrofit.http.GET;
import retrofit.http.Path;
import yifeiyuan.practice.gmei.GMeiResult;

/**
 * Created by alanchen on 15/8/26.
 */
public interface GMeiService {
    @GET("/api/data/{dateType}/10/{page}")
    GMeiResult getMeizi(@Path("dateType")String dataType,@Path("page")int page);
}
