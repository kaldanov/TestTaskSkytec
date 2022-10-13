package task.db;


import java.util.Date;

public class UserGoldChangeHistory {    // История изменении золото в клане
    private long clanId;
    private long userId;
    private Date date;
    private String reason;
    private int gold;
    private boolean isInc;


    public UserGoldChangeHistory() {
    }


    public UserGoldChangeHistory(long clanId, long userId, Date date, int gold, String reason, boolean isInc) {
        this.clanId = clanId;
        this.userId = userId;
        this.date = date;
        this.reason = reason;
        this.isInc = isInc;
        this.gold = gold;
    }

    public long getClanId() {
        return clanId;
    }

    public void setClanId(long clanId) {
        this.clanId = clanId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isInc() {
        return isInc;
    }

    public void setInc(boolean inc) {
        isInc = inc;
    }

}
