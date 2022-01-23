package dev.halilerkan.week4.dao.member;

import java.util.List;

public interface MemberDao {

    Long create(MemberEntity entity);

    List<MemberEntity> fetchAll();

    MemberEntity fetchById(Long id);

    Long update(Long id, MemberEntity entity);

    void delete(Long id);

}
