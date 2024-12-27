package com.abc.projexct_customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    Context context;
    // pojo  value class -->
    List<jsValueGET> items;
    private john Arun;

     public Adapter(Context context, List<jsValueGET> items,john arun) {
        this.context = context;
        this.items = items;
        this.Arun = arun;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder holder, int position) {
        jsValueGET mc = items.get(position);

        holder.id.setText     ("Id : "+mc.getCustId());
        holder.name.setText   ("Name : "+mc.getCustName());
        holder.number.setText ("ph.No : "+mc.getCustMobile());
        holder.comname.setText("Company Name : "+mc.getCompName());

        if (mc.getActiveStatus().equals("A"))
        {
            holder.edit.setVisibility(View.VISIBLE);
           holder.delete.setImageResource(R.drawable.delete);
        }
        else {
            holder.edit.setVisibility(View.GONE);
            holder.delete.setImageResource(R.drawable.delete_one);
        }

        //step - 3
        holder.delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Arun.inter(mc, holder.delete, holder.edit);
            }
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView id, name, number , comname;
        ImageView edit, delete;
        LinearLayout total;
        @SuppressLint("WrongViewCast")
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            comname = itemView.findViewById(R.id.comname);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            total = itemView.findViewById(R.id.total);
        }
    }

    public interface john {
        void inter(jsValueGET mc, ImageView delete, ImageView edit);


    }
    }



