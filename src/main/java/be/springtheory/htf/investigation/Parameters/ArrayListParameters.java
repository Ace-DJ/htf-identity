package be.springtheory.htf.investigation.Parameters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class ArrayListParameters {
    private int index;
    private ArrayList<String> arrayList;
    private String replacement;
}
