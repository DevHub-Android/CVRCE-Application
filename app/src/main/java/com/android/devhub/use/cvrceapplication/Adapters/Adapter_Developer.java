package com.android.devhub.use.cvrceapplication.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.devhub.use.cvrceapplication.R;

public class Adapter_Developer extends RecyclerView.Adapter<Adapter_Developer.ImageHolder>{
    private  int[] images;
    public Adapter_Developer(int[] images)
    {
        this.images = images;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater
               .from(parent.getContext()).inflate(R.layout.developer_layout,parent,false
               );
       Adapter_Developer.ImageHolder imageHolder = new Adapter_Developer.ImageHolder(view);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
            int image_id = images[position];
            holder.devPhoto.setImageResource(image_id);

            if(image_id==R.drawable.rakesh)
            {
                holder.name.setText("Name : Rakesh Swain");
                holder.branch.setText("Branch : CSE");
                holder.email.setText("Email : swain.rakesh131@gmail.com");
            }else if(image_id==R.drawable.sonali){
                holder.name.setText("Name : Sonali Dash");
                holder.branch.setText("Branch : CSE");
                holder.email.setText("Email : sonali031998@gmail.com");
        }else if(image_id==R.drawable.jitu)
            {
                holder.name.setText("Name : Jitu Nayak");
                holder.branch.setText("Branch : CSE");
                holder.email.setText("Email :jitunayak715@gmail.com ");
            }else if (image_id == R.drawable.akash)
            {
                holder.name.setText("Name : Aakash Kumar Nanda");
                holder.branch.setText("Branch : CSE");
                holder.email.setText("Email : aakash.nanda99@gmail.com");
            }
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
    public static class ImageHolder extends RecyclerView.ViewHolder{
        ImageView devPhoto;
        TextView name,branch,email;
        public ImageHolder(View itemView) {
            super(itemView);
            devPhoto = itemView.findViewById(R.id.devPhoto);
            name = itemView.findViewById(R.id.devName);
            branch = itemView.findViewById(R.id.devBranch);
            email = itemView.findViewById(R.id.devEmail);

        }
    }

}
