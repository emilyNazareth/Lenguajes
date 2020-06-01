package cr.ac.ucr.labconsumeapicovid;

public class ByCountry {


    private String Country;
    private String CountryCode;
    private String Province;
    private String City;
    private String CityCode;
    private String Lat;
    private String Lon;
    private Integer Cases;
    private String Status;
    private String Date;


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        this.CountryCode = countryCode;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        this.Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        this.CityCode = cityCode;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        this.Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        this.Lon = lon;
    }

    public Integer getCases() {
        return Cases;
    }

    public void setCases(Integer cases) {
        this.Cases = cases;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }


}
