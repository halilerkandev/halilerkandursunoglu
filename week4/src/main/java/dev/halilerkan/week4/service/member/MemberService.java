package dev.halilerkan.week4.service.member;

import java.util.List;

public interface MemberService {

    Long create(Member member);

    List<Member> fetchAll();

    Member fetchById(Long id);

    Long update(Long id, Member member);

    void delete(Long id);

}
