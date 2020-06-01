package cr.ac.ucr.labconsumeapicovid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import adapters.CountryAdapter;
import api.CountriesInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements CountryAdapter.CountryAdapterListener, Callback<List<Country>> {
    private static final String baseUrl = "https://api.covid19api.com/";
    private CountryAdapter mCountryAdapter;
    private ArrayList<Country> listCountries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView cRecyclerView = findViewById(R.id.countries_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cRecyclerView.setLayoutManager(layoutManager);
        mCountryAdapter = new CountryAdapter(listCountries, this);
        cRecyclerView.setAdapter(mCountryAdapter);
        getCountries();
    }

    public void getCountries(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountriesInterface countriesInterface =  retrofit.create(CountriesInterface.class);
        Call<List<Country>> call = countriesInterface.getAllCountries();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
        if(response.isSuccessful()){
            List<Country> list = response.body();

            for(Country country: list){
                listCountries.add(country);
            }
            mCountryAdapter.notifyDataSetChanged();
        }else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Country>> call, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void OnItemClicked(Country country) {
        Toast.makeText(this, country.getCountry(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CountryInformationActivity.class);
        intent.putExtra("country-id", country.getSlug());
        startActivity(intent);
    }
}
