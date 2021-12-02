package be.springtheory.htf.service;

import be.springtheory.htf.model.CaseResult;
import be.springtheory.htf.model.Suspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SuspectService {
    private List<Suspect> possibleSuspects;
    private final RestService restService;

    @Autowired
    public SuspectService(RestService restService) {
        this.restService = restService;
    }

    public List<Suspect> getAllSuspects() {
        if (possibleSuspects == null) {
            createSuspects();
        }
        return possibleSuspects;
    }


    private void createSuspects() {
        possibleSuspects = restService.getSuspects();
    }

    public List<Suspect> findPossibleSuspects(Set<String> clues){
        //See if any suspects contain our clues
        return getAllSuspects().stream().filter(suspect -> {
            for (String clue : clues) {
                //If one of our clues is not in the suspect, filter it out
                if(!suspect.getClues().contains(clue)){
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    public void addCluesToMurder(CaseResult result, Set<String> cluesSet) {
        log.info("Adding clues to murderer");
        var murdererOptional = possibleSuspects.stream().filter(suspect -> suspect.getId().equals(result.getMurderer().getId())).findFirst();
        if(murdererOptional.isPresent()){
            final Suspect murderer = murdererOptional.get();
            cluesSet.forEach(s ->{
                //Add each new clue to the murderer
                if(!murderer.getClues().contains(s)){
                    log.info("New clue: {}",s);
                    murderer.getClues().add(s);
                }
            });
        }else {
            log.error("Could not find murderer! ");
        }

    }
}
