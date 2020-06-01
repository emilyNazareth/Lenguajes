package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cr.ac.ucr.labconsumeapicovid.ByCountry;
import cr.ac.ucr.labconsumeapicovid.R;

public class ByCountryAdapter extends RecyclerView.Adapter<ByCountryAdapter.CountriesViewHolder>{
    private ArrayList<ByCountry> myCountry;
    private Context mContext;


    public ByCountryAdapter(ArrayList<ByCountry> country, Context context){
        myCountry = country;
        mContext = context;
    }
    @Override
    public ByCountryAdapter.CountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_country_information, parent, false);
        return new ByCountryAdapter.CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        final ByCountry byCountry = myCountry.get(position);
        holder.nameCountry.setText(byCountry.getCountry());
        holder.cases.setText(byCountry.getCases());
        holder.city.setText("test");
        System.out.println("EEEEEE");
        holder.itemLayou.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mContext instanceof CountryAdapter.CountryAdapterListener){
                    ((ByCountryAdapter.CountryAdapterListener) mContext).OnItemClicked(byCountry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCountry.size();
    }

    public interface CountryAdapterListener {
        void OnItemClicked(ByCountry byCountry);
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder{

        public TextView nameCountry;
        public TextView code;
        public TextView province;
        public TextView city;
        public TextView cityCode;
        public TextView lat;
        public TextView lon;
        public TextView cases;
        public TextView status;
        public TextView date;
        public ConstraintLayout itemLayou;
        CountriesViewHolder(View itemView){
            super(itemView);
            this.nameCountry =  (TextView) itemView.findViewById(R.id.tv_name);
           // this.cases =  (TextView) itemView.findViewById(R.id.tv_cases);
            this.itemLayou = (ConstraintLayout) itemView.findViewById(R.id.cl_countries_info);
        }
    }
}
