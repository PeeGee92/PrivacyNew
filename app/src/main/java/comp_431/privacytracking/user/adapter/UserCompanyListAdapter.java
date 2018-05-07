package comp_431.privacytracking.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;
import comp_431.privacytracking.database.company.CompanyDB;
import comp_431.privacytracking.user.UserCreateContractActivity;

public class UserCompanyListAdapter extends RecyclerView.Adapter<UserCompanyListAdapter.ViewHolder> {

    List<CompanyDB> companiesList;
    RecyclerView recyclerView;

    public UserCompanyListAdapter(List<CompanyDB> companiesList) {
        this.companiesList = companiesList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    private final View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            CompanyDB temp = companiesList.get(itemPosition);

            Intent intent = new Intent(view.getContext(), UserCreateContractActivity.class)
                    .putExtra("COMPANY_ID", temp.getCompanyId());

            view.getContext().startActivity(intent);
        }
    };

    @Override
    public UserCompanyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserCompanyListAdapter.ViewHolder holder, int position) {
        holder.tvCompanyName.setText(companiesList.get(position).getCompanyName());
    }

    @Override
    public int getItemCount() {
        if (companiesList != null)
            return companiesList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCompanyName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
        }
    }
}
