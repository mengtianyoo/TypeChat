package cn.henu.typechatbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.henu.typechatbackend.controller.FriendController;
import cn.henu.typechatbackend.controller.GroupController;
import cn.henu.typechatbackend.entity.Member;
import cn.henu.typechatbackend.service.MessageServiceImp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper extends BaseMapper<Member> {

    @Update("update db_member " +
            "set unread=0 " +
            "where member_id=#{memberId} and group_id=#{groupId}")
    void clearUnread(GroupController.MemberParam memberParam);

    ///更新状态
    @Update("update db_member " +
            "set last_message=#{lastMessage},unread=unread+1,update_time=NOW() " +
            "where group_id=#{sessionId} and is_deleted=0")
    void updateFriend(MessageServiceImp.TestParam param);

    @Update("update db_member " +
            "set last_message=#{lastMessage} " +
            "where group_id=#{sessionId} and is_deleted=0")
    void updateSession(MessageServiceImp.TestParam param);
}
