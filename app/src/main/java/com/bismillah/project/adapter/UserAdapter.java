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
   private String avatar,username1,url1;

    public UserAdapter(String avatar, String username, String url) {
        this.avatar = avatar;
        this.username1 = username;
        this.url1 = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username1;
    }

    public void setUsername(String username) {
        this.username1 = username;
    }

    public String getUrl() {
        return url1;
    }

    public void setUrl(String url) {
        this.url1 = url;
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
        private ImageView foto;
        private TextView username,url;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.nameView);
            url = itemView.findViewById(R.id.urlView);
            foto = itemView.findViewById(R.id.dataImage);
        }

        @Override
        public void bindView(UserAdapter item, List<Object> payloads) {
            Picasso.get().load(item.avatar).into(foto);
            username.setText(item.username1);
            url.setText(item.url1);

        }

        @Override
        public void unbindView(UserAdapter item) {
            foto.setImageBitmap(null);
            username.setText(null);
            url.setText(null);
        }
    }
}

