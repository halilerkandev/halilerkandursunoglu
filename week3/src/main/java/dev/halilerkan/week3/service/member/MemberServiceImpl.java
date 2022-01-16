package dev.halilerkan.week3.service.member;

import dev.halilerkan.week3.dao.member.MemberDao;
import dev.halilerkan.week3.dao.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    //private final RedisTemplate<Long, Movie> movieRedisTemplate;

    @Override
    public Long create(Member member) {
        MemberEntity entity = member.toMemberEntity();
        return memberDao.create(entity);
    }

    @Override
    public List<Member> fetchAll() {
        List<MemberEntity> entityList = memberDao.fetchAll();
        return entityList
                .stream()
                .map(Member::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Member fetchById(Long id) {
        MemberEntity entity = memberDao.fetchById(id);
        return Member.valueOf(entity);
    }

    @Override
    public Long update(Long id, Member member) {
        MemberEntity entity = member.toMemberEntity();
        return memberDao.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        memberDao.delete(id);
    }

}
