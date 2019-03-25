package controller;

import java.sql.*;
import model.Node;
import java.util.ArrayList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class DBController {

    // Note: DB
    @SuppressFBWarnings(value="UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD", justification="Triggered because it's unused. Will be used when implemented.")
    public static DBController myDBC;

    private DBController(Connection connection, String name) throws SQLException {
    }

    public static void init(String name) {
    }

    public static void init() {
    }

    public static void close()  {
    }

    public boolean insertNode(Node node) {
        return false;
    }

    public boolean deleteNode(Node node) {
        return false;
    }

    public boolean updateNode(Node node) {
        return false;
    }

    public ArrayList<Node> getNodes(int num, int offset) {
        return null;
    }

    public ArrayList<Node> getAllNodes() {
        return null;
    }

    public Node getNode(String nodeID) {
        return null;
    }
}