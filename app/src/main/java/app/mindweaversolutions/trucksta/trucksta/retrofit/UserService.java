package app.mindweaversolutions.trucksta.trucksta.retrofit;

import java.util.List;

import app.mindweaversolutions.trucksta.trucksta.model.LoginResp;
import app.mindweaversolutions.trucksta.trucksta.model.RespObj;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("mws_login.php")
    Call<LoginResp> login(@Field("email")String a,
                               @Field("password")String b);

    @FormUrlEncoded
    @POST("mws_register.php")
    Call<Response> register(@Field("name")String name,
                         @Field("contact")String contact,
                            @Field("dob")String dob,
                            @Field("email")String email,
                            @Field("address")String address,
                            @Field("vid")String vid,
                            @Field("vpic")String vpic,
                            @Field("pid")String pid,
                            @Field("ppic")String ppic,
                            @Field("aid")String aid,
                            @Field("apic")String apic,
                            @Field("did")String did,
                            @Field("dpic")String dpic,
                            @Field("years")String years,
                            @Field("tot")String tot,
                            @Field("terrain")String terrain,
                            @Field("bname")String bname,
                            @Field("bno")String bno,
                            @Field("bifsc")String bifsc
                            );

    /*
    @GET("vendorm.php")
    Call<ArrayList<MediaObject>> vendorm(
            @Query("id") String id);


    @FormUrlEncoded
    @POST("mediaupload.php")
    Call<Void> mediaUpload(@Field("vendorID")String vendorID,
                           @Field("mediaType")String mediaType,
                           @Field("landmark")String landmark,
                           @Field("facing")String facing,
                           @Field("width")String width,
                           @Field("height")String height,
                           @Field("street")String street,
                           @Field("city")String city,
                           @Field("state")String state,
                           @Field("available") String available,
                           @Field("price")String price,
                           @Field("price6")String price6,
                           @Field("price12")String price12,
                           @Field("imagedata")String imagedata,
                           @Field("longitude")String longitude,
                           @Field("latitude")String latitude);
*/
}
