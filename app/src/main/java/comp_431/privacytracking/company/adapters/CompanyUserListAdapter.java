package comp_431.privacytracking.company.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp_431.privacytracking.R;
import comp_431.privacytracking.company.UserContractsActivity;

public class CompanyUserListAdapter extends RecyclerView.Adapter<CompanyUserListAdapter.ViewHolder> {

    List<String> userList;
    RecyclerView recyclerView;

    private final View.OnClickListener myOnClickListener = new View.OnClickListener()  {
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            String id = userList.get(itemPosition);
            //TODO: Create a new class for showing all the contrats with one user.
            view.getContext().startActivity(new Intent(view.getContext(), UserContractsActivity.class).putExtra("Id",id));
        }
    };

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }


    public CompanyUserListAdapter(List<String> userIDs){
        this.userList = userIDs;
    }

    @Override
    public CompanyUserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyUserListAdapter.ViewHolder holder, int position) {
        holder.tv.setText(userList.get(position));
    }

    @Override
    public int getItemCount() {
        if (userList != null)
            return userList.size();
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
