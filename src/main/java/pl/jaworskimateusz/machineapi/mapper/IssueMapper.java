package pl.jaworskimateusz.machineapi.mapper;

import pl.jaworskimateusz.machineapi.dto.IssueDto;
import pl.jaworskimateusz.machineapi.model.Issue;

import java.util.List;
import java.util.stream.Collectors;

public class IssueMapper {

    public static List<IssueDto> mapToIssueDtoList(List<Issue> issues) {
        return issues.stream()
                .map(IssueMapper::mapToIssueDto)
                .collect(Collectors.toList());
    }

    public static IssueDto mapToIssueDto(Issue issue) {
        return new IssueDto(
                issue.getIssueId(),
                issue.getKeywords(),
                issue.getDescription(),
                issue.getSolution(),
                issue.getWorkerSignature(),
                issue.getMachine().getMachineId()
        );
    }
}
