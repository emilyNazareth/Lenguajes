package api;

import java.util.List;

import cr.ac.ucr.labconsumeapicovid.ByCountry;
import cr.ac.ucr.labconsumeapicovid.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CountriesInterface {
    @GET("countries")
    Call <List<Country>>  getAllCountries();

    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @GET("country/{id}/status/confirmed")
    Call <List<ByCountry>> getCountyInformation(@Path("id")  String id, @Query("from") String from, @Query("to")  String to);


}
