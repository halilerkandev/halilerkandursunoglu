package dev.halilerkan.week4.dao.member;

import dev.halilerkan.week4.common.exception.NoContentException;
import dev.halilerkan.week4.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Long create(MemberEntity entity) {
        return memberJpaRepository.save(entity).getId();
    }

    @Override
    public List<MemberEntity> fetchAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public MemberEntity fetchById(Long id) {
        return memberJpaRepository
                .findById(id)
                .orElseThrow(NotFoundException.withSupplier());
    }

    @Override
    public Long update(Long id, MemberEntity entity) {
        MemberEntity memberEntity = memberJpaRepository.findById(id).orElseThrow(NotFoundException.withSupplier());
        memberEntity.setUpdatedDate(Instant.now());
        memberEntity.setFirstName(entity.getFirstName());
        memberEntity.setLastName(entity.getLastName());
        memberJpaRepository.save(memberEntity);
        return memberEntity.getId();
    }

    @Override
    public void delete(Long id) {
        MemberEntity memberEntity = memberJpaRepository.findById(id).orElseThrow(NoContentException.withSupplier());
        memberJpaRepository.deleteById(memberEntity.getId());
    }

}
