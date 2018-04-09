package app.com.model;


import java.util.*;

public class Voter {
    private int id;
    private Constituency constituency;
    private TreeSet<Candidate> candidates
            = new TreeSet<Candidate>(
                    Comparator
                            .comparing(Candidate::getName)
                            .thenComparing(Candidate::getSurname)
                            .thenComparing(Candidate::getConstituency)
    );

    private static int sid = 1;

    public Voter(Constituency constituency) {
        this.constituency = constituency;
        id = sid++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public void setConstituency(Constituency constituency) {
        this.constituency = constituency;
    }

    public void setCandidates(TreeSet<Candidate> candidates) {
        this.candidates = candidates;
    }

    public TreeSet<Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return id == voter.id &&
                constituency == voter.constituency &&
                Objects.equals(candidates, voter.candidates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, constituency, candidates);
    }

    @Override
    public String toString() {
        return "V: " + id + " " + constituency;
    }

    public static Set<Voter> generator()
    {
        Set<Voter> voters = new LinkedHashSet<>();
        voters.addAll(
                Arrays.asList(
                        new Voter(Constituency.WARSZAWA),
                        new Voter(Constituency.WARSZAWA),
                        new Voter(Constituency.WARSZAWA),
                        new Voter(Constituency.WARSZAWA),
                        new Voter(Constituency.KRAKOW),
                        new Voter(Constituency.KRAKOW),
                        new Voter(Constituency.KRAKOW),
                        new Voter(Constituency.KRAKOW),
                        new Voter(Constituency.WROCLAW),
                        new Voter(Constituency.WROCLAW),
                        new Voter(Constituency.WROCLAW),
                        new Voter(Constituency.WROCLAW),
                        new Voter(Constituency.GDANSK),
                        new Voter(Constituency.GDANSK),
                        new Voter(Constituency.GDANSK),
                        new Voter(Constituency.GDANSK)
                )
        );
        return voters;
    }
}
