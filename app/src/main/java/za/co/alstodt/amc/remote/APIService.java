package za.co.alstodt.amc.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import za.co.alstodt.amc.model.form;

public interface APIService {
    @POST("mail.php")
    @FormUrlEncoded
    Call<form> insert(
                      @Field("name") String name,
                      @Field("comment") String comment,
                      @Field("phone") String phone,
                      @Field("email") String email

    );
}
