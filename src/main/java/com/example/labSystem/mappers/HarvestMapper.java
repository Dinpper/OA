package com.example.labSystem.mappers;

import com.example.labSystem.domain.Harvest;
import com.example.labSystem.dto.HarvestDto;
import com.example.labSystem.dto.MeetingsDto;
import com.example.labSystem.dto.PageRequestQto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HarvestMapper {
    Integer insert(Harvest harvest);

    Integer queryCountByPage(PageRequestQto qto);

    List<HarvestDto> queryHarvestByPage(PageRequestQto qto);

    Harvest queryHarvestById(@Param("harvestId") Integer harvestId);
}
