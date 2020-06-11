package com.example.strooper.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strooper.Clases.Utilidades;
import com.example.strooper.Clases.Vo.AvatarVo;
import com.example.strooper.R;

import java.util.List;

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ViewHolderAvatar> {

    private List<AvatarVo> listaAvatars;
    private View vista;
    private int posMarcada = 0;

    public AdaptadorAvatar(List<AvatarVo> listaAvatars) {
        this.listaAvatars = listaAvatars;
    }

    @NonNull
    @Override
    public ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_avatar,parent,false);
        return new ViewHolderAvatar(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvatar holder, final int position) {
        holder.imgAvatar.setImageResource(listaAvatars.get(position).getAvatarId());

        final int pos=position;

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posMarcada = pos;
                Utilidades.avatarSeleccion=listaAvatars.get(pos);
                Utilidades.avatarIdSeleccion = pos+1;
                notifyDataSetChanged();
            }
        });

        if (Utilidades.avatarIdSeleccion == 0){
            if(posMarcada==position){
                holder.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
            }else{
                holder.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));
            }
        }else{
            if (Utilidades.avatarIdSeleccion-1 == pos){
                holder.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
            }else{
                holder.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));
            }
        }


    }

    @Override
    public int getItemCount() {
        return listaAvatars.size();
    }

    public class ViewHolderAvatar extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imgAvatar;
        private TextView barraSeleccion;

        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardAvatar);
            imgAvatar = itemView.findViewById(R.id.idAvatar);
            barraSeleccion = itemView.findViewById(R.id.barraSeleccionId);
        }
    }
}
