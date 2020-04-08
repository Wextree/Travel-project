package com.dao;

import com.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    /**
     * 通过id查询成员信息
     * @param id
     * @return
     */
    @Select("select * from member where id = #{id}")
    public Member findById(Integer id);
}
