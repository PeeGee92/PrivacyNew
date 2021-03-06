package comp_431.privacytracking.user.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import comp_431.privacytracking.LoginActivity;
import comp_431.privacytracking.R;
import comp_431.privacytracking.database.company.CompanyDB;

public class UserCompanyListAdapter extends RecyclerView.Adapter<UserCompanyListAdapter.ViewHolder> {

    List<CompanyDB> companiesList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    AlertDialog dialog;

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
            final CompanyDB temp = companiesList.get(itemPosition);

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            final Context context = builder.getContext();
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View dialogView = inflater.inflate(R.layout.user_add_contract_dialog, null, false);

            builder.setView(dialogView);
            builder.setTitle("Create Contract");
            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LoginActivity.dbmanag.UserJoinsCompany(LoginActivity.currentUser.getUid(), temp.getCompanyId());

                    dismissDialog();

                    companiesList.remove(temp);
                    adapter = new UserCompanyListAdapter(companiesList);
                    recyclerView.setAdapter(adapter);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismissDialog();
                }
            });

            dialog = builder.create();
            dialog.show();
        }
    };

    private void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    public UserCompanyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        view.setOnClickListener(myOnClickListener);
        adapter = this;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserCompanyListAdapter.ViewHolder holder, int position) {
        holder.tv.setText(companiesList.get(position).getCompanyName());
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
