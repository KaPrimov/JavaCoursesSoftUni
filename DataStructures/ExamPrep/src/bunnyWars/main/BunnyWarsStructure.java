package bunnyWars.main;

import java.util.*;

public class BunnyWarsStructure implements IBunnyWarsStructure {

    private NavigableSet<Integer> roomIds;
    private Map<Integer, Map<Integer, Set<Bunny>>> roomsByTeamsByBunnies;
    private Map<String, Bunny> bunnies;
    private SortedMap<String, Bunny> reversedNameBunnies;
    private Map<Integer, SortedMap<String, Bunny>> bunniesByTeam;

    private static final int TEAM_COUNT = 5;

    public BunnyWarsStructure() {
        this.roomsByTeamsByBunnies = new HashMap<>();
        this.bunnies = new HashMap<>();
        this.reversedNameBunnies = new TreeMap<>();
        this.bunniesByTeam = new HashMap<>();
        this.roomIds = new TreeSet<>();
    }

    @Override
    public int getBunnyCount() {
        return bunnies.size();
    }

    @Override
    public int getRoomCount() {
        return this.roomsByTeamsByBunnies.size();
    }

    @Override
    public void addRoom(int roomId) {
        if (this.roomsByTeamsByBunnies.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }
        this.roomsByTeamsByBunnies.put(roomId, new HashMap<>());
        this.roomIds.add(roomId);
    }

    @Override
    public void addBunny(String name, int team, int roomId) {
        if (!this.roomsByTeamsByBunnies.containsKey(roomId) || this.bunnies.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        if (team < 0 || team > 4) {
            throw new IndexOutOfBoundsException();
        }

        Bunny bunny = new Bunny(name, team, roomId);

        this.bunnies.put(name, bunny);

        this.roomsByTeamsByBunnies.get(roomId).putIfAbsent(team, new HashSet<>());
        this.roomsByTeamsByBunnies.get(roomId).get(team).add(bunny);

        this.bunniesByTeam.putIfAbsent(team, new TreeMap<>());
        this.bunniesByTeam.get(team).put(name, bunny);

        StringBuilder sb = new StringBuilder(name);
        this.reversedNameBunnies.put(sb.reverse().toString(), bunny);
    }

    @Override
    public void remove(int roomId) {
        if (!this.roomsByTeamsByBunnies.containsKey(roomId)) {
            throw new IllegalArgumentException();
        }

        Map<Integer, Set<Bunny>> roomsWithBunnies = this.roomsByTeamsByBunnies.get(roomId);
        for (Set<Bunny> bunnySet : roomsWithBunnies.values()) {
            for (Bunny bunny : bunnySet) {
                removeBunny(bunny);
            }
        }
        this.roomsByTeamsByBunnies.remove(roomId);
        this.roomIds.remove(roomId);
    }

    @Override
    public void next(String bunnyName) {
        Bunny bunny = this.bunnies.getOrDefault(bunnyName, null);
        if (bunny == null) {
            throw new IllegalArgumentException();
        }

        int currentRoom = bunny.getRoomId();
        Integer nextRoomId = this.roomIds.ceiling(currentRoom + 1);
        if(nextRoomId == null) {
            nextRoomId = this.roomIds.first();
        }

        bunny.setRoomId(nextRoomId);
        this.roomsByTeamsByBunnies.get(currentRoom).get(bunny.getTeam()).remove(bunny);
        this.roomsByTeamsByBunnies.get(nextRoomId).putIfAbsent(bunny.getTeam(), new HashSet<>());
        this.roomsByTeamsByBunnies.get(nextRoomId).get(bunny.getTeam()).add(bunny);

    }

    @Override
    public void previous(String bunnyName) {
        Bunny bunny = this.bunnies.getOrDefault(bunnyName, null);
        if (bunny == null) {
            throw new IllegalArgumentException();
        }

        int currentRoom = bunny.getRoomId();
        Integer nextRoomId = this.roomIds.floor(currentRoom - 1);
        if(nextRoomId == null) {
            nextRoomId = this.roomIds.last();
        }

        bunny.setRoomId(nextRoomId);
        this.roomsByTeamsByBunnies.get(currentRoom).get(bunny.getTeam()).remove(bunny);
        this.roomsByTeamsByBunnies.get(nextRoomId).putIfAbsent(bunny.getTeam(), new HashSet<>());
        this.roomsByTeamsByBunnies.get(nextRoomId).get(bunny.getTeam()).add(bunny);
    }

    @Override
    public void detonate(String bunnyName) {
        Bunny bunny = this.bunnies.getOrDefault(bunnyName, null);

        if (bunny == null) {
            throw new IllegalArgumentException();
        }

        Map<Integer, Set<Bunny>> room = this.roomsByTeamsByBunnies.get(bunny.getRoomId());

        List<Bunny> removedBunnies = new LinkedList<>();

        for (Integer team : room.keySet()){
            for (Bunny attackedBunny : room.get(team)) {
                if (attackedBunny.getName().equals(bunny.getName()) || attackedBunny.getTeam() == bunny.getTeam()) {
                    break;
                }
                attackedBunny.takeDamage();
                if (attackedBunny.getHealth() <= 0) {
                    this.removeBunny(attackedBunny);
                    bunny.setScore(bunny.getScore() + 1);
                    removedBunnies.add(attackedBunny);
                }
            }
            for (Bunny removedBunny : removedBunnies) {
                room.get(team).remove(removedBunny);
            }
        }
    }

    @Override
    public Iterable<Bunny> listBunniesByTeam(int team) {
        if (!this.bunniesByTeam.containsKey(team)) {
            throw new IllegalArgumentException();
        }
        return this.bunniesByTeam.get(team).values();
    }

    @Override
    public Iterable<Bunny> listBunniesBySuffix(String suffix) {
        if (suffix.equals("")) {
            return this.reversedNameBunnies.values();
        }

        StringBuilder sb = new StringBuilder(suffix).reverse();

        return this.reversedNameBunnies.subMap(sb.toString(), sb.toString() + Character.MAX_VALUE).values();
    }

    private void removeBunny(Bunny bunny) {
        this.bunnies.remove(bunny.getName());

        StringBuilder sb = new StringBuilder(bunny.getName());
        this.reversedNameBunnies.remove(sb.reverse().toString());

        this.bunniesByTeam.get(bunny.getTeam()).remove(bunny.getName());
    }
}
