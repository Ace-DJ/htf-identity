package be.springtheory.htf.service;

import be.springtheory.htf.model.Case;
import be.springtheory.htf.model.CaseResult;
import be.springtheory.htf.model.Suspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestService {
    private static final String BASE_URL="https://htf.bewire.org";
    private static final String SUSPECTS_URL=BASE_URL+"/suspects";
    private static final String CASE_URL=BASE_URL+"/case?teamId={teamId}";
    private static final String CASE_CLOSED_URL=BASE_URL+"/case/{caseId}/close?suspectId={suspectId}";
    private final RestTemplate template;
    private final String teamId;

    @Autowired
    public RestService(RestTemplate template, String teamId) {
        this.template = template;
        this.teamId = teamId;
    }


    public List<Suspect> getSuspects() {
     var response = template.getForEntity(SUSPECTS_URL,Suspect[].class);
     return List.of(response.getBody());
    }

    public Case getCase() {
        final Map<String,String> params = new HashMap<>();
        params.put("teamId",teamId);
        var response = template.postForEntity(CASE_URL, null, Case.class, params);
        return response.getBody();
    }

    public CaseResult closeCase(String caseId, String suspectId) {
        final Map<String,String> params = new HashMap<>();
        params.put("caseId",caseId);
        params.put("suspectId",suspectId);
        var response = template.postForEntity(CASE_CLOSED_URL,null, CaseResult.class,params);
        return response.getBody();
    }
}
