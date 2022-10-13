package task.db;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClanServiceImpl implements ClanService{

    private final ConcurrentMap<Long, Lock> clanLockMap = new ConcurrentHashMap<>();

    @Override
    public Clan get(long clanId) {
        return null;
    }

    @Override
    public List<UserGoldChangeHistory> getHistoryByClanId(long clanId) {
        return DBManager.getHistoryByClanId(clanId);
    }

    @Override
    public boolean changeGold(UserGoldChangeHistory userGoldChangeHistory) {
        boolean success;
        lockClan(userGoldChangeHistory.getClanId());
        success = DBManager.changeGold(userGoldChangeHistory);
        unlockClan(userGoldChangeHistory.getClanId());
        return success;
    }

    @Override
    public List<Clan> getClans() {
        return DBManager.getClans();
    }

    public void unlockClan(long clanId) {
        Lock lock = clanLockMap.get(clanId);
        if (lock != null) {
            lock.unlock();
        }
    }

    public void lockClan(long clanId) {
        Lock lock = clanLockMap.computeIfAbsent(clanId, k -> new ReentrantLock());
        lock.lock();
    }

}
