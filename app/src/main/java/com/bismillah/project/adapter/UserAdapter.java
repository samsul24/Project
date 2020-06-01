package com.bismillah.project.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.bismillah.project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends AbstractItem<UserAdapter, UserAdapter.ViewHolder>{
 private String avatar,user;

    public UserAdapter(String avatar, String user) {
        this.avatar = avatar;
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder((v));
    }

    @Override
    public int getType() {
        return R.id.recycler_list;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_user;

    }

    public class ViewHolder extends FastAdapter.ViewHolder<UserAdapter> {
         ImageView foto;
         TextView username;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.userView);
            foto = itemView.findViewById(R.id.dataImage);
        }

        @Override
        public void bindView(UserAdapter item, List<Object> payloads) {
            username.setText(item.user);
            Picasso.get().load(item.avatar).into(foto);
        }

        @Override
        public void unbindView(UserAdapter item) {
            foto.setImageBitmap(null);
            username.setText(null);
        }
    }
}

