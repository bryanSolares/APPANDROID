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
import com.example.strooper.Clases.Vo.JugadorVO;
import com.example.strooper.R;

import java.util.List;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.ViewHolderJugador> implements  View.OnClickListener{

    private List<JugadorVO> listaJugadores;
    private View vista;
    private View.OnClickListener listener;

    public AdaptadorJugador(List<JugadorVO> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    @NonNull
    @Override
    public ViewHolderJugador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_jugador,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolderJugador(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJugador holder, final int position) {
        holder.imgAvatar.setImageResource(Utilidades.listaAvatars.get(listaJugadores.get(position).getAvatar()-1).getAvatarId());
        holder.txtNombre.setText(listaJugadores.get(position).getNombre());

        if (listaJugadores.get(position).getGenero().equals("M")) {
            holder.txtGenero.setText("Masculino");
        } else {
            holder.txtGenero.setText("Femenino");
        }

    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
         listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolderJugador extends RecyclerView.ViewHolder {

        private ImageView imgAvatar;
        private TextView txtNombre, txtGenero;

        public ViewHolderJugador(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.idAvatar);
            txtNombre = itemView.findViewById(R.id.idNombre);
            txtGenero = itemView.findViewById(R.id.idGenero);
        }
    }
}
