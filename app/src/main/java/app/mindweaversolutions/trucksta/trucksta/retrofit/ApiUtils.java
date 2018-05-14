package app.mindweaversolutions.trucksta.trucksta.retrofit;

public class ApiUtils {

        public static final String BASE_URL="";

        public static UserService getUserService(){

                return RetrofitClient.getClient(BASE_URL).create(UserService.class);

        }

}


