package com.example.jhona.clothesshopapp.storage.request;

import com.example.jhona.clothesshopapp.storage.request.entity.CompraRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.CompraResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.LogInBLResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.ProductResponse;
import com.example.jhona.clothesshopapp.storage.request.entity.RegisterBLRaw;
import com.example.jhona.clothesshopapp.storage.request.entity.RegisterBLResponse;
/*import com.isil.am2template.model.entity.UserEntity;
import com.isil.am2template.storage.request.entity.LogInBLRaw;
import com.isil.am2template.storage.request.entity.LogInBLResponse;
import com.isil.am2template.storage.request.entity.LogInRaw;
import com.isil.am2template.storage.request.entity.LogInResponse;
import com.isil.am2template.storage.request.entity.NoteBLRaw;
import com.isil.am2template.storage.request.entity.NoteRaw;
import com.isil.am2template.storage.request.entity.NoteResponse;
import com.isil.am2template.storage.request.entity.NotesBLResponse;
import com.isil.am2template.storage.request.entity.NotesResponse;
import com.isil.am2template.storage.request.entity.UserRaw;
import com.isil.am2template.storage.request.entity.UsersResponse;*/

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


/**
 * Created by em on 8/06/16.
 */
public class ApiClient {

    private static final String TAG = "ApiClient";
    //private static final String API_BASE_URL="https://obscure-earth-55790.herokuapp.com";
    private static final String API_BASE_URL="https://api.backendless.com/";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;


    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient =new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        //Backendless
        //7FBB8DC0-4C21-0178-FF76-367F7D30DC00/E5214A86-653A-529C-FF73-95B4DD4F8C00/users/login
        @POST("/{applicationid}/{restapikey}/users/login")
        Call<LogInBLResponse> logInBL(@Path("applicationid") String appID,
                                      @Path("restapikey") String restApiKEY, @Body LogInBLRaw raw);



        @POST("/{applicationid}/{restapikey}/data/Compra")
        Call<CompraResponse> combraBL(@Path("applicationid") String appID,
                                     @Path("restapikey") String restApiKEY, @Body CompraRaw raw);


        @POST("/{applicationid}/{restapikey}/users/register")
        Call<RegisterBLResponse> RegisterBL(@Path("applicationid") String appID,
                                         @Path("restapikey") String restApiKEY, @Body RegisterBLRaw raw);





        @GET("/{applicationid}/{restapikey}/data/producto")
        Call<ProductResponse> listproductos(@Path("applicationid") String appID,
                                            @Path("restapikey") String restApiKEY, @HeaderMap Map<String, String> headers);


        //https://api.backendless.com/7FBB8DC0-4C21-0178-FF76-367F7D30DC00/E5214A86-653A-529C-FF73-95B4DD4F8C00/data/Note
        /*@GET("/{applicationid}/{restapikey}/data/Note")
        Call<NotesBLResponse> notesbl(@Path("applicationid") String appID,
                                      @Path("restapikey") String restApiKEY, @HeaderMap Map<String, String> headers);


        //Create Note
        //https://api.backendless.com/<application-id>/<REST-api-key>/data/<table-name>
        @POST("/{applicationid}/{restapikey}/data/Note")
        Call<Object> addNoteBL(@Path("applicationid") String appID,
                               @Path("restapikey") String restApiKEY, @HeaderMap Map<String, String> headers,
                               @Body NoteBLRaw noteBLRaw);

        //Delete Note
        //https://api.backendless.com/<application-id>/<REST-api-key>/data/<table-name>/<object-id>
        @DELETE("/{applicationid}/{restapikey}/data/Note/{objectId}")
        Call<Object> deleteNoteBL(@Path("applicationid") String appID,
                                  @Path("restapikey") String restApiKEY, @HeaderMap Map<String, String> headers,
                                  @Path("objectId") String objectId);

        //Update Note
        //https://api.backendless.com/<application-id>/<REST-api-key>/data/<table-name>/<object-id>

        @PUT("/{applicationid}/{restapikey}/data/Note/{objectId}")
        Call<Object> updateNoteBL(@Path("applicationid") String appID,
                                  @Path("restapikey") String restApiKEY, @HeaderMap Map<String, String> headers,
                                  @Path("objectId") String objectId, @Body NoteBLRaw noteBLRaw);*/



        //--------------------------------------------------------
        //https://obscure-earth-55790.herokuapp.com/api/login
       /* @POST("/api/login")
        Call<LogInResponse> login(@Body LogInRaw raw);

        //https://obscure-earth-55790.herokuapp.com/api/users/register
        @POST("/api/users/register")
        Call<UserEntity> register(@Body UserRaw raw);

        //https://obscure-earth-55790.herokuapp.com/api/users
        @GET("/api/users/")
        Call<UsersResponse> users();

        //Notes
        // https://obscure-earth-55790.herokuapp.com/api/notes/register
        @POST("/api/notes/register")
        Call<NoteResponse> addNote(@Body NoteRaw raw);

        // https://obscure-earth-55790.herokuapp.com/api/notes/
        @GET("/api/notes")
        Call<NotesResponse> notes();*/

        /*
        //v1/data/Notes
        @GET("/v1/data/Notes")
        Call<NotesResponse> notes();


        @Headers({
                "Content-Type: application/json",
                "application-id: B9D12B47-6B88-8471-FFAD-2B4FFD1EA100",
                "secret-key: 46C1AEC7-6BA7-D1C7-FF6A-FD9EA95C0C00",
                "application-type: REST"
        })
        @POST("/v1/data/Notes")
        Call<NotesResponse> addNote(@Body NoteRaw raw);*/

    }

    /*private static OkHttpClient.Builder client(){
        if(httpClient==null)httpClient=new OkHttpClient.Builder();
        return httpClient;
    }*/
    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
