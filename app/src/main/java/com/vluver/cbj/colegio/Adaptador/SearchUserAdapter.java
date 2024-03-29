package com.vluver.cbj.colegio.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vluver.cbj.colegio.PerfilPersona;
import com.vluver.cbj.colegio.R;
import com.vluver.cbj.colegio.model.SearchUser;
import com.vluver.cbj.colegio.utilidades.GlideLoadImages;

import java.util.List;


/**
 * Created by Vlover on 21/04/2018.
 */

public class SearchUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<SearchUser> data;

    public SearchUserAdapter(Context context, List<SearchUser> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.adaptador_busqueda_personas, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        SearchUser current=data.get(position);
        myHolder.textUserName.setText(current.getUserName());

        myHolder.tipo_area.setText(current.getUserTipo());
        myHolder.area_user.setText(current.getUserArea());
        GlideLoadImages.loadAvatar(context, current.getUserTipo(),myHolder.imageUserAvatar);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textUserName;

        TextView tipo_area;

        TextView area_user;
        ImageView imageUserAvatar;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textUserName = (TextView) itemView.findViewById(R.id.search_user_name);

            tipo_area = (TextView) itemView.findViewById(R.id.tipo_area);
            area_user = (TextView) itemView.findViewById(R.id.area_user);
            imageUserAvatar = (ImageView) itemView.findViewById(R.id.circleImageView_search_user);

            itemView.setOnClickListener(this);
        }


        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PerfilPersona.class);
            intent.putExtra("userUID", data.get((getAdapterPosition())).getUserUID());
            intent.putExtra("userName", data.get((getAdapterPosition())).getUserName());
            intent.putExtra("userTipo", data.get((getAdapterPosition())).getUserTipo());
            intent.putExtra("userArea",data.get((getAdapterPosition())).getUserArea());
            //intent.putExtra("statefollower",data.get((getAdapterPosition())).statefollow);
            context.startActivity(intent);

        }


    }
}
