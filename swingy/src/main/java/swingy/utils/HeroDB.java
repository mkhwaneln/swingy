package swingy.utils;

import swingy.model.Hero;
import swingy.model.SetHero;
import swingy.model.artefacts.Armor;
import swingy.model.artefacts.Helm;
import swingy.model.artefacts.Weapon;

import java.sql.*;
import java.util.ArrayList;

public class HeroDB {
    private static final String DATABASE_URL = "jdbc:sqlite:SQLite/db_heroes.db";
    private static Connection connection;

    public static void connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        connection = conn;
    }

    public static void createHeroTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS herolist (heroId integer PRIMARY KEY, heroName varchar(255), heroClass varchar(255), heroLevel integer , exp integer , attack integer , defence integer , hitPoints integer , weaponName varchar(50), weaponValue integer, helmName varchar (50), helmValue integer , armorName varchar (50), armorValue integer )";

        try
        {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection getConnection() {
        if (connection == null)
            connect();
        return connection;
    }

    public static int insert(String heroName, String heroClass, int level, int exp, int attack, int defence, int hp) {
        String query = "INSERT INTO herolist(heroName, heroClass, heroLevel, exp, attack, defence, hitPoints) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int id = 0;

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, heroName);
            stmt.setString(2, heroClass);
            stmt.setInt(3, level);
            stmt.setInt(4, exp);
            stmt.setInt(5, attack);
            stmt.setInt(6, defence);
            stmt.setInt(7, hp);
            int result = stmt.executeUpdate();

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT heroID FROM herolist");
            if (rs.next())
                id = rs.getInt("heroID");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        return id;
    }

    public static ArrayList<String> selectAll() {
        String query = "SELECT * FROM herolist";
        ArrayList<String> herolist = new ArrayList<>();

        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            for (int i = 1; rs.next(); i++) {
                herolist.add(String.format("%d. %s => %s |\t %d", i, rs.getString("heroName"), rs.getString("heroClass"), rs.getInt("heroLevel")));
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return herolist;
    }

    public static Hero selectHero(int id) {
        String query = "SELECT * FROM herolist WHERE heroID = ?";
        Hero hero = null;

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SetHero buildHero = new SetHero();
                buildHero.setHeroID(rs.getInt("heroID"));
                buildHero.setHeroName(rs.getString("heroName"));
                buildHero.setHeroClass(rs.getString("heroClass"));
                buildHero.setLevel(rs.getInt("heroLevel"));
                buildHero.setExp(rs.getInt("exp"));
                buildHero.setAttack(rs.getInt("attack"));
                buildHero.setDefence(rs.getInt("defence"));
                buildHero.setHp(rs.getInt("hitPoints"));

                if (rs.getString("weaponName") != null) {
                    buildHero.setWeapon(new Weapon(rs.getString("weaponName"), rs.getInt("weaponValue")));
                }
                if (rs.getString("helmName") != null) {
                    buildHero.setHelm(new Helm(rs.getString("helmName"), rs.getInt("helmValue")));
                }
                if (rs.getString("armorName") != null) {
                    buildHero.setArmor(new Armor(rs.getString("armorName"), rs.getInt("armorValue")));
                }

                hero = buildHero.getHero();
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return hero;
    }

    public static void updateHeroInfo(Hero hero) {
        String query = "UPDATE herolist SET heroLevel = ?, exp = ?, attack = ?, defence = ?, hitPoints = ?, weaponName = ?, weaponValue = ?, helmName = ?, helmValue = ?, armorName = ?, armorValue = ? WHERE heroID = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, hero.getLevel());
            stmt.setInt(2, hero.getExp());
            stmt.setInt(3, hero.getAttack());
            stmt.setInt(4, hero.getDefence());
            stmt.setInt(5, hero.getHitPoints());

            if (hero.getWeapon() != null) {
                stmt.setString(6, hero.getWeapon().getName());
                stmt.setInt(7, hero.getWeapon().getPoints());
            }
            if (hero.getHelm() != null) {
                stmt.setString(8, hero.getHelm().getName());
                stmt.setInt(9, hero.getHelm().getPoints());
            }
            if (hero.getArmor() != null) {
                stmt.setString(10, hero.getArmor().getName());
                stmt.setInt(11, hero.getArmor().getPoints());
            }

            stmt.setInt(12, hero.getHeroID());
            stmt.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
