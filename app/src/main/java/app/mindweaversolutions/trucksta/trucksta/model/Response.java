package app.mindweaversolutions.trucksta.trucksta.model;

/**
 * Created by john on 15/5/18.
 */



        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("login_s")
    @Expose
    private String loginS;
    @SerializedName("login_f")
    @Expose
    private String loginF;
    @SerializedName("register_s")
    @Expose
    private String registerS;
    @SerializedName("register_f")
    @Expose
    private String registerF;

    public String getLoginS() {
        return loginS;
    }

    public void setLoginS(String loginS) {
        this.loginS = loginS;
    }

    public String getLoginF() {
        return loginF;
    }

    public void setLoginF(String loginF) {
        this.loginF = loginF;
    }

    public String getRegisterS() {
        return registerS;
    }

    public void setRegisterS(String registerS) {
        this.registerS = registerS;
    }

    public String getRegisterF() {
        return registerF;
    }

    public void setRegisterF(String registerF) {
        this.registerF = registerF;
    }


}
