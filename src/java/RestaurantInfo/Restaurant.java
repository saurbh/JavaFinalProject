package RestaurantInfo;

/**
 *
 * @author Saurabh
 */
public class Restaurant {

    private String NAME;
    private String MENU;
    private String PRICE;
    private String PHONE;
    private String ADDRESS;
    private String POSTAL_CODE;
    private String COUNTRY_CODE;
    private double LATITUDE;
    private double LONGITUDE;
    private String MOBILE_URL;
    private String RATING;
    private String RATING_IMAGE_URL;
    private String SNIPPET_TEXT;

    /**
     *
     */
    public Restaurant() {
        NAME = "";
        MENU = "";
        PRICE = "";
        PHONE = "";
        ADDRESS = "";
        POSTAL_CODE = "";
        COUNTRY_CODE = "";
        LATITUDE = 0.0;
        LONGITUDE = 0.0;
        MOBILE_URL = "";
        RATING = "";
        RATING_IMAGE_URL = "";
        SNIPPET_TEXT = "";
    }

    /**
     *
     * @return
     */
    public String getPRICE() {
        return PRICE;
    }

    /**
     *
     * @param PRICE
     */
    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    /**
     *
     * @return
     */
    public String getNAME() {
        return NAME;
    }

    /**
     *
     * @param NAME
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     *
     * @return
     */
    public String getMENU() {
        return MENU;
    }

    /**
     *
     * @param MENU
     */
    public void setMENU(String MENU) {
        this.MENU = MENU;
    }

    /**
     *
     * @return
     */
    public String getPHONE() {
        return PHONE;
    }

    /**
     *
     * @param PHONE
     */
    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    /**
     *
     * @return
     */
    public String getADDRESS() {
        return ADDRESS;
    }

    /**
     *
     * @param ADDRESS
     */
    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    /**
     *
     * @return
     */
    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    /**
     *
     * @param POSTAL_CODE
     */
    public void setPOSTAL_CODE(String POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }

    /**
     *
     * @return
     */
    public String getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    /**
     *
     * @param COUNTRY_CODE
     */
    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    /**
     *
     * @return
     */
    public double getLATITUDE() {
        return LATITUDE;
    }

    /**
     *
     * @param LATITUDE
     */
    public void setLATITUDE(double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    /**
     *
     * @return
     */
    public double getLONGITUDE() {
        return LONGITUDE;
    }

    /**
     *
     * @param LONGITUDE
     */
    public void setLONGITUDE(double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    /**
     *
     * @return
     */
    public String getMOBILE_URL() {
        return MOBILE_URL;
    }

    /**
     *
     * @param MOBILE_URL
     */
    public void setMOBILE_URL(String MOBILE_URL) {
        this.MOBILE_URL = MOBILE_URL;
    }

    /**
     *
     * @return
     */
    public String getRATING() {
        return RATING;
    }

    /**
     *
     * @param RATING
     */
    public void setRATING(String RATING) {
        this.RATING = RATING;
    }

    /**
     *
     * @return
     */
    public String getRATING_IMAGE_URL() {
        return RATING_IMAGE_URL;
    }

    /**
     *
     * @param RATING_IMAGE_URL
     */
    public void setRATING_IMAGE_URL(String RATING_IMAGE_URL) {
        this.RATING_IMAGE_URL = RATING_IMAGE_URL;
    }

    /**
     *
     * @return
     */
    public String getSNIPPET_TEXT() {
        return SNIPPET_TEXT;
    }

    /**
     *
     * @param SNIPPET_TEXT
     */
    public void setSNIPPET_TEXT(String SNIPPET_TEXT) {
        this.SNIPPET_TEXT = SNIPPET_TEXT;
    }
}
