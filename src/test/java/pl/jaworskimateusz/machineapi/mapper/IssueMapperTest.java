package pl.jaworskimateusz.machineapi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jaworskimateusz.machineapi.dto.IssueDto;
import pl.jaworskimateusz.machineapi.model.Issue;
import pl.jaworskimateusz.machineapi.model.Machine;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class IssueMapperTest {

    @Test
    public void map_to_issue_dto_list() {
        Issue issue1 = new Issue(1L, "error","description","solution","sign");
        Issue issue2 = new Issue(2L, "cpu","description 2","-","-");
        List<Issue> issues = Arrays.asList(issue1, issue2);

        List<IssueDto> issueDtoList = IssueMapper.mapToIssueDtoList(issues);

        assertEquals(issueDtoList.size(), 2);
        assertEquals(issueDtoList.get(0).getIssueId(),1L);
        assertEquals(issueDtoList.get(1).getKeywords(),"cpu");
    }

    @Test
    public void map_issue_to_issue_dto_without_machine() {
        Issue issue = new Issue(1L, "error","description","solution","sign");

        IssueDto issueDto = IssueMapper.mapToIssueDto(issue);

        assertEquals(issueDto.getIssueId(),1L);
        assertEquals(issueDto.getKeywords(),"error");
        assertEquals(issueDto.getDescription(),"description");
        assertEquals(issueDto.getSolution(),"solution");
        assertEquals(issueDto.getWorkerSignature(),"sign");
        assertNull(issueDto.getMachineId());
    }

    @Test
    public void map_issue_to_issue_dto_with_machine() {
        Issue issue = new Issue(1L, "error","description","solution","sign");
        Machine machine = new Machine(1L, "Arduino","CODE12","description","image_url","-");
        machine.addIssue(issue);

        IssueDto issueDto = IssueMapper.mapToIssueDto(issue);

        assertEquals(issueDto.getIssueId(),1L);
        assertEquals(issueDto.getKeywords(),"error");
        assertEquals(issueDto.getDescription(),"description");
        assertEquals(issueDto.getSolution(),"solution");
        assertEquals(issueDto.getWorkerSignature(),"sign");
        assertEquals(issueDto.getMachineId(),1L);
    }
}