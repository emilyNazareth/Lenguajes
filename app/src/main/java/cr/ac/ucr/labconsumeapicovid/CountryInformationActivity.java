package cr.ac.ucr.labconsumeapicovid;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapters.ByCountryAdapter;
import api.CountriesInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryInformationActivity extends AppCompatActivity
        implements ByCountryAdapter.CountryAdapterListener, Callback<List<ByCountry>> {

    private static final String baseUrl = "https://api.covid19api.com/";
    private ByCountryAdapter  mCountryAdapter;
    private ArrayList<ByCountry> list_countryInformation = new ArrayList<>();
    public TextView nameCountry;
    private String countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_information);
        countryId = getIntent().getStringExtra("country-id");
        getCountriesInfo();
    }

    public void getCountriesInfo(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountriesInterface countriesInterface =  retrofit.create(CountriesInterface.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        Calendar current = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(current.getTime()));
        System.out.println(simpleDateFormat.format(yesterday.getTime()));


        Call<List<ByCountry>> call = countriesInterface.getCountyInformation(countryId,
                simpleDateFormat.format(yesterday.getTime()),
                simpleDateFormat.format(current.getTime()));
        call.enqueue(this);
    }


    @Override
    public void OnItemClicked(ByCountry byCountry) {
        Toast.makeText(this, byCountry.getCountry(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<List<ByCountry>> call, Response<List<ByCountry>> response) {

        if(response.isSuccessful()){
            List<ByCountry> list = response.body();
            this.nameCountry =  (TextView) findViewById(R.id.tv_country_name);
             for(ByCountry byCountry: list){
                 String info = "";
                 info +=  byCountry.getCountry() + "\n";
                 info += "Casos: " + byCountry.getCases() + "\n";
                 nameCountry.append(info);
             }
        }else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<ByCountry>> call, Throwable t) {
        t.printStackTrace();
    }
}
