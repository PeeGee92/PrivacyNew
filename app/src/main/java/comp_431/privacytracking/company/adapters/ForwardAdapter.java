package comp_431.privacytracking.company.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;

public class ForwardAdapter extends RecyclerView.Adapter<ForwardAdapter.ViewHolder> {


    List<String> companiesList;
    RecyclerView recyclerView;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            LoginActivity.dbmanag.CompanyForwardCompany(LoginActivity.currentUser.getUid(),companiesList.get(itemPosition));
            Toast.makeText(view.getContext(), "Data Forwarded to Company",
                    Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    public ForwardAdapter(List<String> companies){
        this.companiesList = companies;
    }

    @Override
    public ForwardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new ForwardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForwardAdapter.ViewHolder holder, int position) {
        String compName = LoginActivity.db.CompanyDAO().getCompanyById(companiesList.get(position)).getCompanyName();
        holder.tv.setText(compName);

    }

    @Override
    public int getItemCount() {
        if (companiesList != null)
            return companiesList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tvText);
        }
    }
}
