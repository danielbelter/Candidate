package app.com;

import app.com.model.Candidate;
import app.com.model.Constituency;
import app.com.model.Election;
import app.com.model.Voter;

import java.util.*;


public class App 
{
    public static void main( String[] args )
    {
        Set<Voter> voters = Voter.generator();
        TreeSet<Candidate> candidates = Candidate.generator();
        Map<Constituency, Candidate> winners = Election.elections(candidates, voters);
        System.out.println(winners);



    }
}
