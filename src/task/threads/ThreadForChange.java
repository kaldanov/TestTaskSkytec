package task.threads;

import task.db.ClanService;
import task.db.ClanServiceImpl;
import task.db.UserGoldChangeHistory;


public class ThreadForChange extends Thread {
    private UserGoldChangeHistory userGoldChangeHistory;
    private ClanService clanService = new ClanServiceImpl();
    public ThreadForChange(UserGoldChangeHistory userGoldChangeHistory) {

        this.userGoldChangeHistory = userGoldChangeHistory;

    }

    @Override
    public void run() {
        clanService.changeGold(userGoldChangeHistory);
    }
}
