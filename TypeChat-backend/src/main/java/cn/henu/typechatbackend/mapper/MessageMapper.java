package cn.henu.typechatbackend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.henu.typechatbackend.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    //撤回消息
    @Update("update db_message " +
            "set is_withdrawn=1 " +
            "where id=#{id}")
    void withdrawMessage(String id);
}

