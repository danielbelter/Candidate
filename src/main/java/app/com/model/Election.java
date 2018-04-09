package app.com.model;

import java.util.*;


public class Election {
    private static TreeSet<Candidate> candidates
            = new TreeSet<Candidate>(
                    Comparator
                            .comparing(Candidate::getName)
                            .thenComparing(Candidate::getSurname)
                            .thenComparing(Candidate::getConstituency)
    );

    private static Set<Voter> voters = new LinkedHashSet<>();


    public static Map<Constituency, Candidate> elections(TreeSet<Candidate> can, Set<Voter> vot) {
        fillElectionLists(can, vot);

        Map<Constituency, Candidate> wm = generateNewWinnersMap();
        Map<Constituency, Integer> vm = generateVotesMap();

        int roundNumber = 1;

        do
        {
            System.out.println("ROUND NUMBER " + roundNumber);
            nextRoundSimulation(wm, vm);
            fillWinnersMapAfterVoting(wm, vm);

            vm = generateVotesMap();
            candidates.forEach(c -> c.clearVotes());
            ++roundNumber;

        }while (wm.containsValue(null));
        return wm;
    }

    private static void nextRoundSimulation(Map<Constituency, Candidate> wm, Map<Constituency, Integer> vm)
    {
        Scanner sc = new Scanner(System.in);
        int candidateNumber, option;

        for (Voter v : voters)
        {
            if (wm.get(v.getConstituency()) == null) //there is no winner in the constituency area
            {
                System.out.println(v.getId() + " FROM " + v.getConstituency() + " IS VOITING");
                candidateNumber = 1;
                for (Candidate c : v.getCandidates())
                {
                    System.out.println(candidateNumber + ". " + c.getName() + " " + c.getSurname());
                    ++candidateNumber;
                }
                do {
                    System.out.println("Choose your candidate number:");
                    option = sc.nextInt();
                }while (option < 1 || option > v.getCandidates().size());

                vm.put(v.getConstituency(), vm.get(v.getConstituency()) + 1);
                candidateNumber = 1;
                for (Candidate c : v.getCandidates())
                {
                    if (candidateNumber == option)
                    {
                        c.incrementVotes();
                        break;
                    }
                }
            }
        }
    }

    private static void fillWinnersMapAfterVoting(Map<Constituency, Candidate> wm, Map<Constituency, Integer> vm)
    {
        System.out.println(candidates);
        System.out.println(vm);
        for(Candidate c : candidates)
        {
            if (c.getVotes() * 2 > vm.get(c.getConstituency()))
            {
                wm.put(c.getConstituency(), c);
            }
        }

    }

    private static Map<Constituency, Candidate> generateNewWinnersMap()
    {
        Map<Constituency, Candidate> m = new HashMap<>();
        for (Constituency c : Constituency.values())
        {
            m.put(c, null);
        }
        return m;
    }

    public static Map<Constituency, Integer> generateVotesMap()
    {
        Map<Constituency, Integer> m = new HashMap<>();
        for (Constituency c : Constituency.values())
        {
            m.put(c, 0);
        }
        return m;
    }

    private static void fillElectionLists(TreeSet<Candidate> can, Set<Voter> vot)
    {
        candidates = can;
        voters = vot;
        /*
        Map key -> Constituency
            value -> TreeSet of Candidate which belong to Constituency
         */
        Map<Constituency,TreeSet<Candidate>> m = new HashMap<>();

        for (Candidate c : candidates)
        {
            if (m.containsKey(c.getConstituency()))
            {
                m.get(c.getConstituency()).add(c);
            }
            else
            {
                TreeSet<Candidate> tempCand
                        = new TreeSet<Candidate>(
                        Comparator
                                .comparing(Candidate::getName)
                                .thenComparing(Candidate::getSurname)
                                .thenComparing(Candidate::getConstituency)
                );
                tempCand.add(c);
                m.put(c.getConstituency(), tempCand);
            }
        }


        for (Voter v : voters)
        {
            v.setCandidates(m.get(v.getConstituency()));
        }


    }


}
