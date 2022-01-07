package dev.halilerkan.movie.mapper;

import dev.halilerkan.movie.dto.member.MemberCreateDTO;
import dev.halilerkan.movie.dto.member.MemberResponseDTO;
import dev.halilerkan.movie.dto.member.MemberUpdateDTO;
import dev.halilerkan.movie.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface MemberMapper {
    MemberEntity mapFromMemberCreateDTOToMemberEntity(MemberCreateDTO memberCreateDTO);

    MemberResponseDTO mapFromMemberEntityToMemberResponseDTO(MemberEntity memberEntity);

    void mapFromMemberUpdateDTOToMemberEntity(
            MemberUpdateDTO memberUpdateDTO,
            @MappingTarget MemberEntity memberEntity
    );
}
