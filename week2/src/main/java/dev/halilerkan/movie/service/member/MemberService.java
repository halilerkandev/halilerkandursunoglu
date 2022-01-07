package dev.halilerkan.movie.service.member;

import dev.halilerkan.movie.dto.member.MemberCreateDTO;
import dev.halilerkan.movie.dto.member.MemberResponseDTO;
import dev.halilerkan.movie.dto.member.MemberUpdateDTO;
import dev.halilerkan.movie.dto.rating.RatingRequestDTO;
import dev.halilerkan.movie.dto.rating.RatingResponseDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import dev.halilerkan.movie.exception.NoContentException;

import java.util.List;

public interface MemberService {
    MemberResponseDTO createMember(MemberCreateDTO memberCreateDTO);

    List<MemberResponseDTO> fetchMemberList();

    MemberEntity fetchMemberById(Long memberId) throws NoContentException;

    MemberResponseDTO updateMemberById(Long memberId, MemberUpdateDTO memberUpdateDTO) throws NoContentException;

    void deleteMemberById(Long memberId) throws NoContentException;

    RatingResponseDTO rateMovie(
            Long memberId,
            Long movieId,
            RatingRequestDTO ratingRequestDTO
    ) throws NoContentException;
}
