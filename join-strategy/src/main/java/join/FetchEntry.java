package join;

import lombok.Getter;

@Getter
public class FetchEntry {

    private final String joinName;
    private FetchType fetchType;

    public FetchEntry(String joinName, FetchType fetchType) {
        this.joinName = joinName;
        this.fetchType = fetchType;
    }

    public void setFetchType(FetchType fetchType) {
        this.fetchType = fetchType;
    }
}
