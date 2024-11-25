package eu.tinky.cfaction.models;

import java.util.Objects;

public class Faction {
    private int id;
    private String tag;
    private String description;
    private int leaderId;
    private double dtr;
    private float homeX;
    private float homeY;
    private float homeZ;
    private int claimXMin;
    private int claimXMax;
    private int claimYMin;
    private int claimYMax;
    private int claimZMin;
    private int claimZMax;

    public Faction(int id, String tag, String description, int leaderId, double dtr,
                   float homeX, float homeY, float homeZ,
                   int claimXMin, int claimXMax, int claimYMin, int claimYMax, int claimZMin, int claimZMax) {
        this.id = id;
        this.tag = tag;
        this.description = description;
        this.leaderId = leaderId;
        this.dtr = dtr;
        this.homeX = homeX;
        this.homeY = homeY;
        this.homeZ = homeZ;
        this.claimXMin = claimXMin;
        this.claimXMax = claimXMax;
        this.claimYMin = claimYMin;
        this.claimYMax = claimYMax;
        this.claimZMin = claimZMin;
        this.claimZMax = claimZMax;
    }

    public Faction(String tag, String description, int leaderId, double dtr) {
        this.tag = tag;
        this.description = description;
        this.leaderId = leaderId;
        this.dtr = dtr;
    }

    public Faction(String tag, String description, int leaderId) {
        this(tag, description, leaderId, 1.00);
    }

    public Faction(String tag, int leaderId) {
        this(tag, null, leaderId, 1.00);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public double getDtr() {
        return dtr;
    }

    public void setDtr(double dtr) {
        this.dtr = dtr;
    }

    public float getHomeX() {
        return homeX;
    }

    public void setHomeX(float homeX) {
        this.homeX = homeX;
    }

    public float getHomeY() {
        return homeY;
    }

    public void setHomeY(float homeY) {
        this.homeY = homeY;
    }

    public float getHomeZ() {
        return homeZ;
    }

    public void setHomeZ(float homeZ) {
        this.homeZ = homeZ;
    }

    public int getClaimXMin() {
        return claimXMin;
    }

    public void setClaimXMin(int claimXMin) {
        this.claimXMin = claimXMin;
    }

    public int getClaimXMax() {
        return claimXMax;
    }

    public void setClaimXMax(int claimXMax) {
        this.claimXMax = claimXMax;
    }

    public int getClaimYMin() {
        return claimYMin;
    }

    public void setClaimYMin(int claimYMin) {
        this.claimYMin = claimYMin;
    }

    public int getClaimYMax() {
        return claimYMax;
    }

    public void setClaimYMax(int claimYMax) {
        this.claimYMax = claimYMax;
    }

    public int getClaimZMin() {
        return claimZMin;
    }

    public void setClaimZMin(int claimZMin) {
        this.claimZMin = claimZMin;
    }

    public int getClaimZMax() {
        return claimZMax;
    }

    public void setClaimZMax(int claimZMax) {
        this.claimZMax = claimZMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                ", leaderId=" + leaderId +
                ", dtr=" + dtr +
                ", homeX=" + homeX +
                ", homeY=" + homeY +
                ", homeZ=" + homeZ +
                ", claimXMin=" + claimXMin +
                ", claimXMax=" + claimXMax +
                ", claimYMin=" + claimYMin +
                ", claimYMax=" + claimYMax +
                ", claimZMin=" + claimZMin +
                ", claimZMax=" + claimZMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faction faction = (Faction) o;
        return id == faction.id && leaderId == faction.leaderId && Double.compare(dtr, faction.dtr) == 0 && Float.compare(homeX, faction.homeX) == 0 && Float.compare(homeY, faction.homeY) == 0 && Float.compare(homeZ, faction.homeZ) == 0 && claimXMin == faction.claimXMin && claimXMax == faction.claimXMax && claimYMin == faction.claimYMin && claimYMax == faction.claimYMax && claimZMin == faction.claimZMin && claimZMax == faction.claimZMax && Objects.equals(tag, faction.tag) && Objects.equals(description, faction.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, description, leaderId, dtr, homeX, homeY, homeZ, claimXMin, claimXMax, claimYMin, claimYMax, claimZMin, claimZMax);
    }
}
