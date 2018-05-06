package comp_431.privacytracking.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;

public class UserCompanyListAdapter extends RecyclerView.Adapter<UserCompanyListAdapter.ViewHolder> {

    List<String> companiesIdList;
    List<String> companiesNameList;
//    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;

    public UserCompanyListAdapter(List<String> companiesIdList) {
        this.companiesIdList = companiesIdList;
    }

    private final View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);

            // TODO intent
        }
    };

    @Override
    public UserCompanyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_item, parent, false);
        view.setOnClickListener(myOnClickListener);
//        adapter = this;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserCompanyListAdapter.ViewHolder holder, int position) {

        // TODO get names from DB using IDs

        holder.tvCompanyName.setText(companiesNameList.get(position));

    }

    @Override
    public int getItemCount() {
        if (companiesIdList != null)
            return companiesIdList.size();
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
