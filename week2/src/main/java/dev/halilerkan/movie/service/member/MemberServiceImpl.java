package dev.halilerkan.movie.service.member;

import dev.halilerkan.movie.dto.member.MemberCreateDTO;
import dev.halilerkan.movie.dto.member.MemberResponseDTO;
import dev.halilerkan.movie.dto.member.MemberUpdateDTO;
import dev.halilerkan.movie.dto.rating.RatingRequestDTO;
import dev.halilerkan.movie.dto.rating.RatingResponseDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.entity.MovieEntity;
import dev.halilerkan.movie.entity.RatingEntity;
import dev.halilerkan.movie.exception.NoContentException;
import dev.halilerkan.movie.mapper.MemberMapper;
import dev.halilerkan.movie.mapper.RatingMapper;
import dev.halilerkan.movie.repository.MemberRepository;
import dev.halilerkan.movie.service.movie.MovieService;
import dev.halilerkan.movie.service.rating.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final RatingService ratingService;
    private final MovieService movieService;
    private final RatingMapper ratingMapper;

    @Override
    public MemberResponseDTO createMember(MemberCreateDTO memberCreateDTO) {
        MemberEntity memberEntity = memberMapper
                .mapFromMemberCreateDTOToMemberEntity(memberCreateDTO);

        memberRepository.save(memberEntity);

        return memberMapper.mapFromMemberEntityToMemberResponseDTO(memberEntity);
    }

    @Override
    public List<MemberResponseDTO> fetchMemberList() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::mapFromMemberEntityToMemberResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MemberEntity fetchMemberById(Long memberId) throws NoContentException {
        return memberRepository
                .findById(memberId)
                .orElseThrow(NoContentException.buildWithSupplier());
    }

    @Override
    public MemberResponseDTO updateMemberById(
            Long memberId,
            MemberUpdateDTO memberUpdateDTO
    ) throws NoContentException {
        MemberEntity memberEntity = memberRepository
                .findById(memberId)
                .orElseThrow(NoContentException.buildWithSupplier());

        memberMapper.mapFromMemberUpdateDTOToMemberEntity(memberUpdateDTO, memberEntity);

        memberEntity.setLastModifiedDate(Instant.now());

        memberRepository.save(memberEntity);

        return memberMapper.mapFromMemberEntityToMemberResponseDTO(memberEntity);
    }

    @Override
    public void deleteMemberById(Long memberId) throws NoContentException {
        MemberEntity memberEntity = memberRepository
                .findById(memberId)
                .orElseThrow(NoContentException.buildWithSupplier());

        memberRepository.deleteById(memberEntity.getMemberId());
    }

    @Override
    public RatingResponseDTO rateMovie(
            Long memberId,
            Long movieId,
            RatingRequestDTO ratingRequestDTO
    ) throws NoContentException {
        Optional<RatingEntity> ratingEntity = ratingService.findByMemberIdAndMovieId(memberId, movieId);
        if(ratingEntity.isEmpty()) {
            MemberEntity memberEntity = fetchMemberById(memberId);
            MovieEntity movieEntity = movieService.fetchMovieById(movieId);
            return ratingMapper.mapFromRatingEntityToRatingResponseDTO(
                    ratingService.createRating(memberEntity, movieEntity, ratingRequestDTO));
        } else {
            return ratingMapper.mapFromRatingEntityToRatingResponseDTO(
                    ratingService.updateRating(ratingEntity.get(), ratingRequestDTO.getPoint()));
        }
    }
}
