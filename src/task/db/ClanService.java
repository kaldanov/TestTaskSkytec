package task.db;

import java.util.List;

public interface ClanService {
    Clan get(long clanId);

    List<UserGoldChangeHistory> getHistoryByClanId(long clanId);

    boolean  changeGold(UserGoldChangeHistory userGoldChangeHistory);

    List<Clan> getClans();


}
