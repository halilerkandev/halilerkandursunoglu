package dev.halilerkan.week4.dao.rate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateDaoImpl implements RateDao {

    private final RateJpaRepository rateJpaRepository;

    @Override
    public void rateMovie(RateEntity entity) {
        Optional<RateEntity> optionalRateEntity = rateJpaRepository
                .findByMemberIdAndMovieId(entity.getMember().getId(), entity.getMovie().getId());
        if(optionalRateEntity.isPresent()) {
            RateEntity rateEntity = optionalRateEntity.get();
            rateEntity.setPoint(entity.getPoint());
            rateJpaRepository.save(rateEntity);
        } else {
            rateJpaRepository.save(entity);
        }
    }

    @Override
    public List<RateEntity> fetchByMovieId(Long movieId) {
        return rateJpaRepository.findAllByMovie_Id(movieId);
    }

    @Override
    public List<RateEntity> fetchByMemberId(Long memberId) {
        return rateJpaRepository.findAllByMember_Id(memberId);
    }

}
