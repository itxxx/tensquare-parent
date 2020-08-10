package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 交友数据访问层
 */
public interface FriendDao extends JpaRepository<Friend,String> {
    /**
     * 根据用户ID与被关注用户ID查询记录个数
     * @param userid
     * @param friendid
     * @return
     */
    @Query(value = "select count(*) from tb_friend f where f.userid=?1 and f.friendid=?2",nativeQuery = true)
    public int selectCount(String userid,String friendid);
    /**
     * 更新为互相喜欢
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query(value ="update tb_friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2",nativeQuery = true)
    public void updateLike(String userid,String friendid,String islike);

    /**
     * 删除喜欢
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query(value ="delete from tb_friend f where f.userid=?1 and f.friendid=?2",nativeQuery = true)
    public void deleteFriend(String userid,String friendid);
}
