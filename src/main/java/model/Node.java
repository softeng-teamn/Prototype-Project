package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Node {
    private String nodeID, floor, building, nodeType, longName, shortName;
    private int xcoord, ycoord;
    private static final String[] VALID_FLOORS = {"L2", "L1", "G", "1", "2", "3"};
    private static final String[] VALID_NODE_TYPES = {"CONF", "HALL", "DEPT", "INFO", "LABS", "REST", "SERV", "STAI"};

    public Node(String nodeID, String floor, String building, String nodeType, String longName, String shortName, int xcoord, int ycoord) {
        this.nodeID = nodeID;
        this.floor = floor;
        this.building = building;
        this.nodeType = nodeType;
        this.longName = longName;
        this.shortName = shortName;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public static boolean validateID(String nodeID) {
        if (nodeID == null) return false;

        final Pattern pattern = Pattern.compile("^[ABCDEFGH][A-Z]{4}[0-9]{5}$");

        return pattern.matcher(nodeID).matches();
    }

    public static boolean validateCoordinate(int coordinate) {
        return coordinate >= 0;
    }

    public static boolean validateFloor(String floor) {
        return validateFromList(floor, VALID_FLOORS);
    }

    public static boolean validateNodeType(String nodeType) {
        return validateFromList(nodeType, VALID_NODE_TYPES);
    }

    private static boolean validateFromList(String value, String[] validValues) {
        if (value == null) return false;

        for (String validNodeType: validValues) {
            if (value.equals(validNodeType)) return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return xcoord == node.xcoord &&
                ycoord == node.ycoord &&
                Objects.equals(nodeID, node.nodeID) &&
                Objects.equals(floor, node.floor) &&
                Objects.equals(building, node.building) &&
                Objects.equals(nodeType, node.nodeType) &&
                Objects.equals(longName, node.longName) &&
                Objects.equals(shortName, node.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeID, floor, building, nodeType, longName, shortName, xcoord, ycoord);
    }
}
