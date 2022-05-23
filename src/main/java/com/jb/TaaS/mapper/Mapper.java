package com.jb.TaaS.mapper;

import java.util.List;

public interface Mapper<DAO, DTO> {
    DAO toDaO(DTO dto);

    DTO toDto(DAO dao);

    List<DAO> toDaoList(List<DTO> dtos);

    List<DTO> toDtoList(List<DAO> daos);
}
