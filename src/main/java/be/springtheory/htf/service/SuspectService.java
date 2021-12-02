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
        final Suspect billBridge = new Suspect("4e9d1cdc-da56-4dba-91f3-c8bb04715d50",
                "Bill Bridge");
        final Suspect ladyBlabla = new Suspect("3ba3c00a-57a8-4039-bd1a-19f14d22a8c0", "Lady Blabla"
                );
        final Suspect richardSwansong = new Suspect("5fd6326c-715f-4a82-bd63-537266535793", "Richard Swansong"
                );
        final Suspect warrenALaCarte = new Suspect("02ff0450-051c-4cd2-974b-a2b8213ddaa6", "Warren a la Carte"
                );
        final Suspect markZuckervalley = new Suspect("01e185ae-9dee-45bf-be46-e55521535899", "Mark Zuckervalley"
                );
        final Suspect donnaX = new Suspect("58274878-3c70-4be7-be6c-fb6282f7418a", "Donna X"
                );
        final Suspect timChef = new Suspect("c52e0302-49b7-48e2-8c47-e12799213ae2", "Tim Chef"
                );
        final Suspect justinFever = new Suspect("d8d66f88-d3dc-40a9-9962-97eab3d92f38", "Justin Fever");
        final Suspect donaldTrunk = new Suspect("39b7d9b2-87b7-4fad-a7da-11a311ad65ff", "Donald Trunk"
                );
        final Suspect larryCage = new Suspect("d15a00a4-7ba6-4d42-9b1c-2e35e1a3320f", "Larry Cage"
               );
        final Suspect vladimirPutout = new Suspect("1eba8ea5-5a26-409a-a168-73b107c5261a", "Vladimir Putout"
               );
        final Suspect jackDe = new Suspect("3a61642b-b89f-489d-b40f-2d55cd787a73", "Jack De"
                );
        final Suspect kanyeEast = new Suspect("57ec35bb-6cbb-416b-a04b-b2a09025dfff", "Kanye East"
               );
        final Suspect hillaryPlankton = new Suspect("77118087-d57b-4d51-8a46-bded99cfca01", "Hillary Plankton"
               );
        final Suspect theQueen = new Suspect("98d18296-6c65-4d37-bc17-51c4972ff95e", "The Queen")
               ;
        final Suspect jeffSos = new Suspect("a2fac31d-e66a-4df1-b889-4950f1dfcf5e", "Jeff Sos"
               );
        final Suspect prinsLarry = new Suspect("73f63403-dd74-4084-aa84-5da3c77946f5", "Prins Larry"
               );
        final Suspect nolanTusk = new Suspect("17ee9c6d-bb7b-462d-be51-9042b621024f", "Nolan Tusk"
               );
        final Suspect meghanSparkel = new Suspect("a40d3043-1c16-412c-8dc6-f399056664af", "Meghan Sparkel"
               );
        
        

        possibleSuspects = List.of(ladyBlabla, richardSwansong, warrenALaCarte, markZuckervalley, donnaX, timChef, jackDe, justinFever, donaldTrunk, larryCage, vladimirPutout, kanyeEast, hillaryPlankton, theQueen, jeffSos, prinsLarry, nolanTusk, meghanSparkel);

    }

    public List<Suspect> findPossibleSuspects(Set<String> clues){
        List<Suspect> remainingSuspects = List.copyOf(getAllSuspects());
        return remainingSuspects.stream().filter(suspect -> {
            for (String clue : suspect.getClues()) {
                //If one of our clues is not in the suspect
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
            Suspect murderer = murdererOptional.get();
            cluesSet.forEach(s ->
                    murderer.getClues().add(s));
            murdererOptional.get().getClues().addAll(cluesSet);
        }else {
            log.error("There was an error ");
        }
        log.info("New clues:{}",murdererOptional.get().getName());
        murdererOptional.get().getClues().forEach(s -> log.info(s));
    }
}
