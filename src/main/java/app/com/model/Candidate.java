package app.com.model;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class Candidate {
    private String name;
    private String surname;
    private int votes;
    private Constituency constituency;

    public Candidate() {
    }

    public Candidate(String name, String surname, Constituency constituency) {
        this.name = name;
        this.surname = surname;
        this.constituency = constituency;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVotes()
    {
        ++votes;
    }

    public void clearVotes()
    {
        votes = 0;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public void setConstituency(Constituency constituency) {
        this.constituency = constituency;
    }

    public static TreeSet<Candidate> generator()
    {
        TreeSet<Candidate> candidates = new TreeSet<Candidate>(
                Comparator
                        .comparing(Candidate::getName)
                        .thenComparing(Candidate::getSurname)
                        .thenComparing(Candidate::getConstituency)
        );

        candidates.addAll(
                Arrays.asList(
                        new Candidate("K1", "NK1", Constituency.WARSZAWA),
                        new Candidate("K2", "NK2", Constituency.WARSZAWA),
                        new Candidate("K3", "NK3", Constituency.KRAKOW),
                        new Candidate("K4", "NK4", Constituency.KRAKOW),
                        new Candidate("K5", "NK5", Constituency.GDANSK),
                        new Candidate("K6", "NK6", Constituency.GDANSK),
                        new Candidate("K7", "NK7", Constituency.WROCLAW),
                        new Candidate("K8", "NK8", Constituency.WROCLAW)
                )
        );

        return candidates;
    }

    @Override
    public String toString() {
        return "C: " + name + " " + surname + " " + constituency + " " + constituency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return votes == candidate.votes &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(surname, candidate.surname) &&
                constituency == candidate.constituency;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname, votes, constituency);
    }
}
