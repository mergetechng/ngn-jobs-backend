package com.mergetechng.jobs.services.api;


import com.mergetechng.jobs.commons.dto.*;
import com.mergetechng.jobs.commons.util.CycleAvoidingMappingContext;
import com.mergetechng.jobs.entities.ApiLogRequest;
import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Job;
import com.mergetechng.jobs.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobApiGeneralMapper {
    JobApiGeneralMapper INSTANCE = Mappers.getMapper(JobApiGeneralMapper.class);

    UserDto userToUserDto(User user, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    User userDtoToUser(UserDto user, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    User NewJobSeekerDtoToUser(NewJobSeekerDto user, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    ApiLogRequestDto apiLogRequestToApiLogRequestDto(ApiLogRequest apiLogRequest, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    ApiLogRequest apiLogRequestDtoToApiLogRequest(ApiLogRequestDto apiLogRequest, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Group1 groupDtoToGroup(Group1Dto group1Dto , @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) ;

    Group1Dto groupToGroupDto(Group1 group1 , @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) ;

    Job jobToJobDto(JobDto jobDto , @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) ;

    Job jobDtoToJob(JobDto job , @Context CycleAvoidingMappingContext cycleAvoidingMappingContext) ;

}