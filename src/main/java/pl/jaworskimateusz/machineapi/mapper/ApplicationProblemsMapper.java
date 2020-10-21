package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.ApplicationProblemDto;
import pl.jaworskimateusz.machineapi.model.ApplicationProblem;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationProblemsMapper {

    public static List<ApplicationProblemDto> mapToApplicationProblemDtoList(List<ApplicationProblem> appProblems) {
        return appProblems.stream()
                .map(ApplicationProblemsMapper::mapToApplicationProblemDto)
                .collect(Collectors.toList());
    }

    public static ApplicationProblemDto mapToApplicationProblemDto(ApplicationProblem applicationProblem) {
        return new ApplicationProblemDto(
                applicationProblem.getApplicationProblemId(),
                applicationProblem.getKeywords(),
                applicationProblem.getDescription(),
                applicationProblem.getVersionCode(),
                applicationProblem.getUserId()
        );
    }
}
