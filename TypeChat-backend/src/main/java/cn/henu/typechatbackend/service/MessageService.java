package cn.henu.typechatbackend.service;


import cn.henu.typechatbackend.entity.GroupMessage;
import cn.henu.typechatbackend.entity.Message;

public interface MessageService {
    void storeMessage(Message message);
    void StoreGroupMessage(GroupMessage groupMessage);
    void withdrawMessage(String id);
    void withdrawGroupMessage(String id);

    void updateMsgWithdrawnSession(String sessionId);

    void updateGroupMsgWithdrawnSession(String groupId,String name);
}
