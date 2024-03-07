package cn.henu.typechat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Message> messages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewAvatar;
        public TextView messageText;
        public TextView messageTime;  // 新添加，用于显示消息时间

        public ImageView imageViewAvatar2;
        public TextView messageText2;
        public TextView messageTime2;  // 新添加，用于显示消息时间

        public ViewHolder(View view) {
            super(view);
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
            messageText = view.findViewById(R.id.textViewMessage);
            messageTime = view.findViewById(R.id.textViewMessageTime);  // 新添加

            imageViewAvatar2 = view.findViewById(R.id.imageViewAvatar2);
            messageText2 = view.findViewById(R.id.textViewMessage2);
            messageTime2 = view.findViewById(R.id.textViewMessageTime2);  // 新添加
        }
    }



    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);

        // 根据消息类型设置头像和位置
        if (!message.isUserMessage()) {
            holder.imageViewAvatar.setVisibility(View.VISIBLE);
            holder.messageText.setVisibility(View.VISIBLE);
            holder.messageTime.setVisibility(View.VISIBLE);  // 新添加

            holder.imageViewAvatar2.setVisibility(View.GONE);
            holder.messageText2.setVisibility(View.GONE);
            holder.messageTime2.setVisibility(View.GONE);  // 新添加

            holder.imageViewAvatar.setImageResource(R.drawable.sever);
            holder.imageViewAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // 将用户的消息放在右边
            holder.messageText.setBackgroundResource(R.drawable.bg_server_message);
            holder.messageText.setText(message.getContent());
            holder.messageTime.setText(message.getTime());  // 设置消息时间
        } else {
            holder.imageViewAvatar.setVisibility(View.GONE);
            holder.messageText.setVisibility(View.GONE);
            holder.messageTime.setVisibility(View.GONE);  // 新添加

            holder.imageViewAvatar2.setVisibility(View.VISIBLE);
            holder.messageText2.setVisibility(View.VISIBLE);
            holder.messageTime2.setVisibility(View.VISIBLE);  // 新添加

            holder.imageViewAvatar2.setImageResource(R.drawable.avtar);
            holder.imageViewAvatar2.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // 将服务器的消息放在左边
            holder.messageText2.setBackgroundResource(R.drawable.bg_user_message);
            holder.messageText2.setText(message.getContent());
            holder.messageTime2.setText(message.getTime());  // 设置消息时间
        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

}
