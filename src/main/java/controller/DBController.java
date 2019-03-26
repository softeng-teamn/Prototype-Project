package controller;

import java.sql.*;
import model.Node;
import java.util.ArrayList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class DBController {

    static DBController myDBC;
    private Connection connection;
    private String name;

    private DBController(Connection connection, String name){
        this.connection = connection;
        initializeTables();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void initializeTables(){
        Statement statement;
        try {
            statement = connection.createStatement();
            try{
                if (!this.tableExists("NODE")) {

                    statement.execute("CREATE TABLE NODE (nodeID varchar(10) PRIMARY KEY, xcoord int, ycoord int, floor varchar(2), building varchar(10), nodeType varchar(4), longName varchar(75), shortName varchar(50))");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init(String name){
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            Connection connection = DriverManager.getConnection("jdbc:derby:" + name + ";create=true");
            myDBC = new DBController(connection, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void init(){
            init("prototype-db-test");
    }

    public static void close(){
        try {
            myDBC.connection.close();
            myDBC = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertNode(Node node) {
        String nodeID = node.getNodeID();
        String floor = node.getFloor();
        String building = node.getBuilding();
        String nodeType = node.getNodeType();
        String longName = node.getLongName();
        String shortName = node.getShortName();
        int xcoord = node.getXcoord();
        int ycoord = node.getYcoord();
        String insertStatement = "INSERT INTO NODE VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(insertStatement);
            stmt.setString(1, nodeID);
            stmt.setInt(2, xcoord);
            stmt.setInt(3, ycoord);
            stmt.setString(4, floor);
            stmt.setString(5, building);
            stmt.setString(6, nodeType);
            stmt.setString(7, longName);
            stmt.setString(8, shortName);
            try{
                stmt.executeUpdate();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }

            stmt.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }

        return true;
    }

    public boolean deleteNode(Node node) {
        PreparedStatement stmt = null;
        String nodeID = node.getNodeID();
        String query = "DELETE FROM NODE WHERE (nodeID = ?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1,nodeID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return true;
    }

    public boolean updateNode(Node node) {
        String nodeID = node.getNodeID();
        String floor = node.getFloor();
        String building = node.getBuilding();
        String nodeType = node.getNodeType();
        String longName = node.getLongName();
        String shortName = node.getShortName();
        int xcoord = node.getXcoord();
        int ycoord = node.getYcoord();
        String insertStatement = "UPDATE NODE SET xcoord=?, ycoord=?, floor=?, building=?, nodeType=?, longName=?, shortName=? WHERE (nodeID = ?)";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(insertStatement);
            stmt.setInt(1, xcoord);
            stmt.setInt(2, ycoord);
            stmt.setString(3, floor);
            stmt.setString(4, building);
            stmt.setString(5, nodeType);
            stmt.setString(6, longName);
            stmt.setString(7, shortName);
            stmt.setString(8, nodeID);
            try{
                stmt.executeUpdate();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }

            stmt.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }

        return true;
    }

    public ArrayList<Node> getNodes(int num, int offset) {
        return null;
    }

    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> allNodes = new ArrayList<Node>();
        String query = "Select * FROM NODE";
        Statement stmt = null;
        ResultSet nodes = null;
        try{
            stmt = connection.createStatement();

            // execute the query
            nodes = stmt.executeQuery(query);
            while(nodes.next()){
                // extract results from each row of the database.
                String newNodeID = nodes.getString("nodeID");
                int newxcoord = nodes.getInt("xcoord");
                int newycoord = nodes.getInt("ycoord");
                String newFloor = nodes.getString("floor");
                String newBuilding = nodes.getString("building");
                String newNodeType = nodes.getString("nodeType");
                String newLongName = nodes.getString("longName");
                String newShortName = nodes.getString("shortName");
                // construct the new node and return it
                Node newNode = new Node(newNodeID, newFloor, newBuilding, newNodeType, newLongName, newShortName, newxcoord, newycoord);
                allNodes.add(newNode);
            }
            stmt.close();
            nodes.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeAll(stmt, nodes);
        }

        return allNodes;
    }

    public Node getNode(String nodeID){
        // assemble the query
        Node newNode;
        PreparedStatement stmt = null;
        ResultSet oneNode = null;
        String query = "Select * FROM NODE WHERE (nodeID = ?)";
        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nodeID);

            // execute the query
            oneNode = stmt.executeQuery();

            // extract results, only one record should be found.
            boolean hasNext = oneNode.next();

            // If there is no next node, return null
            if (!hasNext) {
                return null;
            }

            String newNodeID = oneNode.getString("nodeID");
            int newxcoord = oneNode.getInt("xcoord");
            int newycoord = oneNode.getInt("ycoord");
            String newFloor = oneNode.getString("floor");
            String newBuilding = oneNode.getString("building");
            String newNodeType = oneNode.getString("nodeType");
            String newLongName = oneNode.getString("longName");
            String newShortName = oneNode.getString("shortName");
            // construct the new node and return it
            newNode = new Node(newNodeID, newFloor, newBuilding, newNodeType, newLongName, newShortName, newxcoord, newycoord);
            stmt.close();
            oneNode.close();
            return newNode;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            closeAll(stmt, oneNode);
        }

        return null;
    }

    private void closeAll(Statement stmt, ResultSet oneNode) {
        if(oneNode != null){
            try {
                oneNode.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean tableExists(String table){
        DatabaseMetaData dbm = null;
        try {
            dbm = connection.getMetaData();
            ResultSet rs = dbm.getTables(null, null, table, null);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dropAll(){
        Statement statement = null;
        try {
            statement = myDBC.connection.createStatement();
            if (tableExists("NODE")) {
                try {
                    statement.execute("DROP TABLE NODE");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}