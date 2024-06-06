module LIMS_DB {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.web;
    requires mysql.connector.java;
    requires java.desktop;

    opens menus.login;
    opens menus.dashboard;
    opens menus.employees;
    opens menus.Sample;

    opens menus.main;
    opens entities;
}