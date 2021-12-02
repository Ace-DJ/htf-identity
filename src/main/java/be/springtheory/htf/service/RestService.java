package be.springtheory.htf.service;

import be.springtheory.htf.model.Case;
import be.springtheory.htf.model.Suspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RestService {
    private static final String BASE_URL="https://htf.bewire.org";
    private static final String SUSPECTS_URL=BASE_URL+"/suspects";
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
        var response = template.getForEntity(SUSPECTS_URL,Case.class);
        return null;
    }
}
