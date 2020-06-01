package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cr.ac.ucr.labconsumeapicovid.Country;
import cr.ac.ucr.labconsumeapicovid.R;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountriesViewHolder>{
    private ArrayList<Country> mCountries;
    private Context mContext;


    public CountryAdapter(ArrayList<Country> countries, Context context){
        mCountries = countries;
        mContext = context;
    }
    @Override
    public CountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_list_item, parent, false);
        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountriesViewHolder holder, int position) {
        final Country country = mCountries.get(position);
        holder.nameCountry.setText(country.getCountry());
        holder.itemLayou.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mContext instanceof  CountryAdapterListener){
                    ((CountryAdapterListener) mContext).OnItemClicked(country);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public interface CountryAdapterListener {
        void OnItemClicked(Country country);
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder{

        public TextView nameCountry;
        public ConstraintLayout itemLayou;
        CountriesViewHolder(View itemView){
            super(itemView);
            this.nameCountry =  (TextView) itemView.findViewById(R.id.tv_name);
            this.itemLayou = (ConstraintLayout) itemView.findViewById(R.id.cl_countries_list_item);
        }
    }
}
