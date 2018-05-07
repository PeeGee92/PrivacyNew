package comp_431.privacytracking.company.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;

public class UserContractsAdapter extends RecyclerView.Adapter<UserContractsAdapter.ViewHolder> {

    List<String> contractsList;
    RecyclerView recyclerView;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
        }
    };

    public UserContractsAdapter(List<String> contractList){
        this.contractsList=contractList;
    }

    @Override
    public UserContractsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contract_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new UserContractsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserContractsAdapter.ViewHolder holder, int position) {
        // TODO get names from list
        holder.tvUserName.setText(contractsList.get(position));
        holder.tvCompanyName.setText(contractsList.get(position));
    }

    @Override
    public int getItemCount() {
        if (contractsList != null)
            return contractsList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        TextView tvCompanyName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
        }
    }
}
