package comp_431.privacytracking.company.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;

public class Request1Adapter extends RecyclerView.Adapter<Request1Adapter.ViewHolder> {


    List<String> companiesList;
    RecyclerView recyclerView;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            LoginActivity.dbmanag.CompanyrequestCompany(LoginActivity.currentUser.getUid(),companiesList.get(itemPosition));
        }
    };
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    public Request1Adapter(List<String> companies){
        this.companiesList = companies;
    }

    @Override
    public Request1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new Request1Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Request1Adapter.ViewHolder holder, int position) {
        // TODO get names from list
        holder.tv.setText(companiesList.get(position));

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
