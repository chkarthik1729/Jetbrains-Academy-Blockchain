package blockchain;

import java.io.Serializable;
import java.util.Date;
import blockchain.utils.*;

public class Block implements Serializable, Cloneable {

    private final long id;
    private final long timestamp;
    private String prevBlockHash;
    private String hash;
    private int magicNum;
    private long timeTookMs;
    private long timeTookForMiningMs;
    private long minerId;

    private Block(final long id) {
        this.id = id;
        timestamp = new Date().getTime();
    }

    public static Block with(final long id) { return new Block(id); }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(prevBlockHash);
        str.append(id);
        str.append(timestamp);
        str.append(magicNum);
        return str.toString();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Something messed up!");
        }
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }

        if (obj == null) { return false; }

        if (obj.getClass() != getClass()) { return false; }

        if (this.id != ((Block) obj).id) { return false; }

        if (this.timestamp != ((Block) obj).timestamp) { return false; }

        if (minerId != ((Block) obj).minerId) { return false; }

        if (timeTookMs != ((Block) obj).timeTookMs) { return false; }

        if (magicNum != ((Block) obj).magicNum) { return false; }

        if (!this.prevBlockHash.equals(((Block) obj).prevBlockHash)) { return false; }

        if (!this.hash.equals(((Block) obj).hash)) { return false; }

        return true;
    }

    public boolean isConsistent() {
        return hash.equals(StringUtils.applySha256(this.toString()));
    }

    public long getId() { return id; }

    public long getTimestamp() { return timestamp; }

    public String getPrevBlockHash() { return prevBlockHash; }

    public void setPrevBlockHash(String prevBlockHash) { this.prevBlockHash = prevBlockHash; }

    public String getHash() { return hash; }

    public void setHash(String hash) { this.hash = hash; }

    public long getTimeTookMs() { return timeTookMs; }

    public void setTimeTookMs(long timeTook) { this.timeTookMs = timeTookMs; }

    public int getMagicNum() { return magicNum; }

    public void setMagicNum(int magicNum) { this.magicNum = magicNum; }

    public long getTimeTookForMiningMs() { return this.timeTookForMiningMs; }

    public void setTimeTookForMiningMs(long timeTookForMiningMs) {
        this.timeTookForMiningMs = timeTookForMiningMs;
    }

    public long getMinerId() { return minerId; }

    public void setMinerId(long minerId) { this.minerId = minerId; }
}