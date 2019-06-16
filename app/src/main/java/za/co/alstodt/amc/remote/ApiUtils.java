package za.co.alstodt.amc.remote;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://www.alstodt.co.za/api/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
