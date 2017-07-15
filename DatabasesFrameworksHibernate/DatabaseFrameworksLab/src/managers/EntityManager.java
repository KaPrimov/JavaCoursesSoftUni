package managers;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import interfaces.DBContext;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class EntityManager implements DBContext {

    private Connection connection;
    private Set<Object> persistedEntities;

    public EntityManager(Connection connection) {
        this.connection = connection;
        this.persistedEntities = new HashSet<>();
    }


    @Override
    public <E> boolean persist(E entity) throws IllegalArgumentException, SQLException, IllegalAccessException {
        this.checkPrimaryKey(entity);
        Field primary = this.findPrimaryKey(entity);
        primary.setAccessible(true);
        Object value = primary.get(entity);

        this.doCreate(entity, primary);

        if (value == null || (Long)value <= 0) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    @Override
    public <E> Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException {

        String query = "SELECT * FROM " + this.getTableName(table);
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (this.persistedEntities.size() > 0) {
            this.persistedEntities.clear();
        }
        
        while (resultSet.next()) {
            E entity = table.newInstance();
            entity = this.fillEntity(table, resultSet, entity);
            this.persistedEntities.add(entity);
        }
        
        return (Iterable<E>) Collections.unmodifiableCollection(this.persistedEntities);
    }


    @Override
    public <E> Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table).toLowerCase() + " WHERE 1=1 " +
                (!where.isEmpty() ? "AND " + where : "");

        ResultSet resultSet = statement.executeQuery(query);

        this.persistedEntities.clear();

        while (resultSet.next()) {
            E entity = table.newInstance();
            entity = this.fillEntity(table, resultSet, entity);
            this.persistedEntities.add(entity);
        }

        return (Iterable<E>) Collections.unmodifiableCollection(this.persistedEntities);
    }

    @Override
    public <E> E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table).toLowerCase() + "LIMIT 1";
        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();
        resultSet.next();
        this.fillEntity(table, resultSet, entity);
        return entity;
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " WHERE 1=1 " +
                (!where.isEmpty() ? "AND " + where : "") + " LIMIT 1";
        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();
        resultSet.next();
        this.fillEntity(table, resultSet, entity);
        return entity;
    }

    private <E> boolean doCreate(E entity, Field primary) throws SQLException {

        String query = "CREATE TABLE IF NOT EXISTS " + this.getTableName(entity.getClass()) + "( ";

        List<String> parameters = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String param = this.getColumnName(field) + " " + this.getDbType(field);
            parameters.add(param);
        }

        query += String.join(", ", parameters);
        query += ")";

        return this.connection.createStatement().execute(query);
    }

    private <E> boolean doInsert(E entity, Field primary) throws SQLException, IllegalAccessException {
        String query = "INSERT INTO " + this.getTableName(entity.getClass()) + " (";

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!this.isPrimaryKey(field)) {
                field.setAccessible(true);
                String column = "`" + this.getColumnName(field) + "`";
                Object value = field.get(entity);

                columns.add(column);
                values.add("'" + value.toString() + "'");
            }
        }

        query += String.join(", ", columns) + ") VALUES (" + String.join(", ", values) + " )";
        return connection.prepareStatement(query).execute();
    }


    private <E> boolean doUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {
        String query = "UPDATE " + this.getTableName(entity.getClass()) + " SET ";
        String where = " WHERE " + this.getColumnName(primary) + " = ?";

        List<String> updateRows = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!this.isPrimaryKey(field)) {
                field.setAccessible(true);

                Object fieldValue = field.get(entity);


                String updateRow = this.getColumnName(field) + " = '" + fieldValue + "'";
                updateRows.add(updateRow);
            }
        }

        query += String.join(", ", updateRows);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query + where);
        preparedStatement.setLong(1, (Long) primary.get(entity));
        return preparedStatement.execute();
    }

    private <E> String getTableName(Class<E> entity) {
        String tableName = entity.getSimpleName().toLowerCase();
        if (entity.isAnnotationPresent(Entity.class)) {
            Entity entityAnnotation = entity.getAnnotation(Entity.class);
            tableName = entityAnnotation.name().isEmpty()
                    ? entity.getSimpleName()
                    : entityAnnotation.name();
        }
        return tableName;
    }

    private String getColumnName(Field field) {
        String columnName = field.getName();

        if (field.getClass().isAnnotationPresent(Column.class)) {
            columnName = field.getAnnotation(Column.class).name().isEmpty()
                    ? field.getName()
                    : field.getAnnotation(Column.class).name();
        }

        return columnName;
    }

    private boolean isPrimaryKey(Field field) {

        return field.isAnnotationPresent(Id.class);

    }

    private <E> void checkPrimaryKey(E entity) {

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Primary key is missing for entity: %s",
                                entity.getClass().getName())
                ));
    }

    private <E> Field findPrimaryKey(E entity) {
        Optional<Field> fieldOptional = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst();

        if (fieldOptional.isPresent()) {
            return fieldOptional.get();
        }

        throw new IllegalStateException(
                String.format("Primary key is missing for entity: %s",
                        entity.getClass().getName()));
    }

    private <E> E fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        if (resultSet != null) {
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getName();
                if (field.getType().equals(String.class)) {
                    field.set(entity, resultSet.getString(columnName));
                } else if (field.getType().equals(Integer.TYPE) || field.getType().equals(Integer.class)) {
                    field.set(entity, resultSet.getInt(columnName));
                } else if (field.getType().equals(Long.TYPE) || field.getType().equals(Long.class)) {
                    field.set(entity, resultSet.getLong(columnName));
                } else if (field.getType().equals(Boolean.TYPE) || field.getType().equals(Boolean.class)) {
                    field.set(entity, resultSet.getBoolean(columnName));
                } else if (field.getType().equals(LocalDate.class)) {
                    field.set(entity, resultSet.getDate(columnName).toLocalDate());
                }
            }
        }
        return entity;
    }

    private String getDbType(Field field) {
        field.setAccessible(true);

        if (this.isPrimaryKey(field)) {
            return "BIGINT PRIMARY KEY AUTO_INCREMENT";
        }

        switch (field.getType().getSimpleName().toLowerCase()) {
            case "int":
                return "INT";
            case "string":
                return "VARCHAR(50)";
            case "boolean":
                return "TINYINT(1)";
            case "localdate":
                return "DATE";
            case "long":
                return "LONG";
        }
        return null;
    }

}
